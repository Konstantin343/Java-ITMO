package expression;

import expression.exceptions.*;
import operations.Operation;

public class Negate<T> extends UnaryOperator<T> {
	
	public Negate(TripleExpression<T> x, Operation<T> op) {
		super(x, op);
	}
	
	public T doEvaluate(T x) throws EvaluatingException {
		return operation.neg(x);
	}
	
}
