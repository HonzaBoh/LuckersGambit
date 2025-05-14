package assets;

import java.util.Random;

public class RandomGen {
    private final static Random generalRandom = new Random();

    public static int getRandomInt(int min, int max){
        return generalRandom.nextInt(min, max + 1);
    }
}
