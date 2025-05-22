package assets;

import core.Game;
import model.GameResult;
import model.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerStats {

    public static void printStats(Player player) {
        List<GameResult> history = player.getHistory();

        System.out.println("=== Player Statistics ===");
        System.out.println("Hráč: " + player.getNickName());
        System.out.println("Současný stav: " + player.getBalance());
        System.out.println("Total Games Played: " + history.size());
        System.out.println("Počet vyhraných her: " + countWins(history));
        System.out.println("Prohry: " + countLosses(history));
        System.out.println("Zisk/ztráta: " + totalWinnings(history));
        System.out.println("Win rate " + getWinRate(history));
    }

    public static long countWins(List<GameResult> history) {
        if (history.isEmpty()){
            return 0;
        }
        return history.stream().filter(GameResult::isWin).count();
    }

    public static long countLosses(List<GameResult> history) {
        if (history.isEmpty()){
            return 0;
        }
        return (history.size() - history.stream().filter(GameResult::isWin).count());
    }

    public static int totalWinnings(List<GameResult> history) {
        if (history.isEmpty()){
            return 0;
        }
        int balance = 0;
        for(GameResult gr:history){
            if (gr.isWin()){
                balance += gr.getWinnings();
            }
            else{
                balance -= gr.getBet();
            }
        }
        return balance;
    }

    public static String getWinRate(List<GameResult> history) {
        if (history.isEmpty()){
            return 0 + "%";
        }
        double winrate = (double) countWins(history) /history.size();
        return (winrate*100) + "%";
    }
}
