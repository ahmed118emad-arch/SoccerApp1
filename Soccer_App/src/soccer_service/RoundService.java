package soccer_service;

import soccer_entity.Round;
import soccer_entity.League;
import soccer_dao.RoundDAO;

import java.util.List;

public class RoundService {
    private final RoundDAO roundDAO;

    public RoundService() {
        this.roundDAO = new RoundDAO();
    }

    public void addRound(Round round) {
        try {
            roundDAO.addRound(round);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add round: " + e.getMessage(), e);
        }
    }

    public List<Round> getRoundsByLeague(League league) {
        try {
            return roundDAO.getRoundsByLeague(league);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch rounds: " + e.getMessage(), e);
        }
    }
}
