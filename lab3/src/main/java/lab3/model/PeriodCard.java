package lab3.model;

import java.time.LocalDate;
import java.util.Objects;

public class PeriodCard extends Card {

    private final PeriodType periodType;
    private final LocalDate issueDate;
    private final LocalDate validUntil;

    public PeriodCard(String id, Category category, PeriodType periodType, LocalDate issueDate) {
        super(id, category);
        this.periodType = Objects.requireNonNull(periodType, "periodType");
        this.issueDate = Objects.requireNonNull(issueDate, "issueDate");
        this.validUntil = periodType.lastValidDay(issueDate);
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    @Override
    public String getTypeName() {
        return getCategory().getTitle() + ", " + periodType.getTitle();
    }

    @Override
    public AccessResult tryPass(LocalDate today) {
        if (today.isBefore(issueDate) || today.isAfter(validUntil)) {
            return AccessResult.DENIED_EXPIRED;
        }
        return AccessResult.ALLOWED;
    }

    @Override
    public String getDetails() {
        return "діє до: " + validUntil;
    }
}
