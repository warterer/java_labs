package lab3;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CardRegistry registry = new CardRegistry();

        registry.issuePeriodCard(Category.STUDENT, PeriodType.MONTH, LocalDate.of(2026, 10, 1));
        Card trip = registry.issueTripCard(Category.PUPIL, TripPack.TEN);
        AccumulativeCard acc = registry.issueAccumulativeCard(new BigDecimal("50"));

        System.out.println("Видано карток: " + registry.size());
        registry.getAll().forEach(card -> System.out.println("  " + card));

        System.out.println("Пошук P-0001: " + registry.find("P-0001").map(Card::toString).orElse("не знайдено"));
        System.out.println("Пошук X-9999: " + registry.find("X-9999").map(Card::toString).orElse("не знайдено"));
        System.out.println("Картка " + trip.getId() + " у реєстрі: " + registry.isRegistered(trip));
        System.out.println("Підроблена картка T-0002 у реєстрі: "
                + registry.isRegistered(new TripCard("T-0002", Category.PUPIL, TripPack.TEN)));
        System.out.println("Баланс " + acc.getId() + ": " + acc.getBalance());
    }
}