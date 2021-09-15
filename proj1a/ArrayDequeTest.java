public class ArrayDequeTest {
    public static void main(String[] args) {
        addRemoveTest();
        getTest();
    }

    public static void getTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);
        int[] expected = { 5, 4, 3, 2, 1, 6, 7, 8, 9, 10 };
        deque.printDeque();
        System.out.println();
        for (int i = 0; i < deque.size(); i++) {
            if (expected[i] != deque.get(i)) {
                System.out.println(
                        "Test Failed in position " + i + ", expected " + expected[i] + 
                        " but got " + deque.get(i));
            }

        }
    }

    public static void addRemoveTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();

        deque.printDeque();
        System.out.println();
        int[] expected = { 2, 1, 6, 7, 8, 9 };
        int i;
        for (i = 0; i < expected.length; i++) {
            if (deque.get(i) != expected[i]) {
                System.out.println(
                        "Test Failed in position " + i + ", expected " + expected[i] + 
                        " but got " + deque.get(i));
                break;
            }
        }
    }
}
