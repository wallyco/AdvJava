package lecture;

import java.util.Comparator;
import java.util.List;

public class SortingExamples {

	public static void main(String[] args) {

		DataSetBookSmall3<BookSmall3> books = new DataSetBookSmall3<>();
		books.add(new BookSmall3("medium", "small", 100));
		books.add(new BookSmall3("large", "medium", 150));
		books.add(new BookSmall3("small", "large", 50));

		// comparing titles with inner class
		Comparator<BookSmall3> titleCompare = new Comparator<BookSmall3>() {
			@Override
			public int compare(BookSmall3 b1, BookSmall3 b2) {
				return b1.getTitle().compareTo(b2.getTitle());
			}
		};
		List<BookSmall3> sortedListByTitle = books.sortBy(titleCompare);
		//System.out.println(sortedListByTitle);

		// comparing titles with lambda
		Comparator<BookSmall3> authorCompare = (s1, s2) -> (s1.getAuthor().compareTo(s2.getAuthor()));
		List<BookSmall3> sortedListByAuthor = books.sortBy(authorCompare);
		//System.out.println(sortedListByAuthor);

		// DataSetComparator in separate class
		DataSetComparatorMeasure dataSetComparator = new DataSetComparatorMeasure();
		System.out.println(books.sortBy(dataSetComparator));
	}

}
