package interfaces;

import models.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerServiceTest {

    /**
     * Get player name
     */
    @Test
    public void getPlayerName() {
        PlayerService playerService = new Player("MOUAD");
        assertEquals("MOUAD",playerService.getPlayerName());
    }

    /**
     * Get player score
     */
    @Test
    public void getPlayerScore() {
        PlayerService playerService = new Player("MOUAD");
        assertEquals(0,playerService.getPlayerScore());
    }

    /**
     * Player win points and checking the score
     */

    @Test
    public void winAPoint() {
        PlayerService playerService = new Player("MOUAD");
        assertTrue(playerService.winAPoint());
        assertEquals(15, playerService.getPlayerScore());
        assertTrue(playerService.winAPoint());
        assertEquals(30, playerService.getPlayerScore());

    }
}