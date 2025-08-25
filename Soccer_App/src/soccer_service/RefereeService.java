package soccer_service;

import soccer_entity.Referee;
import soccer_dao.RefereeDAO;

import java.util.List;

public class RefereeService {
    private final RefereeDAO refereeDAO;

    public RefereeService() {
        this.refereeDAO = new RefereeDAO();
    }

    public void addReferee(Referee referee) {
        try {
            refereeDAO.addReferee(referee);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add referee: " + e.getMessage(), e);
        }
    }

    public List<Referee> getAllReferees() {
        try {
            return refereeDAO.getAllReferees();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch referees: " + e.getMessage(), e);
        }
    }

    public Referee getRefereeById(int id) {
        try {
            return refereeDAO.getRefereeById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch referee by ID: " + e.getMessage(), e);
        }
    }
}
