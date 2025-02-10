package cz.dudasoft.cryptexplorer.item;

public class Armor extends cz.dudasoft.cryptexplorer.item.Item {
    private final int defenseBonus;

    public Armor(String name, String description, int defenseBonus) {
        super(name, description);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public void use() {

    }
}
