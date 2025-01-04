package cz.dudasoft.cryptexplorer.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MapGeneratorTest {

    MapGenerator mapGenerator = new MapGenerator();

    @Test
    public void testGenerateMapDimensions() {
        int rows = 5;
        int cols = 5;
        char[][] map = mapGenerator.generateMap(rows, cols);
        assertEquals(rows, map.length, "Map should have the correct number of rows.");
        for (char[] row : map) {
            assertEquals(cols, row.length, "Map should have the correct number of columns.");
        }
    }

    @Test
    public void testMapHasStartAndExit() {
        char[][] map = mapGenerator.generateMap(5, 5);
        int startCount = 0;
        int exitCount = 0;

        for (char[] row : map) {
            for (char cell : row) {
                if (cell == 'S') startCount++;
                if (cell == 'E') exitCount++;
            }
        }

        assertEquals(1, startCount, "Map should have exactly one start ('S').");
        assertEquals(1, exitCount, "Map should have exactly one exit ('E').");
    }

    @Test
    public void testMapHasNavigablePath() {
        char[][] map = mapGenerator.generateMap(5, 5);
        mapGenerator.displayMap(map);
        assertTrue(mapGenerator.isPathNavigable(map), "There should be a path from 'S' to 'E'.");
    }

    @Test
    public void testMapIsSurroundedByWalls() {
        int rows = 5;
        int cols = 5;
        char[][] map = mapGenerator.generateMap(rows, cols);

        // Check top and bottom rows
        for (int col = 0; col < cols; col++) {
            assertEquals('#', map[0][col], "Top row should be surrounded by walls.");
            assertEquals('#', map[rows - 1][col], "Bottom row should be surrounded by walls.");
        }

        // Check left and right columns
        for (int row = 0; row < rows; row++) {
            assertEquals('#', map[row][0], "Left column should be surrounded by walls.");
            assertEquals('#', map[row][cols - 1], "Right column should be surrounded by walls.");
        }
    }

}

