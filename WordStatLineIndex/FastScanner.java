import java.io.*;

class FastScanner {
	private InputStreamReader din;	
	final private int BUFFER_SIZE = 1 << 8;
	private int bytesCount = 0;
	private char[] buffer = new char[BUFFER_SIZE];
	private int bufferCur = 0;
	
	public FastScanner(InputStream in) {
		din = new InputStreamReader(in);
	}
	
	public FastScanner(FileInputStream in, String encoding) {
		try {
			din = new InputStreamReader(in, encoding);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Кодировка не поддерживается.");
		}
	}
	
	public void close() throws IOException {
		din.close();
	}
	
	private char nextChar() {
		if(bufferCur == bytesCount) {
			  try {
				  bufferCur = 0;
				  bytesCount = din.read(buffer, bufferCur, BUFFER_SIZE);
			  } catch (Exception e) {
				  System.out.println(e);
			  }
			  if (bytesCount == -1) 
				  buffer[0] = (char)(-1);
		}
		return buffer[bufferCur++];
	}

	public boolean hasNextLine() {
		char c = nextChar();
		bufferCur--;
		if ((byte)c == -1) {
			return false;
		}	
		return true;
	}
	
	public boolean hasNextInt() {
		char c = nextChar();
		bufferCur--;
		while (c == ' ' || c == '\n' || c == '\r') {
        	c = nextChar();
        }	
		if ((c < '0' || c > '9') && c != '-') {
			return false;
		}		
		return true;
	}
	
	public String nextLine() {
		StringBuilder ans = new StringBuilder();	
			char c = nextChar();	
			while (c != '\n') {
				ans.append(c);
				c = nextChar();				
			}
		return ans.toString();
	}
	
	public int nextInt() {
	    int ans = 0;
	    StringBuilder s = new StringBuilder();
	        char c = nextChar();    
	        while (c != ' ' && c != '\r') {
	        	s.append(c);
	            c = nextChar();
	        }
	        try {
	        	ans = Integer.parseInt(s.toString());
	        } catch (NumberFormatException e) {
	        	System.out.println("Введено неккоректное значение:" + s);
	        } 
	    return ans;
	}
	
	public long nexLong() {
	    long ans = 0;
	    StringBuilder s = new StringBuilder();
	        char c = nextChar();    
	        while (c != ' ' && c != '\r') {
	        	s.append(c);
	            c = nextChar();
	        }
	        try {
	        	ans = Long.parseLong(s.toString());
	        } catch (NumberFormatException e) {
	        	System.out.println("Введено неккоректное значение:" + s);
	        }
	    return ans;
	}
	
	public double nextDouble() {
	    double ans = 0;
	    StringBuilder s = new StringBuilder();
	        char c = nextChar();    
	        while (c != ' ' && c != '\r') {
	        	s.append(c);
	            c = nextChar();
	        }
	        try {
	        	ans = Double.parseDouble(s.toString());
	        } catch (NumberFormatException e) {
	        	System.out.println("Введено неккоректное значение:" + s);
	        }   
	    return ans;
	}
}