package bikerental;

public class ActionFactory {

    private final String RENT_ACTION = "1";
    private final String SHOW_RENTED = "2";
    private final String WALLET = "3";
    private InputProvider inputProvider;
    private BikeRent bikeRent;

    public ActionFactory(InputProvider inputProvider, BikeRent bikeRent) {
        this.inputProvider = inputProvider;
        this.bikeRent = bikeRent;
    }

    public Action getAction(String input) {
        switch (input) {
            case RENT_ACTION:
                //TODO
        }
    }
}
