package stacks_and_Queues.queues;

import stacks_and_Queues.common.IQueue;
import LinkedList.LinkedList;

public class QueueUsingLinkedList<T extends Comparable<T>> implements IQueue<T> {

	// Data Members
	private LinkedList<T> list;

	// Constructors
	public QueueUsingLinkedList() {
		this.list = new LinkedList<>();
	}

	// Methods
	public int size() {
		return this.list.size();
	}

	public boolean IsEmpty() {
		return this.size()==0;
	}

	public void enqueue(T item) throws Exception {
		this.list.AddLast(item);

	}

	public T dequeue() throws Exception {
		return this.list.removeFirst();
	}

	public T front() throws Exception {
		return this.list.getFirst();
	}

	public void display() {
		System.out.println(this);

	}
	
	public String toString(){
		return this.list.toString();
	}
}