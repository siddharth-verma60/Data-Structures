package hashtable;

import java.util.ArrayList;

import LinkedList.LinkedList;

public class HashTable<K, V> implements Map<K, V> {
	private class Node implements Comparable<Node> {
		K key;
		V value;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(HashTable<K, V>.Node o) {
			// TODO Auto-generated method stub
			return 0;
		}

		public boolean equals(Object other) {
			Node oNode = (Node) other;
			return this.key.equals(oNode.key);
		}

		public String toString() {
			String retval = "";
			retval += "{ " + this.key + ":" + this.value + " }";
			return retval;
		}
	}

	private int size;
	private LinkedList<Node>[] bucketArray;
	public static final int DEFAULT_INITIAL_BUCKET_ARRAY_SIZE = 10;

	public HashTable() {
		this(DEFAULT_INITIAL_BUCKET_ARRAY_SIZE);
	}

	public HashTable(int bucketInitialSize) {
		this.bucketArray = (LinkedList<Node>[]) new LinkedList[bucketInitialSize];
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public void display() {
		System.out.println(this);
	}

	public String toString() {
		String retval = "[\n";

		for (int i = 0; i < this.bucketArray.length; i++) {
			if (this.bucketArray[i] == null) {
				retval += "NULL";
			} else {
				retval += this.bucketArray[i].toString();
			}
			retval += "\n";
		}

		retval += "]\n";
		retval += "**********************************";
		return retval;
	}

	@Override
	public void put(K key, V value) {
		int bucketIndex = this.HashFunction(key);
		LinkedList<Node> list = this.bucketArray[bucketIndex];

		Node temp = new Node(key, value);

		if (list == null) {
			LinkedList<Node> list1 = new LinkedList();

			list1.AddLast(temp);

			this.bucketArray[bucketIndex] = list1;
		} else {
			int findIndex = list.find(temp);
			if (findIndex == -1) {
				list.AddLast(temp);
				this.size++;
			} else {
				try {
					Node foundNode = list.getAt(findIndex);
					foundNode.value = value;
				} catch (Exception ex) {

				}
			}

		}
		double loadFactor = (1.0 * this.size) / this.bucketArray.length;
		if (loadFactor > 0.75) {
			this.rehash();
		}
	}

	public void rehash() {
		LinkedList<Node>[] oldBucketArray = this.bucketArray;

		this.bucketArray = (LinkedList<Node>[]) new LinkedList[2 * oldBucketArray.length];
		this.size = 0;

		for (int i = 0; i < oldBucketArray.length; i++) {
			LinkedList<Node> bucket = oldBucketArray[i];

			while (bucket != null && !bucket.isEmpty()) {
				Node temp = null;
				try {
					temp = bucket.removeFirst();
				} catch (Exception ex) {

				}
				this.put(temp.key, temp.value);
			}

		}
	}

	@Override
	public V get(K key) {
		int bucketIndex = this.HashFunction(key);
		LinkedList<Node> list = this.bucketArray[bucketIndex];

		Node temp = new Node(key, null);

		if (list == null) {
			return null;
		} else {
			int findIndex = list.find(temp);
			if (findIndex == -1) {
				return null;
			} else {
				V retval = null;

				try {
					retval = list.getAt(findIndex).value;
				} catch (Exception ex) {

				}
				return retval;
			}
		}

	}

	private int HashFunction(K key) {
		int retval;
		retval = key.hashCode();

		retval = Math.abs(retval % this.bucketArray.length);
		return retval;
	}

	@Override
	public V remove(K key) {
		int bucketIndex = this.HashFunction(key);
		LinkedList<Node> list = this.bucketArray[bucketIndex];

		Node temp = new Node(key, null);

		if (list == null) {
			return null;
		} else {
			int findIndex = list.find(temp);
			if (findIndex == -1) {
				return null;
			} else {
				V retval = null;

				try {
					this.size--;
					retval = list.getAt(findIndex).value;
					list.removeAt(findIndex);
				} catch (Exception ex) {

				}
				return retval;
			}
		}
	}

	public ArrayList<K> keys() {
		ArrayList<K> retval = new ArrayList<>();
		ArrayList<V> helper = new ArrayList<>();
		int counter = 0;

		for (int i = 0; i < this.bucketArray.length; i++) {
			LinkedList<Node> bucket = this.bucketArray[i];

			while (bucket != null && !bucket.isEmpty()) {
				Node temp = null;

				try {
					temp = bucket.removeFirst();
				} catch (Exception ex) {

				}
				retval.add(temp.key);
				helper.add(temp.value);
			}

			for (int j = counter; bucket != null && j < retval.size(); j++) {
				bucket.AddLast(new Node(retval.get(j), helper.get(j)));
			}
			counter = retval.size();
		}
		return retval;
	}
	
	public ArrayList<V> values(){
		ArrayList<V> retval = new ArrayList<>();
		ArrayList<K> helper = new ArrayList<>();
		int counter = 0;

		for (int i = 0; i < this.bucketArray.length; i++) {
			LinkedList<Node> bucket = this.bucketArray[i];

			while (bucket != null && !bucket.isEmpty()) {
				Node temp = null;

				try {
					temp = bucket.removeFirst();
				} catch (Exception ex) {

				}
				retval.add(temp.value);
				helper.add(temp.key);
			}

			for (int j = counter; bucket != null && j < retval.size(); j++) {
				bucket.AddLast(new Node(helper.get(j), retval.get(j)));
			}
			counter = retval.size();
		}
		return retval;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
