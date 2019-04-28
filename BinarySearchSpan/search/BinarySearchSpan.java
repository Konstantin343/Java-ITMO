package search;

import java.util.*;

public class BinarySearchSpan {
	
	private static void BinarySearchSpanIterative(String[] args) {
		
		//inv: array is sorted in reverse order
		//inv: left - right >= 1
		//inv: -1 <= left < right <= array.length
		//inv: left <= pos(element) <= right
		int element = Integer.parseInt(args[0]);
		List<Integer> array = new ArrayList<Integer>();
		for (int i = 1; i < args.length; i++) {
			array.add(Integer.parseInt(args[i]));
		}
		
		int left = -1;
		int right = array.size();
		
		//inv: left <= middle <= right
		//pre: left == -1 && right == array.size() ===> l < r - 1
		//post: 1) if (element < array[0]) right == 0 ===> right == indexOfInsert(element)
		//		2) if (element > array[array.size() - 1]) right == array.size() ===> right == indexOfInsert(element)
		//		3) if (array[0] <= element <= array[array.size() - 1] && element ∈ array[]) ===>
		//		===> a[right] == element ===> right == pos(element)
		//		4) if (array[0] <= element <= array[array.size() - 1] && element ∉ array[]) 
		//		∃ i: array[i] < element && ¬∃  j: (j < i && a[j] > element) && right == i ===> right == indexOfInsert(element)
		while (left < right - 1) {
			//pre: -1 <= left <= array.size() - 1
			//		0 <= right <= array.size()
			//post: left < middle < right
			int middle = (left + right) / 2;
			
			//pre: left < middle < right
			//post: 1) array[middle] < element <= array[right] 
			//		(∀ {i, j}: i < j <===> array[i] >= array[j]) && (element < array[middle]) ===>
			//		===> middle < pos(element) <= right
			//		2) array[left] < element <= array[middle] 
			//		(∀ {i, j}: i < j <===> array[i] >= array[j]) && (array[middle] <= element) ===>
			//		===> left < pos(element) <= middle
			if (element < array.get(middle)) {
				left = middle;		
			} else {
				right = middle;			
			}
		}	
		
		//pre: right == pos(element): ∀ i < right: 
		//		array[i] > element || right == indexOfInsert(element)
		//post: index == pos(element) || index == indexOfInsert(element)
		int index = right;
		
		left = -1;
		right = array.size();
		
		//inv: left <= middle <= right
		//pre: left == -1 && right == array.size() ===> l < r - 1
		//post: 1) if (element < array[0]) right == 0 ===> right == indexOfInsert(element)
		//		2) if (element > array[array.size() - 1]) right == array.size() ===> right == indexOfInsert(element)
		//		3) if (array[0] <= element <= array[array.size() - 1] && element ∈ array[]) 
		//		a[right] == element ===> right == pos(element)
		//		4) if (array[0] <= element <= array[array.size() - 1] && element ∉ array[]) 
		//		∃ i: array[i] < element && ¬∃  j: (j < i && a[j] > element) && right == i ===> right == indexOfInsert(element)
		while (left < right - 1) {
			//pre: -1 <= left <= array.size() - 1
			//		0 <= right <= array.size()
			//post: left < middle < right
			int middle = (left + right) / 2;
			
			//pre: left < middle < right
			//post: 1) array[middle] <= element < array[right] 
			//		(∀ {i, j}: i < j <===> array[i] >= array[j]) && (element <= array[middle]) ===>
			//		===> middle <= pos(element) < right
			//	 	2) array[left] < element < array[middle] 
			//		(∀ {i, j}: i < j <===> array[i] >= array[j]) && (array[middle] < element) ===>
			//		===> left < pos(element) < middle
			if (element <= array.get(middle)) {
				left = middle;
				
			} else {
				right = middle;
				
			}
		}
		
		//pre: right == pos(element): ∀ i > pos(element): 
		//		array[i] < element || right == indexOfInsert(element)
		//post: ∀ i < index: array[i] > element
		//		∀ i > index + length: array[i] < element
		//		===> ∀ i ∈ [index; index + length]: array[i] == element || index = indexOfInsert(element)
		int length = right - index;
		
		System.out.println(index + " " + length);
	}
	
