package stacks_and_Queues.stacks;

import stacks_and_Queues.common.*;

public class StacksUsingArrays<T> implements IStack<T> {

	//Data Members
	protected T[] data;
	protected int tos;										 //Index of top of the stack.
	public static final int DEFAULT_CAPACITY=10;
	
	//Constructors
	public StacksUsingArrays(){
		this(DEFAULT_CAPACITY);
	}	
	
	public StacksUsingArrays(int capacity){
		this.data=(T[]) new Object[capacity];
		this.tos=-1;
	}

	//Methods
	public int size() {
		return this.tos+1;
		
	}

	public boolean isEmpty() {
		return this.size()==0;
	}

	public void push(T item) throws Exception {
		if(this.data.length==this.size()){
			throw new Exception("Stack Full.");
		}
		this.tos++;
		this.data[this.tos]=item;
	}

	public T pop() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty Stack.");
		}
		T retval = this.data[this.tos];
		this.data[this.tos] = null;
		this.tos--;
		return retval;
	}

	public T top() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty Stack");
		}
		T retval = this.data[this.tos];
		return retval;
	}

	public void display() {
		System.out.println(this);	
	}
	
	public String toString() {
		String retval = "";
		for (int i = this.tos; i >=0; i--) {
			retval += this.data[i] + "=>";
		}
		retval += "END";
		return retval;
	}
	
	
}
