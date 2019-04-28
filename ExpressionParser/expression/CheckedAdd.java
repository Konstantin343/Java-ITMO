package expression;

import expression.exceptions.*;

public class CheckedAdd<T, S> extends BinaryOperator<T> {
	
	public CheckedAdd(TripleExpression<T> x, TripleExpression<T> y) {
		super(x, y);
	}
	
	public T doEvaluate(T x, T y) throws EvaluatingException {
		if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new OverflowException();
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new OverflowException();
        }
		return x + y;
	}
	
}
