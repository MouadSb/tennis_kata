package interfaces;

import models.Game;
import models.Player;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameServiceTest {

    /**
     *  First player score then the second player score too
     */

    @Test
    public void playerScore() {
        GameService gameService = Game.initGame("MOUAD","SBAII");

        gameService.playerScore(0); // PlayerOne score

        assertEquals(15, gameService.getPlayerOne().getPlayerScore());
        assertEquals(0, gameService.getPlayerTwo().getPlayerScore());

        gameService.playerScore(1); // PlayerTwo score
        assertEquals(15, gameService.getPlayerOne().getPlayerScore());
        assertEquals(15, gameService.getPlayerTwo().getPlayerScore());
    }

    /**
     * PlayerOne score four times and PlayerTwo score one time
     * PlayerOne is the winner
     */

    @Test
    public void getWinner() {

        GameService gameService = Game.initGame("MOUAD","SBAII");

        gameService.playerScore(0);
        gameService.playerScore(0);
        gameService.playerScore(0);
        gameService.playerScore(1);
        gameService.playerScore(1);
        gameService.playerScore(1);
        gameService.playerScore(1);
        gameService.playerScore(0);
        gameService.playerScore(1);
        gameService.playerScore(1);


        Optional<Player> winningPlayer = gameService.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("SBAII", winningPlayer.get().getPlayerName());
    }
}