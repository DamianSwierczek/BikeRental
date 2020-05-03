package bikerental;

import java.math.BigDecimal;

public class Wallet implements Action {

    private InputProvider inputProvider;
    private BikeRent bikeRent;
    private BigDecimal walletSize;

    public Wallet(InputProvider inputProvider, BikeRent bikeRent) {
        this.inputProvider = inputProvider;
        this.bikeRent = bikeRent;
    }

    @Override
    public void performAction() {

    }
}

