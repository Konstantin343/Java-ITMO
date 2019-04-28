package operations;

import java.math.BigInteger;

import expression.exceptions.DBZException;
import expression.exceptions.IllegalConstException;

public class BigIntegerOperation implements Operation<BigInteger>{

	public BigInteger parseNumber(String number) throws IllegalConstException {
		try {
			return new BigInteger(number);
		} catch (NumberFormatException e) {
			throw new IllegalConstException(number);
		}
	}
	
	public BigInteger intToType(int number) {
		return new BigInteger(Integer.toString(number));
	}
	
	public BigInteger add(BigInteger x, BigInteger y) {
		return x.add(y);
	}

	public BigInteger sub(BigInteger x, BigInteger y) {
    	return x.subtract(y);
	}

	public BigInteger mul(BigInteger x, BigInteger y) {
		return x.multiply(y);
	}

	public BigInteger div(BigInteger x, BigInteger y) throws DBZException {
		if (y.equals(BigInteger.ZERO)) {
			throw new DBZException();
		}
		return x.divide(y);
	}

	public BigInteger max(BigInteger x, BigInteger y) {
		if (x.compareTo(y) == 1) {
			return x;
		} else {
			return y;
		}
	}

	public BigInteger min(BigInteger x, BigInteger y) {
		if (x.compareTo(y) == -1) {
			return x;
		} else {
			return y;
		}
	}

	public BigInteger abs(BigInteger x) {
		if (x.compareTo(new BigInteger("0")) == -1) {
			return x.negate();
		}
		return x;
	}

	public BigInteger neg(BigInteger x) {
		return x.negate();
	}
	
	public BigInteger square(BigInteger x) {
		return x.multiply(x);
	}
	
	public BigInteger mod(BigInteger x, BigInteger y) throws DBZException {
		if (y.compareTo(new BigInteger("0")) <= 0) {
			throw new DBZException();
		}
		return x.mod(y);
	}
}
