package cz.dudasoft.cryptexplorer.item;

import cz.dudasoft.cryptexplorer.character.Monster;

public abstract class Weapon extends cz.dudasoft.cryptexplorer.item.Item {
    private final int attackBonus;
    private final int defenseBonus;
    private final WeaponType type;

    public Weapon(String name, String description, int attackBonus, int defenseBonus, WeaponType type) {
        super(name, description);
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.type = type;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public WeaponType getType() {
        return type;
    }

    public void applySpecialEffect(Monster target) {
        // Defaultní implementace: žádný efekt
    }
}

