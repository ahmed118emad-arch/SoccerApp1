package soccer_dao;

import soccer_entity.League;
import soccer_util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeagueDAO {

    public void addLeague(League league) throws SQLException {
        String sql = "INSERT INTO Leagues (name, country) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, league.getName());
            stmt.setString(2, league.getCountry());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                league.setId(keys.getInt(1));
            }
        }
    }

    public List<League> getAllLeagues() throws SQLException {
        List<League> leagues = new ArrayList<>();
        String sql = "SELECT * FROM Leagues";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                leagues.add(new League(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("country")
                ));
            }
        }
        return leagues;
    }

    public League getLeagueById(int id) throws SQLException {
        String sql = "SELECT * FROM Leagues WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new League(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
            }
        }
        return null;
    }
}
