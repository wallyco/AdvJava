package project_start.collection;

import java.util.Comparator;

public class DSCSalary<K extends Measurable> implements Comparator<K>{
	@Override
	public int compare(K o1, K o2) {
		if(o1.getMeasure() > o2.getMeasure()) return 1;
		if(o1.getMeasure() < o2.getMeasure()) return -1;
		return 0;
	}
}
