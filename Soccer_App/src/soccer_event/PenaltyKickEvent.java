package soccer_event;

import soccer_entity.Match;
import soccer_entity.Player;
import java.time.LocalDateTime;

public class PenaltyKickEvent extends GameEvent {
    private boolean scored;

    public PenaltyKickEvent(int id, Match match, Player player, LocalDateTime eventTime, int minute, boolean scored) {
        super(id, match, player, eventTime, minute);
        this.scored = scored;
    }

    @Override
    public String getDescription() {
        return playerText() + " took a penalty kick at " + getMinute() + "' (Scored: " + scored + ")";
    }

    private String playerText() {
        return getPlayer() != null ? getPlayer().getName() : "Unknown Player";
    }
}
