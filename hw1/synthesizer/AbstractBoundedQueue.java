package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    /** Return the count of size in the buffer */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /** Return the capacity of the buffer */
    @Override
    public int capacity() {
        return capacity;
    }
}
