package cz.dudasoft.cryptexplorer.gameplay;

public class MovementHandler {
    private final int[][] DIRECTIONS = {
            {1, -1},  // 1: Down-Left
            {1, 0},   // 2: Down
            {1, 1},   // 3: Down-Right
            {0, -1},  // 4: Left
            {0, 0},   // 5: Stay (optional)
            {0, 1},   // 6: Right
            {-1, -1}, // 7: Top-Left
            {-1, 0},  // 8: Up
            {-1, 1}   // 9: Top-Right
    };

    public int[] getNewPosition(int currentRow, int currentCol, int input, char[][] map) {
        if (input < 1 || input > 9 || input == 5) {
            throw new IllegalArgumentException("Invalid numpad input.");
        }

        int[] move = DIRECTIONS[input - 1];
        int newRow = currentRow + move[0];
        int newCol = currentCol + move[1];

        // Validate new position
        if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length && map[newRow][newCol] != '#') {
            return new int[]{newRow, newCol};
        }

        // If invalid, return current position
        return new int[]{currentRow, currentCol};
    }
}