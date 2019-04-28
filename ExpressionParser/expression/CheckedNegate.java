package expression;

import expression.exceptions.*;

public class CheckedNegate extends UnaryOperator {
	
	public CheckedNegate(TripleExpression x) {
		super(x);
	}
	
	public int doEvaluate(int x) throws EvaluatingException {
		if (x == Integer.MIN_VALUE) {
			throw new OverflowException();
		}
		return -x;
	}
	
}
