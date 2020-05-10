package bikerental;

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
        System.out.println(bikeRent.bikeTypeList.stream()
                .filter(bike -> bike.getStatus().equals("Rented"))
                .collect(Collectors.toList()));
        System.out.println("Choose action: \n1 - End your reservation \nAny other - Go back to main menu");
        if(inputProvider.takeStringInput().equals("1")) {
            System.out.println("Choose number of bike which reservation you want to end");
            try {
                bikeRent.endReservationOnBike(inputProvider.takeIntInput());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong bike number");
            }
        }
    }
}
