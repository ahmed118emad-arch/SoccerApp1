package soccer_dao;

import soccer_entity.Coach;
import soccer_entity.Team;
import soccer_util.DBConnection;

import java.sql.*;

public class CoachDAO {

    public void addCoach(Coach coach) throws SQLException {
        String sql = "INSERT INTO Coaches (name, team_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, coach.getName());
            stmt.setInt(2, coach.getTeam() != null ? coach.getTeam().getId() : Types.NULL);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                coach.setId(keys.getInt(1));
            }
        }
    }
}
