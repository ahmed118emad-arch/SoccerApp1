package soccer_dao;

import soccer_entity.Team;
import soccer_entity.League;
import soccer_entity.Coach;
import soccer_util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    public void addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO Teams (name, league_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, team.getName());
            stmt.setObject(2, team.getLeague() != null ? team.getLeague().getId() : null);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                team.setId(keys.getInt(1));
            }
        }
    }

    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT t.id, t.name, t.league_id, " +
                "l.name AS league_name, l.country AS league_country, " +
                "c.id AS coach_id, c.name AS coach_name " +
                "FROM Teams t " +
                "LEFT JOIN Leagues l ON t.league_id = l.id " +
                "LEFT JOIN Coaches c ON c.team_id = t.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                League league = null;
                int leagueId = rs.getInt("league_id");
                if (!rs.wasNull()) {
                    league = new League(leagueId, rs.getString("league_name"), rs.getString("league_country"));
                }

                Coach coach = null;
                int coachId = rs.getInt("coach_id");
                String coachName = rs.getString("coach_name");
                if (!rs.wasNull() && coachName != null) {
                    coach = new Coach(coachId, coachName);
                }

                Team team = new Team(rs.getInt("id"), rs.getString("name"), league);
                team.setCoach(coach);
                teams.add(team);
            }
        }
        return teams;
    }

    public Team getTeamById(long id) throws SQLException {
        String sql = "SELECT t.id, t.name, t.league_id, " +
                "l.name AS league_name, l.country AS league_country, " +
                "c.id AS coach_id, c.name AS coach_name " +
                "FROM Teams t " +
                "LEFT JOIN Leagues l ON t.league_id = l.id " +
                "LEFT JOIN Coaches c ON c.team_id = t.id " +
                "WHERE t.id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                League league = null;
                int leagueId = rs.getInt("league_id");
                if (!rs.wasNull()) {
                    league = new League(leagueId, rs.getString("league_name"), rs.getString("league_country"));
                }

                Coach coach = null;
                int coachId = rs.getInt("coach_id");
                String coachName = rs.getString("coach_name");
                if (!rs.wasNull() && coachName != null) {
                    coach = new Coach(coachId, coachName);
                }

                Team team = new Team(rs.getInt("id"), rs.getString("name"), league);
                team.setCoach(coach);
                return team;
            }
        }
        return null;
    }

    public List<Team> searchTeamsByName(String keyword) throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT t.id, t.name, t.league_id, " +
                "l.name AS league_name, l.country AS league_country, " +
                "c.id AS coach_id, c.name AS coach_name " +
                "FROM Teams t " +
                "LEFT JOIN Leagues l ON t.league_id = l.id " +
                "LEFT JOIN Coaches c ON c.team_id = t.id " +
                "WHERE t.name LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                League league = null;
                int leagueId = rs.getInt("league_id");
                if (!rs.wasNull()) {
                    league = new League(leagueId, rs.getString("league_name"), rs.getString("league_country"));
                }

                Coach coach = null;
                int coachId = rs.getInt("coach_id");
                String coachName = rs.getString("coach_name");
                if (!rs.wasNull() && coachName != null) {
                    coach = new Coach(coachId, coachName);
                }

                Team team = new Team(rs.getInt("id"), rs.getString("name"), league);
                team.setCoach(coach);
                teams.add(team);
            }
        }
        return teams;
    }
    public List<Team> searchTeamsByCoachName(String keyword) throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT t.id, t.name, t.league_id, l.name AS league_name, l.country AS league_country, " +
                "c.id AS coach_id, c.name AS coach_name " +
                "FROM Teams t " +
                "LEFT JOIN Leagues l ON t.league_id = l.id " +
                "LEFT JOIN Coaches c ON c.team_id = t.id " +
                "WHERE c.name LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                League league = null;
                int leagueId = rs.getInt("league_id");
                if (!rs.wasNull()) {
                    league = new League(leagueId, rs.getString("league_name"), rs.getString("league_country"));
                }

                Coach coach = null;
                int coachId = rs.getInt("coach_id");
                String coachName = rs.getString("coach_name");
                if (!rs.wasNull() && coachName != null) {
                    coach = new Coach(coachId, coachName);
                }

                Team team = new Team(rs.getInt("id"), rs.getString("name"), league);
                team.setCoach(coach);
                teams.add(team);
            }
        }
        return teams;
    }

}
