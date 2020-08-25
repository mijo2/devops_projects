package calc;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class CalcRunner {
	public static void main(String[] args) throws ParseException, IOException {
		Menu menu = new Menu();
		Hashtable data = menu.getD();
		String file_name = "log.txt";
		
		MenuProcessor mp = new MenuProcessor(data);
		String answer = mp.process();
		data.put("answer", answer);
		
		System.out.println(answer);
		
		SaveHistory.save_file("log.txt", data);
	}
}
