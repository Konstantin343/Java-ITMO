package expression.exceptions;

public class UnknownSymbolException extends ParsingException {
	public UnknownSymbolException(int pos, String exp) {
		super("Unknow symbol or operation in position: " + pos + '\n' + exp + '\n' + getPos(pos));
	}
}
