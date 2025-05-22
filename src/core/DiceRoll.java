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
        int position = 0;
        int rollNumber;
        printTrack(position);
        int choice = InputHandler.readChoices("Zacatek hodu kostkou, prejete si házet?", "Ano", "Ne");
        while (choice != 1) {
            System.out.println("DiceRoll");
            rollNumber = RandomGen.getRandomInt(1, 6);
            position += rollNumber;
            printTrack(position);
            if (position <= 7) {
                choice = InputHandler.readChoices("Nepřestřelil jste, přejete si znovu házet?", "Ano", "Ne");
            } else {
                System.out.println("Přestřelil jste");
                return new GameResult(this, 0, getInputBet(), false);
            }
        }
        if (position >= 1 && position <= 4) {
            winnings *= 1.1;
        } else if (position >= 5 && position <= 6) {
            winnings *= 1.4;
        } else {
            winnings *= 2;
        }
        player.increaseBalance(winnings);
        System.out.println("Vyhrál jste: "+winnings);
        return new GameResult(this, winnings, getInputBet(), true);
    }



}
