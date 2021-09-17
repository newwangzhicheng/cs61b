import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    /** Test if the word is separated to characters */
    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    /** test isPalindrome */
    @Test
    public void testIsPalindrome() {
        /** palindrome without offset */
        boolean word0 = palindrome.isPalindrome("");
        boolean word1 = palindrome.isPalindrome("a");
        boolean word2more = palindrome.isPalindrome("racecar");
        boolean wordNot = palindrome.isPalindrome("this");
        assertTrue(word0);
        assertTrue(word1);
        assertTrue(word2more);
        assertFalse(wordNot);
    }

    /** test isPalindrome with offset by one */
    @Test
    public void testIsPalindromeWithOffByOne() {
        CharacterComparator offByOne = new OffByOne();
        boolean word0 = palindrome.isPalindrome("", offByOne);
        boolean word1 = palindrome.isPalindrome("a", offByOne);
        boolean word2more = palindrome.isPalindrome("racecar", offByOne);
        boolean wordT0 = palindrome.isPalindrome("this", offByOne);
        boolean wordT1 = palindrome.isPalindrome("aefb", offByOne);
        boolean wordT2 = palindrome.isPalindrome("eygzf", offByOne);
        assertTrue(word0);
        assertTrue(word1);
        assertFalse(word2more);
        assertTrue(wordT0);
        assertTrue(wordT1);
        assertTrue(wordT2);
    }

    /** test isPalindrome with offset by N */
    @Test
    public void testIsPalindromeWithOffByN() {
        CharacterComparator offByN = new OffByN(3);
        boolean word0 = palindrome.isPalindrome("", offByN);
        boolean word1 = palindrome.isPalindrome("a", offByN);
        boolean word2more = palindrome.isPalindrome("ad", offByN);
        boolean wordT0 = palindrome.isPalindrome("ampd", offByN);
        boolean wordT1 = palindrome.isPalindrome("amtpd", offByN);
        boolean wordT2 = palindrome.isPalindrome("eygzf", offByN);
        assertTrue(word0);
        assertTrue(word1);
        assertTrue(word2more);
        assertTrue(wordT0);
        assertTrue(wordT1);
        assertFalse(wordT2);
    }
}
