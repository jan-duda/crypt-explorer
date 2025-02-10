package cz.dudasoft.cryptexplorer.character;

import cz.dudasoft.cryptexplorer.item.Armor;
import cz.dudasoft.cryptexplorer.item.Item;
import cz.dudasoft.cryptexplorer.item.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    private final String name;
    private int health;
    private final List<Item> inventory;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    protected int x;
    protected int y;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
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
        this.health += amount;
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

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void attack(Character target);
}
