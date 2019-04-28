package expression;

import expression.exceptions.*;
import operations.Operation;

public class Multiply<T> extends BinaryOperator<T> {

	public Multiply(TripleExpression<T> x, TripleExpression<T> y, Operation<T> op) {
		super(x, y, op);
	}
	
	public T doEvaluate(T x, T y) throws EvaluatingException {
		return operation.mul(x, y);
	}
	
}
