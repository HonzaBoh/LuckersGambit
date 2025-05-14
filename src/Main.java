import core.GameHub;
import model.Player;
import ui.InputHandler;

/**
 * Hlavni spousteci trida pro program
 */
public class Main {

    static void initialize(){
        String prompt = "Zacatek aplikace, prejete si zacit novou hru, nebo navazat na jiz existujici?";
        String newGame = "Zacatek nove hry";
        String loadGame = "Nacteni hry (TO-DO)";
        String end = "Konec";
        System.out.println("LuckersGamibit App:");
        int choice = InputHandler.readChoices(prompt, newGame, loadGame, end);
        if (choice == 0){
            GameHub hub = new GameHub(Player.createPlayer());
            hub.hubLoop();
        } else if (choice == 1) {
            System.out.println("Not yet implemented :(");
            // TODO: 30.04.2025 LOAD mechinismus
        } else {
            System.out.println("Ukoncuji...");
        }
    }

    public static void main(String[] args) {
        // TODO: 30.04.2025 Scanner input text -> cislo a vice versa 
        
        initialize();
    }
}
