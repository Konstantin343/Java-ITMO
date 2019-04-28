package expression.exceptions;

public class MissingWhiteSpaceException extends ParsingException {
	public MissingWhiteSpaceException(int pos, String exp) {
		super("Missing white space after position: " + pos + '\n' + exp + '\n' + getPos(pos));
	}
}
