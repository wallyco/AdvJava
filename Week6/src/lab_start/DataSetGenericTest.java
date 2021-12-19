package lab_start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataSetGenericTest {
	static DataSetGeneric<Book> set;
	@BeforeEach
	public void setUp() {
		set = new DataSetGeneric<>();
	}
//	@Test
//	public void testAddWrongObject() {  
//		Movie movie = new Movie();
//		Throwable exception = 
//				assertThrows(Exception.class, () ->
//				set.add(movie));
//		I am guessing even though this does cause an exception, the complier catches it before
//		it reaches run time thanks to generic
//	}
	
	@Test
	public void testGeneric() {
		Book b1 = new Book();
		set.add(b1);
		MeasurableTest t = new MeasurableTest();
		DataSetGeneric<MeasurableTest> newset = new DataSetGeneric<>();
		newset.add(t);
		
	}
	
	@Test
	public void testGetMin() {
		Book b1 = new Book();
		Book b2 = new Book();
		b1.setPages(20);
		b2.setPages(30);
		set.add(b2);
		set.add(b1);
		assertEquals(set.getMin(), 20);	
	}
	
	@Test
	public void testGetMax() {
		Book b1 = new Book();
		Book b2 = new Book();
		b1.setPages(20);
		b2.setPages(30);
		set.add(b2);
		set.add(b1);
		assertEquals(set.getMax(), 30);	
	}
	
	@Test
	public void testDBConnection() {
		assertNotNull(DbConnection.getConnection());
	}
	
	@Test
	public void testGetBookMinMax() {
		set = DbConnection.ReadFromDataBaseBooks();
		int min = set.getMin();
		int max = set.getMax();
		assertEquals(min, 160);
		assertEquals(max, 283);
	}
	
	class MeasurableTest implements Measurable{

		@Override
		public int getMeasure() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
