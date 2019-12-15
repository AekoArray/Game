package main.java.Model.GameObjects;

public class Player {
    private String name;
    private String colourValue;
    private int score;

    public String getName() {
        return name;
    }

    public String getColourValue() {
        return colourValue;
    }

    public int getScore() {
        return score;
    }

    public Player(String colourValue, String name) {
        this.colourValue = colourValue;
        this.name = name;
    }

    public void addPoints(int receivedPoints) {
        score += receivedPoints;
    }
}
