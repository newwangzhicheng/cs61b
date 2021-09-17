public class Palindrome {
    /** Decode word to characters in a list */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /** Check if the word is palindrome */
    public boolean isPalindrome(String word) {
        /*
         * // Iteration Deque<Character> wordDeque = wordToDeque(word); String reverse =
         * ""; while (wordDeque.size() != 0) { reverse += wordDeque.removeLast(); } if
         * (reverse.compareTo(word) == 0) { return true; } return false;
         */

        // Recursion
        if (word.length() <= 1) {
            return true;
        }

        char first = word.charAt(0);
        char last = word.charAt(word.length() - 1);
        if (first != last) {
            return false;
        }
        return isPalindrome(word.substring(1, word.length() - 1));
    }

    /** Check if the word is palindrome with a character comparator */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        // Recursion
        if (word.length() <= 1) {
            return true;
        }

        char first = word.charAt(0);
        char last = word.charAt(word.length() - 1);
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return isPalindrome(word.substring(1, word.length() - 1), cc);
    }
}
