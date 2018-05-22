package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Client {

	public static void main(String[] args) {
//		Map<String,Integer> ht=new HashTable<>();
//		
//		ht.put("India", 10);
//		ht.put("UK", 100);
//		ht.put("US", 90);
//		ht.put("AUS", 70);
//		ht.put("CHINA", 50);
//		ht.put("Pak", 60);
//		ht.put("EU", 99);
//		ht.display();
//		
//		System.out.println(ht.remove("AUS"));
//		ht.display();
//		System.out.println(ht.get("India"));
//		ht.display();
//		
////		ArrayList<String> list=ht.keys();
////		for(int i=0;i<list.size();i++){
////			System.out.print(list.get(i)+", ");
////		}
//		
//		ArrayList<Integer> list=ht.values();
//		System.out.println(list);
		
//		int[] arr={1,6,9,8,8,2,7,6};
//		int []arr1=RemoveDuplicates(arr);
//		for(int i=0;i<arr.length;i++){
//			System.out.print(arr[i]+" ");
//		}
	}

	public int[] RemoveDuplicates(int[] arr){
		HashMap<Integer, Integer> map=new HashMap<>();
		
		for(int i=0;i<arr.length;i++){
			map.put(arr[i], arr[i]);
		}
		Set<Entry<Integer,Integer>> allEntries=map.entrySet();
		int i=0;
		int[] arr1=new int[map.entrySet().size()];
		for(int val:map.values()){
			arr1[i++]=val;
		}
		return arr1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
