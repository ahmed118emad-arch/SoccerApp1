package soccer_service;

import soccer_event.GameEvent;
import soccer_entity.Match;
import soccer_dao.GameEventDAO;

import java.util.List;

public class GameEventService {
    private final GameEventDAO gameEventDAO;

    public GameEventService() {
        this.gameEventDAO = new GameEventDAO();
    }

    public void addEvent(GameEvent event) {
        try {
            gameEventDAO.addEvent(event);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add game event: " + e.getMessage(), e);
        }
    }

    public List<GameEvent> getEventsByMatch(Match match) {
        try {
            return gameEventDAO.getEventsByMatch(match);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch events: " + e.getMessage(), e);
        }
    }
}
