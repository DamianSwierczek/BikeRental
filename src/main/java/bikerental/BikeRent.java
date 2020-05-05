package bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BikeRent {

    private InputProvider inputProvider;
    private ActionFactory actionFactory;
    List<BikeType> bikeTypeList = new ArrayList<>();
    BigDecimal walletSize = new BigDecimal(0);


    public BikeRent(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
        this.actionFactory = new ActionFactory(inputProvider, this);
        initBikes();
    }


    private void initBikes() {
        bikeTypeList.add(new BikeType("Kross", "Red", "Free", new BigDecimal(10), 1));
        bikeTypeList.add(new BikeType("Mark", "Green", "Free", new BigDecimal(13), 2));
        bikeTypeList.add(new BikeType("Croll", "Blue", "Free", new BigDecimal(15), 3));
        bikeTypeList.add(new BikeType("Leisch", "Black", "Free", new BigDecimal(15), 4));
        bikeTypeList.add(new BikeType("Laver", "Red", "Free", new BigDecimal(15), 5));
        bikeTypeList.add(new BikeType("Ferox", "Green", "Free", new BigDecimal(13), 6));
        bikeTypeList.add(new BikeType("Kaks", "Blue", "Free", new BigDecimal(10), 7));
        bikeTypeList.add(new BikeType("Kross", "Black", "Free", new BigDecimal(13), 8));
        bikeTypeList.add(new BikeType("Leisch", "Red", "Free", new BigDecimal(15), 9));
        bikeTypeList.add(new BikeType("Laver", "Black", "Free", new BigDecimal(10), 10));
    }

    public void printListOfBikes() {
        System.out.println(Arrays.toString(bikeTypeList.toArray()));
    }


    public void rentOrSortBike(String input) {
        switch (input) {
            case "1":
                System.out.println("Which bike you want? Choose by number");
                printListOfBikes();
                long bikeOption = inputProvider.takeLongInput();
                checkBikeToRent(bikeOption);
                break;
            case "2":
                sortBikesByBrand();
                break;
            case "3":
                sortBikesByCost();
                break;
            case "4":
                sortBikesByStatus();
                break;
            case "5":
                sortBikesByColor();
                break;
        }
    }

    private void performAction(Action action) {
        action.performAction();
    }

    private void checkBikeToRent(long bikeOption) {
        checkIfBikeIsFree(bikeOption);
        setBikeStatusToRented(bikeOption);
    }

    private boolean checkIfBikeIsFree(long bikeId) {

        return bikeTypeList.stream()
                .filter(bike -> bike.getId() == bikeId)
                .collect(Collectors.toList()).get(0).getStatus().equals("Free");

    }

    private void setBikeStatusToRented(long bikeId) {
        bikeTypeList.stream()
                .filter(bike -> bike.getId() == bikeId)
                .peek(bike -> bike.setStatus("Rented"))
                .collect(Collectors.toList());
    }

    public void run() {
        while (true) {
            System.out.println("Pick action: \n1 - Rent a Bike \n2 - Show my rented bikes \n3 - My wallet \n4 - Exit");
            performAction(actionFactory.getAction(inputProvider.takeStringInput()));
        }
    }

    public void sortBikesByBrand() {
        System.out.println(bikeTypeList.stream()
                .sorted(Comparator.comparing(BikeType::getBrand))
                .collect(Collectors.toList()));
    }

    public void sortBikesByColor() {
        System.out.println(bikeTypeList.stream()
                .sorted(Comparator.comparing(BikeType::getColor))
                .collect(Collectors.toList()));
    }

    public void sortBikesByStatus() {
        System.out.println(bikeTypeList.stream()
                .sorted(Comparator.comparing(BikeType::getStatus))
                .collect(Collectors.toList()));
    }

    public void sortBikesByCost() {
        System.out.println(bikeTypeList.stream()
                .sorted(Comparator.comparing(BikeType::getCostPerHour))
                .collect(Collectors.toList()));
    }
    public void addMoneyToWallet(BigDecimal money){
        walletSize = walletSize.add(money);
    }
}