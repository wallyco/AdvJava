package lab_start;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestSort {
	private DataSetGenericSort<Book> books = new DataSetGenericSort<>();

	private ArrayList<Book> bok = new ArrayList<>();

	@Test
	public void testCompareTo() {
		books = DbConnection.ReadFromDataBaseBooks();
		
	}

}
