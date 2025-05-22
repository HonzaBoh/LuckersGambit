package assets;

import model.GameResult;
import model.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerStats {

    public static void printStats(Player player) {
        List<GameResult> history = player.getHistory();
        if (!history.isEmpty()) {
            System.out.println("=== Player Statistics ===");
            System.out.println("Hráč: " + player.getNickName());
            System.out.println("Současný stav: " + player.getBalance());
            System.out.println("Total Games Played: " + history.size());
            System.out.println("Počet vyhraných her: " + countWins(history));
            System.out.println("Prohry: " + countLosses(history));
            System.out.println("Zisk/ztráta " + totalWinnings(history));
            System.out.println("Win rate " + getWinRate(history));
        }
        else {
            System.out.println("Není žádná historie hre");
        }
    }

    public static long countWins(List<GameResult> history) {
        return history.stream().filter(GameResult::isWin).count();
    }

    public static long countLosses(List<GameResult> history) {
        return history.stream()
                .filter(win -> !(win.isWin())).count();
    }

    public static int totalWinnings(List<GameResult> history) {
        int lose = history.stream().filter(win -> !(win.isWin())).mapToInt(GameResult::getWinnings).sum();
        int win = history.stream().filter(GameResult::isWin).mapToInt(GameResult::getWinnings).sum();
        return win - lose;
    }

    public static String getWinRate(List<GameResult> history) {
        return (( (double) (countWins(history)) / history.size()) * 100) + "x";
    }
}
