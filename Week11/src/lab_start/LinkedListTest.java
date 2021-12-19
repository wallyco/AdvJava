package lab_start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinkedListTest {
	
	@Test
	void testContain() {
		Object[] obj = {1,2,3,4,5};
		MyLinkedList link = new MyLinkedList(obj);
		
		assertTrue(link.contains(5));
	}
	
	@Test
	void testget() {
		Object[] obj = {1,2,3,4,5};
		MyLinkedList link = new MyLinkedList(obj);
		Object getLink = link.get(4);
		
		assertTrue(getLink.equals(link.get(4)));
	}
	
	@Test
	void testIndexOf() {
		Object[] obj = {1,2,3,3,4,5};
		MyLinkedList link = new MyLinkedList(obj);
		
		int test = link.indexOf(3);
		assertEquals(test, 2);
	}
	
	@Test
	void testLastIndexOf() {
		Object[] obj = {1,2,3,4,3};
		MyLinkedList link = new MyLinkedList(obj);
		
		int test = link.lastIndexOf(3);
		assertEquals(test, 4);	
	}
	
	@Test
	void testSet() {
		Object[] obj = {1,2,3,4,3};
		MyLinkedList link = new MyLinkedList(obj);	
		link.set(3, 10);
		
		System.out.println(link);		
	}
	
	
	@Test
	void testSet0() {
		Object[] obj = {1,2,3,4,3};
		MyLinkedList link = new MyLinkedList(obj);
		link.set(0, 10);
		
		System.out.println(link);
	}
	
	@Test
	void testSetMax() {
		Object[] obj = {1,2,3,4,3};
		MyLinkedList link = new MyLinkedList(obj);
		link.set(link.size()-1, 10);
		
		System.out.println(link);
	}

}
