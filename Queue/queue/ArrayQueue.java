package queue;

public class ArrayQueue extends AbstractQueue {
	
	private final int START_SIZE = 10;
	private int maxSize = START_SIZE;
	private int head = 0;
	private int tail = 0;
	private Object[] elements;
	
	public ArrayQueue( ) {
		elements = new Object[maxSize];
	}
	
	public ArrayQueue(int size) {
		elements = new Object[size];
	}
	
	private void ensureCapacity() {
		Object[] array = toArray();
		head = 0;
		tail = currentSize;		
		maxSize *= 2;
		elements = new Object[maxSize];
		for (int index = 0; index < currentSize; index++) {
			elements[index] = array[index];
		}
	}
	
	protected void doEnqueue(Object element) {
		if (currentSize == maxSize) {
			ensureCapacity();
		}
		elements[tail++] = element;
		tail %= maxSize;
	}

	protected Object doElement() {
		return elements[head];
	}
	
	protected void doDequeue() {
		head = (head + 1) % maxSize;	
	}
	
	protected void doClear() {
		maxSize = START_SIZE;
		elements = new Object[maxSize];
		tail = head = 0;		
	}
	
	public void push(Object element) {
		assert element != null;
		if (currentSize == maxSize) {
			ensureCapacity();
		}
		currentSize++;
		head = (head - 1 + maxSize) % maxSize;
		elements[head] = element;		
	}
	
	public Object peek() {
		assert currentSize > 0;
		return elements[(tail - 1 + maxSize) % maxSize];
	}
	
	public Object remove() {
		assert currentSize > 0;
		currentSize--;
		Object last = elements[(tail - 1 + maxSize) % maxSize];
		tail = (tail - 1 + maxSize) % maxSize;
		return last;
	}
	
	protected ArrayQueue makeCopy() {
		ArrayQueue copy = new ArrayQueue(maxSize);
		copy.head = head;
		copy.tail = tail;
		copy.currentSize = currentSize;
		copy.maxSize = maxSize;
		System.arraycopy(elements, 0, copy.elements, 0, maxSize);
		return copy;
	}
	
}
