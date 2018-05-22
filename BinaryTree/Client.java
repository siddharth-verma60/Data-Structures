package BinaryTree;

//1 true 8 true 4 false false true 5 false false true 3 true 6 false false true 7 false false
//1 true 8 true 4 true 12 false false false true 5 false false true 3 true 6 false false true 7 false false
//1 true 2 true 4 true 8 false false false true 5 false false true 3 true 8 false false false
//50 true 25 true 12 false false true 37 false false true 75 true 62 false false true 87 false false

public class Client {

	public static void main(String[] args) {
		// BinaryTree bt=new BinaryTree();
		// bt.display();

		// System.out.println(bt.max());
		// System.out.println(bt.min());

		// System.out.println(bt.isBST1());
		// System.out.println(bt.isBST2());
		// System.out.println(bt.isBST3());

		int[] pre = { 2, 5, 1, 8, 9, 7, 3, 4, 6 };
		 int[] in = { 8, 1, 5, 9, 2, 3, 4, 7, 6 };
		 int[] post = { 8, 1, 9, 5, 4, 3, 6, 7, 2 };

//		int[] in = { 8, -2, -4, 10, 7, 6, 5 };
//		int[] post = { 8, -4, -2, 7, 5, 6, 10 };

		// BinaryTree bt=new BinaryTree(pre, in);
		// bt.display();

		BinaryTree bt = new BinaryTree(post, in);
		bt.display(); 
		System.out.println(bt.max());
		// System.out.println(bt.sum());
		// System.out.println(bt.isBalanced());
		//
		// bt.print_line_by_line();

		// bt.root_to_leaf_path();
		//bt.tree_to_list();
		// System.out.println(bt.isBalanced_1());
		// System.out.println(bt.isBalanced_2());

//		bt.double_tree();
//		bt.display();
		
//		bt.sum_tree();
//		bt.display();
		
//		System.out.println(bt.lca_1(5,4));
//		System.out.println(bt.lca_2(6,4));

	}

}
