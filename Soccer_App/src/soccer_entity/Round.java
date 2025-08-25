package soccer_entity;

public class Round {
    private int id;
    private League league;
    private String name;
    private int seq;

    public Round() {}

    public Round(int id, League league, String name, int seq) {
        this.id = id;
        this.league = league;
        this.name = name;
        this.seq = seq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return name;
    }
}
