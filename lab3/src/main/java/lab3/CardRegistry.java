package lab3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class CardRegistry {
    private final Map<String, Card> cards = new LinkedHashMap<>();
    private int counter = 0;

    public PeriodCard issuePeriodCard(Category category, PeriodType periodType, LocalDate issueDate) {
        PeriodCard card = new PeriodCard(nextId("P"), category, periodType, issueDate);
        register(card);
        return card;
    }

    public TripCard issueTripCard(Category category, TripPack pack) {
        TripCard card = new TripCard(nextId("T"), category, pack);
        register(card);
        return card;
    }

    public AccumulativeCard issueAccumulativeCard(BigDecimal initialAmount) {
        AccumulativeCard card = new AccumulativeCard(nextId("A"), Category.REGULAR);
        if (initialAmount != null && initialAmount.signum() > 0) {
            card.topUp(initialAmount);
        }
        register(card);
        return card;
    }

    public Optional<Card> find(String id) {
        return Optional.ofNullable(cards.get(id));
    }

    public boolean isRegistered(Card card) {
        return card != null && cards.get(card.getId()) == card;
    }

    public Collection<Card> getAll() {
        return Collections.unmodifiableCollection(cards.values());
    }

    public int size() {
        return cards.size();
    }

    private String nextId(String prefix) {
        counter++;
        return String.format("%s-%04d", prefix, counter);
    }

    private void register(Card card) {
        cards.put(card.getId(), card);
    }
}
