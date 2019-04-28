package expression;

import expression.exceptions.EvaluatingException;
import operations.Operation;

public class Square<T> extends UnaryOperator<T> {
	
	public Square(TripleExpression<T> x, Operation<T> op) {
		super(x, op);
	}
	
	public T doEvaluate(T x) throws EvaluatingException {
		return operation.square(x);
	}
}

