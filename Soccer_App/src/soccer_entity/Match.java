package soccer_entity;

import java.time.LocalDateTime;

public class Match {
    private int id;
    private Round round;
    private Team homeTeam;
    private Team awayTeam;
    private Referee referee;
    private LocalDateTime kickOff;
    private String stadium;
    private MatchStatus status;
    private Integer homeGoals;
    private Integer awayGoals;

    public Match() {}

    public Match(int id, Round round, Team homeTeam, Team awayTeam, Referee referee,
                 LocalDateTime kickOff, String stadium, MatchStatus status,
                 Integer homeGoals, Integer awayGoals) {
        this.id = id;
        this.round = round;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.referee = referee;
        this.kickOff = kickOff;
        this.stadium = stadium;
        this.status = status;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public LocalDateTime getKickOff() {
        return kickOff;
    }

    public void setKickOff(LocalDateTime kickOff) {
        this.kickOff = kickOff;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    @Override
    public String toString() {
        return homeTeam.getName() + " vs " + awayTeam.getName() +
                " (" + status.getDisplayName() + ")";
    }
    public Integer getHomeScore() { return homeGoals; }
    public Integer getAwayScore() { return awayGoals; }
    public LocalDateTime getDate() { return kickOff; }

}
