package stacks_and_Queues.stacks;

import stacks_and_Queues.common.*;
import stacks_and_Queues.queues.*;

//Push Efficient
public class StacksUsingQueues_PushEfficient<T> implements IStack<T> {

	// Data Members
	QueueUsingDynamicArrays<T> primaryQ;
	QueueUsingDynamicArrays<T> secondaryQ;

	// Constructors
	public StacksUsingQueues_PushEfficient() {
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
		this.primaryQ.enqueue(item);
	}

	public T pop() throws Exception {
		while (this.size() > 1) {
			T item = this.primaryQ.dequeue();
			this.secondaryQ.enqueue(item);
		}
		T retval = this.primaryQ.dequeue();

		QueueUsingDynamicArrays<T> temp = this.primaryQ;
		this.primaryQ = this.secondaryQ;
		this.secondaryQ = temp;

		return retval;
	}

	public T top() throws Exception {
		while (this.size() > 1) {
			T item = this.primaryQ.dequeue();
			this.secondaryQ.enqueue(item);
		}
		T retval = this.primaryQ.dequeue();
		this.secondaryQ.enqueue(retval);
		QueueUsingDynamicArrays<T> temp = this.primaryQ;
		this.primaryQ = this.secondaryQ;
		this.secondaryQ = temp;

		return retval;
	}

	public void display() {
		System.out.println(this);

	}

	public String toString() {
		try {
			this.ReverseQueue(primaryQ);
		} catch (Exception ex) {

		}
		String retval = this.primaryQ.toString();
		try {
			this.ReverseQueue(primaryQ);
		} catch (Exception ex) {

		}
		return retval;
	}

	public void ReverseQueue(QueueUsingDynamicArrays<T> queue) throws Exception {
		if (queue.IsEmpty()) {
			return;
		}
		T item = queue.dequeue();
		ReverseQueue(queue);
		queue.enqueue(item);
	}
}
