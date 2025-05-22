package persistence;

import assets.Constants;
import model.Player;

import java.io.*;

public class SaveManager {


    public static boolean checkAssets() {
        File dir = new File(Constants.SAVE_DIR);
        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        return dir.mkdir();
    }

    public static boolean createSave(Player player) {
        if (checkAssets()) {
            //tmp: napis do textaku nick + hitorii
            //nick + _ + *pocet souboru + 1*
            String saveName = player.getNickName() + "_" + new File(Constants.SAVE_DIR).listFiles().length + 1 + ".txt";
            try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(saveName)))) {
                pw.println(player.getNickName());
                //TMP!!
                player.getHistory().forEach(gameResult -> {
                    pw.println("#####");
                    pw.println(gameResult.toString());
                    pw.println("#####");
                });
            } catch (IOException e) {
                System.out.println("chyba pri tvorbe save: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

}
