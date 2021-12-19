package lecture;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StaticSorts {

	public static void bubbleSort(List<Voter> list) {
		boolean needNextPass = true;
		for (int k = 1; k < list.size() && needNextPass; k++) {
			// Array may be sorted and next pass not needed
			needNextPass = false;
			for (int i = 0; i < list.size() - k; i++) {
				if (list.get(i).getAge() > list.get(i + 1).getAge()) {
					// Swap list[i] with list[i + 1]
					Voter temp = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, temp);
					needNextPass = true; // Next pass still needed
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <E> void heapSort(List<E> list) {
		// Create a Heap of integers
		heapSort(list, (e1, e2) -> ((Comparable<E>) e1).compareTo(e2));
	}

	/** Heap sort method */
	public static <E> void heapSort(List<E> list, Comparator<E> c) {
		// Create a Heap of integers
		Heap<E> heap = new Heap<>(c);

		// Add elements to the heap
		for (int i = 0; i < list.size(); i++)
			heap.add(list.get(i));

		// Remove elements from the heap
		for (int i = list.size() - 1; i >= 0; i--) {
			E temp = heap.remove();
			list.set(i, temp);
		}
	}

	public static <E extends Comparable<E>> void insertionSort(List<E> list) {
		insertionSort(list, (e1, e2) -> ((Comparable<E>) e1).compareTo(e2));
	}

	public static <E> void insertionSort(List<E> list, Comparator<? super E> comparator) {
		insertionSort(list, 0, list.size() - 1, comparator);
	}

	private static <E> void insertionSort(List<E> list, int first, int last, Comparator<? super E> comparator) {
		for (int i = 1; i < list.size(); i++) {
			/**
			 * insert list[i] into a sorted sublist list[0..i-1] so that list[0..i] is
			 * sorted.
			 */
			E currentElement = list.get(i);
			int k;
			for (k = i - 1; k >= 0 && comparator.compare(list.get(k), currentElement) > 0; k--) {
				E tempElement = list.get(k);
				list.set(k + 1, tempElement);
			}
			// Insert the current element into list[k+1]
			list.set(k + 1, currentElement);
		}
	}

	@SuppressWarnings("unchecked")
	public static <E> void mergeSort(List<E> list) {
		mergeSort(list, (e1, e2) -> ((Comparable<E>) e1).compareTo(e2));
	}

	public static <E> void mergeSort(List<E> list, Comparator<? super E> comparator) {
		if (list.size() > 1) {
			// Merge sort the first half
			// int[] firstHalf = new int[list.size() / 2];
			List<E> firstHalf = new ArrayList<E>();
			List<E> secondHalf = new ArrayList<E>();
			//System.arraycopy(list, 0, firstHalf, 0, list.size() / 2);
			int count = 0;
			for (E e : list) {
				int middle = list.size() / 2;
				if (count < middle) {
					firstHalf.add(e);
				} else {
					secondHalf.add(e);	
				}
				count++;
			}
			mergeSort(firstHalf, comparator);
			mergeSort(secondHalf, comparator);

			// Merge firstHalf with secondHalf into list
			merge(firstHalf, secondHalf, list, comparator);
		}
	}

	/** Merge two sorted lists */
	public static <E> void merge(List<E> list1, List<E> list2, List<E> temp, Comparator<? super E> comparator) {
		int current1 = 0; // Current index in list1
		int current2 = 0; // Current index in list2
		int current3 = 0; // Current index in temp

		while (current1 < list1.size() && current2 < list2.size()) {
			if (comparator.compare(list1.get(current1), list2.get(current2)) < 0)
				temp.set(current3++, list1.get(current1++));
			else
				temp.set(current3++, list2.get(current2++));
		}

		while (current1 < list1.size())
			temp.set(current3++, list1.get(current1++));

		while (current2 < list2.size())
			temp.set(current3++, list2.get(current2++));
	}

	@SuppressWarnings("unchecked")
	public static <E> void quickSort(List<E> list) {
		quickSort(list, 0, list.size() - 1, (e1, e2) -> ((Comparable<E>) e1).compareTo(e2));
	}

	private static <E> void quickSort(List<E> list, int first, int last, Comparator<? super E> comparator) {
		if (last > first) {
			int pivotIndex = partition(list, first, last, comparator);
			quickSort(list, first, pivotIndex - 1, comparator);
			quickSort(list, pivotIndex + 1, last, comparator);
		}
	}

	/** Partition the array list[first..last] */
	private static <E> int partition(List<E> list, int first, int last, Comparator<? super E> comparator) {
		E pivot = list.get(first); // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
//			while (low <= high && list.get(low) <= pivot)
			while (low <= high && comparator.compare(list.get(low), pivot) <= 0)
				low++;

			// Search backward from right
			// comparator.compare(list.get(hight), pivot) > 0
//			while (low <= high && list.get(high) > pivot)
			while (low <= high && comparator.compare(list.get(high), pivot) > 0)
				high--;

			// Swap two elements in the list
			if (high > low) {
				E temp = list.get(high);
				list.set(high, list.get(low));
				list.set(low, temp);
//				int temp = list[high];
//				list[high] = list[low];
//				list[low] = temp;
			}
		}

//		while (high > first && list.get(high) >= pivot)
		while (high > first && comparator.compare(list.get(high), pivot) > 0)
			high--;

		// Swap pivot with list[high]
//		if (pivot > list.get(high)) {
		if (comparator.compare(pivot, list.get(high)) > 0) {
			list.set(first, list.get(high));
			list.set(high, pivot);
			return high;
		} else {
			return first;
		}
	}

}
