package cz.dudasoft.cryptexplorer;

import cz.dudasoft.cryptexplorer.dungeon.MapGenerator;
import cz.dudasoft.cryptexplorer.gameplay.MovementHandler;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        MapGenerator mapGenerator = new MapGenerator();
        MovementHandler movementHandler = new MovementHandler();

        char[][] dungeon = mapGenerator.generateMap(10, 120);

        int playerX = 1, playerY = 1;
        Map<Character, String> roomDescriptions = Map.of(
                'S', "You stand at the dungeon entrance.",
                'T', "You see a pile of glittering treasure.",
                'M', "A fierce monster blocks your path!",
                'E', "You see the dungeon's exit."
        );


        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;
        int health = 10, score = 0;

        while (gameRunning) {
            mapGenerator.displayMap(dungeon, playerX, playerY);

            // Describe the current room
            char currentRoom = dungeon[playerX][playerY];
            System.out.println(roomDescriptions.getOrDefault(currentRoom, "An empty room."));

            // Present choices based on room type
            if (currentRoom == 'T') {
                System.out.println("1. Take treasure");
                System.out.println("2. Move to another room");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("You collected treasure!");
                    score += 10;
                }
            } else if (currentRoom == 'M') {
                System.out.println("1. Fight monster");
                System.out.println("2. Run away");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("You defeated the monster but took damage!");
                    health -= 2;
                } else {
                    System.out.println("You ran away!");
                    continue;
                }
            }

            // Movement options
            System.out.println("Use numpad (1-9) to move:");
            int input = scanner.nextInt();
            int[] newPosition = movementHandler.getNewPosition(playerX, playerY, input, dungeon);

            // Update player position
            playerX = newPosition[0];
            playerY = newPosition[1];

            // Check for exit
            if (dungeon[playerX][playerY] == 'E') {
                System.out.println("You found the exit! You win!");
                System.out.println("Final score: " + score);
                gameRunning = false;
            }

            // Display player stats
            System.out.println("Health: " + health + ", Score: " + score);
            if (health <= 0) {
                System.out.println("You have perished in the dungeon. Game over.");
                gameRunning = false;
            }
        }
    }
}