package models;

import java.util.Optional;

abstract class Duel {

    private final Player playerOne;
    private final Player playerTwo;
    private boolean haveWinner = false;
    private Player winner;

    Duel(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }


    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public boolean isHaveWinner() {
        return haveWinner;
    }

    public void setHaveWinner(boolean haveWinner) {
        this.haveWinner = haveWinner;
    }

    public Optional<Player> getWinner() {
        if(this.winner == null) return Optional.empty();
        return Optional.of(this.winner);
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
