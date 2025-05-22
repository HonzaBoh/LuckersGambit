package model;

import assets.Constants;
import ui.InputHandler;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final String nickName;
    private int balance;
    private final List<GameResult> history;

    public Player(String nickName, int balance) {
        this.nickName = nickName;
        this.balance = balance;
        history = new ArrayList<>();
    }

    public void insertRecord(GameResult newRecord){
        history.add(newRecord);
    }

    public void printHistory(){
       if (history.isEmpty()){
           System.out.println("Zatim nebyla odehrana zadna hra");
       } else {
           history.forEach(gameResult -> {
               System.out.println("#####");
               System.out.println(gameResult.toString());
               System.out.println("#####");
           });
       }
    }

    public static Player createPlayer(){
        return new Player(InputHandler.readText("Zadejte uzivatelske jmeno"), Constants.DEFAULT_START_BALANCE);
    }

    public List<GameResult> getHistory() {
        return history;
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
