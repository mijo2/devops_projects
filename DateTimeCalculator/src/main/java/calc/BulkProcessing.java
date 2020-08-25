package calc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;

public class BulkProcessing {
	public static void main(String[] args) throws IOException, ParseException {
		String file_name = "src/main/resources/data.txt";

		File file = new File(file_name);
		FileReader fr = new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer(); 
		String line;

		ArrayList<String> answers = new ArrayList<String>();

		while((line=br.readLine())!=null) {
			answers.add(_process_datapoint(line));
		}

		System.out.println(answers);
	}

	private static String _process_datapoint(String line) throws ParseException {
		String[] data = line.split(" ");
		int option = Integer.parseInt(data[0]);

		ArrayList dat = new ArrayList();

		for(int i = 1; i < data.length; i++) {
			dat.add(data[i]);
		}

		Hashtable d = new Hashtable();
		d.put("option", option);
		d.put("data", dat);

		MenuProcessor mp = new MenuProcessor(d);

		return mp.process();
	}
}
