package lab4.vehicle;

import lab4.person.Human;

public class Taxi extends Car<Human> {
    public static final int DEFAULT_SEATS = 4;

    public Taxi() {
        this(DEFAULT_SEATS);
    }

    public Taxi(int maxSeats) {
        super(maxSeats);
    }
}
