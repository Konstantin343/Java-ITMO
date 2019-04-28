package expression;

import expression.exceptions.EvaluatingException;

public class Variable<T> implements TripleExpression<T> {
	private final String name;
	
	public Variable(String s) {
		name = s;
	}
	
	public T evaluate(T x, T y, T z) throws EvaluatingException {
		switch(name) {
			case "x":
				return x;
			case "y":
				return y;
			case "z":
				return z;
			default: 
				throw new EvaluatingException("Incorrect variable");
		}
	}
	
}
