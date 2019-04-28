package operations;

import expression.exceptions.DBZException;
import expression.exceptions.EvaluatingException;
import expression.exceptions.IllegalConstException;
import expression.exceptions.OverflowException;

public class IntegerOperation implements Operation<Integer> {

	final String mode;

	public IntegerOperation(String newMode) {
		mode = newMode;
	}

	public Integer parseNumber(String number) throws IllegalConstException {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			throw new IllegalConstException(number);
		}
	}

	public Integer intToType(int number) {
		return number;
	}

	public Integer add(Integer x, Integer y) throws OverflowException {
		if (mode == "i") {
			if (x > 0 && Integer.MAX_VALUE - x < y) {
				throw new OverflowException();
			}
			if (x < 0 && Integer.MIN_VALUE - x > y) {
				throw new OverflowException();
			}
		}
		return x + y;
	}

	public Integer sub(Integer x, Integer y) throws OverflowException {
		if (mode == "i") {
			if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
				throw new OverflowException();
			}
			if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
				throw new OverflowException();
			}
		}
		return x - y;
	}

	public Integer mul(Integer x, Integer y) throws OverflowException {
		if (mode == "i") {
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
		}
		return x * y;
	}

	public Integer div(Integer x, Integer y) throws EvaluatingException {
		if (y == 0) {
			throw new DBZException();
		}
		if (mode == "i") {
			if (x == Integer.MIN_VALUE && y == -1) {
				throw new OverflowException();
			}
		}
		return x / y;
	}

	public Integer max(Integer x, Integer y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	public Integer min(Integer x, Integer y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	public Integer abs(Integer x) throws OverflowException {
		if (mode == "i") {
			if (x == Integer.MIN_VALUE) {
				throw new OverflowException();
			}
		}
		if (x < 0) {
			return -x;
		}
		return x;
	}

	public Integer neg(Integer x) throws OverflowException {
		if (mode == "i") {
			if (x == Integer.MIN_VALUE) {
				throw new OverflowException();
			}
		}
		return -x;
	}

	public Integer square(Integer x) throws OverflowException {
		if (mode == "i") {
			if (x > Math.sqrt(Integer.MAX_VALUE)) {
				throw new OverflowException();
			}
			if (x < -Math.sqrt(Integer.MAX_VALUE)) {
				throw new OverflowException();
			}
		}
		return x * x;
	}
	
	public Integer mod(Integer x, Integer y) throws DBZException {
		if (y == 0) {
			throw new DBZException();
		}
		return x % y;
	}
	
}
