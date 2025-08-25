package soccer_entity;

public enum Position {
    GK("GK"),   // Goalkeeper
    DF("DF"),   // Defender
    MF("MF"),   // Midfielder
    FW("FW");   // Forward

    private final String shortName;

    Position(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Position fromShortName(String sn) {
        for (Position p : Position.values()) {
            if (p.getShortName().equalsIgnoreCase(sn)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid position short name: " + sn);
    }
}
