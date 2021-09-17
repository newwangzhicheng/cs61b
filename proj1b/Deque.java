public interface Deque<T> {
    /** Add an item to the first place */
    public void addFirst(T x);

    /** Add an item to the last place */
    public void addLast(T x);

    /** Judge if this is empty */
    public boolean isEmpty();

    /** Return the size of the list */
    public int size();

    /** Print the items of the list, separated by a space */
    public void printDeque();

    /** Remove the first item of the list, return null if it is not existd */
    public T removeFirst();

    /** Remove the last item of the list, return null if it is not existd */
    public T removeLast();

    /** Return the ith item of the list, return null if it is not existed */
    public T get(int index);
}
