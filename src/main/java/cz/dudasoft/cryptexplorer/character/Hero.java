package cz.dudasoft.cryptexplorer.character;

public class Hero extends Monster {
    private int score;

    public Hero(String name, int health) {
        super(name, health, 0); // Base damage is 0 because heroes rely entirely on weapons
        this.score = 0;
        this.x = 1;
        this.y = 1;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void attack(Character target) {
        super.attack(target);
        if (target instanceof Monster && !target.isAlive()) {
            System.out.println("You defeated " + target.getName() + "!");
            // Loot the monster's inventory
            getInventory().addAll(target.getInventory());
        }
    }
}
