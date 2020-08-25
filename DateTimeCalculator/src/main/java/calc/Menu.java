package calc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Menu {
	private Hashtable d;
	private int option;
	private static SimpleDateFormat format;
	
	static {
		format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
	}

	public Hashtable getD() {
		return d;
	}

	private Scanner sc;
	
	public Menu() {
		d = new Hashtable();
		run_menu();
	}
	
	private void run_menu() {
		sc = new Scanner(System.in);
		
		sysout("Menu: ");
		sysout("1. Add and substract two dates");
		sysout("2. Add days/months/weeks to the given date");
		sysout("3. Determine the day of the week for a given date");
		sysout("4. Determine the week number for a given date");
		sysout("5. Translate one of the language phrases: ");
		sysout("       Today, Tomorrow, Day-after-tomorrow, yesterday,");
		sysout("       Day-before-yesterday, Last week, Previous week,");
		sysout("       Next week, Next month, Next year, Last month,");
		sysout("       Last year, Month after, Month before, n weeks from now,");
		sysout("       n days from now, n months from now, n years from now,");
		sysout("       n days earlier, n weeks earlier, n months earlier,");
		sysout("       n years earlier");
		sysout("\nNote: When the prompt asks for date, enter the date in the format: DD/MM/YYYY");
		sysout("\nEnter your option: ");

		option = sc.nextInt();
		switch(option) {
			case 1:
				d.put("option", 1);
				d.put("data", _get_data_option1());
				break;
			case 2:
				d.put("option", 2);
				d.put("data", _get_data_option2());
				break;
			case 3:
				d.put("option", 3);
				d.put("data", _get_data_option34());
				break;
			case 4:
				d.put("option", 4);
				d.put("data", _get_data_option34());
				break;
			case 5:
				d.put("option", 5);
				d.put("data", _get_data_option5());
				break;
			default:
				sysout("Incorrect option type.");
				break;
		}
	}

	private ArrayList<String> _get_data_option5() {

		sysout("Enter the options from the ones listed above:");
		ArrayList<String> data = new ArrayList<String>();
		sc.nextLine();
		String input = sc.nextLine();
		_end_execution(input, "days");
		data.add(input);

		return data;
	}

	private ArrayList _get_data_option2() {
		ArrayList data = new ArrayList();
		
		sysout("Enter unit to be added(days, weeks, months): ");
		String unit = sc.next();
		_end_execution(unit, "unit");
		
		sysout("Enter n units to be added(negative if substraction is the target: ");
		int n = sc.nextInt();
		
		sysout("Enter date: ");
		String date = sc.next();
		_end_execution(date, "date");
		
		data.add(unit);
		data.add(n);
		data.add(date);
		
		return data;
	}

	private ArrayList<String> _get_data_option1() {
		ArrayList<String> data = new ArrayList<String>();
		String date1, date2, operation;

		sysout("\nEnter Date 1: ");
		date1 = sc.next();
		data.add(date1);

		sysout("Enter Date 2: ");
		date2 = sc.next();
		data.add(date2);
		
		for(String s: data) {
			_end_execution(s, "date");
		}
		
		sysout("Enter the operation(+, -):");
		operation = sc.next();
		_end_execution(operation, "operation");
		data.add(operation);

		return data;
	}
	
	private ArrayList<String> _get_data_option34(){
		ArrayList<String> data = new ArrayList<String>();
		
		sysout("Enter date: ");
		String date = sc.next();
		_end_execution(date, "date");

		data.add(date);
		
		return data;
	}

	private void sysout(String s) {
		System.out.println(s);
	}
	
	public void _end_execution(String s, String type) {
		boolean correct_format = _end_execution_bool(s, type);
		if(correct_format) return;

		if(type.contentEquals("date")) {
			System.out.println("Incorrect date format!" + s);
		}
		else if(type.contentEquals("unit")) {
			System.out.println("Incorrect input for option 2!");
		}
		else if(type.contentEquals("days")) {
			System.out.println("Incorrect input for option 5!");
		}
		else {
			System.out.println("Incorrect input! Please check the format");
		}
		System.exit(1);
	}
	
	public static boolean _end_execution_bool(String s, String type) {
		if(type.equals("date")) {
			if(!_check_date_format(s)) {
				return false;
			}
		}
		else if(type.equals("unit")) {
			String[] allowed_values = {
					"days",
					"months",
					"weeks"
			};
			boolean not_valid = true;
			for(String str: allowed_values) {
				not_valid &= !str.contentEquals(s);
			}
			
			if(not_valid) {
				return false;
			}
		}
		else if(type.contentEquals("days")) {
			String[] types1 = {
					"Today", "Tomorrow", "Day-after-tomorrow", "yesterday",
					"Day-before-yesterday", "Last week", "Previous week",
					"Next week", "Next month", "Next year", "Last month",
					"Last year", "Month after", "Month before",
			};
			String[] types2 = {
					"n days from now", "n months from now", "n years from now",
					"n days earlier", "n weeks earlier", "n months earlier",
					"n years earlier"
			};
			
			boolean is_type1 = false, is_type2 = false;
			
			for(String str: types1) {
				is_type1 |= str.contentEquals(s);
			}
			
			if(!is_type1) {
				for(String str: types2) {
					is_type2 |= _check_type2_equality(str, s);
				}
			}
			
			if(!is_type1 && !is_type2) {
				return false;
			}
			
		}
		else if(type.contentEquals("operation")) {
			String[] values = {
					"+", "-"
			};

			boolean not_valid = true;
			for(String val: values) {
				not_valid &= !val.contentEquals(s);
			}

			if(not_valid) {
				return false;
			}
		}

		return true;
	}
	
	private static boolean _check_type2_equality(String form, String toCheck) {
		String[] forms = form.split(" ");
		String[] toChecks = toCheck.split(" ");
		
		if(forms.length != toChecks.length)	return false;
		
		boolean to_return = true;
		for(int i = 1; i < forms.length; i++) {
			to_return = forms[i].contentEquals(toChecks[i]);
		}
		
		if(to_return) {
			to_return = isInt(toChecks[0]);
		}
		
		return to_return;
	}
	
	private static boolean isInt(String s)
	{
	 try
	  { int i = Integer.parseInt(s); return true; }

	 catch(NumberFormatException er)
	  { return false; }
	}

	public static boolean _check_date_format(String date) {
		boolean is_correct = true;
		
		try {
			format.parse(date);
		}
		catch(ParseException e) {
			is_correct = false;
		}
		
		return is_correct;
	}
}
