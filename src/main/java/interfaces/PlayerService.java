package interfaces;

public interface PlayerService {

    String getPlayerName();
    int getPlayerScore();
    boolean winAPoint();
    void setScoreGame(int scoreGame);
}
