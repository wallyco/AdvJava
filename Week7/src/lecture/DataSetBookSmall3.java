package lecture;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataSetBookSmall3<E extends Measurable> extends ArrayList<E> {
	private static final long serialVersionUID = 1L;

	public DataSetBookSmall3() {
	}

	@Override
	public boolean add(E objectToAdd) {
		return super.add(objectToAdd);
	}

	// Returns size of DataSetGeneric
	public int size() {
		return super.size();
	}


	public java.util.List<E> getList() {
		ArrayList<E> new_list = new ArrayList<>();
		for (int i = 0; i < super.size(); i++) {
			new_list.add(super.get(i));
		}
		return new_list;

	}

	public List<E> sortBy(Comparator<? super E> comparator) {
		List<E> sortList = this.getList();
		sortList.sort(comparator);
		return sortList;
	}

	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		for (Measurable measurable : this) {
			returnString.append(measurable.toString() + "\n");
		}
		return returnString.toString();
	}

}