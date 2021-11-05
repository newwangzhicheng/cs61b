package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int[] count;
    private int N;
    private int T;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IndexOutOfBoundsException();
        }
        count = new int[T];
        this.N = N;
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
        return StdStats.mean(count) / (N * N);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        double[] meanCount = new double[T];
        for (int i = 0; i < T; i++) {
            meanCount[i] = (double) count[i] / (N * N);
        }
        return StdStats.stddev(meanCount);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(T);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev()) / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 10, pf);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());
    }
}
