package lab_start;

public class DataSetGeneric<E extends Measurable> extends MyArrayList<E> {
	
	public E getMin() {
		E returnMin = null;
		if(!this.isEmpty()) {
			int min = this.get(0).getMeasure();
			returnMin = this.get(0);
			for(E obj : this) {
				if(obj.getMeasure() < min)
					returnMin = obj;
			}
		}
		return returnMin;
	}
	
	public E getMax() {
		E returnMax = null;
		if(!this.isEmpty()) {
			int max = this.get(0).getMeasure();
			returnMax = this.get(0);
			for(E obj : this) {
				if(obj.getMeasure() > max)
					returnMax = obj;
			}
		}
		return returnMax;
	}
	
	public MyList<E> sort() {
		MyArrayList<E> returnArray = new MyArrayList<E>();

		for(int i = 0; i < this.size(); i++) {
			returnArray.add(i, this.get(i));
		}
		
		int n = returnArray.size();
		if(n > 1)
			bsort(returnArray, n);
		
		return returnArray;
		
	}
	
	private void bsort(MyArrayList<E> arr, int n) {
		if(n == 1) return;
		
		for(int i = 0; i < n - 1; i++) {
			if(arr.get(i).getMeasure() > arr.get(i+1).getMeasure()) {
				E temp = (E) arr.get(i);
				arr.set(i, arr.get(i+1));
				arr.set(i+1, temp);
			}
		}
		bsort(arr,n-1);
	}
	
}
