package lab3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class AccumulativeCard extends Card {

    public static final BigDecimal FARE = new BigDecimal("30.00");

    private BigDecimal balance = BigDecimal.ZERO;

    public AccumulativeCard(String id, Category category) {
        super(id, category);
        if (category != Category.REGULAR) {
            throw new IllegalArgumentException(
                    "Накопичувальна картка тільки звичайна");
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void topUp(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Сума поповнення має бути додатною");
        }
        balance = balance.add(amount);
    }

    @Override
    public String getTypeName() {
        return "Накопичувальна";
    }

    @Override
    public AccessResult tryPass(LocalDate today) {
        if (balance.compareTo(FARE) < 0) {
            return AccessResult.DENIED_NO_FUNDS;
        }
        balance = balance.subtract(FARE);
        return AccessResult.ALLOWED;
    }
}
