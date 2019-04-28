package expression;

import expression.exceptions.EvaluatingException;
import operations.Operation;

public class Abs<T> extends UnaryOperator<T> {
	
	public Abs(TripleExpression<T> x, Operation<T> op) {
		super(x, op);
	}
	
	public T doEvaluate(T x) throws EvaluatingException {
		return operation.abs(x);
	}
}

