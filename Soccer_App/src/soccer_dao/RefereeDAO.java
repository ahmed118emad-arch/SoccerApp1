package soccer_dao;

import soccer_entity.Referee;
import soccer_util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RefereeDAO {

    public void addReferee(Referee referee) throws SQLException {
        String sql = "INSERT INTO Referees (name, country) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, referee.getName());
            stmt.setString(2, referee.getCountry());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                referee.setId(keys.getInt(1));
            }
        }
    }

    public List<Referee> getAllReferees() throws SQLException {
        List<Referee> list = new ArrayList<>();
        String sql = "SELECT * FROM Referees";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Referee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("country")
                ));
            }
        }
        return list;
    }

    public Referee getRefereeById(int id) throws SQLException {
        String sql = "SELECT * FROM Referees WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Referee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("country")
                );
            }
        }
        return null;
    }
}
