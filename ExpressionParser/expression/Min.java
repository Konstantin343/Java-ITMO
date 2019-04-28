package expression;

import operations.Operation;

public class Min<T> extends BinaryOperator<T> {
	
	public Min(TripleExpression<T> x, TripleExpression<T> y, Operation<T> op) {
		super(x, y, op);
	}
	
	public T doEvaluate(T x, T y) {
		return operation.min(x, y);
	}
}
