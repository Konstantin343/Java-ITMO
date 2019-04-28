package expression;

import expression.exceptions.DBZException;
import operations.Operation;

public class Mod<T> extends BinaryOperator<T> {
	
	public Mod(TripleExpression<T> x, TripleExpression<T> y, Operation<T> op) {
		super(x, y, op);
	}
	
	public T doEvaluate(T x, T y) throws DBZException {
		return operation.mod(x, y);
	}
}
