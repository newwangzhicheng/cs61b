package byog.Core;

import java.util.Random;

public class LimitedRandom {
    public static int pareto(Random random, int min, int max) {
        int num = (int) RandomUtils.pareto(random, 1) + min;
        while (num < max) {
            num = (int) RandomUtils.pareto(random, 1) + min;
        }
        return num;
    }
}
