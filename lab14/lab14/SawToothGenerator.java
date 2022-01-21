package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private double frequency;
    private int state;

    public SawToothGenerator(double frequency) {
        state = 0;
        this.frequency = frequency;
    }

    @Override
    public double next() {
        state += 1;
        double period = StdAudio.SAMPLE_RATE / frequency;
        return -1 + 2 * state % period / period;
    }

}
