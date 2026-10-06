public class ArrayStack<T> {

    private static int initialCapacity = 10;
    private Object[] stack;
    private int size;

    // Initial constructor
    private ArrayStack() {
        this.stack = new Object[initialCapacity];
        this.size = 0;
    }

    // Creates an empty generic stack
    public static <T> ArrayStack<T> emptyStack() {
        return new ArrayStack<>();
    }

    // Appends a value to the top of the stack & doubles length if necessary
    public void push(T value) {
        if (this.size == this.stack.length) {
            this.doubleLength();
        }
        this.stack[this.size] = value;
        this.size++;
    }

    // Removes the last value & returns as generic value
    @SuppressWarnings("unchecked")
    public T pop() {
        this.checkNotEmpty();
        this.size--;
        T result = (T) this.stack[this.size];
        this.stack[this.size] = null;
        return result;
    }

    // Returns generic value of the end of stack
    @SuppressWarnings("unchecked")
    public T peek() {
        this.checkNotEmpty();
        return (T) this.stack[this.size - 1];
    }

    // Checks if ArrayStack size is 0
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Returns size of ArrayStack
    public int size() {
        return this.size;
    }

    // Helper method to check if empty
    private void checkNotEmpty() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("stack is empty");
        }
    }

    // Helper method to double ArrayStack stack length
    private void doubleLength() {
        Object[] bigger = new Object[this.stack.length * 2];
        for (int i = 0; i < this.size; i++) {
            bigger[i] = this.stack[i];
        }
        this.stack = bigger;
    }

}