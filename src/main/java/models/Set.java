package models;

import interfaces.SetService;

public class Set extends Duel implements SetService {

    private boolean tieBreak;

    public Set(Player playerOne, Player playerTwo) {
        super(playerOne,playerTwo);
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
               tieBreak = true;

            }else if (winRuleOne(this.getPlayerOne(), this.getPlayerTwo())) {

                this.getPlayerOne().winSet();
                setHaveWinner(true);
                this.setWinner(this.getPlayerOne());

                return false;
            }

            return this.getPlayerOne().winSet();
        } else {

            if(tieBreak) return tieRule(this.getPlayerTwo(),this.getPlayerOne());

            if (this.getPlayerTwo().getScoreSet() == 5 && this.getPlayerOne().getScoreSet() == 5) {
                tieBreak = true;
            }else if (winRuleOne(this.getPlayerTwo(), this.getPlayerOne())) {
                this.getPlayerTwo().winSet();
                setHaveWinner(true);
                this.setWinner(this.getPlayerTwo());
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

        if(playerOne.getScoreSet() == playerTwo.getScoreSet()+1){
            playerOne.winSet();
            this.setWinner(playerOne);
            this.setHaveWinner(true);
            return false;
        }

        return playerOne.scoreTie();
    }


    @Override
    public String getCurrentScore() {
        return this.getPlayerOne().getPlayerName() + " : "+this.getPlayerOne().getScoreSet()+" - "
                +this.getPlayerTwo().getScoreSet()+ " : "+ this.getPlayerTwo().getPlayerName();
    }
}
