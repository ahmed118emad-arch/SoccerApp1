package soccer_event;

import soccer_entity.Match;
import soccer_entity.Player;
import soccer_entity.Team;
import java.time.LocalDateTime;

public class PossessionEvent extends GameEvent {
    private Team team;

    public PossessionEvent(int id, Match match, Player player, LocalDateTime eventTime, int minute, Team team) {
        super(id, match, player, eventTime, minute);
        this.team = team;
    }

    @Override
    public String getDescription() {
        return "Possession gained by " + (team != null ? team.getName() : "Unknown Team") +
                " at " + getMinute() + "'";
    }
}
