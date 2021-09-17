public class LinkedListDeque<T> implements Deque<T> {
    /** Double linked node */
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node() {
            item = null;
            prev = null;
            next = null;
        }

        public Node(T x) {
            item = x;
            prev = null;
            next = null;
        }
    }

    private Node sentinel;
    private int size;

    /** Create an empty list */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Add item to the sentinel.next with constant time */
    @Override
    public void addFirst(T x) {
        size++;
        Node first = new Node(x);
        first.next = sentinel.next;
        sentinel.next.prev = first;
        sentinel.next = first;
        first.prev = sentinel;
    }

    /** Add item to the sentinel.prev with constant time */
    @Override
    public void addLast(T x) {
        size++;
        Node last = new Node(x);
        last.prev = sentinel.prev;
        sentinel.prev.next = last;
        sentinel.prev = last;
        last.next = sentinel;
    }

    /** Judge if the list is empty */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the size of the list */
    @Override
    public int size() {
        return size;
    }

    /** Print the items in the list separated by a space */
    @Override
    public void printDeque() {
        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /** Remove the first item from the list,if no such item return null */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node first = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return first.item;
    }

    /** Remove the last item from the list,if no such item return null */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node last = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return last.item;
    }

    /** Get the ith item in the list, return null if not existed */
    @Override
    public T get(int index) {
        if (isEmpty() || index >= size || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        for (; index > 0; index--) {
            p = p.next;
        }
        return p.item;
    }

    /** Get the ith item with recursion */
    public T getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }
        return getRecursive(sentinel.next, index).item;
    }

    /** Recursion helper */
    private Node getRecursive(Node p, int index) {
        if (index == 0) {
            return p;
        }
        return getRecursive(p.next, index - 1);
    }
}
