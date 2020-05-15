package bikerental;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BikeRentTest {

    @Test

    public void testIfSelectedFirstBikeStatusIsRented() {
        System.out.println("Test 1");
        String selectedBike = "1\r\n";
        String menuOption = "1\r\n";
        String bikeRentOption = "1\r\n";

        System.setIn(new ByteArrayInputStream((menuOption + bikeRentOption + selectedBike).getBytes()));
        InputProvider inputProvider = new InputProvider(new Scanner(System.in));
        BikeRent bikeRent = new BikeRent(inputProvider);

        try {
            bikeRent.run();
        } catch (NoSuchElementException e) {
            String status = bikeRent.bikeTypeList.get(0).getStatus();
            Assert.assertEquals("Rented", status);
        }
    }

}