package bikerental;

import java.math.BigDecimal;

public class BikeType {
    private final String brand;
    private final String color;
    private String status;
    private final BigDecimal costPerHour;
    private final long id;

    public long getId() {
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

    public BikeType(String brand, String color, String status, BigDecimal costPerHour, long id) {
        this.brand = brand;
        this.color = color;
        this.status = status;
        this.costPerHour = costPerHour;
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
