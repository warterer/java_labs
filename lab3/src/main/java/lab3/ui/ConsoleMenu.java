package lab3.ui;

import lab3.model.*;
import lab3.service.CardRegistry;
import lab3.service.PassStats;
import lab3.service.Turnstile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleMenu {
    private final CardRegistry registry;
    private final Turnstile Turnstile;
    private final Scanner in;

    public ConsoleMenu(CardRegistry registry, Turnstile Turnstile, Scanner in) {
        this.registry = registry;
        this.Turnstile = Turnstile;
        this.in = in;
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Ваш вибір", 0, 5);
            switch (choice) {
                case 1 -> issueCard();
                case 2 -> passTourniquet();
                case 3 -> topUpCard();
                case 4 -> showCards();
                case 5 -> showStatistics();
                case 0 -> running = false;
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== Турнікет швидкісного трамваю ===");
        System.out.println("1 - Випустити картку");
        System.out.println("2 - Прикласти картку до турнікета");
        System.out.println("3 - Поповнити накопичувальну картку");
        System.out.println("4 - Показати всі картки");
        System.out.println("5 - Статистика турнікета");
        System.out.println("0 - Вихід");
    }

    private void issueCard() {
        int kind = readInt("Тип картки: 1 - за терміном дії, 2 - за кількістю поїздок, 3 - накопичувальна", 1, 3);
        Card card;
        if (kind == 1) {
            Category category = choose("Категорія:", Category.values(), Category::getTitle);
            PeriodType period = choose("Термін дії:", PeriodType.values(), PeriodType::getTitle);
            card = registry.issuePeriodCard(category, period, LocalDate.now());
        } else if (kind == 2) {
            Category category = choose("Категорія:", Category.values(), Category::getTitle);
            TripPack pack = choose("Кількість поїздок:", TripPack.values(), p -> p.getTrips() + " поїздок");
            card = registry.issueTripCard(category, pack);
        } else {
            BigDecimal amount = readAmount("Початкова сума, грн (0 - без поповнення)", true);
            card = registry.issueAccumulativeCard(amount);
        }
        System.out.println("Видано картку: " + card + " (" + card.getDetails() + ")");
    }

    private void passTourniquet() {
        System.out.print("Введіть ID картки (наприклад P-0001): ");
        String id = in.nextLine().trim();
        Card card = registry.find(id).orElse(null);

        AccessResult result = Turnstile.check(card);
        if (result.isAllowed()) {
            System.out.println("[OK] " + result.getMessage() + " (" + card.getDetails() + ")");
        } else {
            System.out.println("[ВІДМОВА] " + result.getMessage());
        }
    }

    private void topUpCard() {
        System.out.print("Введіть ID накопичувальної картки: ");
        String id = in.nextLine().trim();
        Card card = registry.find(id).orElse(null);

        if (card == null) {
            System.out.println("Картку " + id + " не знайдено.");
        } else if (!(card instanceof AccumulativeCard)) {
            System.out.println("Поповнювати можна тільки накопичувальні картки.");
        } else {
            AccumulativeCard accumulative = (AccumulativeCard) card;
            accumulative.topUp(readAmount("Сума поповнення, грн", false));
            System.out.println("Готово: " + accumulative + " (" + accumulative.getDetails() + ")");
        }
    }

    private void showCards() {
        if (registry.size() == 0) {
            System.out.println("Карток ще не видано.");
            return;
        }
        System.out.println("Видані картки (" + registry.size() + "):");
        for (Card card : registry.getAll()) {
            System.out.println("  " + card + " - " + card.getDetails());
        }
    }

    private void showStatistics() {
        PassStats total = Turnstile.getTotalStats();
        if (total.total() == 0) {
            System.out.println("Проходів ще не було.");
            return;
        }
        System.out.println("Разом: дозволів " + total.allowed() + ", відмов " + total.denied());

        System.out.println("За типами карток:");
        for (Map.Entry<String, PassStats> e : Turnstile.getStatsByType().entrySet()) {
            System.out.println("  " + e.getKey() + ": дозволів " + e.getValue().allowed()
                    + ", відмов " + e.getValue().denied());
        }

        if (!Turnstile.getDenialReasons().isEmpty()) {
            System.out.println("Причини відмов:");
            for (Map.Entry<AccessResult, Integer> e : Turnstile.getDenialReasons().entrySet()) {
                System.out.println("  " + e.getKey().getMessage() + ": " + e.getValue());
            }
        }
    }

    private <T> T choose(String title, T[] options, Function<T, String> label) {
        System.out.println(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + " - " + label.apply(options[i]));
        }
        return options[readInt("Ваш вибір", 1, options.length) - 1];
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " [" + min + "-" + max + "]: ");
            String line = in.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {  }
            System.out.println("Некоректне значення, спробуйте ще раз.");
        }
    }

    private BigDecimal readAmount(String prompt, boolean allowZero) {
        while (true) {
            System.out.print(prompt + ": ");
            String line = in.nextLine().trim().replace(',', '.');
            try {
                BigDecimal value = new BigDecimal(line).setScale(2, RoundingMode.HALF_UP);
                if (value.signum() > 0 || (allowZero && value.signum() == 0)) {
                    return value;
                }
            } catch (NumberFormatException ignored) {  }
            System.out.println("Некоректна сума, спробуйте ще раз.");
        }
    }
}
