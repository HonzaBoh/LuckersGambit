package model;

import assets.Constants;
import ui.InputHandler;

public class Player {
    private final String nickName;
    private int balance;

    public Player(String nickName, int balance) {
        this.nickName = nickName;
        this.balance = balance;
    }

    public static Player createPlayer(){
        return new Player(InputHandler.readText("Zadejte uzivatelske jmeno"), Constants.DEFAULT_START_BALANCE);
    }

    public String getNickName() {
        return nickName;
    }

    public int getBalance() {
        return balance;
    }

    public boolean increaseBalance(int toAdd){
        if (balance + toAdd < 0 || toAdd < 0){
            return false;
        }
        balance += toAdd;
        return true;
    }

    public boolean decreaseBalance(int toRemove){
        if (toRemove > balance){
            return false;
        }
        balance -= toRemove;
        return true;
    }
}
