package expression.parser;

import expression.exceptions.*;
import operations.Operation;

import java.util.Set;
import java.util.EnumSet;

public class Tokenizer<T> {

	private String expression;
	private int pos;
	private Token current;
	private String var;
	private T value;
	private int balance;
	private Operation<T> operation;

	private Set<Token> operations = EnumSet.of(Token.ADD, Token.DIV, Token.MAX, Token.MIN, Token.MINUS, Token.MUL,
			Token.SUB, Token.BEGIN, Token.OPEN_BRACKET, Token.ABS, Token.MOD, Token.SQR);

	public Tokenizer(String newExpression, Operation<T> op) {
		expression = newExpression;
		pos = 0;
		current = Token.BEGIN;
		balance = 0;
		operation = op;
	}

	private void toNextToken() {
		while (pos < expression.length() && Character.isWhitespace(expression.charAt(pos))) {
			pos++;
		}
	}

	private void checkConstOrVariable(String place) throws MissingConstOrVariableException {
		if (operations.contains(current)) {
			throw new MissingConstOrVariableException(pos, expression, place);
		}
	}

	private void checkOperation() throws MissingOperationException {
		if (current == Token.CONST || current == Token.VARIABLE || current == Token.CLOSE_BRACKET) {
			throw new MissingOperationException(pos, expression);
		}
	}

	private boolean correctSymbol(char symbol) throws MissingWhiteSpaceException {
		if (!Character.isWhitespace(symbol) && symbol != '(' && symbol != '-') {
			return false;
		}
		return true;
	}

	private void nextToken() throws ParsingException {
		toNextToken();
		if (pos >= expression.length()) {
			checkConstOrVariable("in last");
			current = Token.END;
			return;
		}
		char c = expression.charAt(pos);

		switch (c) {
		case '-':
			if (current == Token.CONST || current == Token.VARIABLE || current == Token.CLOSE_BRACKET) {
				current = Token.SUB;
			} else {
				if (expression.length() - pos > 1 && Character.isDigit(expression.charAt(pos + 1))) {
					pos++;
					checkOperation();
					int left = pos;
					while (pos < expression.length() && isNumber(expression.charAt(pos))) {
						pos++;
					}
					int right = pos;
					value = operation.parseNumber(expression.substring(left - 1, right));
					if (pos != expression.length()) {
						pos--;
					}
					current = Token.CONST;
				} else {
					current = Token.MINUS;
				}
			}
			break;
		case '+':
			checkConstOrVariable("before");
			current = Token.ADD;
			break;
		case '*':
			checkConstOrVariable("before");
			current = Token.MUL;
			break;
		case '/':
			checkConstOrVariable("before");
			current = Token.DIV;
			break;
		case '(':
			checkOperation();
			current = Token.OPEN_BRACKET;
			balance++;
			break;
		case ')':
			checkConstOrVariable("before");
			balance--;
			if (balance < 0) {
				throw new ExtraClosingBracketException(pos, expression);
			}
			current = Token.CLOSE_BRACKET;
			break;
		default:
			if (Character.isDigit(c)) {
				checkOperation();
				int left = pos;
				while (pos < expression.length() && isNumber(expression.charAt(pos))) {
					pos++;
				}
				int right = pos;
				value = operation.parseNumber(expression.substring(left, right));
				if (pos != expression.length()) {
					pos--;
				}
				current = Token.CONST;
			} else if (c == 'x' || c == 'y' || c == 'z') {
				checkOperation();
				var = new String() + c;
				current = Token.VARIABLE;
			} else if (expression.length() - pos >= 3 && expression.substring(pos, pos + 3).equals("abs")) {
				checkOperation();
				if (pos + 3 == expression.length()) {
					throw new MissingConstOrVariableException(pos + 2, expression, "after");
				}
				if (!correctSymbol(expression.charAt(pos + 3))) {
					throw new MissingWhiteSpaceException(pos + 2, expression);
				}
				current = Token.ABS;
				pos += 2;
			} else if (expression.length() - pos >= 3 && expression.substring(pos, pos + 3).equals("min")) {
				checkConstOrVariable("before");
				if (pos + 3 == expression.length()) {
					throw new MissingConstOrVariableException(pos + 2, expression, "after");
				}
				if (!correctSymbol(expression.charAt(pos + 3))) {
					throw new MissingWhiteSpaceException(pos + 2, expression);
				}
				current = Token.MIN;
				pos += 2;
			} else if (expression.length() - pos >= 3 && expression.substring(pos, pos + 3).equals("max")) {
				checkConstOrVariable("before");
				if (pos + 3 == expression.length()) {
					throw new MissingConstOrVariableException(pos + 2, expression, "after");
				}
				if (!correctSymbol(expression.charAt(pos + 3))) {
					throw new MissingWhiteSpaceException(pos + 2, expression);
				}
				current = Token.MAX;
				pos += 2;
			} else if (expression.length() - pos >= 3 && expression.substring(pos, pos + 3).equals("mod")) {
				checkConstOrVariable("before");
				if (pos + 3 == expression.length()) {
					throw new MissingConstOrVariableException(pos + 2, expression, "after");
				}
				if (!correctSymbol(expression.charAt(pos + 3))) {
					throw new MissingWhiteSpaceException(pos + 2, expression);
				}
				current = Token.MOD;
				pos += 2;
			} else if (expression.length() - pos >= 6 && expression.substring(pos, pos + 6).equals("square")) {
				if (pos + 6 == expression.length()) {
					throw new MissingConstOrVariableException(pos + 5, expression, "after");
				}
				if (!correctSymbol(expression.charAt(pos + 6))) {
					throw new MissingWhiteSpaceException(pos + 5, expression);
				}
				current = Token.SQR;
				pos += 5;
				
			} else {
				throw new UnknownSymbolException(pos, expression);
			}
		}
		pos++;
	}

	public Token getNext() throws ParsingException {
		nextToken();
		return current;
	}

	public T getValue() {
		return value;
	}

	public String getVar() {
		return var;
	}

	public String getExpression() {
		return expression;
	}

	public Token getCur() {
		return current;
	}

	public int getPos() {
		return pos;
	}

	private boolean isNumber(char c) {
		if (c == '.' || c == '-' || Character.isDigit(c)) {
			return true;
		}
		return false;
	}
}
