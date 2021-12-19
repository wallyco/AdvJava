package lab_starter;

import java.util.HashSet;


public class Dictionary {
	static HashSet<String> dictionary = new HashSet<>();
	
	public Dictionary() { }
		
	public boolean add(String s) {
		 if(s == null) return false;
		 if(!dictionary.add(s)) return false;
		 return true;
	}
	
	public int size() {
		return dictionary.size();
	}
}
