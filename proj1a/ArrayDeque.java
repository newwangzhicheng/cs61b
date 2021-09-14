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
  private resize(int capacity) {

  }

  /** Add an item to the first place */
  public void addFirst(T x) {
    int length = item.length;
    if (size == length - 1) {
      resize(size * 2);
    }
    size++;
    nextFirst = (--nextFirst + length) % length;
    item[nextFirst] = x;
  }

  /** Add an item to the last place */
  public void addLast(T x) {
    int length = item.length;
    if (size == length - 1) {
      resize(size * 2);
    }
    size++;
    nextLast = (++nextLast + length) % length;
    item[nextLast] = x;
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
    int length = item.length;
    for (int i = 0; i < size; i++) {
      int index = (++nextFirst + i + length) % length;
      System.out.print(item[index] + " ");
    }
  }

  /** Remove the first item of the list, return null if it is not existd */
  public T removeFirst() {
    int length = item.length;
    if (isEmpty()) {
      return null;
    }
    size--;
    nextFirst = (++nextFirst + length) % length;
    T x = item[nextFirst];
    item[nextFirst] = null;
    if (size / item.length < 0.25) {
      resize(size / 2);
    }
    return x;
  }

  /** Remove the last item of the list, return null if it is not existd */
  public T removeLast() {
    int length = item.length;
    if (isEmpty()) {
      return null;
    }
    size--;
    nextLast = (--nextLast + length) % length;
    T x = item[nextLast];
    item[nextLast] = null;
    if (size / item.length < 0.25) {
      resize(size / 2);
    }
    return x;
  }

  /** Return the ith item of the list, return null if it is not existed */
  public T get(int index) {
    boolean indexInListLinear = (nextLast > nextFirst) && (index > nextFirst) && (index < nextLast);
    boolean indexOutListCircle = (nextLast < nextFirst) && (index <= nextFirst) && (index  nextLast);
    if (index >= nextLast && index) {
      return null;
    }
  }

}
