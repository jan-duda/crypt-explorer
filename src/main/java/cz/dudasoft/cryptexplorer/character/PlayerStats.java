package cz.dudasoft.cryptexplorer.character;

public class PlayerStats {
    private int health;
    private int score;
    private int x, y;

    public PlayerStats() {
        this.health = 10;
        this.score = 0;
        this.x = 1;
        this.y = 1;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}