package soccer_event;

import soccer_entity.Match;
import soccer_entity.Player;
import java.time.LocalDateTime;

public class GoalEvent extends GameEvent {
    private boolean ownGoal;
    private boolean penalty;

    public GoalEvent(int id, Match match, Player player, LocalDateTime eventTime,
                     int minute, boolean ownGoal, boolean penalty) {
        super(id, match, player, eventTime, minute);
        this.ownGoal = ownGoal;
        this.penalty = penalty;
    }

    @Override
    public String getDescription() {
        if (ownGoal) {
            return playerText() + " scored an OWN GOAL at " + getMinute() + "'";
        } else if (penalty) {
            return playerText() + " scored a PENALTY at " + getMinute() + "'";
        } else {
            return playerText() + " scored at " + getMinute() + "'";
        }
    }

    private String playerText() {
        return getPlayer() != null ? getPlayer().getName() : "Unknown Player";
    }
}
