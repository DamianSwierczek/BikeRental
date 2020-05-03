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
        inputProvider.takeStringInput();
    }
}
