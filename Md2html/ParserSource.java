package md2html;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.nio.charset.StandardCharsets;

public class ParserSource {
	public static char END = '\0';
	
	private Reader reader;
	
    private int line = 1;
    private int posInLine = 1;
	private char c;
	private char last = '\0';
	
    public ParserSource(final String fileName) throws ParseException {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
        } catch (final IOException e) {
        	throw error("Error opening input file '%s': %s", fileName, e.getMessage());
        }
    }
    
    private char readChar() throws IOException {
        final int read = reader.read();
        return read == -1 ? END : (char)read;
    }
    
    public char nextChar() throws ParseException {
        try {
            if (c == '\n') {
                line++;
                posInLine = 0;
            }
            last = c;
            c = readChar();
            posInLine++;
            return c;
        } catch (final IOException e) {
        	throw error("Source read error", e.getMessage());
        }
    }
    
    public char getChar() {
    	return c;
    }
    
    public char getLast() {
    	return last;
    }
    
    public ParseException error(final String format, final Object... args) throws ParseException {
        return new ParseException(line, posInLine, String.format("%d:%d: %s", line, posInLine, String.format(format, args)));
    }
}
