package interfaces;

import models.Player;

import java.util.Optional;

public interface SetService {
    boolean playerWinSet(int index);
    Player getPlayerOne();
    Player getPlayerTwo();
    Optional<Player> getWinner();
    boolean isHaveWinner();
    String getCurrentScore();

}
