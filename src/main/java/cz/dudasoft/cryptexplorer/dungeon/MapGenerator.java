package cz.dudasoft.cryptexplorer.dungeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MapGenerator {
    public static char[][] generateMap(int rows, int cols) {
        Random random = new Random();
        char[][] map = new char[rows][cols];

        // Fill map with walls
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = '#';
            }
        }

        // Place start and exit
        map[0][0] = 'S';
        map[rows - 1][cols - 1] = 'E';

        do {
            createRandomPaths(rows, cols, random, map);
        } while (!isPathNavigable(map));

        // Add treasures and monsters randomly
        for (int i = 0; i < rows * cols / 10; i++) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            if (map[x][y] == '.') {
                map[x][y] = random.nextBoolean() ? 'T' : 'M';
            }
        }

        return map;
    }

    private static void createRandomPaths(int rows, int cols, Random random, char[][] map) {
        // Create some random paths
        for (int i = 0; i < rows * cols / 3; i++) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            if (map[x][y] == '#') {
                map[x][y] = '.';
            }
        }
    }

    public static boolean isPathNavigable(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // Find the start position
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 'S') {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    break;
                }
            }
        }

        // BFS to check for path to 'E'
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            if (map[x][y] == 'E') return true;

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols &&
                        !visited[newX][newY] && (map[newX][newY] == '.' || map[newX][newY] == 'E')) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }

        return false;
    }
}
