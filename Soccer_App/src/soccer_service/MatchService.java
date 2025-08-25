package soccer_service;

import soccer_entity.Match;
import soccer_dao.MatchDAO;

import java.util.List;

public class MatchService {
    private final MatchDAO matchDAO;

    public MatchService() {
        this.matchDAO = new MatchDAO();
    }

    public void addMatch(Match match) {
        try {
            matchDAO.addMatch(match);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add match: " + e.getMessage(), e);
        }
    }

    public List<Match> getAllMatches() {
        try {
            return matchDAO.getAllMatches();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all matches: " + e.getMessage(), e);
        }
    }

    public Match getMatchById(long id) {
        try {
            return matchDAO.getMatchById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch match by ID: " + e.getMessage(), e);
        }
    }
}
