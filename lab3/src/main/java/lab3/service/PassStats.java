package lab3.service;

public record PassStats(int allowed, int denied) {
    public static final PassStats EMPTY = new PassStats(0, 0);

    public int total() {
        return allowed + denied;
    }

    public PassStats plus(PassStats other) {
        return new PassStats(allowed + other.allowed, denied + other.denied);
    }
}
