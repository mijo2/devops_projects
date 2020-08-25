package calc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class SaveHistory {
	
	public static String[][] formats = new String[][]{
			{"Add/Substract", "Date 1", "Date 2", "Operation"},
			{ "Add n units to the Date", "Unit", "n", "Date",},
			{"Day of the week", "Date"},
			{"Week number", "Date"},
			{"Language Phrase conversion", "Phrase: "}
	};
	
	public static void read_file(String file_name) throws IOException, InterruptedException {
		file_name = "src/main/resources" + file_name;
		
		File file = new File(file_name);
		FileReader fr = new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer(); 
		String line;
		
		ArrayList<String> lines = new ArrayList<String>();
		
		while((line=br.readLine())!=null) {
			lines.add(line);
		}
		
		Collections.reverse(lines);
		
		int i = 0;
		while(i < lines.size() && i < 100) {
			System.out.println(lines.get(i++));
		}
		
		TimeUnit.SECONDS.sleep(30);
	}
	
	public static void save_file(String file_name, Hashtable data_dict) throws IOException {
		int option = MenuProcessor.obj_to_int(data_dict.get("option"));
		ArrayList data = (ArrayList) data_dict.get("data");
		
		String to_store = "";
		
		String[] format = formats[option-1];
		
		to_store += format[0];
		
		for(int i = 1; i < format.length; i++) {
			to_store += ", " + format[i] + " : " + data.get(i-1).toString();
		}
		
		file_name = "src/main/resources/" + file_name;
		
		File file = new File(file_name);
		if(!file.exists())
			file.createNewFile();
		
	    FileWriter fstream = new FileWriter(file_name, true);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(to_store);
        out.newLine();
        out.close();
        fstream.close();

	}
}
