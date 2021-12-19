package lab_start;

import java.util.ArrayList;
import java.util.Comparator;


public class DataSetGenericSort<E extends Measurable> extends ArrayList<E> 
implements Comparator<E> {
	private static final long serialVersionUID = 1L;

	public DataSetGenericSort() { }
	
	@Override
	public int compare(Measurable o1, Measurable o2) {
		if(o1.getMeasure() < o2.getMeasure()) 
			return -1;
		if(o1.getMeasure() > o2.getMeasure())
			return 1;
		else
			return 0;
	}

}
		
		/*
		 * 		// comparing titles with inner class
		Comparator<BookSmall3> titleCompare = new Comparator<BookSmall3>() {
			@Override
			public int compare(BookSmall3 b1, BookSmall3 b2) {
				return b1.getTitle().compareTo(b2.getTitle());
			}
		};
				List<BookSmall3> sortedListByTitle = books.sortBy(titleCompare);
		 */
	
	
	
