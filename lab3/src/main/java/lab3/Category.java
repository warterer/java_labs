package lab3;

public enum Category {
    PUPIL("Учнівська"),
    STUDENT("Студентська"),
    REGULAR("Звичайна");

    private final String title;

    Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
