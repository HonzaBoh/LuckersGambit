package core;

import assets.RandomGen;
import model.GameResult;
import ui.InputHandler;

public class DiceRoll extends Game{
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

    @Override
    public GameResult startGame() {
        System.out.println("Priprava na roulette hru...");
        System.out.println("Vsazeno " + getInputBet());
        player.decreaseBalance(getInputBet());
        int winnings = getInputBet();
        int roll, position;
        position = 0;
        printTrack(position);
        int choice = InputHandler.readChoices("Zacatek DiceRoll, prejete si hodit?", "Ano", "Ne");
        while (choice != 1){
            System.out.println("Hod...");
            roll = RandomGen.getRandomInt(1, 6);
            System.out.println("Hozeno: " + roll);
            position += roll;
            printTrack(position);
            if (position > 7){
                System.out.println("DiceRoll jste prohral");
                return new GameResult(this, 0, getInputBet(), false);
            }
            choice = InputHandler.readChoices("Prejete si hodit znovu?", "Ano", "Ne");
        }

        if (position < 5){
            winnings *= 1.1;
        }else if (position < 7 && position > 4){
            winnings *= 1.4;
        }else {
            winnings *= 2;
        }

        System.out.println("Vyhral jste: " + winnings);
        player.increaseBalance(winnings);
        return new GameResult(this, winnings, getInputBet(), true);
    }
}
