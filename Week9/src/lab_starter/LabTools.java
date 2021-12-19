package lab_starter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class LabTools{
	TreeMap<String, Integer> data = new TreeMap<>();
	TreeMap<String, Integer> incorrectData = new TreeMap<>();
	HashSet<String> dictionary = Dictionary.dictionary;
	String output = "";
	int max = 0;
	int i;
	
	public LabTools(Object[] s){ 
		for(int i = 0; i<s.length; i++) {
			populate((String)s[i]);
		}
		
	}

	private void populate(String s) {
		String low = s.toLowerCase();
		if(dictionary.contains(low)) {
			if(!data.containsKey(low)) {
				data.put(low, 1);
			}else {
				int value = data.get(low);
				value++;
				if(value > max) max = value;
				data.put(low, value);
			}
		}else {
			if(!incorrectData.containsKey(low)) {
				incorrectData.put(low, 1);
			}else {
				int value = incorrectData.get(low);
				value++;
				incorrectData.put(low, value);
			}
		}
	}
	
	public String spellcheck() {
		output = "Spell Check: \n\n";
		
		incorrectData.forEach((k, v) -> {
			output += k + ", ";
		});
		return output;
	}
	
	@SuppressWarnings("unchecked")
	public String frequency() {
		output = "Frequency of words: \n\nMost Frequent Word(s)\n";
		
		data.forEach((k,v) -> {
			if (v==max) {
				output += k + ", ";
			}
		});
		
		ArrayList<String> temp = new ArrayList<>();
		
		for(i = 0; i <= max; i++) {
			temp.clear();
			if(data.containsValue(i)) {
				output += "Frequency " + i + "\n";
				data.forEach((k,v) -> {
					if(v == i) temp.add(k);
				});
				for(String s: temp) {
					output += s + ",";
				}
			}
			if(!output.endsWith("\n")) output += "\n\n";
		}
		return output;	
	}

}
