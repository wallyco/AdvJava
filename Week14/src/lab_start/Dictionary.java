package lab_start;

import java.util.TreeSet;

public class Dictionary {

	private TreeSet<String> dictionary = new TreeSet<>();

	public boolean contains(String word) {
		return dictionary.contains(word);
	}

	public boolean add(String word) {
		return dictionary.add(word);
	}
	public int size() {
		return dictionary.size();
	}

}
