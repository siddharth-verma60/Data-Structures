package LinkedList;

public class Client {

	public static void main(String[] args) throws Exception {
		LinkedList<String> list=new LinkedList<>();
//		list.Display();
		list.Addfirst("Hello");
//		list.Display();
		list.AddLast("Kid");
//		list.Display();
		list.AddAt("Jaadu", 2);
//		list.Display();
		list.AddLast("Coding");
//		list.Display();
		list.AddLast("Blocks");
		list.AddLast("India");
		list.Display();
//		System.out.println(list.removeFirst());
//		list.Display();
//		list.removeLast();
//		list.Display();
//		list.removeAt(1);
//		list.Display();
//		System.out.println(list.isEmpty());
//		list.Addfirst("Sid");
//		list.Display();
//		list.AddAt("Jaadu", 2);
//		list.Display();
//		list.AddLast("Coding");
//		list.Display();
//		System.out.println(list.getFirst());
//		System.out.println(list.getLast());
//		System.out.println(list.getAt(2));
//		System.out.println(list.isEmpty());
//		System.out.println(list.size());
//		System.out.println(list.kthElementBack(2));
//		System.out.println(list.mid());
//		System.out.println(list.find("Jaadu"));
//		list.DataInverseRecursive();
//		list.Display();
//		list.IterativeReverseData();
//		list.Display();
//		list.MergeSort();
//		list.Display();
//		list.BubbleSort();
//		list.Display();
//		list.ReversePointersRecursive();
//		list.Display();
//		list.merge_sort();
//		list.Display();
		list.DataInverseIterative();
		list.Display();
//		LinkedList<String> list1=new LinkedList<>();
//		list1.Addfirst("Do");
//		list1.AddLast("Emy");
//		list1.AddLast("Farth");
//		list1.AddLast("Ooy");
//		list1.Display();
//		LinkedList<String> newlist=list.merge_1(list1);
//		newlist.Display();
		
	}

}
