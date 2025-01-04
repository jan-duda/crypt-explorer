package cz.dudasoft.cryptexplorer.dungeon;

import cz.dudasoft.cryptexplorer.character.PlayerStats;

import java.util.Map;
import java.util.Scanner;

public class RoomInteractionHandler {
    private static final Map<Character, String> roomDescriptions = Map.of(
            'S', "You stand at the dungeon entrance.",
            'T', "You see a pile of glittering treasure.",
            'M', "A fierce monster blocks your path!",
            'E', "You see the dungeon's exit."
    );

    public void handleRoom(char room, PlayerStats player, Scanner scanner) {
        System.out.println(roomDescriptions.getOrDefault(room, "An empty room."));

        switch (room) {
            case 'T' -> handleTreasure(player, scanner);
            case 'M' -> handleMonster(player, scanner);
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

