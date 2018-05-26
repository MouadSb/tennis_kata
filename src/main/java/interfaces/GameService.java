package interfaces;

public interface GameService extends DuelService {

    boolean playerScore(int index);
    boolean scoreIsDeuce();
    void resetGame();
}
