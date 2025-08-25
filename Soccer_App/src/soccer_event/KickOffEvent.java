package soccer_event;

import soccer_entity.Match;
import soccer_entity.Player;
import java.time.LocalDateTime;

public class KickOffEvent extends GameEvent {
    public KickOffEvent(int id, Match match, Player player, LocalDateTime eventTime, int minute) {
        super(id, match, player, eventTime, minute);
    }

    @Override
    public String getDescription() {
        return "Kick-off at " + getMinute() + "' by " + playerText();
    }

    private String playerText() {
        return getPlayer() != null ? getPlayer().getName() : "Unknown Player";
    }
}
