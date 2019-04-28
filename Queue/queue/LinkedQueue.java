package queue;

public class LinkedQueue extends AbstractQueue {
	
	//inv: value ≠ null
	private class Node {	
		Object value;
		Node next;		
		public Node (Object value, Node next) {
			assert value != null;
			this.value = value;
			this.next = next;
		}
	}
			
	private Node head;
	private Node tail;
	
	protected void doEnqueue(Object element) {
		if (currentSize == 0){
            head = tail = new Node(element, null);
        } else {
            tail.next = new Node(element, null);
            tail = tail.next;
        }
	}
	
	protected Object doElement() {
		return head.value;
	}
	
	protected void doDequeue() {
		head = head.next;
	}
	
	protected void doClear() {
		head = null;
		tail = null;
	}
	
	protected LinkedQueue makeCopy() {
		LinkedQueue copy = new LinkedQueue();
		copy.head = head;
		copy.tail = tail;
		copy.currentSize = currentSize;
		return copy;
	}
	
}
