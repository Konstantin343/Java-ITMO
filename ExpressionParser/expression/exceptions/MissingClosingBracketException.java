package expression.exceptions;

public class MissingClosingBracketException extends ParsingException{
	public MissingClosingBracketException(int pos, String exp) {
		super("Missing closing bracket for opening bracket in position: " + pos + '\n' + exp + '\n' + getPos(pos));
	}
}
