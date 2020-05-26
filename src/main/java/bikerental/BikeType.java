package bikerental;

import java.math.BigDecimal;

public class BikeType {
    private final String brand;
    private final String color;
    private String status;
    private final int costPerHour;
    private final int id;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nNumber: " + id +
                "\nBrand: " + brand + " | " +
                "Color: " + color + " | " +
                "Status: " + status + " | " +
                "Cost per hour: " + costPerHour + "| ";
    }

    public BikeType(String brand, String color, String status, int costPerHour, int id) {
        this.brand = brand;
        this.color = color;
        this.status = status;
        this.costPerHour = costPerHour;
        this.id = id;
    }

    public int getCostPerHour() {
        return costPerHour;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
