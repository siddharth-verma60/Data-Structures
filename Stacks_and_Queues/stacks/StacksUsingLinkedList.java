package stacks_and_Queues.stacks;

import LinkedList.LinkedList;
import stacks_and_Queues.common.IStack;

public class StacksUsingLinkedList<T extends Comparable<T>> implements IStack<T> {

	// Data Members
	private LinkedList<T> list;

	// Constructor
	public StacksUsingLinkedList() {
		this.list = new LinkedList<>();
	}
	
	//Methods
	public int size() {
		return this.list.size();
	}

	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	public void push(T item) throws Exception {
		this.list.Addfirst(item);		
	}

	public T pop() throws Exception {
		return this.list.removeFirst();
	}

	public T top() throws Exception {
		return this.list.getFirst();
	}

	public void display() {
		System.out.println(this);		
	}
	
	public String toString(){
		return this.list.toString();
	}

}
