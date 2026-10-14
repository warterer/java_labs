package lab3;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Card {

    private final String id;
    private final Category category;

    protected Card(String id, Category category) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Card id must not be empty");
        }
        this.id = id;
        this.category = Objects.requireNonNull(category, "category");
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public abstract String getTypeName();

    public abstract AccessResult tryPass(LocalDate today);

    public abstract String getDetails();

    @Override
    public String toString() {
        return getTypeName() + " [" + id + "]";
    }
}
