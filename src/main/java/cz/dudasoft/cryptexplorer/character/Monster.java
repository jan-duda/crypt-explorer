package cz.dudasoft.cryptexplorer.character;

import cz.dudasoft.cryptexplorer.item.WeaponType;

import java.util.HashMap;
import java.util.Map;

public class Monster extends Character {
    private final int baseAttackDamage;
    private final Map<WeaponType, Integer> skills;

    public Monster(String name, int health, int baseAttackDamage) {
        super(name, health);
        this.baseAttackDamage = baseAttackDamage;
        this.skills = new HashMap<>();
    }

    public void improveSkill(WeaponType type, int amount) {
        skills.put(type, skills.getOrDefault(type, 0) + amount);
    }

    public int getSkillBonus(WeaponType type) {
        return skills.getOrDefault(type, 0);
    }

    @Override
    public void attack(Character target) {
        int attackPower = baseAttackDamage + getAttackPower(); // Base + equipped weapon bonus
        target.takeDamage(attackPower);
        System.out.println(getName() + " attacked " + target.getName() + " for " + attackPower + " damage!");
    }
}
