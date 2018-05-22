package bst;

import LinkedList.LinkedList;

public class BST {
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

	public BST() {
		this.root = null;
		this.size = 0;
	}

	public BST(int[] sortedArray) {
		this.root = this.construct(sortedArray, 0, sortedArray.length - 1);
	}

	private Node construct(int[] sortedArray, int si, int li) {
		if (si > li) {
			return null;
		}

		int mid = (si + li) / 2;
		Node retval = new Node(sortedArray[mid], null, null);

		retval.left = this.construct(sortedArray, si, mid - 1);
		retval.right = this.construct(sortedArray, mid + 1, li);

		return retval;

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

	public int height() {
		// System.out.println(this.root.data + "efwe");
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

	// Maximum element of the tree
	public int max() {
		return this.max(this.root);
	}

	private int max(Node node) {
		while (node.right != null) {
			node = node.right;
		}
		return node.data;

	}

	// Minimum element of the tree
	public int min() {
		return this.min(this.root).data;
	}

	private Node min(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	// Add Element
	public void addElement(int data) {
		this.root = this.addElement(this.root, data);
	}

	private Node addElement(Node node, int data) {
		if (node == null) {
			this.size++;
			return new Node(data, null, null);
		}
		if (data < node.data) {
			node.left = addElement(node.left, data);
			return node;
		}
		if (data > node.data) {
			node.right = addElement(node.right, data);
			return node;
		} else {
			return node;
		}

	}

	// Remove Element
	public void removeElement(int data) {
		this.removeElement(this.root, data);
	}

	private Node removeElement(Node node, int data) {
		if (node == null) {
			return null;
		}

		if (data < node.data) {
			node.left = removeElement(node.left, data);
			return node;
		} else if (data > node.data) {
			node.right = removeElement(node.right, data);
			return node;
		} else {
			// found

			if (node.left == null && node.right == null) {
				this.size--;
				return null;
			} else if (node.left == null) {
				this.size--;
				return node.right;
			} else if (node.right == null) {
				this.size--;
				return node.left;
			} else {
				int lmax = this.max(node.left);
				node.data = lmax;

				node.left = this.removeElement(node.left, lmax);

				return node;
			}
		}

	}

	public boolean find(int data) {
		return find(this.root, data);
	}

	private boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}
		if (data < node.data) {
			return find(node.left, data);
		} else if (data > node.data) {
			return find(node.right, data);
		} else {
			return true;
		}

	}

	public void mirror() {
		mirror(this.root);
	}

	private void mirror(Node node) {

		Node temp = node.left;
		node.left = node.right;
		node.right = temp;

		if (node.left != null) {
			mirror(node.left);
		}
		if (node.right != null) {
			mirror(node.right);
		}
	}

	public void printInRange(int k1, int k2) {
		this.printInRange(this.root, k1, k2);
		System.out.println("END");
	}

	private void printInRange(Node node, int k1, int k2) {
		if (node == null) {
			return;
		}
		if (node.data > k1 && node.data < k2) {
			System.out.print(node.data + ", ");
			this.printInRange(node.left, k1, k2);
			this.printInRange(node.right, k1, k2);
		}
		if (k1 < node.data && k2 < node.data) {
			this.printInRange(node.left, k1, k2);
		}
		if (k1 > node.data && k2 > node.data) {
			this.printInRange(node.right, k1, k2);
		}
	}

	public LinkedList<Integer> makeSortedLinkedList() {
		LinkedList<Integer> retval = new LinkedList<>();
		this.makeSortedLinkedList(this.root, retval);
		return retval;
	}

	private void makeSortedLinkedList(Node node, LinkedList<Integer> retval) {
		if (node == null) {
			return;
		}
		makeSortedLinkedList(node.left, retval);
		retval.AddLast(node.data);
		makeSortedLinkedList(node.right, retval);
	}

	// Changing a heirachical tree into a straight chain tree.
	public void changeTree() {
		this.root = this.changeTree(this.root).head;
	}

	private class Pair {
		Node head;
		Node tail;

		Pair(Node head, Node tail) {
			this.head = head;
			this.tail = tail;
		}
	}

	private Pair changeTree(Node node) {
		if (node == null) {
			return null;
		}

		Pair retval = new Pair(node, node);
		Pair lresult = changeTree(node.left);
		Pair rresult = changeTree(node.right);

		if (lresult != null) {
			lresult.tail.right = node;
			retval.head = lresult.head;
		}

		node.left = null;

		if (rresult != null) {
			node.right = rresult.head;
			retval.tail = rresult.tail;
		}
		return retval;

	}

}