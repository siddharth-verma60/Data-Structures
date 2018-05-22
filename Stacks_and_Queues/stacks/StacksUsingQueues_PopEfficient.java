package stacks_and_Queues.stacks;

import stacks_and_Queues.common.*;
import stacks_and_Queues.queues.*;

//Pop Efficient
public class StacksUsingQueues_PopEfficient<T> implements IStack<T> {

	// Data Members
	QueueUsingDynamicArrays<T> primaryQ;
	QueueUsingDynamicArrays<T> secondaryQ;

	// Constructors
	public StacksUsingQueues_PopEfficient() {
		primaryQ = new QueueUsingDynamicArrays<>();
		secondaryQ = new QueueUsingDynamicArrays<>();
	}

	// Methods
	public int size() {
		return this.primaryQ.size();
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void push(T item) throws Exception {
		while (this.primaryQ.size() > 0) {
			this.secondaryQ.enqueue(this.primaryQ.dequeue());
		}
		this.primaryQ.enqueue(item);
		while (this.secondaryQ.size() > 0) {
			this.primaryQ.enqueue(this.secondaryQ.dequeue());
		}
	}

	public T pop() throws Exception {
		return this.primaryQ.dequeue();
	}

	public T top() throws Exception {
		return this.primaryQ.front();
	}

	public void display() {
		System.out.println(this);

	}

	public String toString() {
		return this.primaryQ.toString();
	}
}