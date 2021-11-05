package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int[] count;
    private int T;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        count = new int[T];
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int ith = StdRandom.uniform(N * N);
                int row = ith / N;
                int col = ith % N;
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    count[i]++;
                }
            }
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(count);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(count);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(T);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(T);
    }
}
