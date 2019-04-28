package queue;

import java.util.function.Function;
import java.util.function.Predicate;

//inv: n >= 0
//inv: 0 <= head <= tail <= n
//inv: queue is sorted in order of insert
public interface Queue {
	
	//pre: element ≠ null
	//post: tail = tail' + 1 && ∀ i < tail': a[i] = a[i]' && a[tail'] = element
	void enqueue(Object element);
	
	//post: ℝ = a[head] && ∀ head <= i < tail: a[i] = a[i]'
	Object element();
	
	//pre: size > 0
	//post: ℝ = a[head] && ∀ head <= i < tail: a[i] = a[i]' && head = head' + 1
	Object dequeue();
	
	//post: ℝ = size
	int size();
	
	//post: if(size = 0) ℝ = true
	//post: if(size ≠ 0) ℝ = false
	boolean isEmpty();
	
	//post: head = tail = currentSize = 0
	void clear();
	
	//post: ℝ = array: ∀ head <= i < tail: array[i - head] = a[i]
	Object[] toArray();
	
	//post: ℝ = queue: ∀ head <= i < tail: queue[i] = function(queue[i]');
	AbstractQueue map(Function<Object, Object> function);
	
	//post: ℝ = queue: ∀ head' <= i < tail' ∀ head <= j < tail: if(predicate(queue[i]') = true) queue[j] = queue[i]';
	AbstractQueue filter(Predicate<Object> predicate);
}
