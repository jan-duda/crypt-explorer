package cz.dudasoft.cryptexplorer.gameplay;

import cz.dudasoft.cryptexplorer.character.PlayerStats;
import cz.dudasoft.cryptexplorer.dungeon.MapGenerator;
import cz.dudasoft.cryptexplorer.dungeon.RoomInteractionHandler;

import java.util.Scanner;

public class GameEngine {
    private final MapGenerator mapGenerator;
    private final MovementHandler movementHandler;
    private final RoomInteractionHandler roomHandler;
    private final PlayerStats player;

    public GameEngine() {
        this.mapGenerator = new MapGenerator();
        this.movementHandler = new MovementHandler();
        this.roomHandler = new RoomInteractionHandler();
        this.player = new PlayerStats();
    }

    public void startGame() {
        char[][] dungeon = mapGenerator.generateMap(10, 120);
        Scanner scanner = new Scanner(System.in);

        boolean gameRunning = true;

        while (gameRunning) {
            mapGenerator.displayMap(dungeon, player.getX(), player.getY());

            // Room description and interaction
            char currentRoom = dungeon[player.getX()][player.getY()];
            roomHandler.handleRoom(currentRoom, player);

            // Check game state
            if (player.getHealth() <= 0) {
                System.out.println("You have perished in the dungeon. Game over.");
                gameRunning = false;
                continue;
            }

            if (currentRoom == 'E') {
                System.out.println("You found the exit! You win!");
                System.out.println("Final score: " + player.getScore());
                gameRunning = false;
                continue;
            }

            // Movement
            System.out.println("Use numpad (1-9) to move:");
            int input = scanner.nextInt();
            int[] newPosition = movementHandler.getNewPosition(player.getX(), player.getY(), input, dungeon);
            player.updatePosition(newPosition[0], newPosition[1]);

            // Display stats
            System.out.println("Health: " + player.getHealth() + ", Score: " + player.getScore());
        }
    }
}