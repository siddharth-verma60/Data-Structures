package LinkedList;

public class LinkedList<T extends Comparable<T>> {
	private class Node {
		// Data Members
		private T data;
		private Node next;

		// Constructor
		Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	// Data Members
	private Node head;
	private Node tail;
	private int size;

	// Constructor
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public LinkedList(Node head, Node tail, int size) {
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	// Methods
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public T getFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty.");
		}
		return this.head.data;
	}

	public T getLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty.");
		}
		return this.tail.data;
	}

	public T getAt(int index) throws Exception {
		return this.getNodeAt(index).data;
	}

	private Node getNodeAt(int index) throws Exception {
		if (index < 0 || index >= size) {
			throw new Exception(" Index Out of Bounds.");
		}
		Node temp = this.head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}

	public void Addfirst(T item) {
		Node newnode = new Node(item, this.head);
		this.head = newnode;
		if (this.size() == 0) {
			this.tail = this.head;
		}
		this.size++;
	}

	public void AddLast(T item) {
		Node newnode = new Node(item, null);
		if (this.size() == 0) {
			this.tail = newnode;
			this.head = this.tail;
		} else {
			this.tail.next = newnode;
			this.tail = newnode;
		}
		this.size++;
	}

	public void AddAt(T item, int index) throws Exception {
		if (index < 0 || index > this.size()) {
			throw new Exception("Index out of bounds .");
		}
		if (index == 0) {
			this.Addfirst(item);
			return;
		}
		if (index == this.size()) {
			this.AddLast(item);
			return;
		}
		Node temp = this.getNodeAt(index - 1);
		Node newnode = new Node(item, temp.next);
		temp.next = newnode;
		this.size++;
	}

	public T removeFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Invalid Operation.");
		}
		T retVal = this.head.data;
		if (this.size == 1) {
			this.head = null;
			this.tail = null;
		} else {
			this.head = this.head.next;
		}
		this.size--;
		return retVal;
	}

	public T removeLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Invalid Operation.");
		}
		T retval = this.tail.data;
		if (this.size == 1) {
			this.head = null;
			this.tail = null;
		} else {
			Node temp = this.getNodeAt(this.size() - 2);
			temp.next = null;
			this.tail = temp;
		}
		this.size--;
		return retval;
	}

	public T removeAt(int index) throws Exception {
		if (index < 0 || index >= this.size()) {
			throw new Exception("Invalid Operation.");
		}
		if (index == 0) {
			return this.removeFirst();
		} else if (index == this.size() - 1) {
			return this.removeLast();
		} else {
			Node temp = this.getNodeAt(index - 1);
			T retval = temp.next.data;
			temp.next = temp.next.next;
			this.size--;
			return retval;
		}
	}

	public void Display() {
		System.out.println(this);
	}

	public String toString() {
		String retval = "";
		Node temp = this.head;
		while (temp != null) {
			retval += temp.data + "=>";
			temp = temp.next;
		}
		retval += "END";
		return retval;
	}

	public T kthElementBack(int k) throws Exception {
		if (k <= 0 || k > this.size()) {
			throw new Exception("Invalid Operation.");
		}
		Node fast = this.head;
		Node slow = this.head;
		for (int i = 1; i <= k; i++) {
			fast = fast.next;
		}
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow.data;
	}

	public T mid() throws Exception {
		return MidNode().data;
	}

	private Node MidNode() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty LinkedList.");
		}
		
		Node OrgHead = this.head;
		Node b = OrgHead.next;

		while (b != null && b.next != null) {
			OrgHead = OrgHead.next;
			b = (b.next).next;
		}
		return OrgHead;
	}

	public int find(T data) {
		return find(data, this.head, 0);
	}

	private int find(T data, Node node, int idx) {
		if (node == null) {
			return -1;
		}
		if (node.data.equals(data)) {
			return idx;
		}
		return find(data, node.next, idx + 1);
	}

	// Iterative Data Inverse = O(n)
	public void DataInverseIterative() throws Exception {
		int sIndex = 0, lIndex = this.size() - 1;
		while (sIndex < lIndex) {
			Node s_data = this.getNodeAt(sIndex);
			Node l_data = this.getNodeAt(lIndex);

			T temp = s_data.data;
			s_data.data = l_data.data;
			l_data.data = temp;

			sIndex++;
			lIndex--;
		}
	}

	// Recursive Data Inverse = O(n)
	public void DataInverseRecursive() throws Exception {
		Node midnode = this.MidNode();
		Node temphead = this.head;
		RecursiveDataInverse(midnode.next);
		this.head = temphead;
	}

	private void RecursiveDataInverse(Node temp) {
		if (temp == null) {
			return;
		}
		RecursiveDataInverse(temp.next);
		T temp1 = temp.data;
		temp.data = this.head.data;
		this.head.data = temp1;
		this.head = head.next;
	}

	// Iterative Pointer Inverse = o(n)
	public void IterativeReversePointers() {
		Node PreviousNode = this.head;
		Node CurrentNode = this.head.next;
		Node NextNode = this.head.next.next;
		while (NextNode != null) {
			CurrentNode.next = PreviousNode;
			PreviousNode = CurrentNode;
			CurrentNode = NextNode;
			NextNode = NextNode.next;
		}
		CurrentNode.next = PreviousNode;
		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;
		this.tail.next = null;
	}

	// Recursive Pointer Inverse = o(n)
	public void ReversePointersRecursive() {
		RecursiveReversePointers(this.head);
		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;
		this.tail.next = null;
	}

	private void RecursiveReversePointers(Node node) {
		if (node == this.tail) {
			return;
		}
		RecursiveReversePointers(node.next);
		node.next.next = node;
	}

	// MergeSort-Method 1
	private Node merge(Node a, Node b) {
		Node temp = new Node(null, null);
		Node head = temp;
		Node c = head;
		while (a != null && b != null) {
			if (a.data.compareTo(b.data) < 0) {
				c.next = a;
				c = a;
				a = a.next;
			} else {
				c.next = b;
				c = b;
				b = b.next;
			}
		}
		c.next = (a == null) ? b : a;
		return head.next;
	}

	public void MergeSort() {
		change(mergesort(this.head));
	}

	private Node mergesort(Node originalHead) {
		if (originalHead == null || originalHead.next == null) {
			return originalHead;
		}
		Node a = originalHead;
		Node b = originalHead.next;
		while (b != null && b.next != null) {
			originalHead = originalHead.next;
			b = (b.next).next;
		}
		b = originalHead.next;
		originalHead.next = null;
		Node fhalf = mergesort(a);
		Node shalf = mergesort(b);
		Node sorted = this.merge(fhalf, shalf);
		return sorted;
	}

	// MergeSort-Method 2
	public LinkedList<T> merge_1(LinkedList<T> other) {
		Node ttemp = this.head;
		Node otemp = other.head;
		LinkedList<T> retval = new LinkedList<>();
		while (ttemp != null && otemp != null) {
			if (ttemp.data.compareTo(otemp.data) < 0) {
				retval.AddLast(ttemp.data);
				ttemp = ttemp.next;
			} else {
				retval.AddLast(otemp.data);
				otemp = otemp.next;
			}
		}
		while (ttemp != null) {
			retval.AddLast(ttemp.data);
			ttemp = ttemp.next;
		}
		while (otemp != null) {
			retval.AddLast(otemp.data);
			otemp = otemp.next;
		}
		return retval;
	}

	public void merge_sort() throws Exception {
		LinkedList<T> sorted = this.merge_sort_helper();
		this.head = sorted.head;
		this.tail = sorted.tail;
		this.size = sorted.size;
	}

	private LinkedList<T> merge_sort_helper() throws Exception {
		if (this.size() == 1) {
			return this;
		}
		Node midnode = this.MidNode();
		Node midNext = midnode.next;

		midnode.next = null;

		LinkedList<T> fhalf = new LinkedList<>(this.head, midnode, (this.size() + 1) / 2);
		LinkedList<T> shalf = new LinkedList<>(midNext, this.tail, this.size() / 2);
		fhalf = fhalf.merge_sort_helper();
		shalf = shalf.merge_sort_helper();
		LinkedList<T> sorted = fhalf.merge_1(shalf);
		return sorted;
	}

	public void change(Node result) {
		this.head = result;
	}

	public void BubbleSort() {
		Node OrgHead = this.head;
		Node temp = OrgHead;
		for (int i = 0; i < this.size(); i++) {
			temp = OrgHead;
			for (int j = 0; j < this.size() - i - 1; j++, temp = temp.next) {
				if (temp.data.compareTo(temp.next.data) > 0) {
					T temp1 = temp.data;
					temp.data = temp.next.data;
					temp.next.data = temp1;
				}
			}
		}
	}
}