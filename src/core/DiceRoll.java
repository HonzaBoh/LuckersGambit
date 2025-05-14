package core;

import assets.RandomGen;
import model.GameResult;
import ui.InputHandler;

import java.util.Random;

public class DiceRoll extends Game {
    public DiceRoll(String gameName, int minBet) {
        super(gameName, minBet);
    }

    public static void printTrack(int position) {
        final int FIELD_COUNT = 7;
        StringBuilder output = new StringBuilder();

        if (position == 0) {
            output.append("● -> "); // pred hracim polem
        }

        for (int i = 1; i <= FIELD_COUNT; i++) {
            if (position == i) {
                output.append("[ ● ]");
            } else {
                output.append(String.format("[ %d ]", i));
            }
        }

        if (position > FIELD_COUNT) {
            output.append(" -> ●"); // jiz za hracim polem
        }

        System.out.println(output);
    }


    public static void main(String[] args) {
        printTrack(0);
        printTrack(2);
        printTrack(3);
        printTrack(6);
        printTrack(7);
        printTrack(8);

    }

    @Override
    public GameResult startGame() {
        System.out.println("Priprava na dice roll hru...");
        System.out.println("Vsazeno " + getInputBet());
        player.decreaseBalance(getInputBet());
        int position = 0;
        int winnings = getInputBet();

        printTrack(0);
        int choice = InputHandler.readChoices("Zacatek dice rollu, prejete si risknout?", "Ano", "Ne");
        while (choice != 1) {
            int diceValue = RandomGen.getRandomInt(1, 6);
            position += diceValue;
            System.out.println("Na kostce: " + diceValue);

            printTrack(position);
            if (position <= 4){
                winnings *= 1.1;
            } else if (position <= 6) {
                winnings *= 1.4;
            } else if (position == 7) {
                winnings *= 2;
            }
            else {
                System.out.println("Dice roll jste prohral");
                return new GameResult(this, 0, false);
            }
            choice = InputHandler.readChoices("Chcete pokracovat v hazeni?", "Ano", "Ne");

        }
        System.out.println("Vysledek hry: " + winnings);
        player.increaseBalance(winnings);
        return new GameResult(this, winnings, true);
    }
}
