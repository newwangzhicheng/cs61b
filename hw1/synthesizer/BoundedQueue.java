package synthesizer;

public interface BoundedQueue<T> extends Iterable<T> {
    /** Return size of the buffer */
    public int capacity();

    /** Return number of items currently in the buffer */
    public int fillCount();

    /** add item x to the end */
    public void enqueue(T x);

    /** Delete and return item from the front */
    public T dequeue();

    /** Return item from the front */
    public T peek();

    /** Check if the buffer empty */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** Check if the buffer full */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}