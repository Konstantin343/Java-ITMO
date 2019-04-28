package operations;

import expression.exceptions.DBZException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.IllegalConstException;
import expression.exceptions.OverflowException;

public interface Operation<T> {
	
	public T parseNumber(String nubmer) throws IllegalConstException;
	
	public T intToType(int nubmer);
	
	public T add(T x, T y) throws OverflowException;
	
	public T sub(T x, T y) throws OverflowException;
	
	public T mul(T x, T y) throws OverflowException;
	
	public T div(T x, T y) throws EvaluatingException;
	
	public T max(T x, T y);
 	
	public T min(T x, T y);
	
	public T abs(T x) throws OverflowException;
	
	public T neg(T x) throws OverflowException;
	
	public T square(T x) throws OverflowException;
	
	public T mod(T x, T y) throws DBZException;
}
