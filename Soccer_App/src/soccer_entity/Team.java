package soccer_entity;

import java.util.ArrayList;
import java.util.List;
import soccer_entity.Coach;
import soccer_entity.Player;
import soccer_entity.League;

public class Team {
    private int id;
    private String name;
    private League league;
    private Coach coach;
    private List<Player> players = new ArrayList<>();

    // Stats
    private Integer points = 0;
    private Integer played = 0;
    private Integer won = 0;
    private Integer drawn = 0;
    private Integer lost = 0;
    private Integer goalsFor = 0;
    private Integer goalsAgainst = 0;

    public Team() {}

    public Team(int id, String name, League league) {
        this.id = id;
        this.name = name;
        this.league = league;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public League getLeague() { return league; }
    public void setLeague(League league) { this.league = league; }
    public Coach getCoach() { return coach; }
    public void setCoach(Coach coach) { this.coach = coach; }
    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }
    public Integer getPlayed() { return played; }
    public void setPlayed(Integer played) { this.played = played; }
    public Integer getWon() { return won; }
    public void setWon(Integer won) { this.won = won; }
    public Integer getDrawn() { return drawn; }
    public void setDrawn(Integer drawn) { this.drawn = drawn; }
    public Integer getLost() { return lost; }
    public void setLost(Integer lost) { this.lost = lost; }
    public Integer getGoalsFor() { return goalsFor; }
    public void setGoalsFor(Integer goalsFor) { this.goalsFor = goalsFor; }
    public Integer getGoalsAgainst() { return goalsAgainst; }
    public void setGoalsAgainst(Integer goalsAgainst) { this.goalsAgainst = goalsAgainst; }

    @Override
    public String toString() {
        return name + (league != null ? " (" + league.getName() + ")" : "");
    }
}
