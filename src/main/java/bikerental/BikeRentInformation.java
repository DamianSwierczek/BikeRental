package bikerental;

import java.math.BigDecimal;
import java.time.LocalTime;

public class BikeRentInformation {

    private final BigDecimal costPerHour;
    private final int id;
    private LocalTime rentTime;

    public BikeRentInformation(BigDecimal costPerHour, int id, LocalTime rentTime) {
        this.costPerHour = costPerHour;
        this.id = id;
        this.rentTime = rentTime;
    }

    @Override
    public String toString() {
        return "BikeRentInformation{" +
                "costPerHour=" + costPerHour +
                ", id=" + id +
                ", rentTime=" + rentTime +
                '}';
    }

    public LocalTime getRentTime() {
        return rentTime;
    }
}
