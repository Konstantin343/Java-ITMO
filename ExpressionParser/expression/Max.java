package expression;

import operations.Operation;

public class Max<T> extends BinaryOperator<T> {
	
	public Max(TripleExpression<T> x, TripleExpression<T> y, Operation<T> op) {
		super(x, y, op);
	}
	
	public T doEvaluate(T x, T y) {
		return operation.max(x, y);
	}
}
