package stacks_and_Queues.queues;

import stacks_and_Queues.common.IQueue;
import stacks_and_Queues.common.IStack;
import stacks_and_Queues.stacks.StacksUsingLinkedList;

public class QueueUsingStacks_DequeueEfficient<T extends Comparable<T>> implements IQueue<T> {

	// Data Members
	IStack<T> primaryStack;
	IStack<T> secondaryStack;

	// Constructor
	public QueueUsingStacks_DequeueEfficient() {
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
		while (!this.primaryStack.isEmpty()) {
			this.secondaryStack.push(this.primaryStack.pop());
		}
		this.secondaryStack.push(item);
		while (!this.secondaryStack.isEmpty()) {
			this.primaryStack.push(this.secondaryStack.pop());
		}
	}

	public T dequeue() throws Exception {
		return this.primaryStack.pop();
	}

	public T front() throws Exception {
		return this.primaryStack.top();
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		return this.primaryStack.toString();
	}

}
