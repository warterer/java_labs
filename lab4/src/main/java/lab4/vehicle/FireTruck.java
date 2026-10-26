package lab4.vehicle;

import lab4.person.Firefighter;

public class FireTruck extends Car<Firefighter> {
    public static final int DEFAULT_SEATS = 6;

    public FireTruck() {
        this(DEFAULT_SEATS);
    }

    public FireTruck(int maxSeats) {
        super(maxSeats);
    }
}
