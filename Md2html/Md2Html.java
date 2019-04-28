package md2html;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Md2Html {

	private final ParserSource source;
	private final FileWriter writer;
	
	private Map<Tags, Boolean> tagOpened = new HashMap<Tags, Boolean>();
	private enum Tags {
		HEADER, 
		PARAGRAPH, 
		BOLD, 
		STRONG,
		STRIKETHROUGH,
		CODE
	}
	
	{
		tagOpened.put(Tags.HEADER, false);
		tagOpened.put(Tags.PARAGRAPH, false);
		tagOpened.put(Tags.BOLD, false);
		tagOpened.put(Tags.STRONG, false);
		tagOpened.put(Tags.STRIKETHROUGH, false);
		tagOpened.put(Tags.CODE, false);
	}
	
	public Md2Html(final String inputFile, final String outputFile) throws Exception, ParseException {
		source = new ParserSource(inputFile);
		writer = new FileWriter(outputFile);
	}
	
	public void parse() throws ParseException {	
		source.nextChar();
		while (source.getChar() != source.END) {
			System.out.print(parseValue());
		}
	}
	
	private String parseValue() throws ParseException {
		if (test('#')) {
			return parseHeader();
		} if (test('\r')) {
			//return parseParagraph();
		} if (test('*')) {
			return parseBold();
		} 
		String res = "";
		res += source.getChar();
		return res;
	}
	/*
	private String parseParagraph() {
		if (source.nextChar() == '\n') {
			if (source.nextChar() == '\r') {
				if (source.nextChar() == '\n') {
					
				}
			}
		}
	}
	*/
	private String parseBold() throws ParseException {
		boolean isSpace = false;
		if (Character.isWhitespace(source.getLast())) {
			isSpace = true;
		}
		int count = 1;
		while (source.nextChar() == '*') {
			count++;
		}
		
		if (count >= 2) {
			if (tagOpened.get(Tags.STRONG)) {
				if (!isSpace) {
					tagOpened.put(Tags.STRONG, false);
					return "<\\strong>" + source.getChar();
				} else {
					StringBuilder res = new StringBuilder();
					for (int i = 0; i < count; i++) {
						res.append('*');
					}
					return res.toString();
				}
			} else {
				if (!Character.isWhitespace(source.getChar())) {
					tagOpened.put(Tags.STRONG, true);
					return "<strong>" + source.getChar();
				} else {
					StringBuilder res = new StringBuilder();
					for (int i = 0; i < count; i++) {
						res.append('*');
					}
					return res.toString();
				}
			}
		} else {
			if (tagOpened.get(Tags.BOLD)) {
				if (!isSpace) {
					tagOpened.put(Tags.BOLD, false);
					return "<\\em>" + source.getChar();
				} else {
					return "*" + source.getChar();
				}
			} else {
				if (!Character.isWhitespace(source.getChar())) {
					tagOpened.put(Tags.BOLD, true);
					return "<em>";
				} else {
					return "*";
				}
			}
		}
	}
	
	private String parseHeader() throws ParseException {
		if (tagOpened.get(Tags.HEADER) || tagOpened.get(Tags.PARAGRAPH)) {
			return "#";
		}
		int count = 1;
		while(source.nextChar() == '#') {
			count++;
		} 

		if (count > 6 || !Character.isWhitespace(source.getChar())) {
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < count; i++) {
				result.append('#');
			}
			return result.toString();
		} else {
			tagOpened.put(Tags.HEADER, true);
			return String.format("<h%d>", count);
		}
	}
	
	private boolean test(final char c) {
		return c == source.getChar();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

