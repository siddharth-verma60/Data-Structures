package heapsAndPriorityqueues;

import java.util.ArrayList;

public class Heap<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
	private class Node implements Comparable<Node> {
		K priority;
		V value;

		Node(K priority, V value) {
			this.priority = priority;
			this.value = value;
		}

		@Override
		public int compareTo(Heap<K, V>.Node o) {
			if (isMin) {
				return -1 * this.priority.compareTo(o.priority);
			} else {
				return this.priority.compareTo(o.priority);
			}
		}

		public String toString() {
			String retval = "";
			retval += "{ " + this.priority + ", " + this.value + " }";
			return retval;
		}

	}

	private ArrayList<Node> data;
	private boolean isMin;

	public Heap() {
		this(false);
	}

	public Heap(boolean isMin) {
		this.data = new ArrayList<>();
		this.isMin = isMin;
	}
	
	public Heap(boolean isMin, K[] priorities, V[] values){
		this(isMin);
		
		for(int i=0;i<priorities.length;i++){
			Node node=new Node(priorities[i],values[i]);
			this.data.add(node);
		}
		
		for(int i=(this.data.size()/2)-1; i>0;i--){
			this.downheapify(i);
		}
	}

	public int size() {
		return this.data.size();
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		return this.data.toString();
	}

	public void add(K priority, V value) {
		Node temp = new Node(priority, value);
		this.data.add(temp);

		this.upheapify(this.size() - 1);
	}

	private void upheapify(int ci) {
		if (ci == 0) {
			return;
		}

		int pi = (ci - 1) / 2;
		if (this.data.get(pi).compareTo(this.data.get(ci)) < 0) {
			this.swap(pi, ci);
			this.upheapify(pi);
		}
	}

	public V remove() throws Exception {
		if (this.data.isEmpty()) {
			throw new Exception("Empty Heap");
		}

		V retval = this.data.get(0).value;

		this.swap(0, this.data.size() - 1);
		this.data.remove(this.size() - 1);
		this.downheapify(0);

		return retval;
	}

	public void downheapify(int pi) {
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;

		int maxIndex = pi;
		if (lci < this.data.size() && this.data.get(maxIndex).compareTo(this.data.get(lci)) < 0) {
			maxIndex = lci;
		}
		if (rci < this.data.size() && this.data.get(maxIndex).compareTo(this.data.get(rci)) < 0) {
			maxIndex = rci;
		}

		if (maxIndex != pi) {
			this.swap(maxIndex, pi);
			this.downheapify(maxIndex);
		}

	}

	public V get() throws Exception {
		if (this.data.isEmpty()) {
			throw new Exception("Heap Empty.");
		}
		return this.data.get(0).value;
	}

	private void swap(int i, int j) {
		Node temp = this.data.get(i);
		this.data.set(i, this.data.get(j));
		this.data.set(j, temp);
	}

}
