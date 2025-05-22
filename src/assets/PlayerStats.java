package assets;

import core.Game;
import model.GameResult;
import model.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerStats {

    public static void printStats(Player player) {
        List<GameResult> history;
        if ((history = player.getHistory()).isEmpty()) {
            System.out.println("Zatim nebyla odehrana zadna hra");
            return;
        }

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
        return history.stream()
                .filter(result -> !result.isWin())
                .count();
    }

    public static int totalWinnings(List<GameResult> history) {
        int winnings = history.stream()
                .filter(GameResult::isWin)
                .mapToInt(result -> result.getWinnings() - result.getBet())
                .sum();
        int losses = history.stream()
                .filter(result -> !result.isWin())
                .mapToInt(GameResult::getBet)
                .sum();
        return winnings - losses;
    }

    public static String getWinRate(List<GameResult> history) {
        double average = history.stream()
                .mapToDouble(result -> result.isWin() ? 1.0 : 0.0)
                .average()
                .orElse(Double.NaN);
        return (average * 100) + " %";
    }
}
