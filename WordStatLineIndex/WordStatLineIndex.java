import java.util.*;
import javafx.util.*;
import java.io.*;

public class WordStatLineIndex {
	public static void main(String[] args) throws IOException {
		
		if (args.length != 2) {
    		System.out.println("Не введены названия файлов ввода и вывода." + "\n" + "Введите их в формате: <input_file> <output_file>.");
    		return;
    	}  		

		try (FileInputStream fileIn = new FileInputStream(args[0]);) {	
			FastScanner in = new FastScanner(fileIn, "UTF-8");
			ArrayList<String> str = new ArrayList<String>();
			Map<String, ArrayList<Pair<Integer, Integer>>> words = new TreeMap<String, ArrayList<Pair<Integer, Integer>>>();

			while (in.hasNextLine()) {
				str.add(in.nextLine());
			}			
			
			for(int i = 0; i < str.size(); i++) {
				int num = 1;
				for (String a : str.get(i).toLowerCase().split("[^\\p{L}\\p{Pd}']")) {
					if (words.get(a) != null) {
						words.get(a).add(new Pair<>(i + 1, num++));
					} else {
						if(a.length() > 0) {
							words.put(a, new ArrayList<Pair<Integer, Integer>>());
							words.get(a).add(new Pair<>(i + 1, num++));
						}
				    }					
				}
			}
			
			try (PrintWriter fileOut  = new PrintWriter(new File(args[1]), "UTF-8");) {
				for (Map.Entry<String, ArrayList<Pair<Integer, Integer>>> entry: words.entrySet()) { 
					fileOut.print(entry.getKey() + " " + entry.getValue().size());
					for (int i = 0; i < entry.getValue().size(); i++) {
						fileOut.print(" " + entry.getValue().get(i).getKey() + ":" + entry.getValue().get(i).getValue());
					}
					fileOut.println();
				} 
			} catch (FileNotFoundException e) {
            	System.out.print("Файл вывода не был создан.");
            }
			
			in.close();
			
		} catch (FileNotFoundException e) {
			System.out.print("Файл ввода не был создан.");
		} catch (UnsupportedEncodingException e) {
        	System.out.print("Кодировка файлов не поддержиёвается.");
		}
	}
}

