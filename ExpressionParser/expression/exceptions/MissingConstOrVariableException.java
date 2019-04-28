package expression.exceptions;

public class MissingConstOrVariableException extends ParsingException {
	public MissingConstOrVariableException(int pos, String exp, String place) {
		super("Missing const or variable " + place + " position: " + pos + '\n' + exp + '\n' + getPos(pos));
	}
}
