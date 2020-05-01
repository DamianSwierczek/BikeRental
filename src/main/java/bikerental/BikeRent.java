package bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BikeRent {

    private InputProvider inputProvider;
    List<BikeType> bikeTypeList = new ArrayList<>();

    public BikeRent(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
        initBikes();
    }


    private void initBikes() {
        bikeTypeList.add(0, new BikeType("Kross", "Red", "Free", new BigDecimal(10)));
        bikeTypeList.add(1, new BikeType("Mark", "Green", "Free", new BigDecimal(13)));
        bikeTypeList.add(2, new BikeType("Croll", "Blue", "Free", new BigDecimal(15)));
        bikeTypeList.add(3, new BikeType("Leisch", "Black", "Free", new BigDecimal(15)));
        bikeTypeList.add(4, new BikeType("Laver", "Red", "Free", new BigDecimal(15)));
        bikeTypeList.add(5, new BikeType("Ferox", "Green", "Free", new BigDecimal(13)));
        bikeTypeList.add(6, new BikeType("Kaks", "Blue", "Free", new BigDecimal(10)));
        bikeTypeList.add(7, new BikeType("Kross", "Black", "Free", new BigDecimal(13)));
        bikeTypeList.add(8, new BikeType("Leisch", "Red", "Free", new BigDecimal(15)));
        bikeTypeList.add(9, new BikeType("Laver", "Black", "Free", new BigDecimal(10)));
    }

    public void printListOfBikes() {
        System.out.println(Arrays.toString(bikeTypeList.toArray()));
    }


    public void rentOrSortBike(String input) {
        switch (input) {
            case "1":
                //METHOD FOR RENT
            case "2":
                // METHOD FOR SORT
            case "3":
                // METHOD FOR SORT
            case "4":
                //METHOD FOR SORT
            case "5":
                //METHOD FOR SORT
        }
    }

    private void performAction(Action action) {
        action.perfomrAction();
    }
}
