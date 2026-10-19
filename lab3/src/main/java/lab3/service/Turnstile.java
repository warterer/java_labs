package lab3.service;

import lab3.model.AccessResult;
import lab3.model.Card;

import java.time.Clock;
import java.time.LocalDate;
import java.util.*;

public class Turnstile {
    private static final String UNKNOWN_TYPE = "Невідомий тип";

    private final CardRegistry registry;
    private final Clock clock;

    private PassStats total = PassStats.EMPTY;
    private final Map<String, PassStats> byType = new TreeMap<>();
    private final Map<AccessResult, Integer> denialReasons = new EnumMap<>(AccessResult.class);

    public Turnstile(CardRegistry registry, Clock clock) {
        this.registry = Objects.requireNonNull(registry, "registry");
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    public Turnstile(CardRegistry registry) {
        this(registry, Clock.systemDefaultZone());
    }

    public AccessResult check(Card card) {
        AccessResult result;
        String type;
        if (!registry.isRegistered(card)) {
            result = AccessResult.DENIED_UNREADABLE;
            type = UNKNOWN_TYPE;
        }
        else {
            result = card.tryPass(LocalDate.now(clock));
            type = card.getTypeName();
        }
        record(type, result);
        return result;
    }

    public PassStats getTotalStats() {
        return total;
    }

    public Map<String, PassStats> getStatsByType() {
        return Collections.unmodifiableMap(byType);
    }

    public Map<AccessResult, Integer> getDenialReasons() {
        return Collections.unmodifiableMap(denialReasons);
    }

    private void record(String type, AccessResult result) {
        PassStats delta = result.isAllowed() ? new PassStats(1, 0) : new PassStats(0, 1);
        total = total.plus(delta);
        byType.merge(type, delta, PassStats::plus);
        if (!result.isAllowed()) {
            denialReasons.merge(result, 1, Integer::sum);
        }
    }
}
