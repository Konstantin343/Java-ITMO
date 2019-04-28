package expression.exceptions;

public class MissingOperationException extends ParsingException {
	public MissingOperationException(int pos, String exp) {
		super("Missing operation before position: " + pos + '\n' + exp + '\n' + getPos(pos));
	}
}
