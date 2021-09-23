package synthesizer;

import java.util.Set;
import java.util.HashSet;

//Make sure this class is public
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final means the
     * values cannot be changed at runtime. We'll discuss this and other topics in
     * lecture on Friday.
     */
    private static final int SR = 44100; // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency. */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);

        /** Fill buffer with 0 */
        while (!buffer.isFull()) {
            buffer.enqueue(Double.valueOf(0));
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Dequeue everything in the buffer, and replace it with random numbers
        // between -0.5 and 0.5. You can get such a number by using:
        // double r = Math.random() - 0.5;
        //
        // Make sure that your random numbers are different from each other.

        /** the set store unique random numbers */
        Set<Double> randomNumber = new HashSet<>();
        while (randomNumber.size() < buffer.capacity()) {
            double r = Math.random() - 0.5;
            randomNumber.add(r);
        }

        /** Empty the buffer and fill with random number */
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        for (double r : randomNumber) {
            buffer.enqueue(r);
        }
    }

    /*
     * Advance the simulation one time step by performing one iteration of the
     * Karplus-Strong algorithm.
     */
    public void tic() {
        // Dequeue the front sample and enqueue a new sample that is
        // the average of the two multiplied by the DECAY factor.
        // Do not call StdAudio.play().
        double sampleOne = buffer.dequeue();
        double sampleTwo = buffer.peek();
        double newSample = DECAY * 0.5 * (sampleOne + sampleTwo);
        buffer.enqueue(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
