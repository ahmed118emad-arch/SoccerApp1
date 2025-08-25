package soccer_dao;

import soccer_event.GameEvent;
import soccer_entity.Match;
import soccer_entity.Player;
import soccer_util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameEventDAO {

    public void addEvent(GameEvent event) throws SQLException {
        String sql = "INSERT INTO Events (match_id, minute, event_type, description, player_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, event.getMatch().getId());
            stmt.setInt(2, event.getMinute());
            stmt.setString(3, event.getClass().getSimpleName());
            stmt.setString(4, event.getDescription());
            stmt.setInt(5, event.getPlayer() != null ? event.getPlayer().getId() : Types.NULL);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                // يمكن تخزين ID في event إذا أردت
            }
        }
    }

    public List<GameEvent> getEventsByMatch(Match match) throws SQLException {
        List<GameEvent> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE match_id=? ORDER BY minute";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, match.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // هنا يمكن استخدام Factory pattern لتحويل event_type إلى subclass
            }
        }
        return events;
    }
}
