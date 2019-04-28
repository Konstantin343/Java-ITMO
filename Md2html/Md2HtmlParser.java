package md2html;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Md2HtmlParser {

	private final ParserSource source;
	private final FileWriter writer;
	
	private enum Tags {
		HEADER1, HEADER2, HEADER3, HEADER4, HEADER5, HEADER6,  
		PARAGRAPH, 
		BOLD, 
		STRONG,
		STRIKETHROUGH,
		CODE,
		UNDERLINE,
		MARK
	}
	private Stack<Tags> stack = new Stack<Tags>();
	private Set<Character> special = new HashSet<Character>();
	{
		special.add('*');
		special.add('#');
		special.add('-');
		special.add('_');
		special.add('`');
		special.add('<');
		special.add('>');
		special.add('&');
		special.add('\\');
		special.add('+');
		special.add('[');
		special.add(']');
		special.add('(');
		special.add(')');
		special.add('!');
		special.add('~');
	}
	
	public Md2HtmlParser(final String inputFile, final String outputFile) throws Exception, ParseException {
		source = new ParserSource(inputFile);
		writer = new FileWriter(outputFile, StandardCharsets.UTF_8);		
	}
	
	public void parse() throws ParseException, IOException {	
		source.nextChar();
		while (test('\r') || test('\n')) {
			source.nextChar();
		}
		if (!test('#')) {
			stack.add(Tags.PARAGRAPH);
			writer.write("<p>");
		}
		while(!test(ParserSource.END)) {
			writer.write(parseValue());
		}
		writer.write(closeAll());
		writer.close();
		source.close();
	}
	
	private String parseValue() throws ParseException {
		if (test('#')) {
			return parseHeader();
		} if (test('\r')) {
			return parseParagraph();
		} if (test('*') || test('_')) {
			return parseBold();
		} if (test('-')) {
			return parseStrikethrough();
		} if (test('\\')) {
			source.nextChar();
			if (special.contains(source.getChar())) {
				String res = "";
				res += source.getChar();
				source.nextChar();
				return res;
			}
			return "\\";
		} if (test('`')) {
			return parseCode();
		} if (test('&') || test('<') || test('>')) {
			switch (source.getChar()) {
				case '&':
					source.nextChar();
					return "&amp;";
				case '>':
					source.nextChar();
					return "&gt;";
				case '<':
					source.nextChar();
					return "&lt;";
			}
		} if (test('+')) {
			return parseUnderline();
		} if (test('[')) {
			return parseLink();
		} if (test('~')) {
			return parseMark();
		} if (test('!')) {
			return parseImage();
		}
		String res = "";
		res += source.getChar();
		source.nextChar();
		return res;
	}
	
	private String parseMark() throws ParseException {
		if (stack.peek() == Tags.MARK) {
			stack.pop();
			source.nextChar();
			return "</mark>";
		}
		stack.add(Tags.MARK);
		source.nextChar();
		return "<mark>";
	}
	
	private String parseImage() throws ParseException {
		StringBuilder text = new StringBuilder();
		StringBuilder image = new StringBuilder();
		source.nextChar();
		source.nextChar();
		while (!test(']')) {
			text.append(source.getChar());
			source.nextChar();
		}
		source.nextChar();
		source.nextChar();
		while (!test(')')) {
			image.append(source.getChar());
			source.nextChar();
		}
		source.nextChar();
		return String.format("<img alt='%s' src='%s'>", text.toString(), image.toString());
	}
	
	private String parseLink() throws ParseException {
		StringBuilder text = new StringBuilder();
		StringBuilder link = new StringBuilder();
		source.nextChar();
		while (!test(']')) {
			text.append(parseValue());
		}
		source.nextChar();
		source.nextChar();
		while (!test(')')) {
			link.append(source.getChar());
			source.nextChar();
		}
		source.nextChar();
		return String.format("<a href='%s'>%s</a>", link.toString(), text.toString());
	}
	
	private String parseCode() throws ParseException {
		if (stack.peek() == Tags.CODE) {
			stack.pop();
			source.nextChar();
			return "</code>";
		}
		stack.add(Tags.CODE);
		source.nextChar();
		return "<code>";
	}

	private String parseUnderline() throws ParseException {
		source.nextChar();
		if (test(source.getLast())) {
			if (stack.peek() == Tags.UNDERLINE) {
				if (!Character.isWhitespace(source.getLast())) {
					stack.pop();
					source.nextChar();
					return "</u>";
				} 
				String res = "";
				res += source.getLast() + source.getLast();
				return res;
			}
			if (!Character.isWhitespace(source.nextChar())) {
				stack.add(Tags.UNDERLINE);
				return "<u>";
			}
		}
		String res = "";
		res += source.getLast();
		return res;
	}
	
	private String parseStrikethrough() throws ParseException {
		source.nextChar();
		if (test(source.getLast())) {
			if (stack.peek() == Tags.STRIKETHROUGH) {
				if (!Character.isWhitespace(source.getLast())) {
					stack.pop();
					source.nextChar();
					return "</s>";
				} 
				String res = "";
				res += source.getLast() + source.getLast();
				return res;
			}
			if (!Character.isWhitespace(source.nextChar())) {
				stack.add(Tags.STRIKETHROUGH);
				return "<s>";
			}
		}
		String res = "";
		res += source.getLast();
		return res;
	}
	
	private String parseBold() throws ParseException {
		if (stack.peek() == Tags.BOLD) {
			char prev = source.getChar();
			source.nextChar();
			if (test(source.getLast())) {
				if (!Character.isWhitespace(source.nextChar())) {
					stack.add(Tags.STRONG);
					return "<strong>";
				} 
			}
			if (!Character.isWhitespace(prev)) {
				stack.pop();
				return "</em>";
			} 
			String res = "";
			res += source.getLast() + source.getLast();
			return res;
		}
		if (stack.peek() == Tags.STRONG) {
			source.nextChar();
			if (test(source.getLast()) && !Character.isWhitespace(source.getLast())) {
				stack.pop();
				source.nextChar();
				return "</strong>";
			}
			if (!Character.isWhitespace(source.getChar())) {
				stack.add(Tags.BOLD);
				return "<em>";
			} 
			String res = "";
			res += source.getLast();
			return res;
		}
		source.nextChar();
		if (test(source.getLast())) {
			if (!Character.isWhitespace(source.nextChar())) {
				stack.add(Tags.STRONG);
				return "<strong>";
			} 
			String res = "";
			res += source.getLast() + source.getLast();
			return res;
		} else {
			if (!Character.isWhitespace(source.getChar())) {
				stack.add(Tags.BOLD);
				return "<em>";
			} 
			String res = "";
			res += source.getLast();
			return res;
		}
	}
	
	private String parseParagraph() throws ParseException {
		source.nextChar();
		if (source.nextChar() == ParserSource.END) {
			return closeAll();
		}
		if (test('\r')) {
			source.nextChar();
			while (test('\r') || test('\n')) {
				source.nextChar();
			}
			StringBuilder res = new StringBuilder();
			res.append(closeAll() + "\r\n");
			if (!test(ParserSource.END) && !test('#')) {
				res.append("<p>");
				stack.add(Tags.PARAGRAPH);
			}
			return res.toString();
		}
		return "\r\n";
	}
	
	private String parseHeader() throws ParseException {
		if (!stack.isEmpty()) {
			source.nextChar();
			return "#";
		}	
		int count = 0;
		while (test('#')) {
			count++;
			source.nextChar();
		}		
		if (!Character.isWhitespace(source.getChar()) || count > 6) {
			StringBuilder res = new StringBuilder();
			res.append("<p>");
			stack.add(Tags.PARAGRAPH);
			for (int i = 0; i < count; i++) {
				res.append('#');
			}
			return res.toString();
		}	
		while (Character.isWhitespace(source.getChar())) {
			source.nextChar();
		}		
		switch (count) {
		case 1:
			stack.add(Tags.HEADER1);
			break;
		case 2:
			stack.add(Tags.HEADER2);
			break;
		case 3:
			stack.add(Tags.HEADER3);
			break;
		case 4:
			stack.add(Tags.HEADER4);
			break;
		case 5:
			stack.add(Tags.HEADER5);
			break;
		case 6:
			stack.add(Tags.HEADER6);
			break;
		}
		return String.format("<h%d>", count);
	}
	
	private String closeAll() {
		StringBuilder res = new StringBuilder();
		while (!stack.isEmpty()) {
			Tags cur = stack.pop();
			switch (cur) {
				case HEADER1:
					res.append("</h1>");
					break;
				case HEADER2:
					res.append("</h2>");
					break;
				case HEADER3:
					res.append("</h3>");
					break;
				case HEADER4:
					res.append("</h4>");
					break;
				case HEADER5:
					res.append("</h5>");
					break;
				case HEADER6:
					res.append("</h6>");
					break;
				case PARAGRAPH:
					res.append("</p>");
					break;
				case BOLD:
					res.append("</em>");
					break;
				case STRONG:
					res.append("</strong>");
					break;
				case STRIKETHROUGH:
					res.append("</s>");
					break;
				case CODE:
					res.append("</code>");
					break;
				case UNDERLINE:
					res.append("</u>");
					break;
				case MARK:
					res.append("</mark>");
					break;
			}
		}
		return res.toString();
	}
	
	private boolean test(final char c) {
		return c == source.getChar();
	}
}
	