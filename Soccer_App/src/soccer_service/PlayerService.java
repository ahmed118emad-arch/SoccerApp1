package soccer_service;

import soccer_entity.Player;
import soccer_dao.PlayerDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerService {
    private final PlayerDAO playerDAO;

    public PlayerService() {
        this.playerDAO = new PlayerDAO();
    }

    public void addPlayer(Player player) {
        try {
            playerDAO.addPlayer(player);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add player: " + e.getMessage(), e);
        }
    }

    public List<Player> getAllPlayers() {
        try {
            return playerDAO.getAllPlayers();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all players: " + e.getMessage(), e);
        }
    }

    public List<Player> getPlayersByTeam(soccer_entity.Team team) {
        try {
            return playerDAO.getPlayersByTeam(team);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch players by team: " + e.getMessage(), e);
        }
    }

    public Player getPlayerById(long id) {
        try {
            return playerDAO.getPlayerById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch player by ID: " + e.getMessage(), e);
        }
    }
    public List<Player> searchPlayersByName(String name) throws Exception {
        List<Player> allPlayers = getAllPlayers(); // دالة موجودة بالفعل
        if (name == null || name.isEmpty()) return allPlayers;

        List<Player> results = new ArrayList<>();
        String lower = name.toLowerCase();
        for (Player p : allPlayers) {
            if (p.getName() != null && p.getName().toLowerCase().contains(lower)) {
                results.add(p);
            }
        }
        return results;
    }

}
