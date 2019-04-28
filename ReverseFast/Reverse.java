import java.util.ArrayList;

public class Reverse {
	public static void main(String[] args) {
		try {
			FastScanner in = new FastScanner(System.in);
			ArrayList<String> arr = new ArrayList<String>();
			
			String cur = in.nextLine();
			while (cur != null) {
				arr.add(cur);
				cur = in.nextLine();
			}
			
			for (int i = arr.size() - 1; i >= 0; i--) {
				if (arr.get(i).isEmpty()) {
					System.out.println();
				} else {
					String[] numbersw = arr.get(i).split(" ");
					for (int j = numbersw.length - 1; j >= 0; j--) {
						System.out.print(numbersw[j] + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}