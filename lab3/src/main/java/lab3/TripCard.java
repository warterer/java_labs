package lab3;

import java.time.LocalDate;
import java.util.Objects;

public class TripCard extends Card {

    private final TripPack pack;
    private int remainingTrips;

    public TripCard(String id, Category category, TripPack pack) {
        super(id, category);
        this.pack = Objects.requireNonNull(pack, "pack");
        this.remainingTrips = pack.getTrips();
    }

    public int getRemainingTrips() {
        return remainingTrips;
    }

    @Override
    public String getTypeName() {
        return getCategory().getTitle() + ", " + pack.getTrips() + " поїздок";
    }

    @Override
    public AccessResult tryPass(LocalDate today) {
        if (remainingTrips <= 0) {
            return AccessResult.DENIED_NO_TRIPS;
        }
        remainingTrips--;
        return AccessResult.ALLOWED;
    }
}
