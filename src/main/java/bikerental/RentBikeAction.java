package bikerental;


public class RentBikeAction implements Action {
    private InputProvider inputProvider;
    private BikeRent bikeRent;

    public RentBikeAction(InputProvider inputProvider, BikeRent bikeRent) {
        this.inputProvider = inputProvider;
        this.bikeRent = bikeRent;
    }

    @Override
    public void performAction(){
        System.out.println("What is your desire? \n1 - Rent a bike " +
                "\n2 - Sort by brand " +
                "\n3 - Sort by price " +
                "\n4 - Sort by status " +
                "\n5 - Sort by color" +
                "\nAny other - Go back to main menu");
        bikeRent.rentOrSortBike(inputProvider.takeStringInput());

    }
}
