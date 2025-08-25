//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import gui.SoccerGUI;
import soccer_service.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // أي عملية تهيئة للـ DB لو محتاجة تعمل هنا (DbUtils.init() أو DBConnection.init() لو عندك)
        try {
            SwingUtilities.invokeLater(() -> {
                // إنشاء الـ services (أفتراض: لديهم constructors بدون باراميتر)
                TeamService teamService = new TeamService();
                PlayerService playerService = new PlayerService();
                MatchService matchService = new MatchService();
                LeagueService leagueService = new LeagueService();

                SoccerGUI gui = new SoccerGUI(teamService, playerService, matchService, leagueService);
                gui.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Failed to start application: " + e.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
