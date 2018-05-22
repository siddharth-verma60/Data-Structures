package BinaryTree;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import stacks_and_Queues.common.IQueue;
import stacks_and_Queues.common.IStack;
import stacks_and_Queues.queues.*;
import stacks_and_Queues.stacks.StacksUsingDynamicArray;

public class BinaryTree {
	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public String toString() {
			String retval = "";
			if (this.left != null) {
				retval += this.left.data + "=>";
			}
			retval += this.data;

			if (this.right != null) {
				retval += "<=" + this.right.data;
			}

			retval += "\n";

			if (this.left != null) {
				retval += this.left;
			}
			if (this.right != null) {
				retval += this.right;
			}
			return retval;
		}

	}

	// Data Members
	private Node root;
	private int size;

	public BinaryTree() {
		Scanner scn = new Scanner(System.in);
		this.root = this.takeInput(scn, null, false);
	}

	// public BinaryTree(int[] preorder, int[] inorder) {
	// this.root = this.construct(preorder, 0, preorder.length - 1, inorder, 0,
	// inorder.length - 1);
	// }

	public BinaryTree(int[] postOrder, int[] inOrder) {
		this.root = this.ConstructPost(postOrder, 0, postOrder.length - 1, inOrder, 0, inOrder.length - 1);
	}

	private Node ConstructPost(int[] postOrder, int psi, int pli, int[] inOrder, int isi, int ili) {
		if (psi > pli || isi > ili) {
			return null;
		}
		Node retval = new Node(postOrder[pli], null, null);
		int si = -1;
		this.size++;
		for (int i = isi; i <= ili; i++) {
			if (inOrder[i] == retval.data) {
				si = i;
				break;
			}
		}

		int nri = si - isi;

		retval.left = this.ConstructPost(postOrder, psi, psi + nri - 1, inOrder, isi, si - 1);
		retval.right = this.ConstructPost(postOrder, psi + nri, pli - 1, inOrder, si + 1, ili);

		return retval;
	}

	private Node construct(int[] pre, int psi, int pli, int[] in, int isi, int ili) {
		if (psi > pli || isi > ili) {
			return null;
		}

		Node retval = new Node(pre[psi], null, null);
		this.size++;
		int si = -1;
		for (int i = isi; i <= ili; i++) {
			if (retval.data == in[i]) {
				si = i;
				break;
			}
		}
		int nri = si - isi;

		retval.left = construct(pre, psi + 1, psi + nri, in, isi, si - 1);
		retval.right = construct(pre, psi + nri + 1, pli, in, si + 1, ili);

		return retval;
	}

	private Node takeInput(Scanner scn, Node parent, boolean leftChild) {
		if (parent == null) {
			System.out.println("Enter the data for root : ");
		} else {
			if (leftChild) {
				System.out.println("Enter the data for left child of parent " + parent.data);
			} else {
				System.out.println("Enter the data for right child of parent " + parent.data);
			}
		}

		int cData = scn.nextInt();
		Node child = new Node(cData, null, null);
		this.size++;
		System.out.println("Do you have a left child for " + cData);
		boolean isLeft = scn.nextBoolean();
		if (isLeft) {
			child.left = takeInput(scn, child, true);
		}

		System.out.println("Do you have a right child for " + cData);
		boolean isRight = scn.nextBoolean();
		if (isRight) {
			child.right = takeInput(scn, child, false);
		}
		return child;
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		return this.root.toString();
	}

	public int size() {
		return this.size;
	}
	
	// Maximum element
	public int max() {
		return this.max(this.root);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		int lmax = max(node.left);
		int rmax = max(node.right);

		return Math.max(node.data, Math.max(lmax, rmax));
	}

	// Minimum Element
	public int min() {
		return this.min(this.root);
	}

	private int min(Node node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		}

		int lmin = min(node.left);
		int rmin = min(node.right);

		return Math.min(node.data, Math.min(lmin, rmin));

	}

	// Height of a Binary tree.
	public int height() {
		System.out.println(this.root.data + "efwe");
		return this.height(this.root);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}
		// System.out.println("Hi"+node.data);
		int lheight = height(node.left);
		int rheight = height(node.right);
		return Math.max(lheight, rheight) + 1;
	}

	// Bad Solution
	public int diameter() {
		return diameter(this.root);
	}

	private int diameter(Node node) {
		if (node == null) {
			return 0;
		}
		int ldia = diameter(node.left);
		int rdia = diameter(node.right);
		int lheight = this.height(node.left);
		int rheight = this.height(node.right);
		return Math.max(lheight + rheight + 2, Math.max(ldia, rdia));
	}

	// Good Solution- Dynamic Programming
	public int diameter2() {
		return diameter2(this.root).diameter;
	}

	private diaPair diameter2(Node node) {
		if (node == null) {
			return new diaPair(-1, 0);
		}

		diaPair lresult = diameter2(node.left);
		diaPair rresult = diameter2(node.right);

		// Calculating Height
		int height = Math.max(lresult.height, rresult.height) + 1;

		// Calculating diameter
		int f1 = lresult.diameter;
		int f2 = rresult.diameter;
		int f3 = lresult.height + rresult.height + 2;

		int diameter = Math.max(f3, Math.max(f1, f2));

		return new diaPair(height, diameter);
	}

	private class diaPair {
		int height;
		int diameter;

		diaPair(int height, int diameter) {
			this.height = height;
			this.diameter = diameter;
		}
	}

	// Orders
	public void preOrder() {
		preOrder(this.root);
	}

	private void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public void postOrder() {
		postOrder(this.root);
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + " ");
	}

	public void inOrder() {
		inOrder(this.root);
	}

	private void inOrder(Node node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		System.out.print(node.data + " ");
		inOrder(node.right);
	}

	public void preOrder_Iterative() throws Exception {
		IStack<Node> stack = new StacksUsingDynamicArray<>();
		stack.push(this.root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			System.out.print(node.data + ", ");
			if (node.left != null || node.right != null) {
				stack.push(node.right);
				stack.push(node.left);
			}

		}
		System.out.print("END");
	}

	public void LevelOrder() throws Exception {
		IQueue<Node> Q = new QueueUsingDynamicArrays<>();
		Q.enqueue(this.root);
		while (!Q.IsEmpty()) {
			Node node = Q.dequeue();
			System.out.print(node.data + ", ");
			if (node.left != null || node.right != null) {
				Q.enqueue(node.left);
				Q.enqueue(node.right);
			}
		}
		System.out.print("END");
	}

	// In-Order Predecessor- (Using stacks-Space Complexity)
	public void inOrderpre(int num) throws Exception {
		IStack<Integer> stack = new StacksUsingDynamicArray<>();
		inOrderpre(this.root, stack, num);
		while (!stack.isEmpty()) {
			stack.pop();
		}
	}

	private void inOrderpre(Node node, IStack<Integer> stack, int num) throws Exception {
		if (node == null) {
			return;
		}
		inOrderpre(node.left, stack, num);
		stack.push(node.data);
		if (stack.top() == num) {
			stack.pop();
			System.out.print(stack.top());
		}
		inOrderpre(node.right, stack, num);
	}

	public boolean find(int data) {
		return find(this.root, data);
	}

	private boolean find(Node node, int data) {
		boolean retval = false;
		if (node.data == data) {
			retval = true;
		}
		if (node.left != null) {
			find(node.left, data);
		}
		if (node.right != null) {
			find(node.right, data);
		}
		return retval;

	}

	public void mirror() {
		this.mirror(this.root);
	}

	private Node mirror(Node node) {
		if (node == null) {
			return null;
		}

		Node mirroredLeftRoot = this.mirror(node.left);
		Node mirroredRightRoot = this.mirror(node.right);

		node.left = mirroredRightRoot;
		node.right = mirroredLeftRoot;

		return node;
	}

	// isBST ? 1st approach.
	public boolean isBST1() {
		return this.isBST1(this.root);
	}

	private boolean isBST1(Node node) {
		if (node == null) {
			return true;
		}

		int lmax = this.max(node.left);
		int rmin = this.min(node.right);

		if (node.data < lmax || node.data > rmin) {
			return false;
		}

		boolean isLeftBST = this.isBST1(node.left);
		boolean isRightBST = this.isBST1(node.right);

		return isLeftBST && isRightBST;
	}

	// 2nd approach, to avoid unneccesary calling of min and max functions.
	public boolean isBST2() {
		return this.isBST2(this.root).isBST;
	}

	private class isBSTpair {
		int min;
		int max;
		boolean isBST;

		isBSTpair(int min, int max, boolean isBST) {
			this.min = min;
			this.max = max;
			this.isBST = isBST;
		}
	}

	private isBSTpair isBST2(Node node) {

		if (node == null) {
			return null;
		}

		isBSTpair retval = new isBSTpair(node.data, node.data, true);

		isBSTpair lresult = isBST2(node.left);
		isBSTpair rresult = isBST2(node.right);

		// Setting isBST of retval
		if (lresult != null && (!lresult.isBST || node.data < lresult.max)) {
			retval.isBST = false;
		}

		if (rresult != null && (!rresult.isBST || node.data > rresult.min)) {
			retval.isBST = false;
		}

		// Setting min and max of retval
		if (retval.isBST) {
			if (lresult != null) {
				retval.min = lresult.min;
			}

			if (rresult != null) {
				retval.max = rresult.max;
			}
		}

		return retval;
	}

	// 3rd approach. Working up to down.
	public boolean isBST3() {
		return this.isBST3(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST3(Node node, int min, int max) {

		if (node == null) {
			return true;
		}

		if (node.data < min && node.data > max) {
			return false;
		}

		return isBST3(node.left, min, node.data) && isBST3(node.right, node.data, max);
	}

	public int sum() {
		return sum(this.root);
	}

	private int sum(Node node) {

		if (node == null) {
			return 0;
		}

		int left_sum = sum(node.left);
		int right_sum = sum(node.right);

		return left_sum + node.data + right_sum;
	}

	// Check if a tree is balanced: Bad Approach
	public boolean isBalanced_1() {
		return this.isBalanced_1(this.root);
	}

	private boolean isBalanced_1(Node node) {

		if (node == null) {
			return true;
		}

		int left_height = height(node.left);
		int right_height = height(node.right);

		if (Math.abs(right_height - left_height) <= 1 && isBalanced_1(node.left) && isBalanced_1(node.right)) {
			return true;
		}

		return false;
	}

	// Check if a tree is balanced: Good Approach
	public boolean isBalanced_2() {
		return this.isBalanced_2(this.root).is_balanced;
	}

	private bal_pair isBalanced_2(Node node) {

		if (node == null) {
			return new bal_pair(0, true);
		}

		bal_pair retval = new bal_pair(0, false);

		bal_pair lresult = isBalanced_2(node.left);
		bal_pair rresult = isBalanced_2(node.right);

		// Calculate height
		int height = Math.max(lresult.height, rresult.height) + 1;
		retval.height = height;

		// Calculating is_balanced
		if (Math.abs(lresult.height - rresult.height) <= 1 && lresult.is_balanced && rresult.is_balanced) {
			retval.is_balanced = true;
		}

		return retval;
	}

	private class bal_pair {
		int height;
		boolean is_balanced;

		public bal_pair(int height, boolean is_balanced) {
			this.height = height;
			this.is_balanced = is_balanced;
		}
	}

	// Printing each level of the tree line by line:
	public void print_line_by_line() {
		print_line_by_line(this.root);
	}

	private void print_line_by_line(Node node) {

		if (node == null) {
			return;
		}
		Queue<Node> q = new java.util.LinkedList<>();
		q.add(node);

		while (!q.isEmpty()) {

			int level_size = q.size();

			while (level_size != 0) {

				Node num = q.poll();
				System.out.print(num.data + " ");

				if (num.left != null) {
					q.add(num.left);
				}
				if (num.right != null) {
					q.add(num.right);
				}

				level_size--;
			}
			System.out.println();
		}
	}

	// Deleting the tree
	public void delete() {
		delete(this.root);
		this.root = null;
	}

	private void delete(Node node) {

		if (node == null) {
			return;
		}

		delete(node.left);
		delete(node.right);

		System.out.println("Deleted Node: " + node.data);
		node = null;
	}

	// Print root to leaf paths.
	public void root_to_leaf_path() {
		root_to_leaf_path(this.root, new int[1000], 0);
	}

	private void root_to_leaf_path(Node node, int[] arr, int i) {
		if (node == null) {
			return;
		}

		arr[i] = node.data;
		i++;

		if (node.left == null && node.right == null) {
			for (int j = 0; j < i; j++) {
				System.out.print(arr[j] + ", ");
			}
			System.out.println();
			return;
		}

		root_to_leaf_path(node.left, arr, i);
		root_to_leaf_path(node.right, arr, i);
	}

	// Convert a tree into a linkedlist
	public void tree_to_list() {
		Node head = tree_to_list(this.root);

		int i = this.size;
		i = this.size;

		while (i-- > 0) {
			System.out.print(head.data + ", ");
			head = head.right;
		}

	}

	private Node tree_to_list(Node node) {

		if (node == null) {
			return null;
		}

		else if (node.left == null && node.right == null) {
			node.left = node;
			node.right = node;
			return node;
		}

		else if (node.left == null) {
			Node right_list = tree_to_list(node.right);

			node.right = right_list;
			node.left = right_list.left;
			right_list.left.right = node;
			right_list.left = node;
			return node;
		}

		else if (node.right == null) {
			Node left_list = tree_to_list(node.left);

			node.left = left_list.left;
			left_list.left.right = node;
			node.right = left_list;
			left_list.left = node;
			return left_list;
		}

		else {
			Node left_list = tree_to_list(node.left);
			Node right_list = tree_to_list(node.right);

			left_list.left.right = node;
			node.left = left_list.left;

			node.right = right_list;
			Node temp = right_list.left;
			right_list.left = node;

			temp.right = left_list;
			left_list.left = temp;

			return left_list;
		}
	}

	// Write a program that converts a given tree to its Double tree.
	// To create Double tree of the given tree,
	// create a new duplicate for each node, and insert the duplicate
	// as the left child of the original node.
	public void double_tree() {
		double_tree(this.root);
	}

	private void double_tree(Node node) {
		if (node == null) {
			return;
		}

		double_tree(node.left);
		double_tree(node.right);

		Node new_node = new Node(node.data, node.left, null);
		node.left = new_node;
	}

	/**
	 * Convert a given tree to its Sum Tree Given a Binary Tree where each node
	 * has positive and negative values. Convert this to a tree where each node
	 * contains the sum of the left and right sub trees in the original tree.
	 * The values of leaf nodes are changed to 0.
	 * 
	 * For example, the following tree
	 * 
	 * 10 / \ -2 6 / \ / \ 8 -4 7 5 should be changed to
	 * 
	 * 20(4-2+12+6) / \ 4(8-4) 12(7+5) / \ / \ 0 0 0 0
	 */
	public void sum_tree() {
		sum_tree(this.root);
	}

	private void sum_tree(Node node) {

		if (node.left == null && node.right == null) {
			node.data = 0;
			return;
		}

		int lsum = this.sum(node.left);
		int rsum = this.sum(node.right);

		node.data = lsum + rsum;

		sum_tree(node.left);
		sum_tree(node.right);
	}

	private Node getNode(Node node, int n) {

		if (node == null) {
			return null;
		}

		Node left_node = getNode(node.left, n);
		Node right_node = getNode(node.right, n);

		if (node.data == n) {
			return node;
		} else if (left_node != null && left_node.data == n) {
			return left_node;
		} else if (right_node != null && right_node.data == n) {
			return right_node;
		}

		else {
			return null;
		}

	}

	// Finding LCA (Lowest Common Ancestor)

	// Approach 1: By storing the paths and finding the common element
	public int lca_1(int n1, int n2) {

		// Node node_1=getNode(this.root, n1);
		// Node node_2=getNode(this.root, n2);

		ArrayList<Integer> path1 = new ArrayList<>();
		ArrayList<Integer> path2 = new ArrayList<>();

		if (!find_path(this.root, n1, path1) || !find_path(this.root, n2, path2)) {
			System.out.println((path1.size() > 0) ? "n1 is present" : "n1 is missing");
			System.out.println((path2.size() > 0) ? "n2 is present" : "n2 is missing");
			return -1;
		}

		int size = (path1.size() > path2.size()) ? path2.size() : path1.size();

		for (int i = size - 1; i >= 0; i--) {
			if (path1.get(i) == path2.get(i)) {
				return path1.get(i);
			}
		}
		return -1;
	}

	private boolean find_path(Node node, int n, ArrayList<Integer> list) {

		if (node == null) {
			return false;
		}

		list.add(node.data);

		if (node.data == n) {
			return true;
		}
		if (node.left != null && find_path(node.left, n, list)) {
			return true;
		}
		if (node.right != null && find_path(node.right, n, list)) {
			return true;
		} else {
			list.remove(list.size() - 1);
			return false;
		}
	}

	// Approach 2: Traversing the tree only once.
	public int lca_2(int n1, int n2) {
		return lca_2(this.root, n1, n2).data;
	}

	private Node lca_2(Node node, int n1, int n2) {
		if (node == null) {
			return null;
		}

		if (node.data == n1 || node.data == n2) {
			return node;
		}

		Node left_lca = lca_2(node.left, n1, n2);
		Node right_lca = lca_2(node.right, n1, n2);

		if (left_lca != null && right_lca != null) {
			return node;
		}

		return (left_lca != null) ? left_lca : right_lca;
	}

	// Largest BST in a subtree:
	private class sub_pair {
		boolean isbst;
		int size;
		int min;
		int max;

		public sub_pair(boolean isbst, int size, int min, int max) {
			this.isbst = isbst;
			this.size = size;
			this.min = min;
			this.max = max;
		}
	}

	public int subtree() {
		return subtree(this.root).size;
	}

	private sub_pair subtree(Node node) {
		if (node == null) {
			return new sub_pair(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}

		sub_pair left_subtree = subtree(node.left);
		sub_pair right_subtree = subtree(node.right);

		sub_pair retval = new sub_pair(false, 0, Math.min(node.data, left_subtree.min),
				Math.max(node.data, right_subtree.max));

		if (left_subtree.isbst && right_subtree.isbst && node.data > left_subtree.max
				&& node.data < right_subtree.min) {
			retval.isbst = true;
			retval.size = left_subtree.size + 1 + right_subtree.size;
		}

		else {
			retval.isbst = false;
			retval.size = Math.max(left_subtree.size, right_subtree.size);
		}

		return retval;
	}
}
