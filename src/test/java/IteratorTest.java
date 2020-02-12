import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.Iterator;

public class IteratorTest {

    GenericStack<Integer> s;
    GenericQueue<String> q;
    Iterator<Integer> iter;
    Iterator<String> iter2;

    @BeforeEach
    void init(){
        s = new GenericStack<Integer>(200);
        q = new GenericQueue<String>("hello");
        iter = s.createIterator();
        iter2 = q.createIterator();
    }

    @AfterEach
    void testInit() {
        assertEquals("GLIterator", iter.getClass().getName(), "did not initialize proper object");
    }

    @Test
    void testNotEmpty(){
        assertTrue(iter.hasNext());
        assertTrue(iter2.hasNext());
    }

    @Test
    void testEmptyStack(){
        s.pop();
        iter = s.createIterator();
        assertFalse(iter.hasNext());
    }

    @Test
    void testEmptyQueue(){
        q.dequeue();
        iter2 = q.createIterator();
        assertFalse(iter2.hasNext());
    }

    @Test
    void testStackSingletonNext(){
        assertEquals(200, iter.next(), "value is incorrect");
        assertFalse(iter.hasNext());
    }

    @Test
    void testQueueSingletonNext(){
        assertEquals("hello", iter2.next(), "value is incorrect");
        assertFalse(iter2.hasNext());
    }

    @Test
    void testStackUnchanged(){
        iter.hasNext();
        iter.next();
        assertNotNull(s.head);
        assertEquals(200, s.head.data, "head was changed");
    }

    @Test
    void testQueueUnchanged(){
        iter2.hasNext();
        iter2.next();
        assertNotNull(q.head);
        assertEquals("hello", q.head.data, "head was changed");
    }

    @Test
    void testLargerStack(){
        s.push(39);
        s.push(21);
        s.push(13);
        iter = s.createIterator();
        assertTrue(iter.hasNext());
        assertEquals(13, iter.next(), "value is incorrect");
        assertTrue(iter.hasNext());
        assertEquals(21, iter.next(), "value is incorrect");
        assertTrue(iter.hasNext());
        assertEquals(39, iter.next(), "value is incorrect");
        assertTrue(iter.hasNext());
        assertEquals(200, iter.next(), "value is incorrect");
        assertFalse(iter.hasNext());
    }

    @Test
    void testLargerQueue() {
        q.enqueue("dude");
        q.enqueue("fan");
        q.enqueue("lamp");
        iter2 = q.createIterator();
        assertTrue(iter2.hasNext());
        assertEquals("hello", iter2.next(), "value is incorrect");
        assertTrue(iter2.hasNext());
        assertEquals("dude", iter2.next(), "value is incorrect");
        assertTrue(iter2.hasNext());
        assertEquals("fan", iter2.next(), "value is incorrect");
        assertTrue(iter2.hasNext());
        assertEquals("lamp", iter2.next(), "value is incorrect");
        assertFalse(iter2.hasNext());
    }

    @Test
    void testPopHasNext(){
        s.push(39);
        s.push(21);
        s.pop();
        iter = s.createIterator();
        assertTrue(iter.hasNext());
        s.pop();
        iter = s.createIterator();
        assertTrue(iter.hasNext());
        s.pop();
        iter = s.createIterator();
        assertFalse(iter.hasNext());
    }

    @Test
    void testDequeueHasNext(){
        q.enqueue("dude");
        q.enqueue("fan");
        q.dequeue();
        iter2 = q.createIterator();
        assertTrue(iter2.hasNext());
        q.dequeue();
        iter2 = q.createIterator();
        assertTrue(iter2.hasNext());
        q.dequeue();
        iter2 = q.createIterator();
        assertFalse(iter2.hasNext());
    }

}
