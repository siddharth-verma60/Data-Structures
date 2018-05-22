package heapsAndPriorityqueues;

public class Client {

	public static void main(String[] args) throws Exception {
		// PriorityQueue<Integer,String> marksMaxHeap=new Heap<>();
		//
		// marksMaxHeap.add(95, "A");
		// marksMaxHeap.add(105, "B");
		// marksMaxHeap.add(85, "C");
		// marksMaxHeap.add(88, "D");
		// marksMaxHeap.add(89, "E");
		// marksMaxHeap.add(99, "F");
		// marksMaxHeap.add(33, "G");
		// marksMaxHeap.add(20, "H");
		//
		// marksMaxHeap.display();
		// while(!marksMaxHeap.isEmpty()){
		// System.out.println(marksMaxHeap.get());
		// System.out.println(marksMaxHeap.remove());
		//
		// marksMaxHeap.display();
		// }

		// PriorityQueue<Integer, String> marksMinHeap = new Heap<>(true);
		//
		// marksMinHeap.add(95, "A");
		// marksMinHeap.add(105, "B");
		// marksMinHeap.add(85, "C");
		// marksMinHeap.add(88, "D");
		// marksMinHeap.add(89, "E");
		// marksMinHeap.add(99, "F");
		// marksMinHeap.add(33, "G");
		// marksMinHeap.add(20, "H");
		//
		// marksMinHeap.display();
		// while (!marksMinHeap.isEmpty()) {
		// System.out.println(marksMinHeap.get());
		// System.out.println(marksMinHeap.remove());
		//
		// marksMinHeap.display();
		// }
		//
		// Integer[] arr1={95,105,85,88,89,99,33,20};
		// Character[] arr2={'A','B','C','D','E','F','G','H'};
		// PriorityQueue<Integer[], Character[]> marksMaxHeapArrays = new
		// Heap<>(false,arr1,arr2);
		//
		// marksMaxHeap.add(95, "A");
		// marksMaxHeap.add(105, "B");
		// marksMaxHeap.add(85, "C");
		// marksMaxHeap.add(88, "D");
		// marksMaxHeap.add(89, "E");
		// marksMaxHeap.add(99, "F");
		// marksMaxHeap.add(33, "G");
		// marksMaxHeap.add(20, "H");
		//
		// marksMaxHeap.display();
		// while (!marksMaxHeap.isEmpty()) {
		// System.out.println(marksMaxHeap.get());
		// System.out.println(marksMaxHeap.remove());
		//
		// marksMaxHeap.display();
		// }

		int[] arr = { 20, 98, -12, 18, 3, 16, 7, 18 };

		// heapSortInPlace(arr);
		heapSortUsingExtraSpace(arr);

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println("END");

	}

	public static void heapSortUsingExtraSpace(int[] arr) throws Exception {
		PriorityQueue<Integer, Integer> heap = new Heap<>(true);

		for (int i = 0; i < arr.length; i++) {
			heap.add(arr[i], arr[i]);
		}

		for (int i = 0; i < arr.length; i++) {
			arr[i] = heap.remove();
		}
	}

	public static void heapSortInPlace(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			downheapify(arr, i, arr.length);
		}

		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, i, 0);
			downheapify(arr, 0, i);
		}
	}

	public static void downheapify(int[] arr, int pi, int l) {
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;

		int maxIndex = pi;
		if (lci < l && arr[maxIndex] < arr[lci]) {
			maxIndex = lci;
		}
		if (rci < l && arr[maxIndex] < arr[rci]) {
			maxIndex = rci;
		}

		if (maxIndex != pi) {
			swap(arr, maxIndex, pi);
			downheapify(arr, maxIndex, l);
		}

	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
