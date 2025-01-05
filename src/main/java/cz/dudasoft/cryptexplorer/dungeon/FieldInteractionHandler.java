package cz.dudasoft.cryptexplorer.dungeon;

import cz.dudasoft.cryptexplorer.character.PlayerStats;

import java.util.Random;

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
        Random random = new Random();
        // Generate random damage between 0 and 5
        int damageTaken = random.nextInt(6);

        if (damageTaken > 0) {
            player.takeDamage(damageTaken);
            System.out.println("You defeated the monster but took " + damageTaken + " damage!");
        } else {
            System.out.println("You defeated the monster without taking a scratch!");
        }

        // 50% chance to leave a treasure, 50% chance to leave an empty room
        if (random.nextBoolean()) {
            map[player.getX()][player.getY()] = MapFieldType.TREASURE.getSymbol();
            System.out.println("The monster left behind a treasure!");
        } else {
            map[player.getX()][player.getY()] = MapFieldType.EMPTY.getSymbol();
            System.out.println("The monster left nothing behind.");
        }
    }
}

