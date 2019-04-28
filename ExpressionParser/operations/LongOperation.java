package operations;

import expression.exceptions.DBZException;
import expression.exceptions.IllegalConstException;

public class LongOperation implements Operation<Long> {

	public Long parseNumber(String number) throws IllegalConstException {
		try {
			return Long.parseLong(number);
		} catch (NumberFormatException e) {
			throw new IllegalConstException(number);
		}
	}
	
	public Long intToType(int number) {
		return (long)number;
	}
	
	public Long add(Long x, Long y) {
		return x + y;
	}

	public Long sub(Long x, Long y) {
    	return x - y;
	}

	public Long mul(Long x, Long y) {
		return x * y;
	}

	public Long div(Long x, Long y) throws DBZException {
		if (y == 0) {
			throw new DBZException();
		}
		return x / y;
	}

	public Long max(Long x, Long y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public Long min(Long x, Long y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public Long abs(Long x) {
		if (x < 0) {
			return -x;
		}
		return x;
	}

	public Long neg(Long x) {
		return -x;
	}
	
	public Long square(Long x) {
		return x * x;
	}
	
	public Long mod(Long x, Long y) throws DBZException {
		if (y == 0) {
			throw new DBZException();
		}
		return x % y;
	}
	
}
