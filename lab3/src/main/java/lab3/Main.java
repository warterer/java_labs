package lab3;

import lab3.model.Category;
import lab3.model.PeriodType;
import lab3.model.TripPack;
import lab3.service.CardRegistry;
import lab3.service.Turnstile;
import lab3.ui.ConsoleMenu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CardRegistry registry = new CardRegistry();
        Turnstile turnstile = new Turnstile(registry);

        registry.issuePeriodCard(Category.STUDENT, PeriodType.MONTH, LocalDate.now());
        registry.issueTripCard(Category.PUPIL, TripPack.FIVE);
        registry.issueAccumulativeCard(new BigDecimal("100.00"));

        try (Scanner in = new Scanner(System.in)) {
            new ConsoleMenu(registry, turnstile, in).run();
        }
    }
}