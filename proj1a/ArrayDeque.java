public class ArrayDeque<T> {
    private T[] item;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Create a circle array deque */
    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    /** Resize the deque */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        item = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** Add an item to the first place */
    public void addFirst(T x) {
        if (size == item.length - 1) {
            resize(item.length * 2);
        }
        size++;
        item[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
    }

    /** Add an item to the last place */
    public void addLast(T x) {
        if (size == item.length - 1) {
            resize(item.length * 2);
        }
        size++;
        item[nextLast] = x;
        nextLast = plusOne(nextLast);
    }

    /** Judge if this is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the size of the list */
    public int size() {
        return size;
    }

    /** Print the items of the list, separated by a space */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int index = (nextFirst + 1 + i + item.length) % item.length;
            System.out.print(item[index] + " ");
        }
    }

    /** Remove the first item of the list, return null if it is not existd */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        nextFirst = plusOne(nextFirst);
        T x = item[nextFirst];
        item[nextFirst] = null;
        if ((float) size / item.length < 0.25) {
            resize(item.length / 2);
        }
        return x;
    }

    /** Remove the last item of the list, return null if it is not existd */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        nextLast = minusOne(nextLast);
        T x = item[nextLast];
        item[nextLast] = null;
        if ((float) size / item.length < 0.25) {
            resize(item.length / 2);
        }
        return x;
    }

    /** Return the ith item of the list, return null if it is not existed */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int offset = nextFirst + 1;
        int i = (offset + index + item.length) % item.length;
        return item[i];

    }

    /** Minus one for nextFirst and nextLast */
    private int minusOne(int x) {
        return (x - 1 + item.length) % item.length;
    }

    /** Plus one for nextFirst and nextLast */
    private int plusOne(int x) {
        return (x + 1 + item.length) % item.length;
    }
}
