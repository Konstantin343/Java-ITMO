import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;

class FastScanner {
	private DataInputStream din;	
	final private int bufferSize;
	private int bytesCount;
	private byte[] buffer;
	private int bufferCur;
	
	public FastScanner(InputStream in) {
		din = new DataInputStream(in);
		bufferSize = 1 << 8;
		buffer = new byte[bufferSize];
		bytesCount = 0;
		bufferCur = 0;
	}
	 
	private byte nextByte() {
		if(bufferCur == bytesCount) {
			  try {
				  bufferCur = 0;
				  bytesCount = din.read(buffer, bufferCur, bufferSize);
			  } catch (Exception e) {
				  System.out.println(e);
			  }
			  if (bytesCount == -1) 
				  buffer[0] = -1;
		}
		return buffer[bufferCur++];
	}

	public String nextLine() {
		StringBuilder ans = new StringBuilder();
		try {		
			byte c = nextByte();	
			if (c == -1) {
				return null;
			}
			while (c != '\r') {
				ans.append((char)c);
				c = nextByte();				
			}
			c = nextByte();
		} catch (Exception e) {
			System.out.println(e);
		}
		return ans.toString();
	}
	
	public int nextInt() {
	    int len = 0;
	    int ans = 0;
	    byte[] s = new byte[bufferSize];
	    try {
	        byte c = nextByte();
	        while ((c > '9' || c < '0') && c != '-') {
	            c = nextByte();
	        }
	        while (c >= '0' && c <= '9' || c == '-') {
	        	s[len++] = c;
	            c = nextByte();
	        }
	        try {
	        	ans = Integer.parseInt(new String(s, 0, len));
	        } catch (NumberFormatException e) {
	        	System.out.println("¬ведено неккоректное значение:" + s);
	        }
	    } catch (Exception e) {
	    	System.out.println(e);
	    }     
	    return ans;
	}
	
	public long nextLong() {
	    int len = 0;
	    byte[] s = new byte[bufferSize];
	    try {
	        byte c = nextByte();
	        while ((c > '9' || c < '0') && c != '-') {
	            c = nextByte();
	        }
	        while (c >= '0' && c <= '9' || c == '-') {
	        	s[len++] = c;
	            c = nextByte();
	        }	   
	    } catch (Exception e) {
	    	System.out.println(e);
	    }     
	    return Long.parseLong(new String(s, 0, len));	   
	}
	
	public double nextDouble() {
	    int len = 0;
	    byte[] s = new byte[bufferSize];
	    try {
	        byte c = nextByte();
	        while ((c > '9' || c < '0') && c != '-' && c != '.') {
	            c = nextByte();
	        }
	        while (c >= '0' && c <= '9' || c == '-' || c == '.') {
	        	s[len++] = c;
	            c = nextByte();
	        }	   
	    } catch (Exception e) {
	    	System.out.println(e);
	    }     
	    return Double.parseDouble(new String(s, 0, len));	   
	}
	
	public ArrayList<Integer> numbers() {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		StringBuilder s = new StringBuilder();	
		try {		
			byte c = nextByte();
			if (c == '\n') {
				c = nextByte();
			}
			if (c == -1) {
				return null;
			}		
			if (c == '\r') {
				return ans;
			}	
			while (c != '\n') {						
				if (c == ' ' || c == '\r') {
					try {
						ans.add(Integer.parseInt(s.toString()));
					} catch (NumberFormatException e) {
						System.out.println("¬ведено число некорректного формата: " + s);
					}
 				} else {
 					s.append((char)c);
 				}
				c = nextByte();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return ans;
	}
}