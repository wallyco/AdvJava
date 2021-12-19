package lecture;

public class MyArrayList<E> implements MyList<E> {
	public static final int INITIAL_CAPACITY = 16;
	@SuppressWarnings("unchecked")
	private E[] data = (E[]) new Object[INITIAL_CAPACITY];
	private int size = 0;

	/** Create an empty list */
	public MyArrayList() {
	}

	/** Create a list from an array of objects */
	public MyArrayList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]); // Warning: don’t use super(objects)!
	}

	@Override /** Add a new element at the specified index */
	public void add(int index, E e) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		ensureCapacity();
		for (int i = size - 1; i >= index; i--)
			data[i + 1] = data[i];
		data[index] = e;
		size++;
	}

	private void ensureCapacity() {
		if (size >= data.length) {
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) (new Object[size * 2 + 1]);
			System.arraycopy(data, 0, newData, 0, size);
			data = newData;
			// System.out.println("ensureCapacity :" + size);
		}
	}

	@SuppressWarnings("unchecked")
	@Override /** Clear the list */
	public void clear() {
		data = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}

	@Override /** Return true if this list contains the element */
	public boolean contains(Object e) {
		for (int i = 0; i < size; i++)
			if (e.equals(data[i]))
				return true;
		return false;
	}

	@Override /** Return the element at the specified index */
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}

	public int indexOf(Object e) {
//		System.out.println("index of");
//		System.out.println(e);
		for (int i = 0; i < size; i++) {
			if (e.equals(data[i]))
				return i;
		}
		return -1;
	}

	public int lastIndexOf(E e) {
		for (int i = size - 1; i >= 0; i--)
			if (e.equals(data[i]))
				return i;
		return -1;
	}

	public E remove(int index) {
		checkIndex(index);
		E e = data[index];
		for (int j = index; j < size - 1; j++)
			data[j] = data[j + 1];
		data[size - 1] = null; // This element is now null
		size--;
		return e;
	}

	public E set(int index, E e) {
		checkIndex(index);
		E old = data[index];
		data[index] = e;
		return old;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		for (int i = 0; i < size; i++) {
			result.append(data[i]);
			if (i < size - 1)
				result.append(", ");
		}
		return result.toString() + "]";
	}

	public void trimToSize() {
		if (size != data.length) {
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) (new Object[size]);
			System.arraycopy(data, 0, newData, 0, size);
			data = newData;
		}
	}

	public java.util.Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements java.util.Iterator<E> {
		private int current = 0; // Current index

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public E next() {
			return data[current++];
		}

		public void remove() {
			if (current == 0) // next() has not been called yet
				throw new IllegalStateException();
			MyArrayList.this.remove(--current);
		}
	}

	public int size() {
		return size;
	}
}