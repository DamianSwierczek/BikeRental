package bikerental;

public class Wallet implements Action {

    private InputProvider inputProvider;
    private BikeRent bikeRent;


    public Wallet(InputProvider inputProvider, BikeRent bikeRent) {
        this.inputProvider = inputProvider;
        this.bikeRent = bikeRent;
    }

    @Override
    public void performAction() {
        System.out.println("Your cash: " + bikeRent.walletSize + "\n Choose option: \n 1 - Add cash\n Any other - Go back to main menu");
        if (inputProvider.takeStringInput().equals("1")) {
            System.out.println("How much money you want to have?");
            bikeRent.addMoneyToWallet(inputProvider.takeBigDecimalInput());
        }
    }
}

