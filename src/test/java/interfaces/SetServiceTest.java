package interfaces;

import models.Player;
import models.Set;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class SetServiceTest {

    /**
     * Player "MOUAD" win 5 - 0 and win the game
     */
    @Test
    public void playerWinSet() {

        SetService setService =  Set.initSet("MOUAD","SBAII");
        assertEquals(0,setService.getPlayerOne().getScoreSet());
        assertTrue(setService.playerWinSet(0));
        assertEquals(1,setService.getPlayerOne().getScoreSet());
        assertTrue(setService.playerWinSet(0));
        assertEquals(2,setService.getPlayerOne().getScoreSet());
        assertTrue(setService.playerWinSet(0));
        assertEquals(3,setService.getPlayerOne().getScoreSet());
        assertTrue(setService.playerWinSet(0));
        assertEquals(4,setService.getPlayerOne().getScoreSet());
        assertTrue(setService.playerWinSet(0));
        assertEquals(5,setService.getPlayerOne().getScoreSet());
        assertFalse(setService.playerWinSet(0));
        assertEquals(6,setService.getPlayerOne().getScoreSet());

        Optional<Player> winner = setService.getWinner();
        assertTrue(winner.isPresent());
        assertEquals("MOUAD", winner.get().getPlayerName());
    }

    /**
     * Test the tie break
     */
    @Test
    public void testTieBreak() {
        SetService setService =  Set.initSet("MOUAD","SBAII");
        setService.playerWinSet(0);
        setService.playerWinSet(0);
        setService.playerWinSet(0);// 3-0
        setService.playerWinSet(1);
        setService.playerWinSet(1);
        setService.playerWinSet(1);
        setService.playerWinSet(1);
        setService.playerWinSet(1); // 3-5
        setService.playerWinSet(0);
        setService.playerWinSet(0);
        setService.playerWinSet(0); // 6-5
        setService.playerWinSet(1); // 6-6
        setService.playerWinSet(1); // 6-7
        assertFalse(setService.isHaveWinner());
        setService.playerWinSet(1); // 6-8
        assertTrue(setService.isHaveWinner());

    }
}