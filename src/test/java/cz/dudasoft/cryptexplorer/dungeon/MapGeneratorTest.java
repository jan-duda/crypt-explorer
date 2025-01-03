package cz.dudasoft.cryptexplorer.dungeon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MapGeneratorTest {

    @Test
    public void testGenerateMapDimensions() {
        int rows = 5;
        int cols = 5;
        char[][] map = MapGenerator.generateMap(rows, cols);
        assertEquals(rows, map.length, "Map should have the correct number of rows.");
        for (char[] row : map) {
            assertEquals(cols, row.length, "Map should have the correct number of columns.");
        }
    }

    @Test
    public void testMapHasStartAndExit() {
        char[][] map = MapGenerator.generateMap(5, 5);
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
        char[][] map = MapGenerator.generateMap(5, 5);
        assertTrue(MapGenerator.isPathNavigable(map), "There should be a path from 'S' to 'E'.");
    }


}