	//inv: left <= middle <= right
	//pre: left == -1 && right == array.size() ===> l < r - 1
	//post: 1) if (element < array[0]) right == 0 ===> right == indexOfInsert(element)
	//		2) if (element > array[array.size() - 1]) right == array.size() ===> right == indexOfInsert(element)
	//		3) if (array[0] <= element <= array[array.size() - 1] && element ∈ array[]) 			
	//		a[right] == element ===> right == pos(element)
	//		4) if (array[0] <= element <= array[array.size() - 1] && element ∉ array[]) 
	//		∃ i: array[i] < element && ¬∃  j: (j < i && a[j] > element) && right == i ===> right == indexOfInsert(element)
	static int upperBound(ArrayList<Integer> array, int left, int right, int element) {		
		if (left < right - 1) {
			//pre: -1 <= left <= array.size() - 1
			//		0 <= right <= array.size()
			//post: left < middle < right
			int middle = (left + right) / 2;
			
			//pre: left < middle < right
			//post: 1) array[middle] < element <= array[right] 
			//		(∀ {i, j}: i < j <===> array[i] >= array[j]) && (element < array[middle]) ===>
			//		===> middle < pos(element) <= right
			//		2) array[left] < element <= array[middle] 
			//		(∀ {i, j}: i < j <===> array[i] >= array[j]) && (array[middle] <= element) ===>
			//		===> left < pos(element) <= middle
			if (element <= array.get(middle)) {
				return upperBound(array, middle, right, element);		
			} else {
				return upperBound(array, left, middle, element);	
			}
		} else {
				
			return right; 
		}
	}
		
	//inv: left <= middle <= right
	//pre: left == -1 && right == array.size() ===> l < r - 1
	//post: 1) if (element < array[0]) right == 0 ===> right == indexOfInsert(element)
	//		2) if (element > array[array.size() - 1]) right == array.size() ===> right == indexOfInsert(element)
	//		3) if (array[0] <= element <= array[array.size() - 1] && element ∈ array[]) 
	//		a[right] == element ===> right == pos(element)
	//		4) if (array[0] <= element <= array[array.size() - 1] && element ∉ array[]) 
	//		∃ i: array[i] < element && ¬∃  j: (j < i && a[j] > element) && right == i ===> right == indexOfInsert(element)	
	private static int lowerBound(ArrayList<Integer> array, int left, int right, int element) {

		if (left < right - 1) {
			//pre: -1 <= left <= array.size() - 1
			//		0 <= right <= array.size()
			//post: left < middle < right
			int middle = (left + right) / 2;
				
			//pre: left < middle < right
			//post: 1) array[middle] <= element < array[right] 
			//		(∀ {i, j}: i < j <===> array[i] >= array[j]) && (element <= array[middle]) ===>
			//		===> middle <= pos(element) < right
			//		2) array[left] < element < array[middle] 
			//		(∀ {i, j}: i < j <===> array[i] >= array[j]) && (array[middle] < element) ===>
			//		===> left < pos(element) < middle
			if (element < array.get(middle)) {
				return lowerBound(array, middle, right, element);			
			} else {
				return lowerBound(array, left, middle, element);
			}
		} else {		
			return right; 
		}
	}
		
	private static void BinarySearchSpanRecursy(String[] args) {	
			
		//inv: left - right >= 1
		//inv: -1 <= left < right <= array.length
		int element = Integer.parseInt(args[0]);
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int i = 1; i < args.length; i++) {
			array.add(Integer.parseInt(args[i]));
		}
		
		int left = -1;
		int right = array.size();
		
		//pre: right == pos(element): ∀ i < pos(element): 
		//		array[i] > element || right == indexOfInsert(element)
		//post: index == pos(element) || index == indexOfInsert(element)
		int index = lowerBound(array, left, right, element);

		//pre: right == pos(element): ∀ i > pos(element): 
		//		array[i] < element || right == indexOfInsert(element)
		//post: ∀ i < index: array[i] > element
		//		∀ i > index + length: array[i] < element
		//		===> ∀ i ∈ [index; index + length]: array[i] == element || index = indexOfInsert(element)
		int length = upperBound(array, left, right, element) - index;
			
		System.out.println(index + " " + length);
	}
	
	public static void main (String[] args) {	
		BinarySearchSpanIterative(args);
		BinarySearchSpanRecursy(args);
	}
}
