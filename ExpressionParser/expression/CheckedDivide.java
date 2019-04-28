package expression;

import expression.exceptions.*;

public class CheckedDivide extends BinaryOperator {
	
	public CheckedDivide(TripleExpression x, TripleExpression y) {
		super(x, y);
	}
	
	public int doEvaluate(int x, int y) throws EvaluatingException {
		if (y == 0) {
			throw new DBZException();
		}
		if (x == Integer.MIN_VALUE && y == -1) {
			throw new OverflowException();
		}
		return x / y;
	}
	
}
