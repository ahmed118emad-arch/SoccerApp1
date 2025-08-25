package soccer_entity;

public class Player {
    private int id;
    private String name;
    private int age;
    private Position position;
    private Team team;
    private int number; // رقم اللاعب


    public Player() {}

    public Player(int id, String name, int age, Position position, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }


    @Override
    public String toString() {
        return name + " (" + position.getShortName() + ")";
    }
}
