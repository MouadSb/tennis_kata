package models;

import interfaces.SetService;

import java.util.Optional;

public class Set implements SetService {

    private final Player playerOne;
    private final Player playerTwo;
    private Player winner;
    private boolean tieBreak;
    private boolean haveWinner = false;


    public Set(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public static Set initSet(String namePlayerOne, String namePlayerTwo){
        Player playerOne = new Player(namePlayerOne);
        Player playerTwo = new Player(namePlayerTwo);

        return new Set(playerOne,playerTwo);
    }

    @Override
    public boolean playerWinSet(int index) {

        if(isHaveWinner()) return false;

        if(index == 0) {
            if(tieBreak) return tieRule(this.getPlayerOne(),this.getPlayerTwo());

            if (this.getPlayerOne().getScoreSet() == 5 && this.getPlayerTwo().getScoreSet() == 5) {
               System.out.println("----");
               tieBreak = true;

            }else if (winRuleOne(this.getPlayerOne(), this.getPlayerTwo())) {

                this.getPlayerOne().winSet();
                haveWinner = true;
                this.winner = this.getPlayerOne();

                return false;
            }

            return this.getPlayerOne().winSet();
        } else {

            if(tieBreak) return tieRule(this.getPlayerTwo(),this.getPlayerOne());

            if (this.getPlayerTwo().getScoreSet() == 5 && this.getPlayerOne().getScoreSet() == 5) {
                System.out.println("----***");
                tieBreak = true;
            }else if (winRuleOne(this.getPlayerTwo(), this.getPlayerOne())) {
                this.getPlayerTwo().winSet();
                haveWinner = true;
                this.winner = this.getPlayerTwo();
                return false;
            }
            return  this.getPlayerTwo().winSet();
        }

    }

    private static boolean winRuleOne(Player playerOne, Player playerTwo){

        return playerTwo.getScoreSet() <= 4 && playerOne.getScoreSet() == 5;
    }

    private boolean tieRule(Player playerOne,
                                        Player playerTwo){

        System.out.println("here");
        if(playerOne.getScoreSet() == playerTwo.getScoreSet()+1){
            playerOne.winSet();
            this.winner = playerOne;
            this.haveWinner = true;
            return false;
        }

        return playerOne.scoreTie();
    }

    @Override
    public Player getPlayerOne() {
        return this.playerOne;
    }

    @Override
    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    @Override
    public Optional<Player> getWinner() {
        if(this.winner == null) return Optional.empty();
        return Optional.of(this.winner);
    }

    public boolean isHaveWinner() {
        return haveWinner;
    }

    @Override
    public String getCurrentScore() {
        return this.getPlayerOne().getPlayerName() + " : "+this.getPlayerOne().getScoreSet()+" - "
                +this.getPlayerTwo().getScoreSet()+ " : "+ this.getPlayerTwo().getPlayerName();
    }
}
