package lab4.vehicle;

import lab4.person.Policeman;

public class PoliceCar extends Car<Policeman> {
    public static final int DEFAULT_SEATS = 4;

    public PoliceCar() {
        this(DEFAULT_SEATS);
    }

    public PoliceCar(int maxSeats) {
        super(maxSeats);
    }
}
