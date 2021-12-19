package lab_start;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import lab_start.BST.TreeNode;

public class ComparatorTree<E extends Comparable<E>> implements Tree<E> {
	private TreeNode<E> root = null;
	private int size = 0;
	private Comparator<E> comparison = new Comparator<E>() {
		@Override
		public int compare(E o1, E o2) {
			return o1.compareTo(o2);
		}
	};
	
	public ComparatorTree() { }
	
	public ComparatorTree(E[] obj) {
		for(E o : obj) {
			insert(o);
		}
	}
	
	public ComparatorTree(E[] obj, Comparator<E> c) {
		this.comparison = c;
		for(E o : obj) {
			insert(o);
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return new InorderIterator();
	}

	@Override
	public void clear() {
		size = 0;
		root = null;
	}

	@Override
	public boolean search(E e) {
		if(root != null) {
			TreeNode<E> current = root;
			while(current != null) {
				if(comparison.compare(e, current.element) < 0) {
					current = current.left;
				}else if
				(comparison.compare(e, current.element) > 0) {
					current = current.right;
				}
				else {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean insert(E e) {
		if(root == null)
			root = new TreeNode<>(e);
		else {
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			
			while(current != null) {
				if(comparison.compare(e, current.element) < 0) {
					parent = current;
					current = current.left;
				}else if
				((comparison.compare(e, current.element) > 0)) {
					parent = current;
					current = current.right;
				}else {
					return false;
				}
			}
			
			if(comparison.compare(e, parent.element) < 0)
				parent.left = new TreeNode<E>(e);
			else
				parent.right = new TreeNode<E>(e);
			size++;
		}
		return true;
				
	}

	
	public boolean delete(E e) {
		return false; 
	}

	@Override
	public int getSize() {
		return size;
	}
	
	private class InorderIterator implements Iterator<E>{
		private ArrayList<E> list = new ArrayList<>();
		private int current = 0;
		
		public InorderIterator() {
			iterator();
		}
		
		private void iterator() {
			recursive(root);
		}
		
		private void recursive(TreeNode<E> root) {
			if(root == null)
				return;
			recursive(root.left);
			list.add(root.element);
			recursive(root.right);
		}
		@Override
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		@Override
		public E next() {
			return list.get(current++);
		}
		
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfLeaves() {
		// TODO Auto-generated method stub
		return 0;
	}

}
