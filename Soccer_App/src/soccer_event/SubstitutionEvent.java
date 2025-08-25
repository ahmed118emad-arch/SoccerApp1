package soccer_event;

import soccer_entity.Match;
import soccer_entity.Player;
import java.time.LocalDateTime;

public class SubstitutionEvent extends GameEvent {
    private Player playerOut;

    public SubstitutionEvent(int id, Match match, Player playerIn, Player playerOut,
                             LocalDateTime eventTime, int minute) {
        super(id, match, playerIn, eventTime, minute);
        this.playerOut = playerOut;
    }

    @Override
    public String getDescription() {
        return "Substitution at " + getMinute() + "': " +
                (playerOut != null ? playerOut.getName() : "Unknown Player") +
                " ⬅️ out, " +
                (getPlayer() != null ? getPlayer().getName() : "Unknown Player") +
                " ➡️ in";
    }
}
