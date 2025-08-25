package soccer_entity;

public enum MatchStatus {
    SCHEDULED("Scheduled"),
    LIVE("Live"),
    FINISHED("Finished"),
    POSTPONED("Postponed"),
    CANCELLED("Cancelled");

    private final String displayName;

    MatchStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
