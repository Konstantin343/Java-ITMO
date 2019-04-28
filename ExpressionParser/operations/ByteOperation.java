package operations;

import expression.exceptions.DBZException;
import expression.exceptions.IllegalConstException;

public class ByteOperation implements Operation<Byte> {

	public Byte parseNumber(String number) throws IllegalConstException {
		try {
			return Byte.parseByte(number);
		} catch (NumberFormatException e) {
			throw new IllegalConstException(number);
		}
	}	
	
	public Byte intToType(int number) {
		return (byte)number;
	}
	
	public Byte add(Byte x, Byte y) {
		return (byte)(x + y);
	}

	public Byte sub(Byte x, Byte y) {
    	return (byte)(x - y);
	}

	public Byte mul(Byte x, Byte y) {
		return (byte)(x * y);
	}

	public Byte div(Byte x, Byte y) throws DBZException {
		if (y == 0) {
			throw new DBZException();
		}
		return (byte)(x / y);
	}

	public Byte max(Byte x, Byte y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public Byte min(Byte x, Byte y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public Byte abs(Byte x) {
		if (x < 0) {
			return (byte)-x;
		}
		return x;
	}

	public Byte neg(Byte x) {
		return (byte)-x;
	}
	
	public Byte square(Byte x) {
		return (byte)(x * x);
	}
	
	public Byte mod(Byte x, Byte y) throws DBZException {
		if (y == 0) {
			throw new DBZException();
		}
		return (byte)(x % y);
	}
	
}
