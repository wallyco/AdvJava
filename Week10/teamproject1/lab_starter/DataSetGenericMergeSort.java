package lab_starter;

import java.util.ArrayList;
import java.util.List;

public class DataSetGenericMergeSort<E extends Measurable> extends ArrayList<E> {
	private static final long serialVersionUID = 1L;
	
	//Author Javon Callens
	
	@SuppressWarnings("unchecked")
	public List<E> sort() {
		DataSetGenericMergeSort<E> returnList = (DataSetGenericMergeSort<E>) this.clone();
		
		if (this.size() > 1) {
			DataSetGenericMergeSort<Measurable> first = new DataSetGenericMergeSort<>();
			for (int i = 0; i < this.size() / 2; i++) {
				first.add(this.get(i));
			}

			first.sort();

			DataSetGenericMergeSort<Measurable> second = new DataSetGenericMergeSort<>();
			for (int i = this.size() / 2; i < this.size(); i++)
				second.add(this.get(i));

			second.sort();

			merge(first, second, returnList);

		}

		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	private void merge(DataSetGenericMergeSort<Measurable> first, DataSetGenericMergeSort<Measurable> second,
			DataSetGenericMergeSort<E> temp) {
		int current1 = 0; // Current index in first
		int current2 = 0; // Current index in second
		int current3 = 0; // Current index in returnlist

		while (current1 < first.size() && current2 < second.size()) {
			if (first.get(current1).getMeasure() < second.get(current2).getMeasure())
				temp.set(current3++, (E) first.get(current1++));
			else
				temp.set(current3++, (E) second.get(current2++));
		}

		while (current1 < first.size())
			temp.set(current3++, (E) first.get(current1++));

		while (current2 < second.size())
			temp.set(current3++, (E) second.get(current2++));
	}	

}


