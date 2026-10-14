package lab3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CardRegistry registry = new CardRegistry();
        Tourniquet tourniquet = new Tourniquet(registry);

        registry.issuePeriodCard(Category.STUDENT, PeriodType.MONTH, LocalDate.now());
        registry.issueTripCard(Category.PUPIL, TripPack.FIVE);
        registry.issueAccumulativeCard(new BigDecimal("30.00"));

        try (Scanner in = new Scanner(System.in)) {
            new ConsoleMenu(registry, tourniquet, in).run();
        }
    }
}