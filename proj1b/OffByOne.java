public class OffByOne implements CharacterComparator {
    /** Judge equal chars offset by one */
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == 1 || y - x == 1;
    }
}
