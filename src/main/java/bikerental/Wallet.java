package bikerental;

import java.util.InputMismatchException;

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
            try {
                bikeRent.addMoneyToWallet(inputProvider.takeBigDecimalInput());
            } catch (InputMismatchException e) {
            System.out.println("Wrong input, try again");
            return;
        }
        }
    }
}

