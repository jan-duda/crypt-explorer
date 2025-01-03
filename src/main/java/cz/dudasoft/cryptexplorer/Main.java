package cz.dudasoft.cryptexplorer;

import cz.dudasoft.cryptexplorer.dungeon.MapGenerator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        char[][] dungeon = MapGenerator.generateMap(10, 10);

        int playerX = 0, playerY = 0;
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
            // Display the map
            for (int i = 0; i < dungeon.length; i++) {
                for (int j = 0; j < dungeon[i].length; j++) {
                    if (i == playerX && j == playerY) {
                        System.out.print('P'); // Player position
                    } else {
                        System.out.print(dungeon[i][j]);
                    }
                }
                System.out.println();
            }

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
            System.out.println("Choose a direction to move:");
            System.out.println("1. Up  2. Down  3. Left  4. Right");
            int move = scanner.nextInt();
            int newX = playerX, newY = playerY;

            switch (move) {
                case 1: newX--; break;
                case 2: newX++; break;
                case 3: newY--; break;
                case 4: newY++; break;
                default: System.out.println("Invalid move!"); continue;
            }

            // Check if the move is valid
            if (newX >= 0 && newX < dungeon.length && newY >= 0 && newY < dungeon[0].length
                    && dungeon[newX][newY] != '#') {
                playerX = newX;
                playerY = newY;
            } else {
                System.out.println("You hit a wall!");
            }

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