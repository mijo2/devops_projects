package calc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

public class CreateData {
	private static Random rand = new Random();
	
	public static void main(String[] args) throws IOException {
		int number = 10;
		ArrayList<String> data_points = new ArrayList<String>();

		for(int i = 0; i < number; i++) {
			data_points.add(_create_data());
		}

		String file_name = "src/main/resources/data_generated.txt";
	    FileWriter fstream = new FileWriter(file_name, true);
        BufferedWriter out = new BufferedWriter(fstream);
        for(String line: data_points) {
	        out.write(line);
	        out.newLine();
        }

        out.close();
        fstream.close();
	}

	private static String _create_data() {
		
		int option = rand.nextInt(5) + 1;
		ArrayList<String> dat = new ArrayList<String>();
		dat.add(Integer.toString(option));
		
		String data = "";
		
		if(option == 1) {
			String date1 = _create_random_date();
			String date2 = _create_random_date();
			
			String operation = _create_random_operation();
			dat.add(date1);
			dat.add(date2);
			dat.add(operation);

		}
		
		if(option == 2) {
			String unit = _create_random_unit();
			String n = Integer.toString(rand.nextInt(1000) + 1);
			String date = _create_random_date();
			
			dat.add(unit);
			dat.add(n);
			dat.add(date);
		}
		
		if(option == 3 || option == 4) {
			String date = _create_random_date();
			dat.add(date);
		}
		
		if(option == 5) {
			String phrase = _create_random_phrase();
			dat.add(phrase);
		}
		
		data = String.join(" ", dat);
		return data;
	}

	private static String _create_random_phrase() {
		String[] types1 = {
				"Today", "Tomorrow", "Day-after-tomorrow", "yesterday",
				"Day-before-yesterday", "Last week", "Previous week",
				"Next week", "Next month", "Next year", "Last month",
				"Last year", "Month after", "Month before",
		};
		String[] types2 = {
				"days from now", "months from now", "years from now",
				"days earlier", "weeks earlier", "months earlier",
				"years earlier"
		};
		String phrase = "";
		
		if(rand.nextInt(2) == 0) {
			phrase = types1[rand.nextInt(types1.length)];
		}
		else {
			phrase = rand.nextInt(1000) + " " + types2[rand.nextInt(types2.length)];
		}
		return phrase;
	}

	private static String _create_random_unit() {
		String[] units = {"days", "weeks", "months"};
		return units[rand.nextInt(3)];
	}

	private static String _create_random_operation() {
		String[] operations = {"+", "-"};
		return operations[rand.nextInt(2)];
	}

	private static String _create_random_date() {
		GregorianCalendar gc = new GregorianCalendar();
		
		int year = randRange(900, 2020);
		gc.set(gc.YEAR, year);

		int day = randRange(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
		gc.set(gc.DAY_OF_YEAR, day);

		return AddDates.calendar_to_string(gc);
	}
	
	public static int randRange(int lower, int higher) {
		return rand.nextInt(higher - lower) + lower;
	}
}
