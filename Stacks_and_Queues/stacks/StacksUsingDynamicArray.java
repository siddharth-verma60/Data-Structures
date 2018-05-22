package stacks_and_Queues.stacks;

import stacks_and_Queues.common.IStack;

public class StacksUsingDynamicArray<T> extends StacksUsingArrays<T> implements IStack<T> {

	// Constructors
	public StacksUsingDynamicArray() {
		super();
	}

	public StacksUsingDynamicArray(int capacity) {
		super(capacity);
	}

	// Methods
	public void push(T item) {
		if (this.data.length == this.size()) {
			T[] temp = (T[]) new Object[2 * this.size()];
			for (int i = 0; i <= this.tos; i++) {
				temp[i] = this.data[i];
			}
			this.data = temp;
		}
		this.tos++;
		this.data[tos] = item;
	}
}
