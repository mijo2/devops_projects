package calc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class CalcRunner {
	public static void main(String[] args) throws ParseException {
		Menu menu = new Menu();
		Hashtable data = menu.getD();
		
		MenuProcessor mp = new MenuProcessor(data);
		String answer = mp.process();
		
		System.out.println(answer);
	}
}
