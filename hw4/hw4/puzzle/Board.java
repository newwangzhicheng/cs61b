package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Board implements WorldState {

    private final static int BLANK = 0;
    private int[][] tiles;

    public Board(int[][] t) {
        int N = t.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = t[i][j];
            }
        }
    }

    public int tileAt(int i, int j) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size() || j < 0 || j >= size()) {
            throw new IndexOutOfBoundsException("i or j should greater than or equals to 0 and less than N");
        }
        return tiles[i][j];
    }

    public int size() {
        return tiles.length;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        List<WorldState> neighborsList = new ArrayList<>();
        int i = 0, j = 0;
        for (int x = 0; x < size(); x++) {
            for (int y = 0; y < size(); y++) {
                if (tileAt(x, y) == BLANK) {
                    i = x;
                    j = y;
                    break;
                }
            }
        }

        // 第一次swap交换数据，第二次swap恢复数据
        if (validIndex(i - 1, j)) {
            swap(tiles, i - 1, j, i, j);
            neighborsList.add(new Board(tiles));
            swap(tiles, i - 1, j, i, j);
        }
        if (validIndex(i + 1, j)) {
            swap(tiles, i + 1, j, i, j);
            neighborsList.add(new Board(tiles));
            swap(tiles, i + 1, j, i, j);
        }
        if (validIndex(i, j - 1)) {
            swap(tiles, i, j - 1, i, j);
            neighborsList.add(new Board(tiles));
            swap(tiles, i, j - 1, i, j);
        }
        if (validIndex(i, j + 1)) {
            swap(tiles, i, j + 1, i, j);
            neighborsList.add(new Board(tiles));
            swap(tiles, i, j + 1, i, j);
        }
        return neighborsList;
    }

    public int hamming() {
        int distance = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int tileValue = tiles[i][j];
                if (tileValue != BLANK && tileValue != correctAt(i, j)) {
                    distance += 1;
                }
            }
        }
        return distance;
    }

    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int tileValue = tiles[i][j];
                if (tileValue == BLANK) {
                    continue;
                }
                int correctX = correctToX(tileValue);
                int correctY = correctToY(tileValue);
                distance += Math.abs(i - correctX) + Math.abs(j - correctY);
            }
        }
        return distance;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != getClass()) {
            return false;
        }
        Board w = (Board) y;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (w.tileAt(i, j) != tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    private boolean validIndex(int i, int j) {
        if (i < 0 || i >= size() || j < 0 || j >= size()) {
            return false;
        }
        return true;
    }

    private void swap(int[][] t, int i1, int j1, int i2, int j2) {
        int temp = t[i1][j1];
        t[i1][j1] = t[i2][j2];
        t[i2][j2] = temp;
    }

    private int correctAt(int i, int j) {
        if (i == size() - 1 && j == size() - 1) {
            return 0;
        }
        return i * size() + j + 1;
    }

    private int correctToX(int x) {
        return (x - 1) / size();
    }

    private int correctToY(int x) {
        return (x - 1) % size();
    }

}
