package expression.exceptions;

public class ExtraClosingBracketException extends ParsingException {
	public ExtraClosingBracketException(int pos, String exp) {
		super("Extra closing bracket in position: " + pos + '\n' + exp + '\n' + getPos(pos));
	}
}
