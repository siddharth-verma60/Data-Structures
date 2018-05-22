package Trees;

//1 2 8 2 4 0 5 1 9 0 3 2 6 0 7 0
import java.util.ArrayList;
import java.util.Scanner;

import stacks_and_Queues.stacks.StacksUsingLinkedList;
import stacks_and_Queues.common.IQueue;
import stacks_and_Queues.common.IStack;
import stacks_and_Queues.queues.*;

public class GenericTree<T extends Comparable<T>> {

	private class Node implements Comparable<Node> {
		T data;
		ArrayList<Node> children;

		Node(T data) {
			this.data = data;
			this.children = new ArrayList<>();
		}

		public String toString() {
			String retval = "";
			retval += this.data + " => ";
			for (int i = 0; i < this.children.size(); i++) {
				retval += this.children.get(i).data + ", ";
			}
			retval += "END\n";
			for (int i = 0; i < this.children.size(); i++) {
				retval += this.children.get(i).toString();
			}
			return retval;
		}

		@Override
		public int compareTo(GenericTree<T>.Node arg0) {
			return this.data.compareTo(arg0.data);
		}
	}

	// Data Members
	private Node root;

	// Constructor
	public GenericTree() {
		Scanner scn = new Scanner(System.in);
		this.root = this.takeInput(scn, null, 0);
	}

	// Methods
	private Node takeInput(Scanner scn, Node parent, int ithChild) {
		if (parent == null) {
			System.out.println("Enter the data for root : ");
		} else {
			System.out.println("Enter the data for " + ithChild + "th child of " + parent.data);
		}
		Integer cData = scn.nextInt();

		System.out.println("Enter the number of children for " + cData + ": ");
		Integer numGC = scn.nextInt(); // Number of GrandChildren

		Node child = new Node((T) cData);

		for (int i = 0; i < numGC; i++) {
			Node grandchild = this.takeInput(scn, child, i + 1);
			child.children.add(grandchild);
		}
		return child;
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		return this.root.toString();
	}

	// Max Element
	public T max() {
		return MaxHelper(this.root).data;
	}

	private Node MaxHelper(Node node) {
		Node retval = node;
		for (int i = 0; i < node.children.size(); i++) {
			Node max = MaxHelper(node.children.get(i));
			if (max.data.compareTo(retval.data) > 0) {
				retval = max;
			}
		}
		return retval;
	}

	// Size Of Tree
	public int size() {
		return sizeHelper(this.root);
	}

	private int sizeHelper(Node node) {
		int retval = 0;
		for (int i = 0; i < node.children.size(); i++) {
			int cSize = sizeHelper(node.children.get(i));
			retval += cSize;
		}
		return retval + 1;
	}

	// Max Height Of Node
	public int Maxheight() {
		return Maxheight(this.root);
	}

	private int Maxheight(Node node) {
		int retval = -1;
		for (int i = 0; i < node.children.size(); i++) {
			int height = Maxheight(node.children.get(i));
			if (height > retval) {
				retval = height;
			}
		}
		return retval + 1;
	}

	// Print At Depth
	public void printAtDepth(int k) {
		printAtDepth(this.root, k);
		System.out.println("END");
	}

	private void printAtDepth(Node node, int k) {
		if (k == 0) {
			System.out.print(node.data + ", ");
			return;
		}
		for (int i = 0; i < node.children.size(); i++) {
			printAtDepth(node.children.get(i), k - 1);
		}
	}

	// Number of Nodes Larger than Integer 'X'
	public int countLargerThan(Integer x) {
		return count_Helper(this.root, x);
	}

	public int count_Helper(Node node, Integer x) {
		int retval = 0;
		for (int i = 0; i < node.children.size(); i++) {
			int cSize = count_Helper(node.children.get(i), x);
			retval += cSize;
		}
		if ((Integer) node.data > x) {
			retval += 1;
		}
		return retval;
	}

	public T max1() {
		return max2(this.root).node.data;
	}

	// Node which has sum of itself and its children maximum (1st Approach -
	// Bad. The function SumPC calls itself unnecessarily)
	private Node max1(Node node) {
		Node retval = node;
		for (int i = 0; i < node.children.size(); i++) {
			Node maximum = max1(node.children.get(i));
			if (this.SumPC(retval) < this.SumPC(maximum)) {
				retval = maximum;
			}
		}
		return retval;
	}

	// Good Approach(Using Pair Class- Dynamic Programming)
	private Pair max2(Node node) {
		Pair retval = new Pair();
		retval.node = node;
		retval.sum = this.SumPC(node);
		for (int i = 0; i < node.children.size(); i++) {
			Pair cmax = max2(node.children.get(i));
			if (cmax.sum > retval.sum) {
				retval = cmax;
			}
		}
		return retval;
	}

	private class Pair { // this is made to assist max2 and storing values
		Node node;
		int sum;
	}

	private int SumPC(Node node) { // Sum of the parent and its children
		// System.out.println("Hi"+node.data); //This line tells us the number
		// of times the function is called.
		int retval = (Integer) node.data;
		for (int i = 0; i < node.children.size(); i++) {
			int num = (Integer) node.children.get(i).data;
			retval += num;
		}
		return retval;
	}

	// Orders

	// PreOrder-Recursive
	public void preOrder() {
		this.preOrder(this.root);
		System.out.println("END");
	}

	private void preOrder(Node node) {
		System.out.print(node.data + ", ");
		for (int i = 0; i < node.children.size(); i++) {
			preOrder(node.children.get(i));
		}
	}

	// Post Order-Recursive
	public void postOrder() {
		this.postOrder(this.root);
		System.out.println("END");
	}

	private void postOrder(Node node) {
		for (int i = 0; i < node.children.size(); i++) {
			postOrder(node.children.get(i));
		}
		System.out.print(node.data + ", ");
	}

	// Level Order
	public void LevelOrder() throws Exception {
		IQueue<Node> queue = new QueueUsingLinkedList<>();
		queue.enqueue(this.root);
		while (!queue.IsEmpty()) {
			Node node = queue.dequeue();
			System.out.print(node.data + ", ");
			for (int i = 0; i < node.children.size(); i++) {
				queue.enqueue(node.children.get(i));
			}
		}
		System.out.println("END");
	}
	
	// PreOrder-Iterative
	public void preOrder_Iterative() throws Exception{
		IStack<Node> stack = new StacksUsingLinkedList<>();
		stack.push(this.root);
		while(!stack.isEmpty()){
			Node node=stack.pop();
			System.out.print(node.data+", ");
			for(int i=node.children.size()-1;i>=0;i--){
				stack.push(node.children.get(i));
			}
		}
		System.out.println("END");
	}
	

}