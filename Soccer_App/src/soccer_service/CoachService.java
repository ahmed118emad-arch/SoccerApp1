package soccer_service;

import soccer_dao.CoachDAO;
import soccer_entity.Coach;

import java.sql.SQLException;

public class CoachService {
    private final CoachDAO coachDAO;

    public CoachService() {
        this.coachDAO = new CoachDAO();
    }

    public void addCoach(Coach coach) {
        try {
            coachDAO.addCoach(coach);
        } catch (SQLException e) {
            throw new RuntimeException("Error adding coach: " + e.getMessage(), e);
        }
    }
}
