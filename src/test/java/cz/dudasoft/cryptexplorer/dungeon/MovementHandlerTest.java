package cz.dudasoft.cryptexplorer.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import cz.dudasoft.cryptexplorer.gameplay.MovementHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MovementHandlerTest {

    private static MovementHandler movementHandler;
    private static char[][] map;

    @BeforeAll
    public static void setup() {
        map = new char[][]{
                {'S', '.', '#'},
                {'.', '.', '#'},
                {'#', '.', 'E'}
        };
        movementHandler = new MovementHandler();
    }

    @Test
    public void testValidMove() {
        int[] newPosition = movementHandler.getNewPosition(0, 0, 2, map); // Move down
        assertArrayEquals(new int[]{1, 0}, newPosition, "Player should move down.");
    }

    @Test
    public void testInvalidMoveToWall() {
        int[] newPosition = movementHandler.getNewPosition(0, 1, 6, map); // Move right into wall
        assertArrayEquals(new int[]{0, 1}, newPosition, "Player should stay in place when hitting a wall.");
    }

    @Test
    public void testOutOfBoundsMove() {
        int[] newPosition = movementHandler.getNewPosition(0, 0, 7, map); // Move top-left (out of bounds)
        assertArrayEquals(new int[]{0, 0}, newPosition, "Player should stay in place when moving out of bounds.");
    }

    @Test
    public void testBoundaryMove() {
        int[] newPosition = movementHandler.getNewPosition(0, 2, 8, map); // Move up out-of-bounds
        assertArrayEquals(new int[]{0, 2}, newPosition, "Player should stay in place at the top boundary.");
    }

    @Test
    public void testMoveFromCenter() {
        int[] newPosition = movementHandler.getNewPosition(1, 1, 3, map); // Move down-right
        assertArrayEquals(new int[]{2, 2}, newPosition, "Player should move down-right.");
    }

    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            movementHandler.getNewPosition(1, 1, 0, map); // Invalid input
        }, "Invalid input should throw an exception.");
    }
}

