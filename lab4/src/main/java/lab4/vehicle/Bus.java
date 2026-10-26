package lab4.vehicle;

import lab4.person.Human;

public class Bus extends Vehicle<Human> {
    public static final int DEFAULT_SEATS = 30;

    public Bus() {
        this(DEFAULT_SEATS);
    }

    public Bus(int maxSeats) {
        super(maxSeats);
    }
}
