package lab_start;

import java.util.ArrayList;

public class DataSetGeneric<T extends Measurable> extends ArrayList<T>{
	
	private static final long serialVersionUID = 1L;
	private int min = 0;
	private int max = 0;
	public DataSetGeneric() { }
	
	@Override
	public boolean add(T obj) {
		return super.add(obj);
	}
	
	@Override
	public int size() {
		return super.size();
	}
	
	public int getMin() {
		super.stream().forEach(t -> {
			if(t.getMeasure() < min || min == 0) {
				min = t.getMeasure();
			}
		});
		
		return min;
	}
	
	public int getMax() {
		super.stream().forEach(t -> {
			if(t.getMeasure() > max || max == 0) {
				max = t.getMeasure();
			}
		});
		
		return max;
	}
	
	@Override
	public String toString() {
		return "DataSetGeneric [\n size()=" + size() + "\n getMin()=" + getMin() + " getMax()=" + getMax()
				+ " Product=\n" + super.toString() + "]";
	}
	
}