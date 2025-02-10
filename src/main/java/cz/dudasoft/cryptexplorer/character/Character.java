package cz.dudasoft.cryptexplorer.character;

import cz.dudasoft.cryptexplorer.item.Armor;
import cz.dudasoft.cryptexplorer.item.Item;
import cz.dudasoft.cryptexplorer.item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Character {
    private final String name;
    private int health;
    private int maxHealth;
    private final List<Item> inventory;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    protected int x;
    protected int y;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        this.health = Math.max(0, health - damage);
    }

    public void heal(int amount) {
        this.health = Math.min(health + amount, maxHealth);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
    }

    public int getAttackPower() {
        return equippedWeapon != null ? equippedWeapon.getAttackBonus() : 0;
    }

    public int getDefense() {
        int defense = 0;
        if (equippedArmor != null) {
            defense += equippedArmor.getDefenseBonus();
        }
        if (equippedWeapon != null) {
            defense += equippedWeapon.getDefenseBonus();
        }
        return defense;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updatePosition(int newX, int newY) {
        if (this.x == newX && this.y == newY) {
            attemptHealing();
        } else {
            this.x = newX;
            this.y = newY;
        }
    }

    private void attemptHealing() {
        Random random = new Random();
        if (random.nextDouble() < 0.2) { // 10% chance to heal
            int healAmount = 1 + random.nextInt(2); // Heal 1-2 HP
            heal(healAmount);
            System.out.println("You take a moment to rest and heal " + healAmount + " HP.");
        }
    }


    public abstract void attack(Character target);
}
