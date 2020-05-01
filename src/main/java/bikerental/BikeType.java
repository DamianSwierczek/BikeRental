package bikerental;

import java.math.BigDecimal;

public class BikeType {
    private final String brand;
    private final String color;
    private String status;
    private final BigDecimal costPerHour;

    @Override
    public String toString() {
        return "\nBrand: " + brand + " | " +
                "Color: " + color + " | " +
                "Status: " + status + " | " +
                "Cost per hour: " + costPerHour + "| ";
    }

    public BikeType(String brand, String color, String status, BigDecimal costPerHour) {
        this.brand = brand;
        this.color = color;
        this.status = status;
        this.costPerHour = costPerHour;
    }
}
