package operations;

import expression.exceptions.DBZException;
import expression.exceptions.IllegalConstException;

public class ShortOperation implements Operation<Short> {

	public Short parseNumber(String number) throws IllegalConstException {
		try {
			return Short.parseShort(number);
		} catch (NumberFormatException e) {
			throw new IllegalConstException(number);
		}
	}
	
	public Short intToType(int number) {
		return (short)number;
	}
	
	public Short add(Short x, Short y) {
		return (short)(x + y);
	}

	public Short sub(Short x, Short y) {
    	return (short)(x - y);
	}

	public Short mul(Short x, Short y) {
		return (short)(x * y);
	}

	public Short div(Short x, Short y) throws DBZException {
		if (y == 0) {
			throw new DBZException();
		}
		return (short)(x / y);
	}

	public Short max(Short x, Short y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public Short min(Short x, Short y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public Short abs(Short x) {
		if (x < 0) {
			return (short)-x;
		}
		return x;
	}

	public Short neg(Short x) {
		return (short)-x;
	}
	
	public Short square(Short x) {
		return (short)(x * x);
	}
	
	public Short mod(Short x, Short y) throws DBZException {
		if (y == 0) {
			throw new DBZException();
		}
		return (short)(x % y);
	}
	
}
