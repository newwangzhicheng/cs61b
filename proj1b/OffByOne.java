public class OffByOne implements CharacterComparator {
    /** Judge equal chars offset by one */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
