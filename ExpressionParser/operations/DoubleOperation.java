package operations;

import expression.exceptions.DBZException;
import expression.exceptions.IllegalConstException;

public class DoubleOperation implements Operation<Double> {

	public Double parseNumber(String number) throws IllegalConstException {
		try {
			return Double.parseDouble(number);
		} catch (NumberFormatException e) {
			throw new IllegalConstException(number);
		}
	}
	
	public Double intToType(int number) {
		return (double)number;
	}
	
	public Double add(Double x, Double y) {
		return x + y;
	}

	public Double sub(Double x, Double y) {
    	return x - y;
	}

	public Double mul(Double x, Double y) {
		return x * y;
	}

	public Double div(Double x, Double y) throws DBZException {
		return x / y;
	}

	public Double max(Double x, Double y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public Double min(Double x, Double y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public Double abs(Double x) {
		if (x < 0) {
			return -x;
		}
		return x;
	}

	public Double neg(Double x) {
		return -x;
	}
	
	public Double square(Double x) {
		return x * x;
	}
	
	public Double mod(Double x, Double y) {
		return x % y;
	}
	
}
