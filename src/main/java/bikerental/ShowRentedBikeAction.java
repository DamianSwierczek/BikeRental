package bikerental;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.stream.Collectors;

public class ShowRentedBikeAction implements Action {
    InputProvider inputProvider;
    BikeRent bikeRent;

    public ShowRentedBikeAction(InputProvider inputProvider, BikeRent bikeRent) {
        this.inputProvider = inputProvider;
        this.bikeRent = bikeRent;
    }

    @Override
    public void performAction() {
        FindIterable<Document> cursor = bikeRent.collection.find(new BasicDBObject("status", "Rented"));
        MongoCursor<Document> iterator = cursor.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            System.out.println("Choose action: \n1 - End your reservation \nAny other - Go back to main menu");
            if (inputProvider.takeStringInput().equals("1")) {
                System.out.println("Choose number of bike which reservation you want to end");
                try {
                    bikeRent.endReservationOnBike(inputProvider.takeIntInput());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Wrong bike number");
                }
            }
        }
    }
}
