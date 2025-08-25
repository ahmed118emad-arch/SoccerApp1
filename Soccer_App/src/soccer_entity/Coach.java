package soccer_entity;

public class Coach {
    private int id;
    private String name;
    private Team team;

    public Coach() {}

    // كونستركتور ثنائي الباراميتر
    public Coach(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // الكونستركتور الأصلي ثلاثي الباراميتر
    public Coach(int id, String name, Team team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return name;
    }
}

