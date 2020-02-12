import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class ListTest {

	GenericStack<Integer> s;
	GenericStack<String> x;
	GenericStack<Float> f;
		
	@BeforeEach
	void init() {
		s = new GenericStack<Integer>(200);
		x = new GenericStack<String>("hello");
		f = new GenericStack<Float>(1.5f);
	}
	
	@AfterEach
	void testInitGS() {
		assertEquals("GenericStack", s.getClass().getName(), "did not initialize proper object");
	}
	
	@Test
	void testInitNode() {
		assertEquals("GenericList$Node", s.head.getClass().getName(), "did not initialize node in constructor");
	}

	@Test
	void testNextNull(){
		assertNull(s.head.next);
	}

	@Test
	void testForNodeVal(){
		assertEquals(200, s.head.data, "s- value not in node");
		assertEquals("hello", x.head.data, "x- value not in node");
		assertEquals(1.5f, f.head.data, "f- value not in node");
	}

	@Test
	void testNotDuplicate(){
		s.push(201);
		assertFalse(s.head.data.equals(s.head.next.data));
	}
	
	@Test
	void testEmptyList() {
		s.pop();
		assertNull(s.head);
		assertNull(s.pop());
	}

	@Test
	void testPopListVal() {
		assertEquals(200, s.pop(), "value not returned");
		assertEquals("hello", x.pop(), "value not returned");
		assertEquals(1.5f, f.pop(), "value not returned");
	}

	@Test
	void testEmptyInt(){
		s.pop();
		assertEquals(null, s.head, "head not null");
		s.push(50);
		assertEquals(50, s.head.data, "value not in node");
		assertNull(s.head.next);
		assertEquals(1, s.getLength(), "did not get correct length");
	}

	@Test
	void testEmptyString(){
		x.pop();
		assertNull(x.head);
		x.push("wumbo");
		assertEquals("wumbo", x.head.data, "value not in node");
		assertNull(x.head.next);
		assertEquals(1, x.getLength(), "did not get correct length");
	}

	@Test
	void testPushToEmpty(){
		s.push(5);
		assertEquals(5, s.head.data, "did not push value correctly");
		x.push("never");
		assertEquals("never", x.head.data, "did not push value correctly");
	}

	@Test
	void testPopReturn(){
		s.push(6);
		assertEquals(6, s.pop(), "wrong value returned");
		assertNull(s.head.next, "next should be null");
		assertEquals(200, s.pop(), "wrong value returned");
		assertNull(s.head, "empty list, head should be null");
	}

	@Test
	void testPopLength(){
		s.push(60);
		s.pop();
		assertEquals(1, s.getLength(), "did not get correct length");
		s.pop();
		assertTrue(s.getLength()>=0);
	}

	@Test
	void testLength(){
		assertEquals(1, s.getLength(), "s- did not get correct length");
		s.push(4);
		assertEquals(2, s.getLength(), "s- did not get correct length");
	}

	@Test
	void testLongLength(){
		s.push(234);
		s.push(253);
		s.push(87);
		s.push(987);
		s.push(64);
		s.push(1);
		assertEquals(7, s.getLength(), "incorrect length");
	}

	@Test
	void testCheckOrderInt() {
		s.push(6);
		assertEquals(200, s.head.next.data, "order is not correct");
		assertEquals(6, s.head.data, "order is not correct");
	}

	@Test
	void testCheckOrderString() {
		x.push("tim");
		assertEquals("hello", x.head.next.data, "order is not correct");
		assertEquals("tim", x.head.data, "order is not correct");
	}

	@Test
	void testAddFourth(){
		s.pop();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		assertEquals(1, s.head.next.next.next.data, "not added to front");
		assertEquals(4, s.head.data, "incorrect head");
	}

}
