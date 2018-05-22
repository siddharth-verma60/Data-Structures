package heapsAndPriorityqueues;

public interface PriorityQueue<K extends Comparable<K>, V> {

	int size();

	boolean isEmpty();

	void display();

	void add(K priority, V value);

	V remove() throws Exception;

	V get() throws Exception;
}
