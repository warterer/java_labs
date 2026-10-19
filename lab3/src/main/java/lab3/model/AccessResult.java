package lab3.model;

public enum AccessResult {
    ALLOWED("Прохід дозволено", true),
    DENIED_UNREADABLE("Дані картки не вдалося зчитати", false),
    DENIED_EXPIRED("Термін дії картки минув", false),
    DENIED_NO_TRIPS("Поїздки на картці закінчились", false),
    DENIED_NO_FUNDS("Недостатньо коштів на картці", false);

    private final String message;
    private final boolean allowed;

    AccessResult(String message, boolean allowed) {
        this.message = message;
        this.allowed = allowed;
    }

    public String getMessage() {
        return message;
    }

    public boolean isAllowed() {
        return allowed;
    }
}
