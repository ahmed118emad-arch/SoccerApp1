package soccer_event;

import soccer_entity.Match;
import soccer_entity.Player;
import java.time.LocalDateTime;

public abstract class GameEvent {
    private int id;
    private Match match;
    private Player player;
    private LocalDateTime eventTime;
    private int minute;

    public GameEvent() {}

    public GameEvent(int id, Match match, Player player, LocalDateTime eventTime, int minute) {
        this.id = id;
        this.match = match;
        this.player = player;
        this.eventTime = eventTime;
        this.minute = minute;
    }

    public int getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public Player getPlayer() {
        return player;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public int getMinute() {
        return minute;
    }

    public abstract String getDescription();
}
