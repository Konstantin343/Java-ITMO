package queue;
	
//inv: 0 <= tail < maxSize
//inv: 0 <= head < maxSize
//inv: 0 <= currentSize <= maxSize
//inv: START_SIZE = 10
public class ArrayQueueADT {

	private final int START_SIZE = 10;
	private int maxSize = START_SIZE;
	private int head = 0;
	private int tail = 0;
	private Object[] elements = new Object[maxSize];
	private int currentSize = 0;
	
	//post: maxSize = 2 * maxSize'
	//		∀ i ∈ [head', maxSize'): elements[i - head'] = elements[i]' && ∀ i ∈ [0, tail'): elements[maxSize - 1 - i] = elements[i]'
	//post: immutable1
	
	//pre: curentSize = maxSize
	//post: currentSize = currentSize' && tail = currentSize && immutable1
	private static void ensureCapacity(ArrayQueueADT queue) {
		Object[] array = toArray(queue);
		queue.head = 0;
		queue.tail = queue.currentSize;		
		queue.maxSize *= 2;
		queue.elements = new Object[queue.maxSize];
		for (int index = 0; index < queue.currentSize; index++) {
			queue.elements[index] = array[index];
		}
	}
	
	//pre: element ≠ null
	//post: currentSize = currentSize' + 1
	//		if (currentSize' == maxSize) tail = currentSize && head = 0 && elements[currentSize'] = element && immutable1
	//		if (currentSize' <= maxSize) head = head' && tail = (tail' + 1) % maxSize && 
	//			&& elements[tail'] = element && ∀ i ∈ [0; maxSize): if (∃ elements[i]') elements[i] = elements[i]'  
	public static void enqueue(ArrayQueueADT queue, Object element) {
		assert element != null;
		if (queue.currentSize == queue.maxSize) {
			ensureCapacity(queue);
		} 
		queue.currentSize++;
		queue.elements[queue.tail++] = element;
		queue.tail %= queue.maxSize;
	}
	
	//post: tail = tail' && head = head' && currentSize = currentSize' && 
	//		&& maxSize = maxSize' && ∀ i ∈ [0; maxSize): if (∃ elements[i]') elements[i] = elements[i]'  
	//post: immutable2
	
	//pre: currentSize > 0
	//post: ℝ = elements[head] && immutable2
	public static Object element(ArrayQueueADT queue) {
		assert queue.currentSize > 0;
		return queue.elements[queue.head];
	}
	
	//pre: currentSize > 0
	//post: ℝ = elements[head'] && head = (head' + 1) % maxSize && currentSize = currentSize' - 1 && tail = tail'
	//		∀ i ∈ [0; head')∪(head'; maxSize): if (∃ elements[i]') elements[i] = elements[i]'
	public static Object dequeue(ArrayQueueADT queue) {
		assert queue.currentSize > 0;
		queue.currentSize--;
		Object first = queue.elements[queue.head++];
		queue.head %= queue.maxSize;
		return first;		
	}
	
	//post: ℝ = currentSize && immutable2
	public static int size(ArrayQueueADT queue) {
		return queue.currentSize;
	}
	
	//post: ℝ = (currentSize == 0) && immutable2
	public static boolean isEmpty(ArrayQueueADT queue) {
		return queue.currentSize == 0;
	}
	
	//post: maxSize = START_SIZE && currentSize = 0 && head = 0 && tail = 0 && elements = []
	public static void clear(ArrayQueueADT queue) {
		queue.maxSize = queue.START_SIZE;
		queue.elements = new Object[queue.maxSize];
		queue.currentSize = queue.tail = queue.head = 0;		
	}

	//post: if (currentSize = 0) immutable2 && array = [] 
	//		if (currentSize > 0) immutable2 && 
	//				if (tail >= head) ∀ i ∈ [head; tail): array[i] = elements[i + head]
	//				if (tail < head) ∀ i ∈ [head, maxSize): array[i - head] = elements[i] && ∀ i ∈ [0, tail): array[maxSize - head + i] = elements[i]
	//		ℝ = array
	public static Object[] toArray(ArrayQueueADT queue) {
		Object[] array = new Object[queue.currentSize];
		for (int index = 0; index < queue.currentSize; index++) {
				array[index] = queue.elements[(queue.head + index) % queue.maxSize];
		}
		return array;
	}
	
	//pre: element ≠ null
	//post: currentSize = currentSize' + 1
	//		if (currentSize' == maxSize)  tail = currentSize' && head = maxSize - 1 && elements[head] = element && immutable1
	//		if (currentSize' < maxSize)  head = (head' - 1 + maxSize) % maxSize && tail = tail'
	//			&& elements[tail'] = element && ∀ i ∈ [0; maxSize): if (∃ elements[i]') elements[i] = elements[i]' 
	public static void push(ArrayQueueADT queue, Object element) {
		assert element != null;
		if (queue.currentSize == queue.maxSize) {
			ensureCapacity(queue);
		} 
		queue.currentSize++;		
		queue.elements[(queue.head - 1 + queue.maxSize) % queue.maxSize] = element;
		queue.head = (queue.head - 1 + queue.maxSize) % queue.maxSize;
	}
	
	//pre: currentSize > 0
	//post: ℝ = elements[(tail - 1 + maxSize) % maxSize] && immutable2
	public static Object peek(ArrayQueueADT queue) {
		assert queue.currentSize > 0;
		return queue.elements[(queue.tail - 1 + queue.maxSize) % queue.maxSize];
	}

	//pre: currentSize > 0
	//post: ℝ = elements[(tail' - 1 + maxSize) % maxSize] && tail = (tail' - 1) % maxSize && currentSize = currentSize' - 1 && head = head'
	//		∀ i ∈ [0; (tail' - 1 + maxSize) % maxSize)∪((tail' - 1 + maxSize) % maxSize; maxSize): if (∃ elements[i]') elements[i] = elements[i]'
	public static Object remove(ArrayQueueADT queue) {
		assert queue.currentSize > 0;
		queue.currentSize--;
		Object last = queue.elements[(queue.tail - 1 + queue.maxSize) % queue.maxSize];
		queue.tail = (queue.tail - 1 + queue.maxSize) % queue.maxSize;
		return last;
	}
}
