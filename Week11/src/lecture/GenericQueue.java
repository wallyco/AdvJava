package lecture;

public class GenericQueue<E> {
	private java.util.LinkedList<E> list = new java.util.LinkedList<E>();

	public void enqueue(E e) {
		list.addLast(e);
	}

	public E dequeue() {
		return list.removeFirst();
	}

	public int getSize() {
		return list.size();
	}
	// ADDED for this example
	public E remove(int position) {
		return list.remove(position);
	}
	// ADDED for this example
	public E get(int position) {
		return list.get(position);
	}
	// ADDED for this example
	public boolean isEmpty() {
		return list.isEmpty();
	}
	// ADDED for this example
	public void clear() {
		list.clear();
	}
	@Override
	public String toString() {
		return "Queue: " + list.toString();
	}
}