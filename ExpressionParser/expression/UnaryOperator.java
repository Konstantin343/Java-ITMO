package expression;

import expression.exceptions.*;
import operations.Operation;

public abstract class UnaryOperator<T> implements TripleExpression<T> {
	
	private TripleExpression<T> value;
	Operation<T> operation;
	
	public UnaryOperator(TripleExpression<T> x) {
		value = x;
	}
	
	public UnaryOperator(TripleExpression<T> x, Operation<T> op) {
		value = x;
		operation = op;
	}
	
	public T evaluate(T x, T y, T z) throws EvaluatingException {
		return doEvaluate(value.evaluate(x, y, z));
	}
	
	protected abstract T doEvaluate(T x) throws EvaluatingException;
	
}
