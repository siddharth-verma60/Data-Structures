package stacks_and_Queues;

import stacks_and_Queues.queues.*;
import stacks_and_Queues.stacks.*;
import stacks_and_Queues.common.*;

public class Client {

	public static void main(String[] args) throws Exception {
		// IStack<Integer> stack = new
		// StacksUsingQueues_PushEfficient<Integer>();
		// IStack<Integer> stack = new StacksUsingArrays<Integer>();
		// System.out.println(stack.isEmpty());
		// stack.push(12);
		// stack.display();
		// stack.push(18);
		// stack.display();
		// stack.push(10);
		// stack.display();
		// stack.push(3);
		// stack.display();
		// stack.push(23);
		// stack.display();
		// stack.push(50);
		// stack.display();
		// System.out.println(stack.pop());
		// stack.display();
		// System.out.println(stack.top());
		// stack.display();
		// System.out.println(stack.isEmpty());
		// System.out.println(stack.pop());
		// stack.display();
		// stack.push(50);
		// stack.display();
		// System.out.println(stack.pop());
		// stack.display();
		// System.out.println(stack.top());
		// stack.display();
		// System.out.println(isBalanced2("(){}[]"));
		// ReverseStack(stack);
		// stack.display();

		// IQueue<Integer> queue = new QueueUsingStacks_DequeueEfficient<>();
		//// IQueue<Integer> queue = new QueueUsingArray<>();
		// System.out.println(queue.IsEmpty());
		// queue.enqueue(45);
		// queue.display();
		// queue.enqueue(25);
		// queue.display();
		// queue.enqueue(95);
		// queue.display();
		// queue.enqueue(15);
		// queue.display();
		// queue.enqueue(55);
		// queue.display();
		// queue.enqueue(5);
		// queue.display();
		// System.out.println(queue.dequeue());
		// queue.display();
		// queue.enqueue(20);
		// queue.display();
		// System.out.println(queue.dequeue());
		// queue.display();
		// System.out.println(queue.front());
		// queue.display();
		// ReverseQueue(queue);
		// queue.display();

		duplicateBrackets("((a + b) + ((c+d)))");

	}

	// Balancing the string brackets
	public static boolean isBalanced(String str) throws Exception {
		StacksUsingLinkedList<Character> stack = new StacksUsingLinkedList<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '{' || str.charAt(i) == '(' || str.charAt(i) == '[') {
				stack.push(str.charAt(i));
			} else if (stack.isEmpty() && (str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}')) {
				return false;
			} else if ((str.charAt(i) == ']' && stack.top() == '[') || (str.charAt(i) == '}' && stack.top() == '{')
					|| (str.charAt(i) == ')' && stack.top() == '(')) {
				stack.pop();
			}
		}
		return stack.isEmpty();
	}

	// Better Solution
	public static boolean isBalanced2(String str) throws Exception {
		StacksUsingLinkedList<Character> stack = new StacksUsingLinkedList<>();
		for (int i = 0; i < str.length(); i++) {
			if ("({[".indexOf(str.charAt(i)) != -1) {
				stack.push(str.charAt(i));
			}

			else if (")}]".indexOf(str.charAt(i)) != -1) {

				if (stack.isEmpty()) {
					return false;
				}

				if ("({[".indexOf(stack.top()) == ")}]".indexOf(str.charAt(i))) {
					stack.pop();
				}

				else {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	// Reversing the stack
	public static <T extends Comparable<T>> void ReverseStack(IStack<T> org) throws Exception {
		IStack<T> helper = new StacksUsingLinkedList<>();
		while (!org.isEmpty()) {
			helper.push(org.pop());
		}
		reverseStackHelper(org, helper);
	}

	private static <T> void reverseStackHelper(IStack<T> org, IStack<T> helper) throws Exception {
		if (helper.isEmpty()) {
			return;
		}
		T item = helper.pop();
		reverseStackHelper(org, helper);
		org.push(item);
	}

	// Reversing the Queue
	public static <T> void ReverseQueue(IQueue<T> org) throws Exception {
		if (org.IsEmpty()) {
			return;
		}
		T item = org.dequeue();
		ReverseQueue(org);
		org.enqueue(item);
	}

	/* Question :- Check for duplicate parenthesis in an expression e.g. ((a + b) + ((c+d)))
	 *  has duplicate parenthesis.
	 */
	public static <T> void duplicateBrackets(String str) throws Exception {
		IStack<Character> stack = new StacksUsingLinkedList<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ')') {
				stack.push(str.charAt(i));
			} else {
				if (stack.top().equals('(')) {
					System.out.println("The string HAS duplicate brackets .");
					return;
				}
				while (!stack.top().equals('(')) {
					stack.pop();
				}
				stack.pop();

			}
		}
		//Below while loop is for the case like "(a+b)/(c+d)".
		while (!stack.isEmpty()) {					
			if (stack.top() != '(') {
				stack.pop();
			}
		}

		if (stack.isEmpty()) {
			System.out.println("The string has NO duplicate brackets .");
		}
	}

}
