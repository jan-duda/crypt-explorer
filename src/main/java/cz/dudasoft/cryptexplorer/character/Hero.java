package cz.dudasoft.cryptexplorer.character;

import cz.dudasoft.cryptexplorer.item.Armor;
import cz.dudasoft.cryptexplorer.item.Weapon;
import cz.dudasoft.cryptexplorer.item.WeaponType;

import java.util.HashMap;
import java.util.Map;

public class Hero extends Monster{
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private final Map<WeaponType, Integer> weaponSkills;

    private int score;
    private int x, y;

    public Hero(String name) {
        super(name, 10, 1, 1);
        this.score = 0;
        this.x = 1;
        this.y = 1;
        this.weaponSkills = new HashMap<>();
        for (WeaponType type : WeaponType.values()) {
            weaponSkills.put(type, 0); // Základní úroveň dovedností
        }
    }

    public void attack(Monster target) {
        int baseAttack = equippedWeapon != null ? equippedWeapon.getAttackBonus() : 0;
        int skillBonus = equippedWeapon != null ? weaponSkills.get(equippedWeapon.getType()) : 0;

        int totalAttack = baseAttack + skillBonus;

        if (equippedWeapon != null) {
            equippedWeapon.applySpecialEffect(target);
        }

        target.takeDamage(totalAttack);
        System.out.println("You attacked the monster for " + totalAttack + " damage!");
    }

    public void defend(int incomingDamage) {
        int defense = 0;
        if (equippedArmor != null) {
            defense += equippedArmor.getDefenseBonus();
        }
        if (equippedWeapon != null) {
            defense += equippedWeapon.getDefenseBonus();
        }

        int damageTaken = Math.max(0, incomingDamage - defense);
        health -= damageTaken;
        System.out.println("You defended against " + incomingDamage + " damage, taking " + damageTaken + " damage!");
    }

    public void improveSkill(WeaponType type, int amount) {
        weaponSkills.put(type, weaponSkills.getOrDefault(type, 0) + amount);
        System.out.println("Your skill with " + type + " improved to " + weaponSkills.get(type) + "!");
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