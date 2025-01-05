package cz.dudasoft.cryptexplorer.gameplay;

import cz.dudasoft.cryptexplorer.character.PlayerStats;
import cz.dudasoft.cryptexplorer.dungeon.MapFieldType;
import cz.dudasoft.cryptexplorer.dungeon.MapGenerator;
import cz.dudasoft.cryptexplorer.dungeon.FieldInteractionHandler;

import java.util.Scanner;

public class GameEngine {
    private final MapGenerator mapGenerator;
    private final MovementHandler movementHandler;
    private final FieldInteractionHandler roomHandler;
    private final PlayerStats player;

    public GameEngine() {
        this.mapGenerator = new MapGenerator();
        this.movementHandler = new MovementHandler();
        this.roomHandler = new FieldInteractionHandler();
        this.player = new PlayerStats();
    }

    public void startGame() {
        char[][] dungeon = mapGenerator.generateMap(10, 10);
        roomHandler.setMap(dungeon);
        Scanner scanner = new Scanner(System.in);

        boolean gameRunning = true;

        while (gameRunning) {
            mapGenerator.displayMap(dungeon, player.getX(), player.getY());

            // Room description and interaction
            char currentField = dungeon[player.getX()][player.getY()];
            roomHandler.handleRoom(currentField, player);

            // Check game state
            if (player.getHealth() <= 0) {
                System.out.println("You have perished in the dungeon. Game over.");
                gameRunning = false;
                continue;
            }

            if (currentField == MapFieldType.EXIT.getSymbol()) {
                System.out.println("You found the exit! You win!");
                System.out.println("Final score: " + player.getScore());
                gameRunning = false;
                continue;
            }

            // Movement
            System.out.println("Use numpad (1-9) to move:");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= 1 && input <= 9) {
                    int[] newPosition = movementHandler.getNewPosition(player.getX(), player.getY(), input, dungeon);
                    player.updatePosition(newPosition[0], newPosition[1]);
                } else {
                    System.out.println("Invalid direction. Please use numpad keys 1-9.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }

            // Display stats
            System.out.println("Health: " + player.getHealth() + ", Score: " + player.getScore());
        }
    }
}