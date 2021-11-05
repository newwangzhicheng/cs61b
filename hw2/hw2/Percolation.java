package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int top;
    private int bottom;
    private int openCount;
    /** 0 represents full, 1 represents open */
    private int[][] sites;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufWithoutBottom;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must > 0");
        }
        this.n = n;
        top = n * n;
        bottom = top + 1;
        openCount = 0;
        sites = new int[n][n];
        /** n^2 th is top virtual node, n^ + 1 th is bottom virtual node */
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufWithoutBottom = new WeightedQuickUnionUF(n * n + 1);

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
        /** connect first row node to top virtual node */
        /** connect last row node to bottom virtual node */
        int i = getI(row, col);
        if (row == 0) {
            uf.union(top, i);
            ufWithoutBottom.union(top, i);
        }
        if (row == n - 1) {
            uf.union(bottom, i);
        }

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
        int i = getI(row, col);
        return ufWithoutBottom.connected(top, i);
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return openCount;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    /** connect two sets */
    private void connect(int rowA, int colA, int rowB, int colB) {
        if (rowB < 0 || rowB > n - 1 || colB < 0 || colB > n - 1 || !isOpen(rowB, colB)) {
            return;
        }
        int ithA = getI(rowA, colA);
        int ithB = getI(rowB, colB);
        uf.union(ithA, ithB);
        ufWithoutBottom.union(ithA, ithB);

    }

    /** get i from site[row][col] */
    private int getI(int row, int col) {
        return row * n + col;
    }

    /** for test */
    public static void main(String[] args) {
        Percolation pc = new Percolation(3);
        System.out.println(pc.isFull(0, 0));
        pc.open(0, 0);
        pc.open(1, 0);
        pc.open(2, 0);
        pc.open(2, 2);
        System.out.println(pc.isFull(2, 2));
        System.out.println(pc.percolates());
    }
}
