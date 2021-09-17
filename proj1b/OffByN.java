public class OffByN implements CharacterComparator {
    int N;

    public OffByN(int n) {
        N = n;
    }

    /** Judge the offset of two characters */
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == N;
    }
}
