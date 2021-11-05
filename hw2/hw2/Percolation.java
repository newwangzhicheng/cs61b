package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;

public class Percolation {
    private int n;
    private int openCount;
    /** 0 represents full, 1 represents open */
    private int[][] sites;
    private WeightedQuickUnionUF uf;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must > 0");
        }
        this.n = n;
        openCount = 0;
        sites = new int[n][n];
        uf = new WeightedQuickUnionUF(n * n);
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (row < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException();
        }
        /** do nothing if sites[row][col] is already open */
        if (isOpen(row, col)) {
            return;
        }
        sites[row][col] = 1;
        openCount += 1;

        /** connect left, top, right, bottom if exists */
        connect(row, col, row - 1, col);
        connect(row, col, row, col - 1);
        connect(row, col, row, col + 1);
        connect(row, col, row + 1, col);
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (row < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException();
        }
        return sites[row][col] == 1;
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        if (row < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException();
        }
        return sites[row][col] == 0;
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return openCount;
    }

    /** does the system percolate? */
    public boolean percolates() {
        /** check all opened bottom site, bottom sites are [n^2-n, n^2-1] */
        for (int i = n * n - n; i < n * n; i++) {
            int bottomRow = getRow(i);
            int bottomCol = getCol(i);
            if (isOpen(bottomRow, bottomCol)) {
                for (int j = 0; j < n; j++) {
                    int topRow = getRow(j);
                    int topCol = getCol(j);
                    if (isOpen(topRow, topCol)) {
                        if (uf.connected(i, j)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /** connect two sets */
    private void connect(int rowA, int colA, int rowB, int colB) {
        if (rowB < 0 || rowB > n - 1 || colB < 0 || colB > n - 1 || isFull(rowB, colB)) {
            return;
        }
        int ithA = getI(rowA, colA);
        int ithB = getI(rowB, colB);
        uf.union(ithA, ithB);

    }

    /** get row from uf[i] */
    private int getRow(int i) {
        return i / n;
    }

    /** get col from uf[i] */
    private int getCol(int i) {
        return i % n;
    }

    /** get i from site[row][col] */
    private int getI(int row, int col) {
        return row * n + col;
    }

    /** for test */
    public static void main(String[] args) {
        Percolation pc = new Percolation(2);
        pc.open(0, 0);
        pc.open(1, 1);
        System.out.println(pc.percolates());
    }
}
