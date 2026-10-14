package lab3;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PeriodCard period = new PeriodCard("P-001", Category.STUDENT, PeriodType.TEN_DAYS, LocalDate.of(2026, 10, 1));
        System.out.println(period + " діє до " + period.getValidUntil());
        System.out.println("10 жовтня: " + period.tryPass(LocalDate.of(2026, 10, 10)).getMessage());
        System.out.println("11 жовтня: " + period.tryPass(LocalDate.of(2026, 10, 11)).getMessage());

        TripCard trips = new TripCard("T-001", Category.PUPIL, TripPack.FIVE);
        System.out.println(trips);
        for (int i = 1; i <= 6; i++) {
            AccessResult r = trips.tryPass(LocalDate.now());
            System.out.println("Поїздка " + i + ": " + r.getMessage() + " (залишилось " + trips.getRemainingTrips() + ")");
        }

        AccumulativeCard acc = new AccumulativeCard("A-001", Category.REGULAR);
        acc.topUp(new BigDecimal("20.00"));
        for (int i = 1; i <= 3; i++) {
            AccessResult r = acc.tryPass(LocalDate.now());
            System.out.println("Накопичувальна, проїзд " + i + ": " + r.getMessage() + ", баланс " + acc.getBalance());
        }

        try {
            new AccumulativeCard("A-002", Category.STUDENT);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}