package cz.dudasoft.cryptexplorer.dungeon;

import cz.dudasoft.cryptexplorer.character.PlayerStats;

public class FieldInteractionHandler {
    private char[][] map;

    public void setMap(char[][] map) {
        this.map = map;
    }

    public void handleRoom(char room, PlayerStats player) {
        System.out.println(MapFieldType.fromSymbol(room).getDescription());

        switch (MapFieldType.fromSymbol(room)) {
            case TREASURE -> handleTreasure(player);
            case MONSTER -> handleMonster(player);
        }
    }

    private void handleTreasure(PlayerStats player) {
        System.out.println("You collected treasure!");
        player.addScore(10);
        map[player.getX()][player.getY()] = MapFieldType.EMPTY.getSymbol();
    }

    private void handleMonster(PlayerStats player) {
        System.out.println("You defeated the monster but took damage!");
        player.takeDamage(2);
        map[player.getX()][player.getY()] = MapFieldType.TREASURE.getSymbol();
    }
}

