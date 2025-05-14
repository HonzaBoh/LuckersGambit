package core;

import model.Player;
import ui.InputHandler;

public class GameHub {
    Player player;
    static Game[] games = {new Roulette("Roulette", 1500)};


    public Player getPlayer() {
        return player;
    }

    private String[] getGameNames() {
        String[] gameNames = new String[games.length + 1];//potreba pro exit
        for (int i = 0; i < games.length; i++) {
            gameNames[i] = games[i].getGameName();
        }
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
            if (choice == games.length) {
                break;
            }
            if (games[choice].loadPlayer(player)) {
                games[choice].startGame();
            }
        }
        System.out.println("Happy end");
    }

}
