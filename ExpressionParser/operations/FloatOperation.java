package operations;

import expression.exceptions.DBZException;
import expression.exceptions.IllegalConstException;

public class FloatOperation implements Operation<Float> {

	public Float parseNumber(String number) throws IllegalConstException {
		try {
			return Float.parseFloat(number);
		} catch (NumberFormatException e) {
			throw new IllegalConstException(number);
		}
	}
	
	public Float intToType(int number) {
		return (float)number;
	}
	
	public Float add(Float x, Float y) {
		return x + y;
	}

	public Float sub(Float x, Float y) {
    	return x - y;
	}

	public Float mul(Float x, Float y) {
		return x * y;
	}

	public Float div(Float x, Float y) throws DBZException {
		return x / y;
	}

	public Float max(Float x, Float y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public Float min(Float x, Float y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public Float abs(Float x) {
		if (x < 0) {
			return -x;
		}
		return x;
	}

	public Float neg(Float x) {
		return -x;
	}
	
	public Float square(Float x) {
		return x * x;
	}
	
	public Float mod(Float x, Float y) {
		return x % y;
	}
	
}
