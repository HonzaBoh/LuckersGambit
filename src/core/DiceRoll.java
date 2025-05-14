package core;

import assets.RandomGen;
import model.GameResult;
import ui.InputHandler;

public class DiceRoll extends Game {
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
        int position = 0;
        int winnings = getInputBet();
        printTrack(position);
        while (choice != 1){
            printTrack(position);
            } else {
            }
        }
    }
}
