package Trees;

public class Client {
//1 2 8 2 4 0 5 1 9 0 3 2 6 0 7 0
	public static void main(String[] args) throws Exception{

			GenericTree<Integer> tree=new GenericTree<>();
			tree.display();
			System.out.println(tree.max());
			System.out.println(tree.size());
			System.out.println(tree.Maxheight());
			tree.printAtDepth(2);
			System.out.println(tree.countLargerThan(1));
			System.out.println(tree.max1());
			tree.postOrder();
			System.out.println();
			tree.preOrder();
			System.out.println();
			tree.LevelOrder();
			System.out.println();
			tree.preOrder_Iterative();
	}
}