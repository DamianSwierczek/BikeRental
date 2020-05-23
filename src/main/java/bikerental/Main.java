package bikerental;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputProvider inputProvider = new InputProvider(new Scanner(System.in));
        BikeRent bikeRent = new BikeRent(inputProvider);
        bikeRent.run();



    }


}


