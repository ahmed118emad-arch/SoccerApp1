package soccer_event;

import soccer_entity.Match;
import soccer_entity.Player;
import java.time.LocalDateTime;

public class ThrowInEvent extends GameEvent {
    public ThrowInEvent(int id, Match match, Player player, LocalDateTime eventTime, int minute) {
        super(id, match, player, eventTime, minute);
    }

    @Override
    public String getDescription() {
        return playerText() + " took a throw-in at " + getMinute() + "'";
    }

    private String playerText() {
        return getPlayer() != null ? getPlayer().getName() : "Unknown Player";
    }
}
