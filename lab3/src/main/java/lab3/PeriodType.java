package lab3;

import java.time.LocalDate;

public enum PeriodType {
    MONTH("на місяць") {
        @Override
        public LocalDate lastValidDay(LocalDate issueDate) {
            return issueDate.plusMonths(1).minusDays(1);
        }
    },
    TEN_DAYS("на 10 днів") {
        @Override
        public LocalDate lastValidDay(LocalDate issueDate) {
            return issueDate.plusDays(9);
        }
    };

    private final String title;

    PeriodType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract LocalDate lastValidDay(LocalDate issueDate);
}
