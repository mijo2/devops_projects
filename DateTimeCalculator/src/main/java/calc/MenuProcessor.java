package calc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

public class MenuProcessor {
	private Hashtable data;
	private SimpleDateFormat format;

	public MenuProcessor(Hashtable d) {
		data = d;
		format = new SimpleDateFormat("dd/MM/yyyy");
	}

	public static int obj_to_int(Object o) {
		return Integer.parseInt(o.toString());
	}

	public String process() throws ParseException {
		int option = obj_to_int(data.get("option"));
		String answer = "";
		
		switch(option) {
			case 1: 
				answer = _process_1();
				break;
			case 2:
				answer = _process_2();
				break;
			case 3:
				answer = _process_3();
				break;
			case 4:
				answer = _process_4();
				break;
			case 5:
				answer = _process_5();
				break;
				
		}
		return answer;
	}
	
	private String _process_5() {
		ArrayList dat = (ArrayList) data.get("data");
		Object string = dat.get(0);
		
		return AddDates._add_units_to_current_date(string.toString());
	}

	private String _process_4() {
		ArrayList dat = (ArrayList) data.get("data");
		Object date = dat.get(0);

		try {
			return AddDates._determine_week_number(date.toString()) + "";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "-1";
	}

	private String _process_3() {
		ArrayList dat = (ArrayList) data.get("data");
		Object date = dat.get(0);

		try {
			return AddDates._determine_day(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "-1";
	}

	private String _process_2() {
		ArrayList dat = (ArrayList) data.get("data");
		Object unit, n, date;
		unit = dat.get(0);
		n = dat.get(1);
		date = dat.get(2);
		
		String answer = "-1";
		try {
			answer = AddDates.add_units(date.toString(), n.toString(), unit.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return answer;
	}

	private String _process_1(){
		ArrayList dat = (ArrayList) data.get("data");
		Object date1, date2, opt;
		date1 = dat.get(0);
		date2 = dat.get(1);
		opt = dat.get(2);

		String answer = "Not solvable";
		if(opt.equals("+"))
			answer = _add_dates(date1, date2);
		else
			answer = _substract_dates(date1, date2);
		return answer;
	}

	private String _add_dates(Object date1, Object date2){
		try {
			return AddDates.add_two_dates(date1.toString(), date2.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "-1";
	}
	
	private String _substract_dates(Object date1, Object date2){
		try {
			return AddDates.substract_two_dates(date1.toString(), date2.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "-1";
	}
}
