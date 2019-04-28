package expression;

import expression.exceptions.*;
import operations.Operation;

public abstract class BinaryOperator<T> implements TripleExpression<T> {
	
	private TripleExpression<T> first;
	private TripleExpression<T> second;
	Operation<T> operation;
	
	public BinaryOperator(TripleExpression<T> x, TripleExpression<T> y, Operation<T> op) {
		first = x;
		second = y;
		operation = op;
	}

	public T evaluate(T x, T y, T z) throws EvaluatingException {
		return doEvaluate(first.evaluate(x, y, z), second.evaluate(x, y, z));
	}
	
	protected abstract T doEvaluate(T x, T y) throws EvaluatingException;

}
