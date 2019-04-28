package expression;

import expression.exceptions.*;

public class CheckedSubtract<T> extends BinaryOperator<T> {
	
	public CheckedSubtract(TripleExpression<T> x, TripleExpression<T> y) {
		super(x, y);
	}
	
	public T doEvaluate(T x, T y) throws EvaluatingException {
		if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
            throw new OverflowException();
        }
        if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
            throw new OverflowException();
        }
		return x - y;
	}
	
}


