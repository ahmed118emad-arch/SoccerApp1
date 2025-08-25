package soccer_service;

import soccer_entity.Team;
import soccer_dao.TeamDAO;

import java.util.List;

public class TeamService {
    private final TeamDAO teamDAO;

    public TeamService() {
        this.teamDAO = new TeamDAO();
    }

    public void addTeam(Team team) {
        try {
            teamDAO.addTeam(team);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add team: " + e.getMessage(), e);
        }
    }

    public List<Team> getAllTeams() {
        try {
            return teamDAO.getAllTeams();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch teams: " + e.getMessage(), e);
        }
    }

    public Team getTeamById(long id) {
        try {
            return teamDAO.getTeamById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch team by ID: " + e.getMessage(), e);
        }
    }

    public List<Team> searchTeamsByName(String keyword) {
        try {
            return teamDAO.searchTeamsByName(keyword);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search teams: " + e.getMessage(), e);
        }
    }
    public List<Team> searchTeamsByCoachName(String keyword) {
        try {
            return teamDAO.searchTeamsByCoachName(keyword);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search teams by coach: " + e.getMessage(), e);
        }
    }

}
