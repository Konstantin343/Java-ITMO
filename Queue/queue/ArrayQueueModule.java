package queue;

//inv: 0 <= tail < maxSize
//inv: 0 <= head < maxSize
//inv: 0 <= currentSize <= maxSize
//inv: START_SIZE = 10
public class ArrayQueueModule {
	
	private static final int START_SIZE = 10;
	private static int maxSize = START_SIZE ;
	private static int head = 0;
	private static int tail = 0;
	private static Object[] elements = new Object[maxSize];
	private static int currentSize = 0;
	
	//post: maxSize = 2 * maxSize'
	//		∀ i ∈ [head', maxSize'): elements[i - head'] = elements[i]' && ∀ i ∈ [0, tail'): elements[maxSize - 1 - i] = elements[i]'
	//post: immutable1
	
	//pre: curentSize = maxSize
	//post: currentSize = currentSize' && tail = currentSize && immutable1
	private static void ensureCapacity() {
		Object[] array = ArrayQueueModule.toArray();
		head = 0;
		tail = currentSize;		
		maxSize *= 2;
		elements = new Object[maxSize];
		for (int index = 0; index < currentSize; index++) {
			elements[index] = array[index];
		}
	}
	
	//pre: element ≠ null
	//post: currentSize = currentSize' + 1
	//		if (currentSize' == maxSize) tail = currentSize && head = 0 && elements[currentSize'] = element && immutable1
	//		if (currentSize' <= maxSize) head = head' && tail = (tail' + 1) % maxSize && 
	//			&& elements[tail'] = element && ∀ i ∈ [0; maxSize): if (∃ elements[i]') elements[i] = elements[i]'   
	public static void enqueue(Object element) {
		assert element != null;
		if (currentSize == maxSize) {
			ArrayQueueModule.ensureCapacity();
		} 
		currentSize++;
		elements[tail++] = element;
		tail %= maxSize;
	}
	
	//post: tail = tail' && head = head' && currentSize = currentSize' && 
	//		&& maxSize = maxSize' && ∀ i ∈ [0; maxSize): if (∃ elements[i]') elements[i] = elements[i]'  
	//post: immutable2
	
	//pre: currentSize > 0
	//post: ℝ = elements[head] && immutable2
	public static Object element() {
		assert currentSize > 0;
		return elements[head];
	}
	
	//pre: currentSize > 0
	//post: ℝ = elements[head'] && head = (head' + 1) % maxSize && currentSize = currentSize' - 1 && tail = tail'
	//		∀ i ∈ [0; head')∪(head'; maxSize): if (∃ elements[i]') elements[i] = elements[i]'
	public static Object dequeue() {
		assert currentSize > 0;
		currentSize--;
		Object first = elements[head++];
		head %= maxSize;
		return first;		
	}
	
	//post: ℝ = currentSize && immutable2
	public static int size() {
		return currentSize;
	}
	
	//post: ℝ = (currentSize == 0) && immutable2
	public static boolean isEmpty() {
		return currentSize == 0;
	}
	
	//post: maxSize = START_SIZE && currentSize = 0 && head = 0 && tail = 0 && elements = []
	public static void clear() {
		maxSize = START_SIZE;
		elements = new Object[maxSize];
		currentSize = tail = head = 0;		
	}
	
	//post: if (currentSize = 0) immutable2 && array = [] 
	//		if (currentSize > 0) immutable2 && 
	//				if (tail >= head) ∀ i ∈ [head; tail): array[i] = elements[i + head]
	//				if (tail < head) ∀ i ∈ [head, maxSize): array[i - head] = elements[i] && ∀ i ∈ [0, tail): array[maxSize - head + i] = elements[i]
	//		ℝ = array
	public static Object[] toArray() {
		Object[] array = new Object[currentSize];
		for (int index = 0; index < currentSize; index++) {
				array[index] = elements[(head + index) % maxSize];
		}
		return array;
	}
	
	//pre: element ≠ null
	//post: currentSize = currentSize' + 1
	//		if (currentSize' == maxSize)  tail = currentSize' && head = maxSize - 1 && elements[head] = element && immutable1
	//		if (currentSize' < maxSize)  head = (head' - 1 + maxSize) % maxSize && tail = tail'
	//			&& elements[tail'] = element && ∀ i ∈ [0; maxSize): if (∃ elements[i]') elements[i] = elements[i]'   
	public static void push(Object element) {
		assert element != null;
		if (currentSize == maxSize) {
			ArrayQueueModule.ensureCapacity();
		}
		currentSize++;
		elements[(head - 1 + maxSize) % maxSize] = element;
		head = (head - 1 + maxSize) % maxSize;
	}
	
	//pre: currentSize > 0
	//post: ℝ = elements[(tail - 1 + maxSize) % maxSize] && immutable2
	public static Object peek() {
		assert currentSize > 0;
		return elements[(tail - 1 + maxSize) % maxSize];
	}

	//pre: currentSize > 0
	//post: ℝ = elements[(tail' - 1 + maxSize) % maxSize] && tail = (tail' - 1) % maxSize && currentSize = currentSize' - 1 && head = head'
	//		∀ i ∈ [0; (tail' - 1 + maxSize) % maxSize)∪((tail' - 1 + maxSize) % maxSize; maxSize): if (∃ elements[i]') elements[i] = elements[i]'
	public static Object remove() {
		assert currentSize > 0;
		currentSize--;
		tail = (tail - 1 + maxSize) % maxSize;
		Object last = elements[tail];	
		return last;
	}
}
