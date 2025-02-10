package cz.dudasoft.cryptexplorer.character;

public class Monster {
    protected final String name;
    protected int health;
    protected final int attackDamage;
    protected final int defense;

    public Monster(String name, int health, int attackDamage, int defense) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void attack(Hero player) {
        if (isAlive()) {
            player.defend(attackDamage);
        } else {
            System.out.println(name + " is already defeated and cannot attack.");
        }
    }

    public void defend(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        takeDamage(actualDamage);
    }
}
