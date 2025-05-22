package core;

import assets.RandomGen;
import model.GameResult;
import ui.InputHandler;

public class DiceRoll extends Game {
    private static final int FIELD_COUNT = 7;

    public DiceRoll(String gameName, int minBet) {
        super(gameName, minBet);
    }

    public static void printTrack(int position) {
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

    @Override
    public GameResult startGame() {
        System.out.println("Hazite kostkou, volte, zda chcete hodit ci ne");
        System.out.println("Pokud vyskocite mimo hraci pole, hra konci s nulovou vyhrou");
        int position = 0;
        int roll;
        int winnings = getInputBet();
        printTrack(position);
        int choice = InputHandler.readChoices("Prejete si hodit?", "Ano", "Ne");
        while (choice != 1) {
            System.out.println("jumping...");
            roll = RandomGen.getRandomInt(1,6);
            position += roll;
            System.out.println("Padlo: " + roll);
            printTrack(position);
            if (position <= FIELD_COUNT) {
                choice = InputHandler.readChoices("Prejete si risknout dalsi pokus?", "Ano", "Ne");
            } else {
                System.out.println("Skocil jste mimo pole jste prohral");
                return new GameResult(this, 0, getInputBet(), false);
            }
        }
        if (position <= 4) winnings *= 1.1;
        else if (position <= 6) winnings *= 1.4;
        else winnings *= 2;
        return new GameResult(this, winnings, getInputBet(), true);
    }
}
