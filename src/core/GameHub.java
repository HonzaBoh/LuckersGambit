package core;

import assets.PlayerStats;
import model.Player;
import persistence.SaveManager;
import ui.InputHandler;

public class GameHub {
    Player player;
    static Game[] games = {new Roulette("Roulette", 1500), new DiceRoll("Dice Roll", 2000)};


    public Player getPlayer() {
        return player;
    }

    private String[] getGameNames() {
        String[] gameNames = new String[games.length + 2];//potreba pro exit
        for (int i = 0; i < games.length; i++) {
            gameNames[i] = games[i].getGameName();
        }
        gameNames[gameNames.length - 2] = "historie a statistiky";
        gameNames[gameNames.length - 1] = "exit";
        return gameNames;
    }

    public GameHub(Player player) {
        this.player = player;
    }

    public void hubLoop() {
        int choice;
        System.out.println("Game hub sekce");
        while (true) {
            choice = InputHandler.readChoices("Zvol hru, kterou chces hrat", getGameNames());
            if (choice == games.length + 1) {
                choice = InputHandler.readChoices("Chces hru ulozit?", "Ano", "Ne");
                if (choice == 0){
                    if (!SaveManager.createSave(player)){
                        System.out.println("Nepodarilo se vytvorit save, ukoncuji...");
                    }
                }
                break;
            }
            if (choice == games.length){
                choice = InputHandler.readChoices("Vyberte, co chcete vypsat: ", "Historie", "Statistiky her");
                if (choice == 1){
                    getPlayer().printHistory();
                } else {
                    PlayerStats.printStats(getPlayer());
                }
                continue;
            }
            if (games[choice].loadPlayer(player)) {
                getPlayer().insertRecord(games[choice].startGame());
            }
        }
        System.out.println("Happy end");
    }

}
