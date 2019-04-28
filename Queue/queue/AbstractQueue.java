package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
	
	protected int currentSize;
	
	public void enqueue(Object element) {
		assert element != null;
		doEnqueue(element);
		currentSize++;
	}
	
	protected abstract void doEnqueue(Object element);
	
	public int size() {
		return currentSize;
	}
	
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	public Object element() {
		assert currentSize > 0;
		return doElement();
	}
	
	protected abstract Object doElement();
	
	public Object dequeue() {
		Object answer = element();
		currentSize--;
		doDequeue();
		return answer;
	}
	
	protected abstract void doDequeue();
	
	public void clear() {
		currentSize = 0;
		doClear();
	}
	
	protected abstract void doClear();	
	
	public Object[] toArray() {
		Object[] array = new Object[currentSize];
		for (int i = 0; i < currentSize; i++) {
			array[i] = dequeue();
			enqueue(array[i]);
		}
		return array;
	}
	
	protected abstract AbstractQueue makeCopy();
	
	public AbstractQueue filter(Predicate<Object> predicate) {
		AbstractQueue copy = makeCopy();
		for (int i = 0; i < currentSize; i++) {
			Object current = copy.dequeue();
			if (predicate.test(current)) {
                copy.enqueue(current);
            }
		}
		return copy;
	}
	
	public AbstractQueue map(Function<Object, Object> function) {
		AbstractQueue copy = makeCopy();
		for (int i = 0; i < currentSize; i++) {
			Object current = copy.dequeue();
			copy.enqueue(function.apply(current));
		}
		return copy;
	}
	
}
