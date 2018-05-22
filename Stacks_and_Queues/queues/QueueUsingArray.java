package stacks_and_Queues.queues;

import stacks_and_Queues.common.IQueue;

public class QueueUsingArray<T> implements IQueue<T> {

	// Data Members
	protected int size;
	protected T[] data;
	protected int front;
	public static final int DEFAULT_CAPACITY = 10;

	// Constructors
	public QueueUsingArray() {
		this(DEFAULT_CAPACITY);
	}

	public QueueUsingArray(int capacity) {
		data = (T[]) new Object[capacity];
		this.front = 0;
		this.size = 0;
	}

	// Methods
	public int size() {
		return this.size;
	}

	public boolean IsEmpty() {
		return this.size == 0;
	}

	public void enqueue(T item) throws Exception {
		if (this.size() == this.data.length) {
			throw new Exception("Queue Full.");
		}
		int ai = (this.front + this.size()) % this.data.length;
		this.data[ai] = item;
		this.size++;
	}

	public T dequeue() throws Exception {
		if (this.size() == 0) {
			throw new Exception("Queue Empty.");
		}
		T retval = this.data[this.front];

		this.data[this.front] = null;
		front = (front + 1) % this.data.length;

		this.size--;

		return retval;
	}

	public T front() throws Exception {
		if (this.size() == 0) {
			throw new Exception("Queue Empty.");
		}
		T retval = this.data[this.front];
		return retval;
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		String retval = "";
		for (int i = 0; i < this.size(); i++) {
			int ai = (this.front + i) % this.data.length;
			retval += this.data[ai] + "=>";
		}
		retval += "END";
		return retval;
	}
}
