import static org.junit.Assert.*;
import org.junit.Test;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(3);

    /** Test OffByN */
    @Test
    public void testOffByN() {
        assertTrue(offByN.equalChars('a', 'd'));
        assertFalse(offByN.equalChars('a', 'c'));
        assertTrue(offByN.equalChars('b', 'e'));
        assertFalse(offByN.equalChars('z', 'e'));
    }
}
