package expression.exceptions;

public class IncorrectModeException extends Exception {
	public IncorrectModeException(String mode) {
		super("Incorrect mode: " + mode);
	}
}
