package edu.waketech.measurable;

import java.util.ArrayList;


public class DataSetMeasurable {	
	private ArrayList<Measurable> data;

	public DataSetMeasurable() {
		data = new ArrayList<>();
	}
	
	public boolean add(Measurable objToAdd) {
		return data.add(objToAdd);
	}
	
	public int size() {
		return data.size();
	}
	
	public Measurable getMin() {
		if(data.isEmpty()) { return null;}
		
		Measurable obj = data.get(0);
		for(int i = 1; i < data.size(); i++) {
			if(obj.getMeasure() > data.get(i).getMeasure()) {
				obj = data.get(i);
			}
		}
		return obj;
	}
	
	public Measurable getMax() {
		if(data.isEmpty()) { return null;}
		
		Measurable obj = data.get(0);
		for(int i = 1; i < data.size(); i++) {
			if(obj.getMeasure() < data.get(i).getMeasure()) {
				obj = data.get(i);
			}
		}
		return obj;
	}
	
	@Override
	public String toString() {
		return "DataSetMeasureable \n size() = " + size() + "\n getMin() = " + getMin().getClass().getName().substring(24) + "| getMax() = " + getMax().getClass().getName().substring(24)
				+ "| Measurable Objects =\n" + data.toString();
	}
	
	
	
	
}

