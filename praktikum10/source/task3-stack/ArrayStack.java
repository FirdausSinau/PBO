import java.util.ArrayList;

public class ArrayStack<T> {
    private final int maxsize;
    private final ArrayList<T> items;

    public ArrayStack(int maxsize) {
        if (maxsize <= 0) {
            throw new ArrayStackException(
                    "Stack size must be positive");
        }
        this.maxsize = maxsize;
        this.items = new ArrayList<>();
    }

    public void push(T item) {
        if (items.size() == maxsize) {
            throw new ArrayStackException("Overflow Error");
        }
        items.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new ArrayStackException("Underflow Error");
        }
        return items.remove(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public static class ArrayStackException
            extends RuntimeException {
        public ArrayStackException(String message) {
            super(message);
        }
    }
}
