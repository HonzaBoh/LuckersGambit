package core;

import assets.RandomGen;
import ui.InputHandler;

public class Roulette extends Game {

    private final double betMultiplyer = 1.6;
    public Roulette(String gameName, int minBet) {
        super(gameName, minBet);
    }
    
    @Override
    public void startGame() {
        System.out.println("Priprava na roulette hru...");
        System.out.println("Vsazeno " + getInputBet());
        player.decreaseBalance(getInputBet());
        int winnings = getInputBet();
        int deathNumber = RandomGen.getRandomInt(1, 6);
        System.out.println("CHEAT: " + deathNumber);
        int counter = 0;
        int choice = InputHandler.readChoices("Zacatek rulety, prejete si risknout?", "Ano", "Ne");
        while (choice != 1){
            System.out.println("Ruleta...");
            counter++;
            if (counter != deathNumber){
                winnings *= betMultiplyer;
                choice = InputHandler.readChoices("Prezil jste. Prejete si risknout dalsi pokus?", "Ano", "Ne");
            } else {
                System.out.println("Ruletu jste prohral");
                winnings = 0;
                break;
            }
        }
        System.out.println("Vysledek hry: " + winnings);
        player.increaseBalance(winnings);
    }
}
