package stacks_and_Queues.common;

public interface IQueue<T> {
	int size();

	boolean IsEmpty();

	void enqueue(T item) throws Exception;

	T dequeue() throws Exception;

	T front() throws Exception;

	void display();
}
