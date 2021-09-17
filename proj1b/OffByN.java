public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int n) {
        N = n;
    }

    /** Judge the offset of two characters */
    public boolean equalChars(char x, char y) {
        return x - y == N || y - x == N;
    }
}
