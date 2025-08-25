package soccer_service;

import soccer_entity.League;
import soccer_dao.LeagueDAO;

import java.util.List;

public class LeagueService {
    private final LeagueDAO leagueDAO;

    public LeagueService() {
        this.leagueDAO = new LeagueDAO();
    }

    public void addLeague(League league) {
        try {
            leagueDAO.addLeague(league);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add league: " + e.getMessage(), e);
        }
    }

    public List<League> getAllLeagues() {
        try {
            return leagueDAO.getAllLeagues();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch leagues: " + e.getMessage(), e);
        }
    }

    public League getLeagueById(int id) {
        try {
            return leagueDAO.getLeagueById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch league by ID: " + e.getMessage(), e);
        }
    }
}
