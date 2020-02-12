import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class QueueTest {

    GenericQueue<Integer> q;
    GenericQueue<String> r;
    GenericQueue<Float> t;

    @BeforeEach
    void init() {
        q = new GenericQueue<Integer>(200);
        r = new GenericQueue<String>("miss");
        t = new GenericQueue<Float>(2.7f);
    }

    @AfterEach
    void testInitGQ() {
        assertEquals("GenericQueue", q.getClass().getName(), "did not initialize proper object");
    }

    @Test
    void testInitNode() {
        assertEquals("GenericList$Node", q.head.getClass().getName(), "did not initialize node in constructor");
    }

    @Test
    void testNextNull(){
        assertNull(q.head.next);
    }

    @Test
    void testForNodeVal() {
        assertEquals(200, q.head.data, "value not in node");
        assertEquals("miss", r.head.data, "x- value not in node");
        assertEquals(2.7f, t.head.data, "f- value not in node");
    }

    @Test
    void testNotDuplicate(){
        q.enqueue(201);
        assertFalse(q.head.data.equals(q.head.next.data));
    }

    @Test
    void testEmptyList() {
        q.dequeue();
        assertNull(q.head);
        assertTrue(q.getLength()>=0);
    }

    @Test
    void testDequeueHead() {
        assertEquals(200, q.head.data, "head value incorrect");
        q.enqueue(30);
        q.dequeue();
        assertEquals(30, q.head.data, "head not updated correctly");
    }

    @Test
    void testDequeueListVal() {
        assertEquals(200, q.dequeue(), "value not returned");
        assertEquals("miss", r.dequeue(), "x- value not returned");
        assertEquals(2.7f, t.dequeue(), "f- value not returned");
    }

    @Test
    void testEmptyInt(){
        q.dequeue();
        assertNull(q.head, "head not null");
        q.enqueue(50);
        assertEquals(50, q.head.data, "value not in node");
        assertNull(q.head.next);
        assertEquals(1, q.getLength(), "did not get correct length");
    }

    @Test
    void testEmptyString(){
        r.dequeue();
        assertNull(r.head);
        r.enqueue("wumbo");
        assertEquals("wumbo", r.head.data, "value not in node");
        assertNull(r.head.next);
        assertEquals(1, r.getLength(), "did not get correct length");
    }

    @Test
    void testPushToEmpty(){
        q.enqueue(5);
        assertEquals(200, q.head.data, "did not push value correctly");
        assertEquals(5, q.head.next.data, "did not push value correctly");
        r.enqueue("never");
        assertEquals("miss", r.head.data, "did not push value correctly");
        assertEquals("never", r.head.next.data, "did not push value correctly");
    }

    @Test
    void testAddFourth(){
        q.dequeue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        assertEquals(4, q.head.next.next.next.data, "not added to back");
        assertEquals(1, q.head.data, "incorrect head");
    }

    @Test
    void testCheckOrder(){
        q.enqueue(20);
        q.enqueue(44);
        q.enqueue(90);
        assertEquals(200, q.head.data, "order is not correct");
        assertEquals(20, q.head.next.data, "order is not correct");
        assertEquals(44, q.head.next.next.data, "order is not correct");
        assertEquals(90, q.head.next.next.next.data, "order is not correct");
        assertEquals(4, q.getLength(), "length is not correct");
    }

    @Test
    void testLength(){
        assertNotEquals(0, q.getLength(), "s- did not get correct length");
        q.enqueue(79);
        assertEquals(2, q.getLength(), "x- did not get correct length");
        q.enqueue(20);
        assertEquals(3, q.getLength(), "x- did not get correct length");
        q.dequeue();
        assertEquals(2, q.getLength(), "x- did not get correct length");
    }

    @Test
    void testLongLength(){
        q.enqueue(234);
        q.enqueue(253);
        q.enqueue(87);
        q.enqueue(987);
        q.enqueue(64);
        q.enqueue(1);
        assertEquals(7, q.getLength(), "incorrect length");
    }

    @Test
    void testPopLength(){
        q.enqueue(49);
        q.enqueue(22);
        assertEquals(3, q.getLength(), "did not get correct length");
        q.dequeue();
        q.dequeue();
        assertEquals(1, q.getLength(), "did not get correct length");
        q.dequeue();
        assertEquals(0, q.getLength(), "did not get correct length");
    }
}
