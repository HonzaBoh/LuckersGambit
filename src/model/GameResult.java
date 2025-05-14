package model;

import core.Game;

public class GameResult {
    private Game game;
    private int winnings;
    private boolean status;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getWinnings() {
        return winnings;
    }

    public void setWinnings(int winnings) {
        this.winnings = winnings;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public GameResult(Game game, int winnings, boolean status) {
        this.game = game;
        this.winnings = winnings;
        this.status = status;
    }

    @Override
    public String toString() {
        return status ? "Game: " + game.getGameName()
                + "\n Vsazeno: " + game.getInputBet()
                + "\n Vyhrana castka: " + getWinnings()
                :
                "Game: " + game.getGameName()
                + "\n Vsazeno: " + game.getInputBet()
                + "\n Prohrano";
    }
}
