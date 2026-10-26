package lab4.vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lab4.person.Human;

public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final List<T> passengers = new ArrayList<>();

    protected Vehicle(int maxSeats) {
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути додатною: " + maxSeats);
        }
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public List<T> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    public void board(T passenger) {
        Objects.requireNonNull(passenger, "passenger");
        if (passengers.size() >= maxSeats) {
            throw new VehicleFullException(
                    "Немає вільних місць (" + maxSeats + "), не вдалось посадити: " + passenger);
        }
        if (passengers.contains(passenger)) {
            throw new IllegalStateException("Пасажир уже в транспортному засобі: " + passenger);
        }
        passengers.add(passenger);
    }

    public void disembark(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new PassengerNotFoundException("Пасажира немає в транспортному засобі: " + passenger);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + passengers.size() + "/" + maxSeats + ")";
    }
}
