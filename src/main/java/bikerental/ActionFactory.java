package bikerental;

public class ActionFactory {

    private final String RENT_ACTION = "1";
    private final String SHOW_RENTED = "2";
    private final String WALLET = "3";
    private final String EXIT = "4";
    private InputProvider inputProvider;
    private BikeRent bikeRent;

    public ActionFactory(InputProvider inputProvider, BikeRent bikeRent) {
        this.inputProvider = inputProvider;
        this.bikeRent = bikeRent;
    }

    public Action getAction(String input) {
        switch (input) {
            case RENT_ACTION:
                return new RentBikeAction(inputProvider, bikeRent);

            case SHOW_RENTED:
                return new ShowRentedBikeAction(inputProvider,bikeRent);
            case WALLET:
                return new Wallet(inputProvider,bikeRent);
            case EXIT:
                System.exit(0);
            default:
                return new UnknownAction();
        }
    }
}
