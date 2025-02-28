package cz.dudasoft.cryptexplorer.dungeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MapGenerator {
    public char[][] generateMap(int rows, int cols) {
        Random random = new Random();
        char[][] map = new char[rows][cols];

        // Fill map with walls
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = MapFieldType.WALL.getSymbol();
            }
        }

        // Place start and exit
        map[1][1] = MapFieldType.START.getSymbol();
        map[rows - 2][cols - 2] = MapFieldType.EXIT.getSymbol();

        do {
            createRandomPaths(rows, cols, random, map);
        } while (!isPathNavigable(map));

        // Add treasures and monsters randomly
        for (int i = 0; i < rows * cols / 10; i++) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            if (map[x][y] == MapFieldType.EMPTY.getSymbol()) {
                map[x][y] = random.nextBoolean() ? MapFieldType.TREASURE.getSymbol() : MapFieldType.MONSTER.getSymbol();
            }
        }

        return map;
    }

    private void createRandomPaths(int rows, int cols, Random random, char[][] map) {
        // Create some random paths
        for (int i = 0; i < rows * cols / 3; i++) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            if (map[x][y] == MapFieldType.WALL.getSymbol() && x != 0 && x != rows - 1 && y != 0 && y != cols - 1) {
                map[x][y] = MapFieldType.EMPTY.getSymbol();
            }
        }
    }

    public boolean isPathNavigable(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // Find the start position
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == MapFieldType.START.getSymbol()) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    break;
                }
            }
        }

        // check for path to 'E'
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            if (map[x][y] == MapFieldType.EXIT.getSymbol()) return true;

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols &&
                        !visited[newX][newY] &&
                        (map[newX][newY] == MapFieldType.EMPTY.getSymbol()
                                || map[newX][newY] == MapFieldType.TREASURE.getSymbol()
                                || map[newX][newY] == MapFieldType.MONSTER.getSymbol()
                                || map[newX][newY] == MapFieldType.EXIT.getSymbol()
                        )
                ) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
        return false;
    }

    public void displayMap(char[][] map, int playerX, int playerY) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print(MapFieldType.PLAYER.getSymbol()); // Player position
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void displayMap(char[][] map) {
        for (char[] row : map) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }
}
