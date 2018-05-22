package bst;

public class Client {

	public static void main(String[] args) throws Exception {
		BST bst=new BST();
		bst.addElement(50);
		bst.addElement(25);
		bst.addElement(12);
		bst.addElement(37);
		bst.addElement(75);
		bst.addElement(62);
		bst.addElement(87);
		bst.display();
		System.out.println(bst.min());
		System.out.println(bst.max());
//		System.out.println(bst.find(87));
//		bst.removeElement(50);
//		bst.display();
//		bst.printInRange(12, 87);
//		int[] arr={1,2,3,4,5};
//		BST bst1=new BST(arr);
//		bst1.display();
		bst.changeTree();
		bst.display();
	}

}
