package lab3.model;

public enum TripPack {
    FIVE(5),
    TEN(10);

    private final int trips;

    TripPack(int trips) {
        this.trips = trips;
    }

    public int getTrips() {
        return trips;
    }
}
