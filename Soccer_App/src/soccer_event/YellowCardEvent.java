package soccer_event;

import soccer_entity.Match;
import soccer_entity.Player;
import java.time.LocalDateTime;

public class YellowCardEvent extends GameEvent {
    public YellowCardEvent(int id, Match match, Player player, LocalDateTime eventTime, int minute) {
        super(id, match, player, eventTime, minute);
    }

    @Override
    public String getDescription() {
        return playerText() + " received a YELLOW card at " + getMinute() + "'";
    }

    private String playerText() {
        return getPlayer() != null ? getPlayer().getName() : "Unknown Player";
    }
}
