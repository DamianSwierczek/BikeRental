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
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class BikeRent {

    private InputProvider inputProvider;
    private ActionFactory actionFactory;
    List<BikeType> bikeTypeList = new ArrayList<>();
    BigDecimal walletSize = new BigDecimal(0);
    private List<BikeRentInformation> rentedBikeList = new ArrayList<>();

    ////////////////////////////////// DATABASE VARIABLES ////////////////////////////
    String uri = "mongodb+srv://Admin:admin@bikes-mdly2.azure.mongodb.net/test\n";
    MongoClientURI clientUri = new MongoClientURI(uri);
    MongoClient mongoClient = new MongoClient(clientUri);
    MongoDatabase database = mongoClient.getDatabase("BikeRent");
    MongoCollection collection = database.getCollection("Bikes");
    ////////////////////////////////////////////////////////////////////////////////

    public BikeRent(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
        this.actionFactory = new ActionFactory(inputProvider, this);
//        initBikes();
    }

//    private void initBikes() {
//        bikeTypeList.add(0, new BikeType("Kross", "Red", "Free", new BigDecimal(10), 1));
//        bikeTypeList.add(1, new BikeType("Mark", "Green", "Free", new BigDecimal(13), 2));
//        bikeTypeList.add(2, new BikeType("Croll", "Blue", "Free", new BigDecimal(15), 3));
//        bikeTypeList.add(3, new BikeType("Leisch", "Black", "Free", new BigDecimal(15), 4));
//        bikeTypeList.add(4, new BikeType("Laver", "Red", "Free", new BigDecimal(15), 5));
//        bikeTypeList.add(5, new BikeType("Ferox", "Green", "Free", new BigDecimal(13), 6));
//        bikeTypeList.add(6, new BikeType("Kaks", "Blue", "Free", new BigDecimal(10), 7));
//        bikeTypeList.add(7, new BikeType("Kross", "Black", "Free", new BigDecimal(13), 8));
//        bikeTypeList.add(8, new BikeType("Leisch", "Red", "Free", new BigDecimal(15), 9));
//        bikeTypeList.add(9, new BikeType("Laver", "Black", "Free", new BigDecimal(10), 10));
//    }

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

        LocalTime time = LocalTime.now();
        rentedBikeList.add(new BikeRentInformation(costPerHour, bikeId, time));
    }

    private boolean checkIfBikeIsFree(int bikeId) {

        Document document = (Document) collection
                .find(new BasicDBObject("_id", bikeId))
                .projection(fields(include("status"), excludeId())).first();

        String status = document.getString("status");

        return status.equals("Free");

       /* return bikeTypeList.stream()
                .filter(bike -> bike.getId() == bikeId)
                .anyMatch(bike -> bike.getStatus().equals("Free"));

        */
    }

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
            endReservationPay(bikeId, LocalTime.now());
        } else {
            System.out.println("There's no rented bike with that number");
        }
    }

    public void endReservationPay(int bikeId, LocalTime bikeEndReservation) {

        Document document = (Document) collection
                .find(new BasicDBObject("_id", bikeId))
                .projection(fields(include("costPerHour"), excludeId())).first();

        int costPerHour = document.getInteger("costPerHour");
        long minutes = ChronoUnit.MINUTES.between(rentedBikeList.get(bikeId - 1).getRentTime(), bikeEndReservation);
        BigDecimal actualCost = new BigDecimal(costPerHour * minutes + costPerHour);
        System.out.println("You've paid " + actualCost + "$. Thank you!");
        walletSize = walletSize.subtract(actualCost);
    }
}
