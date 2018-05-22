package stacks_and_Queues.common;

public interface IStack<T> {
	int size();

	boolean isEmpty();

	void push(T item) throws Exception;

	T pop() throws Exception;

	T top() throws Exception;

	void display();
}
