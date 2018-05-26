package models;

import interfaces.GameService;
import java.util.Optional;

public class Game implements GameService {

    private final Player playerOne;
    private final Player playerTwo;
    private Player winner;
    private static final int MAX_SCORE = 40;
    private boolean haveWinner = false;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public static Game initGame(String namePlayerOne, String namePlayerTwo){
        Player playerOne = new Player(namePlayerOne);
        Player playerTwo = new Player(namePlayerTwo);

        return new Game(playerOne,playerTwo);
    }

    public boolean playerScore(int index){

        if(haveWinner) return false;

        if(index == 0) {
            if (this.getPlayerTwo().isHasAdvantage()) {
                this.getPlayerTwo().setHasAdvantage(false);
                return true;
            }

            if(scoreIsDeuce()){
                this.getPlayerOne().setHasAdvantage(true);
                this.getPlayerTwo().setHasAdvantage(false);
                return true;
            }

            if(this.getPlayerOne().getPlayerScore() == MAX_SCORE || this.getPlayerOne().isHasAdvantage()){
                setWinner(this.getPlayerOne());
                this.haveWinner = true;
                this.getPlayerOne().winSet();
            }

            return this.getPlayerOne().winAPoint();
        }
        else {

            if (this.getPlayerOne().isHasAdvantage()) {
                this.getPlayerOne().setHasAdvantage(false);
                return true;
            }

            if(scoreIsDeuce()){
                this.getPlayerTwo().setHasAdvantage(true);
                this.getPlayerOne().setHasAdvantage(false);
                return true;
            }

            if(this.getPlayerTwo().getPlayerScore() == MAX_SCORE || this.getPlayerTwo().isHasAdvantage()){
                setWinner(this.getPlayerTwo());
                this.haveWinner = true;
                this.getPlayerTwo().winSet();
            }
            return this.getPlayerTwo().winAPoint();
        }
    }


    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Optional<Player> getWinner() {
        if(winner == null) return Optional.empty();
        return Optional.of(winner);
    }


    private void setWinner(Player winner) {
        this.winner = winner;
    }


    public boolean scoreIsDeuce() {
        return (!this.getPlayerOne().isHasAdvantage() && !this.getPlayerTwo().isHasAdvantage()) &&
                (this.getPlayerOne().getPlayerScore() == MAX_SCORE && this.getPlayerTwo().getPlayerScore() == MAX_SCORE);
    }

    @Override
    public String getCurrentScore() {

        if(this.scoreIsDeuce()){
            return this.getPlayerOne().getPlayerName()+ " : DEUCE - DEUCE : " + this.getPlayerTwo().getPlayerName();
        }

        if(playerOne.isHasAdvantage()){
            return this.getPlayerOne().getPlayerName()+ " : ADVANTAGE " +" - "+ this.getPlayerTwo().getPlayerScore()
                    +" : "+ this.getPlayerTwo().getPlayerName();

        }

        if(playerTwo.isHasAdvantage()){
            return this.getPlayerOne().getPlayerName()+ " : " + this.getPlayerOne().getPlayerScore()
                    +" - ADVANTAGE : " + this.getPlayerTwo().getPlayerName();

        }

        return this.getPlayerOne().getPlayerName()+ " : " + this.getPlayerOne().getPlayerScore()
                +" - "+ this.getPlayerTwo().getPlayerScore()+" : "+ this.getPlayerTwo().getPlayerName();
    }

    public void resetGame(){
        this.getPlayerOne().setScoreGame(0);
        this.getPlayerTwo().setScoreGame(0);
        this.haveWinner = false;
        winner = null;
    }


}