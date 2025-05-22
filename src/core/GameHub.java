package core;

import assets.PlayerStats;
import model.Player;
import ui.InputHandler;

public class GameHub {
    Player player;
    static Game[] games = {new Roulette("Roulette", 1500), new DiceRoll("DiceRoll", 2000)};


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
                break;
            }
            if (choice == games.length){
                choice = InputHandler.readChoices("Vyberte, co chcete vypsat: ", "Historie", "Statistiky her");
                //readchoices vracela input-1, takže při zadání 2 to bralo že uživatel zadal 1
                if (choice == 1){
                    PlayerStats.printStats(getPlayer());
                } else {
                    getPlayer().printHistory();
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
