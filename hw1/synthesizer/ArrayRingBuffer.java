package synthesizer;

import java.util.Iterator;

import javax.swing.text.Position;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> implements Iterable<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // first, last, and fillCount should all be set to 0.
        // this.capacity should be set appropriately. Note that the local variable
        // here shadows the field we inherit from AbstractBoundedQueue, so
        // you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        first = 0;
        last = 0;

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then throw new
     * RuntimeException("Ring buffer overflow"). Exceptions covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount++;
        last = addOne(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then throw
     * new RuntimeException("Ring buffer underflow"). Exceptions covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T firstItem = rb[first];
        rb[first] = null;
        first = addOne(first);
        fillCount--;
        return firstItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
    }

    /** Add one for the position */
    private int addOne(int position) {
        return (position + 1 + capacity) % capacity;
    }

    /** Support iteration */
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int step;

        public ArrayRingBufferIterator() {
            step = 0;
        }

        @Override
        public boolean hasNext() {
            return step < fillCount;
        }

        @Override
        public T next() {
            T item = rb[currentIndex()];
            step++;
            return item;
        }

        /** get the index of ith step */
        private int currentIndex() {
            return (first + step) % capacity;
        }

    }
}
