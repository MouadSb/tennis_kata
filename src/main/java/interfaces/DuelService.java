package interfaces;

import models.Player;

import java.util.Optional;

public interface DuelService {

    Player getPlayerOne();
    Player getPlayerTwo();
    Optional<Player> getWinner();
    String getCurrentScore();

}
