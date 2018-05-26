package interfaces;

import models.Score;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreServiceTest {

    /**
     * Player win a points and checking the score
     */
    @Test
    public void showScore() {
        ScoreService scoreService = new Score();
        assertEquals(0, scoreService.showScore());
        assertTrue(scoreService.score());
        assertEquals(15, scoreService.showScore());
        assertTrue(scoreService.score());
        assertEquals(30, scoreService.showScore());
        assertTrue(scoreService.score());
        assertEquals(40, scoreService.showScore());
        assertFalse(scoreService.score());
    }

    /**
     * Win a point
     */
    @Test
    public void score() {
        ScoreService scoreService = new Score();
        assertTrue(scoreService.score());
    }

    /**
     * Reset score
     */
    @Test
    public void init() {
        ScoreService scoreService = new Score();
        assertTrue(scoreService.score());
        assertEquals(15, scoreService.showScore());
        scoreService.init();
        assertEquals(0, scoreService.showScore());

    }
}