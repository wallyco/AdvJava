package lab_start;

import java.util.ArrayList;
import java.util.ListIterator;

public class Redact {
	
	private  ArrayList<String> data = new ArrayList<>();


	private String targetWord;
	private String redactedWord = "";
	private static boolean fr = true;
	

	private static final char SYMBOL = '*';
	
	public Redact() {}
	
	public Redact(String target) {
		this.targetWord = target;
		redactor();
	}
	
	public void redactor() {
		int i = targetWord.length();
		
		for(int k = 0; k < i; k++) {
			this.redactedWord += SYMBOL;
		}
	}
	
	public String traverseRedact(String s) {
		storeStringToData(s);
		
		ListIterator<String> li = data.listIterator();
		do {
			if(li.next().equalsIgnoreCase(targetWord)) {
				li.set(redactedWord); 
			}
		} while(li.hasNext());
		
		return toActualString(data);
	}
	
	private void storeStringToData(String s) {
		String temp = "";
		for(char i : s.toCharArray()) {
			if(i == ' ' || i == '\n') {
				data.add(temp);
				temp = "";
				if(i == '\n') data.add("\n");
				
			}else temp += i;
		}
	}
	
	
	private String toActualString(ArrayList<String> s) {
		String rs = "";
		for(String i : s) {
			if(fr) {
				if(i == "\n") {
					fr = false;
				}
			}else {
				rs += i;
				if(i != "\n") rs += ' ';
			}
		}
					
		return rs;
	}
	
	public static void setFr(boolean fr) {
		Redact.fr = fr;
	}
	
	//For tester
	public String getRedactedWord() {
		return this.redactedWord;
	}
	public void setTargetWord(String t) {
		this.targetWord = t;
	}
	
}
