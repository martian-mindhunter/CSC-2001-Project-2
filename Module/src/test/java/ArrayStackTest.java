import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ArrayStackTest {

    @Test
    void testEmptyStack() {
        ArrayStack<Integer> s = ArrayStack.emptyStack();
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());

    }

    @Test
    void testPush() {
        ArrayStack<Integer> s = ArrayStack.emptyStack();
        s.push(1);
        assertFalse(s.isEmpty());
        assertEquals(1, s.size());
        // Push stack doubling method
        s = ArrayStack.emptyStack();
        for (int i = 0; i < 25; i++) {
            s.push(i);
        }
        assertEquals(25, s.size());
        for (int i = 24; i >= 0; i--) {
            assertEquals(i, s.pop());
        }
        assertTrue(s.isEmpty());
    }

    @Test
    void testPeek() {
        ArrayStack<String> s = ArrayStack.emptyStack();
        s.push("a");
        s.push("b");
        assertEquals("b", s.peek());
        assertEquals("b", s.peek());
        assertEquals(2, s.size());
    }

    @Test
    void testPop() {
        ArrayStack<Integer> s = ArrayStack.emptyStack();
        s.push(1);
        s.push(2);
        assertEquals(2, s.pop());
        assertEquals(1, s.size());
        assertEquals(1, s.peek());
        s = ArrayStack.emptyStack();
        s.push(1);
        s.push(2);
        s.push(3);
        assertEquals(3, s.pop());
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    void pushingToPoppedStack() {
        ArrayStack<Integer> s = ArrayStack.emptyStack();
        s.push(1);
        s.pop();
        s.push(2);
        assertEquals(2, s.peek());
        assertEquals(1, s.size());
    }

    @Test
    void popEmpty() {
        ArrayStack<Integer> s = ArrayStack.emptyStack();
        assertThrows(IndexOutOfBoundsException.class, () -> s.pop());
    }

    @Test
    void peekEmpty() {
        ArrayStack<Integer> s = ArrayStack.emptyStack();
        assertThrows(IndexOutOfBoundsException.class, () -> s.peek());
    }

    @Test
    void popPushEmpty() {
        ArrayStack<Integer> s = ArrayStack.emptyStack();
        s.push(1);
        s.pop();
        assertThrows(IndexOutOfBoundsException.class, () -> s.pop());
    }
}