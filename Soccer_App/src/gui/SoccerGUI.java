package gui;

import soccer_entity.*;
import soccer_service.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SoccerGUI extends JFrame {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final MatchService matchService;
    private final LeagueService leagueService;

    private JTable teamTable, playerTable, matchTable, leagueTable;
    private JLabel statusLabel;

    public SoccerGUI(TeamService teamService,
                     PlayerService playerService,
                     MatchService matchService,
                     LeagueService leagueService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.matchService = matchService;
        this.leagueService = leagueService;

        setTitle("Soccer League Manager");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        refreshAll();
    }

    private void initUI() {
        // Top panel: title + search + refresh
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("⚽ Soccer League Dashboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        topPanel.add(title, BorderLayout.WEST);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshAll());
        JTextField searchField = new JTextField(18);
        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> searchAll(searchField.getText().trim()));
        actionPanel.add(refreshBtn);
        actionPanel.add(searchField);
        actionPanel.add(searchBtn);

        topPanel.add(actionPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();

        teamTable = new JTable();
        playerTable = new JTable();
        matchTable = new JTable();
        leagueTable = new JTable();

        tabs.addTab("Teams", new JScrollPane(teamTable));
        tabs.addTab("Players", new JScrollPane(playerTable));
        tabs.addTab("Matches", new JScrollPane(matchTable));
        tabs.addTab("League Table", new JScrollPane(leagueTable));

        add(tabs, BorderLayout.CENTER);

        // Status bar
        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        add(statusLabel, BorderLayout.SOUTH);

        // Double-click listeners
        teamTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) showSelectedTeamDetails();
            }
        });
        playerTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) showSelectedPlayerDetails();
            }
        });
        matchTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) showSelectedMatchDetails();
            }
        });
    }

    // ===================== Refresh =====================
    private void refreshAll() {
        try {
            loadTeams();
            loadPlayers();
            loadMatches();
            loadLeagueTable();
            statusLabel.setText("Data loaded at " + java.time.LocalTime.now().withNano(0));
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Failed to load data: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error loading data: " + e.getMessage(),
                    "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===================== Loaders =====================
    private void loadTeams() throws Exception {
        List<Team> teams = teamService.getAllTeams();
        String[] cols = {"ID", "Name", "Coach"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        if (teams != null) {
            for (Team t : teams) {
                String coachName = t.getCoach() != null ? t.getCoach().getName() : "N/A";
                model.addRow(new Object[]{t.getId(), t.getName(), coachName});
            }
        }
        teamTable.setModel(model);
        teamTable.getColumnModel().getColumn(0).setPreferredWidth(60);
    }

    private void loadPlayers() throws Exception {
        List<Player> players = playerService.getAllPlayers();
        String[] cols = {"ID", "Name", "Team", "Position"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        if (players != null) {
            for (Player p : players) {
                String teamName = (p.getTeam() != null) ? p.getTeam().getName() : "Free Agent";
                model.addRow(new Object[]{p.getId(), p.getName(), teamName, p.getPosition()});
            }
        }
        playerTable.setModel(model);
    }

    private void loadMatches() throws Exception {
        List<Match> matches = matchService.getAllMatches();
        String[] cols = {"ID", "Date", "Home Team", "Away Team", "Score", "Status"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        if (matches != null) {
            for (Match m : matches) {
                String date = (m.getDate() != null) ? m.getDate().toString() : "";
                String home = m.getHomeTeam() != null ? m.getHomeTeam().getName() : "N/A";
                String away = m.getAwayTeam() != null ? m.getAwayTeam().getName() : "N/A";
                String score = m.getHomeScore() + " - " + m.getAwayScore();
                model.addRow(new Object[]{m.getId(), date, home, away, score, m.getStatus()});
            }
        }
        matchTable.setModel(model);
    }

    private void loadLeagueTable() throws Exception {
        List<Team> teams = teamService.getAllTeams();
        String[] cols = {"Position", "Team", "Played", "Won", "Drawn", "Lost", "GF", "GA", "GD", "Points"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        if (teams != null) {
            teams.sort((a, b) -> Integer.compare(
                    b.getPoints() == null ? 0 : b.getPoints(),
                    a.getPoints() == null ? 0 : a.getPoints()
            ));
            int pos = 1;
            for (Team t : teams) {
                model.addRow(new Object[]{
                        pos++, t.getName(),
                        t.getPlayed() == null ? 0 : t.getPlayed(),
                        t.getWon() == null ? 0 : t.getWon(),
                        t.getDrawn() == null ? 0 : t.getDrawn(),
                        t.getLost() == null ? 0 : t.getLost(),
                        t.getGoalsFor() == null ? 0 : t.getGoalsFor(),
                        t.getGoalsAgainst() == null ? 0 : t.getGoalsAgainst(),
                        (t.getGoalsFor() == null ? 0 : t.getGoalsFor()) - (t.getGoalsAgainst() == null ? 0 : t.getGoalsAgainst()),
                        t.getPoints() == null ? 0 : t.getPoints()
                });
            }
        }
        leagueTable.setModel(model);
    }

    // ===================== Search =====================
    private void searchAll(String q) {
        if (q == null || q.isEmpty()) {
            refreshAll();
            return;
        }
        try {
            // البحث في الفرق
            List<Team> teamResults = teamService.searchTeamsByName(q);

            // البحث في اللاعبين
            List<Player> playerResults = playerService.searchPlayersByName(q);

            // البحث في المدربين
            List<Team> coachResults = teamService.searchTeamsByCoachName(q);

            // تحديث جدول Teams
            String[] cols = {"ID", "Name", "Coach"};
            DefaultTableModel model = new DefaultTableModel(cols, 0) {
                public boolean isCellEditable(int row, int column) { return false; }
            };

            for (Team t : teamResults) {
                model.addRow(new Object[]{t.getId(), t.getName(), t.getCoach() != null ? t.getCoach().getName() : "N/A"});
            }
            for (Team t : coachResults) {
                if (!teamResults.contains(t)) {
                    model.addRow(new Object[]{t.getId(), t.getName(), t.getCoach() != null ? t.getCoach().getName() : "N/A"});
                }
            }

            teamTable.setModel(model);

            // تحديث جدول Players
            String[] playerCols = {"ID", "Name", "Team", "Position"};
            DefaultTableModel playerModel = new DefaultTableModel(playerCols, 0) {
                public boolean isCellEditable(int row, int column) { return false; }
            };
            for (Player p : playerResults) {
                String teamName = p.getTeam() != null ? p.getTeam().getName() : "Free Agent";
                playerModel.addRow(new Object[]{p.getId(), p.getName(), teamName, p.getPosition()});
            }
            playerTable.setModel(playerModel);

            statusLabel.setText("Search results for: " + q);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Search failed: " + e.getMessage(),
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===================== Detail Views =====================
    private void showSelectedTeamDetails() {
        int r = teamTable.getSelectedRow();
        if (r < 0) return;
        Object idObj = teamTable.getValueAt(r, 0);
        if (idObj == null) return;
        try {
            int id = Integer.parseInt(idObj.toString());
            Team t = teamService.getTeamById(id);
            if (t != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Team: ").append(t.getName()).append("\n");
                sb.append("Coach: ").append(t.getCoach() != null ? t.getCoach().getName() : "N/A").append("\n");
                sb.append("Players: \n");
                if (t.getPlayers() != null) {
                    for (Player p : t.getPlayers()) {
                        sb.append(" - ").append(p.getName()).append(" (").append(p.getPosition()).append(")\n");
                    }
                }
                JOptionPane.showMessageDialog(this, sb.toString(), "Team Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load team details: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSelectedPlayerDetails() {
        int r = playerTable.getSelectedRow();
        if (r < 0) return;
        Object idObj = playerTable.getValueAt(r, 0);
        if (idObj == null) return;
        try {
            int id = Integer.parseInt(idObj.toString());
            Player p = playerService.getPlayerById(id);
            if (p != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Name: ").append(p.getName()).append("\n");
                sb.append("Position: ").append(p.getPosition()).append("\n");
                sb.append("Team: ").append(p.getTeam() != null ? p.getTeam().getName() : "Free Agent").append("\n");
                JOptionPane.showMessageDialog(this, sb.toString(), "Player Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load player details: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSelectedMatchDetails() {
        int r = matchTable.getSelectedRow();
        if (r < 0) return;
        Object idObj = matchTable.getValueAt(r, 0);
        if (idObj == null) return;
        try {
            int id = Integer.parseInt(idObj.toString());
            Match m = matchService.getMatchById(id);
            if (m != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Match ID: ").append(m.getId()).append("\n");
                sb.append("Date: ").append(m.getDate()).append("\n");
                sb.append("Home: ").append(m.getHomeTeam() != null ? m.getHomeTeam().getName() : "N/A").append("\n");
                sb.append("Away: ").append(m.getAwayTeam() != null ? m.getAwayTeam().getName() : "N/A").append("\n");
                sb.append("Score: ").append(m.getHomeScore()).append(" - ").append(m.getAwayScore()).append("\n");
                sb.append("Status: ").append(m.getStatus()).append("\n");
                JOptionPane.showMessageDialog(this, sb.toString(), "Match Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load match details: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===================== Main =====================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SoccerGUI(
                new TeamService(),
                new PlayerService(),
                new MatchService(),
                new LeagueService()
        ).setVisible(true));
    }
}
