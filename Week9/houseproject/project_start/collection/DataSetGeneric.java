package project_start.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import project_start.tools.Screener;


public class DataSetGeneric<E extends Measurable> extends ArrayList<E> {
private static final long serialVersionUID = 1L;
	
	public E getMin() {
		super.sort(new DSCSalary<>());
		return super.get(0);
	}
	
	public E getMax() {
		super.sort(new DSCSalary<>());
		return super.get(super.size() - 1);
	}
	
	public List<E> getList() {
		ArrayList<E> rtList = new ArrayList<>();
		for(E index: this) {
			rtList.add(index);
		}
		return rtList;
	}
	
	public List<E> getList(Screener<E> elementScreener){
		ArrayList<E> rtList = new ArrayList<>();
		for(E em : this) {
			if(elementScreener.test(em)) {
				rtList.add(em);
			}
		}
		return rtList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<E> sortBy(Comparator<? super E> comparator){
		Object[] obj = super.toArray();
		Arrays.sort(obj, (Comparator) comparator);
		ListIterator<E> li = super.listIterator();
		for(int i = 0; i <obj.length; i++) {
			li.next();
			li.set((E)obj[i]);
		}
		
		return (List<E>) Arrays.asList(obj);
		
	}
	

	@Override
	public String toString() {
		return "DataSetGeneric [\n size()=" + size() + "\n getMin()=" + getMin() + " getMax()=" + getMax()
				+ " Product=\n" + super.toString() + "]";
	}
	
}
