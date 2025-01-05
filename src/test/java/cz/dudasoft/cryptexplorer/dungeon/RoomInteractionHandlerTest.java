package cz.dudasoft.cryptexplorer.dungeon;

import cz.dudasoft.cryptexplorer.character.PlayerStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomInteractionHandlerTest {

    private RoomInteractionHandler roomHandler;
    private PlayerStats player;

    @BeforeEach
    void setUp() {
        roomHandler = new RoomInteractionHandler();
        player = new PlayerStats();
    }

    @Test
    void testHandleTreasure() {
        char treasureField = MapFieldType.TREASURE.getSymbol();
        int initialScore = player.getScore();

        roomHandler.handleRoom(treasureField, player);

        assertEquals(initialScore + 10, player.getScore(), "Player's score should increase by 10.");
    }

    @Test
    void testHandleMonster() {
        char monsterField = MapFieldType.MONSTER.getSymbol();
        int initialHealth = player.getHealth();

        roomHandler.handleRoom(monsterField, player);

        assertEquals(initialHealth - 2, player.getHealth(), "Player's health should decrease by 2.");
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
