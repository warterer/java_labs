package lab4.road;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lab4.person.Human;
import lab4.vehicle.Vehicle;

public class Road {
    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<? extends Human> vehicle : carsInRoad) {
            count += vehicle.getOccupiedSeats();
        }
        return count;
    }

    public void addCarToRoad(Vehicle<? extends Human> car) {
        Objects.requireNonNull(car, "car");
        if (carsInRoad.contains(car)) {
            throw new IllegalArgumentException("Цей транспортний засіб уже на дорозі: " + car);
        }
        carsInRoad.add(car);
    }
}
