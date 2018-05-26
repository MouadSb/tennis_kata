package globaltest;

import interfaces.GameService;
import interfaces.SetService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import models.Game;
import models.Player;
import models.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class MainTest {

    private GameService gameService;
    private SetService setService;
    private Player playerOne; // MOUAD
    private Player playerTwo; // SBAII

    @Before
    public void init(){
        setService = Set.initSet("MOUAD","SBAII");
        playerOne = setService.getPlayerOne();
        playerTwo = setService.getPlayerTwo();
        gameService = new Game(playerOne, playerTwo);

    }

    /** SPRINT1 : manage a tennis GAME within a set of a tennis match **/

    /*
        As a tennis referee
        I want to manage the score of a game of a set of a tennis match between 2 players with simple Game rules
        In order to display the current Game score of each player

                        Rules details:
            •         The game starts with a score of 0 point for each player
            •         Each time a player win a point, the Game score changes as follow:
        0 -> 15 -> 30 -> 40-> Win game

         As a tennis referee
        I want to manage the specific of the rule DEUCE at the end of a Game
        In order to display the current Game score of each player

                        Rules details:
        •         If the 2 players reach the score 40, the DEUCE rule is activated
        •         If the score is DEUCE , the player who  win the point take the ADVANTAGE
        •         If the player who has the ADVANTAGE win the  point, he win the game
        •         If the player who has the ADVANTAGE looses the point, the score is DEUCE

     */



    @Test
    public void checkSprintOn(){
        assertEquals(gameService.getCurrentScore(), "MOUAD : 0 - 0 : SBAII");

        gameService.playerScore(0); // MOUAD : 15 - 0 : SBAII
        gameService.playerScore(0); // MOUAD : 30 - 0 : SBAII
        gameService.playerScore(0); // MOUAD : 40 - 0 : SBAII
        gameService.playerScore(1); // MOUAD : 40 - 15 : SBAII
        gameService.playerScore(1); // MOUAD : 40 - 30 : SBAII

        gameService.playerScore(1); // MOUAD : DEUCE - DEUCE : SBAII
        assertTrue(gameService.scoreIsDeuce()); // GAME DEUCE
        assertEquals(gameService.getCurrentScore(), "MOUAD : DEUCE - DEUCE : SBAII");

        gameService.playerScore(0); // MOUAD : ADVANTAGE - 40 : SBAII
        gameService.playerScore(1); // MOUAD : DEUCE - DEUCE : SBAII
        assertTrue(gameService.scoreIsDeuce());

        gameService.playerScore(1); // MOUAD : 40 - ADVANTAGE : SBAII
        assertEquals(gameService.getCurrentScore(), "MOUAD : 40 - ADVANTAGE : SBAII");

        assertFalse(gameService.getWinner().isPresent());
        gameService.playerScore(1);

        assertTrue(gameService.getWinner().isPresent()); // SBAII WIN THE GAME
        assertEquals(gameService.getWinner().get().getPlayerName(), playerTwo.getPlayerName());

    }


    /** SPRINT2 : manage a Tennis SET within a tennis match **/

    /*
        As a tennis referee
        I want to manage the score of a set of a tennis match between 2 players
        In order to display the current Game (SPRINT 1) & Set score of each player

                        Rules details:
        •         The set starts with a score of 0 Game for each player
        •         Each time a player win a Game (see SPRINT 1), the Set score changes as follow:
        1 -> 2 -> 3 -> 4 -> 5 -> 6 (-> 7)
        •         If a player reach the Set score of 6 and the other player has a Set score of 4 or lower,
         the player win the Set
        •         If a player wins a Game and reach the Set score of 6 and the other player has a Set score of 5,
         a new Game must be played and the first player who reach the score of 7 wins the match

        As a tennis referee
        I want to manage the specific of the rule of Tie-Break at the end of the Set
        In order to display the current Game, Set score & Tie-Break score of each player

                        Rules details:
        •         If the 2 players reach the score of 6 Games, the Tie-Break rule is activated
        •         Each time a player win a point, the score changes as follow:
        1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 (-> 8-> 9-> 10-> …)
        •         The Tie-Break ends as soon as a player gets a least 7 points and 2 points more than his opponent
        •         The player who wins the Tie-Break wins the Set and the match


     */

    @Test
    public void testWinMatch(){
        assertEquals(setService.getCurrentScore(), "MOUAD : 0 - 0 : SBAII");

        setService.playerWinSet(0);
        setService.playerWinSet(0);
        setService.playerWinSet(0);
        assertEquals(setService.getCurrentScore(), "MOUAD : 3 - 0 : SBAII");
        setService.playerWinSet(0);
        setService.playerWinSet(0);
        setService.playerWinSet(0);
        assertTrue(setService.getWinner().isPresent());
        assertEquals(setService.getWinner().get().getPlayerName(), "MOUAD");

    }

    @Test
    public void testWintMatchTieBreak(){
        assertEquals(setService.getCurrentScore(), "MOUAD : 0 - 0 : SBAII");
        setService.playerWinSet(0);
        setService.playerWinSet(0);
        setService.playerWinSet(0);
        setService.playerWinSet(1);
        setService.playerWinSet(1);
        setService.playerWinSet(1);
        assertEquals(setService.getCurrentScore(), "MOUAD : 3 - 3 : SBAII"); // MOUAD WIN THE FIRST SET
        setService.playerWinSet(1);
        setService.playerWinSet(1); // 3-5
        setService.playerWinSet(0);
        setService.playerWinSet(0); // 5-5
        setService.playerWinSet(1); // 5-6 Tiebreak
        assertFalse(setService.isHaveWinner());
        setService.playerWinSet(0); // 6-7
        setService.playerWinSet(1); // 6-7
        setService.playerWinSet(0); // 7-7
        setService.playerWinSet(1); // 7-8
        assertEquals(setService.getCurrentScore(), "MOUAD : 7 - 8 : SBAII");
        setService.playerWinSet(1); // 7-9

        assertTrue(setService.getWinner().isPresent());
        assertEquals(setService.getWinner().get().getPlayerName(), "SBAII");
    }
}
