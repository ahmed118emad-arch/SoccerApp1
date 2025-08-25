package soccer_dao;

import soccer_entity.Player;
import soccer_entity.Team;
import soccer_entity.Position;
import soccer_util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    public void addPlayer(Player player) throws SQLException {
        String sql = "INSERT INTO Players (name, age, position, team_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getAge());
            stmt.setString(3, player.getPosition().getShortName());
            stmt.setObject(4, player.getTeam() != null ? player.getTeam().getId() : null);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                player.setId(keys.getInt(1));
            }
        }
    }

    public List<Player> getAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.age, p.position, p.team_id, t.name as team_name, t.league_id " +
                "FROM Players p LEFT JOIN Teams t ON p.team_id = t.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Team team = null;
                int teamId = rs.getInt("team_id");
                if (!rs.wasNull()) {
                    team = new Team(teamId, rs.getString("team_name"), null);
                }

                Position pos;
                try {
                    pos = Position.fromShortName(rs.getString("position"));
                } catch (IllegalArgumentException ex) {
                    pos = null; // لو القيمة غير معروفة
                }

                Player player = new Player(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        pos,
                        team
                );
                players.add(player);
            }
        }
        return players;
    }

    public List<Player> getPlayersByTeam(Team team) throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Players WHERE team_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, team.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Position pos;
                try {
                    pos = Position.fromShortName(rs.getString("position"));
                } catch (IllegalArgumentException ex) {
                    pos = null;
                }

                Player player = new Player(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        pos,
                        team
                );
                players.add(player);
            }
        }
        return players;
    }

    public Player getPlayerById(long id) throws SQLException {
        String sql = "SELECT p.id, p.name, p.age, p.position, p.team_id, t.name as team_name " +
                "FROM Players p LEFT JOIN Teams t ON p.team_id = t.id WHERE p.id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Team team = null;
                int teamId = rs.getInt("team_id");
                if (!rs.wasNull()) {
                    team = new Team(teamId, rs.getString("team_name"), null);
                }

                Position pos;
                try {
                    pos = Position.fromShortName(rs.getString("position"));
                } catch (IllegalArgumentException ex) {
                    pos = null;
                }

                return new Player(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        pos,
                        team
                );
            }
        }
        return null;
    }
}
