package bikerental;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Filters.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class BikeRent {

    private InputProvider inputProvider;
    private ActionFactory actionFactory;
    BigDecimal walletSize = new BigDecimal(0);

    ////////////////////////////////// DATABASE VARIABLES ////////////////////////////
    String uri = "mongodb+srv://Admin:admin@bikes-mdly2.azure.mongodb.net/test\n";
    MongoClientURI clientUri = new MongoClientURI(uri);
    MongoClient mongoClient = new MongoClient(clientUri);
    MongoDatabase database = mongoClient.getDatabase("BikeRent");
    MongoCollection collection = database.getCollection("Bikes");
    MongoCollection rentTimeCollection = database.getCollection("RentTime");
    ////////////////////////////////////////////////////////////////////////////////

    public BikeRent(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
        this.actionFactory = new ActionFactory(inputProvider, this);
    }

    public void printListOfBikes() {
        FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("_id", 1));
        MongoCursor<Document> iterator = cursor.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    public void rentOrSortBike(String input) {
        switch (input) {
            case "1":
                System.out.println("Which bike you want? Choose by number");
                printListOfBikes();
                int bikeOption = inputProvider.takeIntInput();
                checkBikeToRent(bikeOption);
                break;
            case "2":
                sortBikesByBrand();
                break;
            case "3":
                sortBikesByCost();
                break;
            case "4":
                sortBikesByStatus();
                break;
            case "5":
                sortBikesByColor();
                break;
        }
    }

    private void performAction(Action action) {
        action.performAction();
    }

    private void checkBikeToRent(int bikeOption) {
        if (checkIfBikeIsFree(bikeOption)) {
            setBikeStatusToRented(bikeOption);
            startCostPerHour(bikeOption);
            System.out.println("Bike number " + bikeOption + " is rented. Thank you!");
        } else {
            System.out.println("Selected bike is not free or don't exist");
        }
    }

    private void startCostPerHour(int bikeId) {
        Document document = (Document) collection
                .find(new BasicDBObject("_id", bikeId))
                .projection(fields(include("costPerHour"), excludeId())).first();
        int costPerHour = document.getInteger("costPerHour");

        Document rentTime = new Document("rentTime", new Date())
                .append("_id", bikeId)
                .append("costPerHour", costPerHour);
        rentTimeCollection.insertOne(rentTime);
    }

    private boolean checkIfBikeIsFree(int bikeId) {
        try {
            Document document = (Document) collection
                    .find(new BasicDBObject("_id", bikeId))
                    .projection(fields(include("status"), excludeId())).first();
            String status = document.getString("status");
            return status.equals("Free");
        } catch (NullPointerException e) {
            return false;
        }

    }
       /* return bikeTypeList.stream()
                .filter(bike -> bike.getId() == bikeId)
                .anyMatch(bike -> bike.getStatus().equals("Free"));

        */

    private void setBikeStatusToRented(int bikeId) {
        collection.updateOne(eq("_id", bikeId), combine(set("status", "Rented")));
        /*
        bikeTypeList.stream()
                .filter(bike -> bike.getId() == bikeId)
                .peek(bike -> bike.setStatus("Rented"))
                .collect(Collectors.toList());

         */
    }

    public void run() {
        while (true) {
            System.out.println("Pick action: \n1 - Rent a Bike \n2 - Show my rented bikes \n3 - My wallet \n4 - Exit");
            performAction(actionFactory.getAction(inputProvider.takeStringInput()));
        }
    }

    public void sortBikesByBrand() {
        FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("brand", 1));
        MongoCursor<Document> iterator = cursor.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

//        System.out.println(bikeTypeList.stream()
//                .sorted(Comparator.comparing(BikeType::getBrand))
//                .collect(Collectors.toList()));
        }
    }

    public void sortBikesByColor() {

        FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("color", 1));
        MongoCursor<Document> iterator = cursor.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
//        System.out.println(bikeTypeList.stream()
//                .sorted(Comparator.comparing(BikeType::getColor))
//                .collect(Collectors.toList()));
//    }
    }

    public void sortBikesByStatus() {

        FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("status", 1));
        MongoCursor<Document> iterator = cursor.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

//        System.out.println(bikeTypeList.stream()
//                .sorted(Comparator.comparing(BikeType::getStatus))
//                .collect(Collectors.toList()));
        }
    }

    public void sortBikesByCost() {
        FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("costPerHour", 1));
        MongoCursor<Document> iterator = cursor.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
//        System.out.println(bikeTypeList.stream()
//                .sorted(Comparator.comparing(BikeType::getCostPerHour))
//                .collect(Collectors.toList()));
        }
    }

    public void addMoneyToWallet(BigDecimal money) {
        walletSize = walletSize.add(money);
    }

    public void endReservationOnBike(int bikeId) {

        Document document = (Document) collection
                .find(new BasicDBObject("_id", bikeId))
                .projection(fields(include("status"), excludeId())).first();

        String status = document.getString("status");

        if (status.equals("Rented")) {
            collection.updateOne(eq("_id", bikeId), combine(set("status", "Free")));
            System.out.println("Reservation of bike number " + bikeId + " has ended.");
            endReservationPay(bikeId);
        } else {
            System.out.println("There's no rented bike with that number");
        }
    }

    public void endReservationPay(int bikeId) {

        Document document = (Document) collection
                .find(new BasicDBObject("_id", bikeId))
                .projection(fields(include("costPerHour"), excludeId())).first();

        Document rentTime = (Document) rentTimeCollection
                .find(new BasicDBObject("_id", bikeId))
                .projection(fields(include("rentTime"), excludeId())).first();

        Date finishRent = rentTime.getDate("rentTime");
        int costPerHour = document.getInteger("costPerHour");

        long minutes = ChronoUnit.MINUTES.between(finishRent.toInstant(), Instant.now());
        BigDecimal actualCost = new BigDecimal(costPerHour * minutes + costPerHour);
        System.out.println("You've paid " + actualCost + "$. Thank you!");
        walletSize = walletSize.subtract(actualCost);
        rentTimeCollection.deleteOne(eq("_id", bikeId));
    }
}
