package expression.exceptions;

public class IllegalConstException extends ParsingException {
	public IllegalConstException(String number) {
		super("Illegal const (overflow):" + number);
	}
}
