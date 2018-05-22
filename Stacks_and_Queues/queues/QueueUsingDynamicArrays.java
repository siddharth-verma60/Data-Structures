package stacks_and_Queues.queues;

import stacks_and_Queues.common.*;

public class QueueUsingDynamicArrays<T> extends QueueUsingArray<T> implements IQueue<T> {

	// Constructors
	public QueueUsingDynamicArrays() {
		super();
	}

	public QueueUsingDynamicArrays(int capacity) {
		super(capacity);
	}

	// Methods
	public void enqueue(T item) {
		if (this.size() == this.data.length) {
			T[] temp = (T[]) new Object[2 * this.size()];

			for (int i = 0; i < this.size(); i++) {
				int ai = (this.front + i) % this.data.length;
				temp[i] = this.data[i];
			}

			this.data = temp;
			this.front = 0;
		}

		int ai = (this.front + this.size()) % this.data.length;
		this.data[ai] = item;

		this.size++;
	}

}
