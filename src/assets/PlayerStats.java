package assets;

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
        System.out.println("Win rate: " + getWinRate(history));
    }

    public static long countWins(List<GameResult> history) {
        return history.stream().filter(GameResult::isWin).count();
    }

    public static long countLosses(List<GameResult> history) {
        return history.stream().filter(game -> !game.isWin()).count();
    }

    public static int totalWinnings(List<GameResult> history) {
        if (history.isEmpty()) {
            System.out.println("Nejsou odehrané žádné hry, výdělek je automaticky 0");
            return 0;
        }
        // vydelek - ztrata
        int earnings = history.stream().mapToInt(GameResult::getWinnings).sum();
        int loss = history.stream().mapToInt(game -> game.getGame().getInputBet()).sum();

        return earnings - loss;
    }

    public static String getWinRate(List<GameResult> history) {
        if (history.isEmpty()) {
            System.out.println("Nejsou odehrané žádné hry, nelze určit winRate");
            return "X";
        }
        long wins = history.stream().filter(GameResult::isWin).count();
        int percentage = (int)((double) wins / history.size());

        return percentage + " %";
    }
}
