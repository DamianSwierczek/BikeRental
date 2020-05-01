package bikerental;

public class RentBikeAction {
    private InputProvider inputProvider;
    private BikeRent bikeRent;

    public RentBikeAction(InputProvider inputProvider, BikeRent bikeRent) {
        this.inputProvider = inputProvider;
        this.bikeRent = bikeRent;
    }

    @Override
    public void performAction() {
        bikeRent.printListOfBikes();
        System.out.println("What is your desire? \n1 - Rent a bike \n2 - Sort by brand \n3 - Sort by price \n4 - Sort by status \n5 - Sort by color");
        String input = inputProvider.takeStringInput();
        bikeRent.rentOrSortBike(input);

    }
}
