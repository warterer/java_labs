package lab4.vehicle;

import lab4.person.Human;

public abstract class Car<T extends Human> extends Vehicle<T> {
    protected Car(int maxSeats) {
        super(maxSeats);
    }
}
