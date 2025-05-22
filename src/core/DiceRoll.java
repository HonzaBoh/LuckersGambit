package core;

import assets.RandomGen;
import model.GameResult;
import ui.InputHandler;

public  class DiceRoll extends Game{
    public DiceRoll(String gameName, int minBet) {
        super(gameName, minBet);

    }

    @Override
    public GameResult startGame() {
        System.out.println("Priprava na dice hru...");
        System.out.println("Vsazeno " + getInputBet());
        player.decreaseBalance(getInputBet());
        int winnings = getInputBet();
        System.out.println("Hazime...");
        int position = RandomGen.getRandomInt(1, 6);
        int choice = InputHandler.readChoices("Kostka je na pozice " + position+ ". Chcete pokracovat?", "Ano", "Ne");
        while (choice != 1){
            System.out.println("Hazime...");
            position += RandomGen.getRandomInt(1, 6);
            System.out.println("Pozice " + position);
            printTrack(position);
            if (position < 8){
                choice = InputHandler.readChoices("Super. Prejete si risknout dalsi pokus?", "Ano", "Ne");
            } else {
                System.out.println("Dice jste prohral");
                winnings = 0;
                break;
            }

        }
        if (position <= 4 ){
            winnings*= 1.1;
        } else if (position <= 6) {
            winnings*= 1.4;
        } else if (position == 7) {
            winnings*= 2;
        }

        System.out.println("Vyhrali jste " + winnings);
        player.increaseBalance(winnings);
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
}
