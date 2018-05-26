package models;

import interfaces.ScoreService;

public class Score implements ScoreService {

    private int scoreGame;
    private int scoreSet;
    private static final int MAX_SET = 7;


    public Score(){
        this.init();
    }

    public Score(int scoreGame){
        this.scoreGame = scoreGame;
    }

    public int showScore() {
        return scoreGame;
    }

    public boolean score() {
        if( this.scoreGame >= 40 ) return false;

        if (this.scoreGame == 0 || this.scoreGame == 15) this.scoreGame += 15;
        else if (this.scoreGame == 30) this.scoreGame = 40;

        return true;
    }

    public void init() {
        this.scoreGame = 0;
    }


    public int getScoreSet() {
        return scoreSet;
    }

    public boolean setScoreSet(int scoreSet) {
        this.scoreSet = scoreSet;
        return scoreSet < MAX_SET;
    }

    @Override
    public boolean setScoreGame(int scoreGame) {
        this.scoreGame = scoreGame;
        return true;
    }

    @Override
    public void scoreTie() {
        scoreSet++;
    }
}
