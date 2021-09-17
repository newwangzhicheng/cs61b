import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

    /** Test equalChars */
    @Test
    public void testEqualChars() {
        boolean expTrue0 = offByOne.equalChars('a', 'B');
        boolean expTrue1 = offByOne.equalChars('a', 'b');
        boolean expTrue2 = offByOne.equalChars('b', 'a');
        boolean expTrue3 = offByOne.equalChars('&', '%');
        boolean expFalse1 = offByOne.equalChars('r', 'z');
        boolean expFalse2 = offByOne.equalChars('-', 't');
        boolean expFalse3 = offByOne.equalChars('o', '6');

        assertFalse(expTrue0);
        assertTrue(expTrue1);
        assertTrue(expTrue2);
        assertTrue(expTrue3);
        assertFalse(expFalse1);
        assertFalse(expFalse2);
        assertFalse(expFalse3);
    }
}
