package cz.dudasoft.cryptexplorer.dungeon;

import cz.dudasoft.cryptexplorer.character.PlayerStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomInteractionHandlerTest {

    private RoomInteractionHandler roomHandler;
    private PlayerStats player;
    private char[][] map;

    @BeforeEach
    void setUp() {
        roomHandler = new RoomInteractionHandler();
        player = new PlayerStats();
        map = new char[][]{
                {'.', '.', '.'},
                {'.', 'T', '.'},
                {'.', 'M', '.'}
        };
        roomHandler.setMap(map);
        player.updatePosition(1, 1);
    }

    @Test
    void testHandleTreasure() {
        char treasureField = MapFieldType.TREASURE.getSymbol();
        int initialScore = player.getScore();

        roomHandler.handleRoom(treasureField, player);

        assertEquals(initialScore + 10, player.getScore(), "Player's score should increase by 10.");
        assertEquals('.', map[1][1], "Treasure should be replaced with an empty field.");
    }

    @Test
    void testHandleMonster() {
        char monsterField = MapFieldType.MONSTER.getSymbol();
        int initialHealth = player.getHealth();

        player.updatePosition(2, 1); // Move to monster position
        roomHandler.handleRoom(monsterField, player);

        assertEquals(initialHealth - 2, player.getHealth(), "Player's health should decrease by 2.");
        assertEquals('T', map[2][1], "Monster should be replaced with a treasure.");
    }

    @Test
    void testHandleEmptyRoom() {
        char emptyField = MapFieldType.EMPTY.getSymbol();
        int initialScore = player.getScore();
        int initialHealth = player.getHealth();

        roomHandler.handleRoom(emptyField, player);

        assertEquals(initialScore, player.getScore(), "Player's score should not change in an empty room.");
        assertEquals(initialHealth, player.getHealth(), "Player's health should not change in an empty room.");
    }
}
