package cz.dudasoft.cryptexplorer.dungeon;

import cz.dudasoft.cryptexplorer.character.PlayerStats;

import java.util.Scanner;

public class RoomInteractionHandler {

    public void handleRoom(char room, PlayerStats player, Scanner scanner) {
        System.out.println(MapFieldType.fromSymbol (room).getDescription());

        switch (MapFieldType.fromSymbol(room)) {
            case TREASURE -> handleTreasure(player, scanner);
            case MONSTER -> handleMonster(player, scanner);
        }
    }

    private void handleTreasure(PlayerStats player, Scanner scanner) {
        System.out.println("1. Take treasure");
        System.out.println("2. Move to another room");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("You collected treasure!");
            player.addScore(10);
        }
    }

    private void handleMonster(PlayerStats player, Scanner scanner) {
        System.out.println("1. Fight monster");
        System.out.println("2. Run away");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("You defeated the monster but took damage!");
            player.takeDamage(2);
        } else {
            System.out.println("You ran away!");
        }
    }
}

