package lecture;

public class BookSmall3 implements Measurable, Comparable<BookSmall3> {
	private String title;
	private String author;
	private int pages;

	
	public BookSmall3(String title, String author, int pages) {
		super();
		this.title = title;
		this.author = author;
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book2 [title=" + title + ", author=" + author + ", pages=" + pages + "]";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public int getMeasure() {
		return pages;
	}

	@Override
	public int compareTo(BookSmall3 o) {
		return this.pages - o.getPages();
	}
	
	
}
