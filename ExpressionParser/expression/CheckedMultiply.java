package expression;

import expression.exceptions.*;

public class CheckedMultiply extends BinaryOperator {

	public CheckedMultiply(TripleExpression x, TripleExpression y) {
		super(x, y);
	}
	
	public int doEvaluate(int x, int y) throws EvaluatingException {
		if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new OverflowException();
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new OverflowException();
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new OverflowException();
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new OverflowException();
        }
		return x * y;
	}
	
}
