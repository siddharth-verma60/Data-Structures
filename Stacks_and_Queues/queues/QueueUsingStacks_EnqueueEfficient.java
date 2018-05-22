package stacks_and_Queues.queues;

import stacks_and_Queues.common.IQueue;
import stacks_and_Queues.common.IStack;
import stacks_and_Queues.stacks.StacksUsingLinkedList;

public class QueueUsingStacks_EnqueueEfficient<T extends Comparable<T>> implements IQueue<T> {

	// Data Members
	IStack<T> primaryStack;
	IStack<T> secondaryStack;

	// Constructor
	public QueueUsingStacks_EnqueueEfficient() {
		this.primaryStack = new StacksUsingLinkedList<>();
		this.secondaryStack = new StacksUsingLinkedList<>();
	}

	// Methods
	public int size() {
		return this.primaryStack.size();
	}

	public boolean IsEmpty() {
		return this.size() == 0;
	}

	public void enqueue(T item) throws Exception {
		this.primaryStack.push(item);
	}

	public T dequeue() throws Exception {
		while (!this.primaryStack.isEmpty()) {
			T item = this.primaryStack.pop();
			this.secondaryStack.push(item);
		}

		T retval = this.secondaryStack.pop();

		while (!this.secondaryStack.isEmpty()) {
			this.primaryStack.push(this.secondaryStack.pop());
		}

		return retval;
	}

	public T front() throws Exception {
		while (!this.primaryStack.isEmpty()) {
			T item = this.primaryStack.pop();
			this.secondaryStack.push(item);
		}

		T retval = this.secondaryStack.top();

		while (!this.secondaryStack.isEmpty()) {
			this.primaryStack.push(this.secondaryStack.pop());
		}

		return retval;
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		try {
			while (!this.primaryStack.isEmpty()) {
				T item = this.primaryStack.pop();
				this.secondaryStack.push(item);
			}
		} catch (Exception ex) {

		}

		String retval = this.secondaryStack.toString();

		try {
			while (!this.secondaryStack.isEmpty()) {
				this.primaryStack.push(this.secondaryStack.pop());
			}
		} catch (Exception ex) {

		}
		return retval;
	}

}
