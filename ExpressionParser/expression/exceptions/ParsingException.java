package expression.exceptions;

public class ParsingException extends Exception {
	public ParsingException(String s) {
		super(s);
	}
	
	static protected String getPos(int pos) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < pos; i++) {
			str.append(' ');
		}
		str.append('^');
		return str.toString();
	}
}
