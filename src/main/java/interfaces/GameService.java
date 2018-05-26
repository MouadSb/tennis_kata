package interfaces;

import models.Player;

import java.util.Optional;

public interface GameService {
    boolean playerScore(int index);
    Player getPlayerOne();
    Player getPlayerTwo();
    Optional<Player> getWinner();
    String getCurrentScore();
    boolean scoreIsDeuce();
    void resetGame();
}
