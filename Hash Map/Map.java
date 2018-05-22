package hashtable;

import java.util.ArrayList;

public interface Map<K, V> {
	int size();

	boolean isEmpty();

	void display();

	void put(K key, V value);

	V get(K key);

	V remove(K key);
	
	ArrayList<K> keys();
	
	ArrayList<V> values();
}
