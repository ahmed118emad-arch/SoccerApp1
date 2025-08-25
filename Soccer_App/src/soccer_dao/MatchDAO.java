package soccer_dao;

import soccer_entity.Match;
import soccer_entity.Round;
import soccer_entity.Team;
import soccer_entity.Referee;
import soccer_entity.MatchStatus;
import soccer_util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {

    public void addMatch(Match match) throws SQLException {
        String sql = "INSERT INTO Matches (round_id, home_team_id, away_team_id, referee_id, kick_off, stadium, status, home_goals, away_goals) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setObject(1, match.getRound() != null ? match.getRound().getId() : null, Types.INTEGER);
            stmt.setInt(2, match.getHomeTeam().getId());
            stmt.setInt(3, match.getAwayTeam().getId());
            stmt.setObject(4, match.getReferee() != null ? match.getReferee().getId() : null, Types.INTEGER);
            stmt.setTimestamp(5, match.getKickOff() != null ? Timestamp.valueOf(match.getKickOff()) : null);
            stmt.setString(6, match.getStadium());
            stmt.setString(7, match.getStatus() != null ? match.getStatus().name() : MatchStatus.SCHEDULED.name());
            stmt.setObject(8, match.getHomeGoals(), Types.INTEGER);
            stmt.setObject(9, match.getAwayGoals(), Types.INTEGER);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                match.setId(keys.getInt(1));
            }
        }
    }

    public List<Match> getAllMatches() throws SQLException {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT m.*, r.id AS round_id, r.name AS round_name, t1.id AS home_id, t1.name AS home_name, " +
                "t2.id AS away_id, t2.name AS away_name " +
                "FROM Matches m " +
                "LEFT JOIN Rounds r ON m.round_id = r.id " +
                "LEFT JOIN Teams t1 ON m.home_team_id = t1.id " +
                "LEFT JOIN Teams t2 ON m.away_team_id = t2.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt("id"));

                // Round may be null
                int roundId = rs.getInt("round_id");
                if (!rs.wasNull()) {
                    Round round = new Round();
                    round.setId(roundId);
                    round.setName(rs.getString("round_name"));
                    match.setRound(round);
                } else {
                    match.setRound(null);
                }

                // Home team
                Team home = new Team();
                home.setId(rs.getInt("home_id"));
                home.setName(rs.getString("home_name"));
                match.setHomeTeam(home);

                // Away team
                Team away = new Team();
                away.setId(rs.getInt("away_id"));
                away.setName(rs.getString("away_name"));
                match.setAwayTeam(away);

                match.setKickOff(rs.getTimestamp("kick_off") != null ? rs.getTimestamp("kick_off").toLocalDateTime() : null);
                match.setStadium(rs.getString("stadium"));
                match.setStatus(MatchStatus.valueOf(rs.getString("status")));
                match.setHomeGoals((Integer) rs.getObject("home_goals"));
                match.setAwayGoals((Integer) rs.getObject("away_goals"));

                matches.add(match);
            }
        }
        return matches;
    }

    public Match getMatchById(long id) throws SQLException {
        String sql = "SELECT m.*, r.id AS round_id, r.name AS round_name, t1.id AS home_id, t1.name AS home_name, " +
                "t2.id AS away_id, t2.name AS away_name " +
                "FROM Matches m " +
                "LEFT JOIN Rounds r ON m.round_id = r.id " +
                "LEFT JOIN Teams t1 ON m.home_team_id = t1.id " +
                "LEFT JOIN Teams t2 ON m.away_team_id = t2.id " +
                "WHERE m.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt("id"));

                int roundId = rs.getInt("round_id");
                if (!rs.wasNull()) {
                    Round round = new Round();
                    round.setId(roundId);
                    round.setName(rs.getString("round_name"));
                    match.setRound(round);
                } else {
                    match.setRound(null);
                }

                Team home = new Team();
                home.setId(rs.getInt("home_id"));
                home.setName(rs.getString("home_name"));
                match.setHomeTeam(home);

                Team away = new Team();
                away.setId(rs.getInt("away_id"));
                away.setName(rs.getString("away_name"));
                match.setAwayTeam(away);

                match.setKickOff(rs.getTimestamp("kick_off") != null ? rs.getTimestamp("kick_off").toLocalDateTime() : null);
                match.setStadium(rs.getString("stadium"));
                match.setStatus(MatchStatus.valueOf(rs.getString("status")));
                match.setHomeGoals((Integer) rs.getObject("home_goals"));
                match.setAwayGoals((Integer) rs.getObject("away_goals"));

                return match;
            }
        }
        return null;
    }
}
