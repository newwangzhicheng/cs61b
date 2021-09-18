import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /** Test array deque */
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        while (true) {
            int i = StdRandom.uniform(99999);
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne >= 0 && numberBetweenZeroAndOne < 0.25) {
                sad.addLast(i);
                solution.addLast(i);
                System.out.println("addLast(" + i + ")");
            } else if (numberBetweenZeroAndOne >= 0.25 && numberBetweenZeroAndOne < 0.5) {
                sad.addFirst(i);
                solution.addFirst(i);
                System.out.println("addFirst(" + i + ")");
            } else if (numberBetweenZeroAndOne >= 0.5 && numberBetweenZeroAndOne < 0.75) {
                Integer actual = sad.removeFirst();
                Integer expected = solution.removeFirst();
                assertEquals("removeFirst(), student was " + actual + ", correct was " + expected, expected, actual);
                System.out.println("removeFirst()");
            } else if (numberBetweenZeroAndOne >= 0.75 && numberBetweenZeroAndOne < 1) {
                Integer actual = sad.removeLast();
                Integer expected = solution.removeLast();
                assertEquals("removeFirst(), student was " + actual + ", correct was " + expected, expected, actual);
                System.out.println("removeLast()");
            }

            /** Check data */
            // for (int j = 0; j < solution.size(); j++) {
            // assertEquals(solution.get(j), sad.get(j));
            // }
        }
    }
}