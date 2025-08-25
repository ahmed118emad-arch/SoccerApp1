package soccer_dao;

import soccer_entity.Round;
import soccer_entity.League;
import soccer_util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoundDAO {

    public void addRound(Round round) throws SQLException {
        String sql = "INSERT INTO Rounds (league_id, name, seq) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, round.getLeague().getId());
            stmt.setString(2, round.getName());
            stmt.setInt(3, round.getSeq());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                round.setId(keys.getInt(1));
            }
        }
    }

    public List<Round> getRoundsByLeague(League league) throws SQLException {
        List<Round> rounds = new ArrayList<>();
        String sql = "SELECT * FROM Rounds WHERE league_id=? ORDER BY seq";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, league.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rounds.add(new Round(
                        rs.getInt("id"),
                        league,
                        rs.getString("name"),
                        rs.getInt("seq")
                ));
            }
        }
        return rounds;
    }
}
