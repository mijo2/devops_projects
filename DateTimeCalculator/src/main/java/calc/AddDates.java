package calc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddDates {
	private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String add_two_dates(String date1, String date2) throws ParseException {
		Date d1, d2;
		
		d1 = format.parse(date1);
		d2 = format.parse(date2);

		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.setTime(d1);
		c2.setTime(d2);
		
		Calendar cTotal = (Calendar) c1.clone();

        cTotal.add(Calendar.YEAR, c2.get(Calendar.YEAR));
        cTotal.add(Calendar.MONTH, c2.get(Calendar.MONTH) + 1);
        cTotal.add(Calendar.DATE, c2.get(Calendar.DATE));
        
        String answer;
        answer = calendar_to_string(cTotal);
        
        return answer;

	}
	
	public static String calendar_to_string(Calendar c) {
		return c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR);
	}
	
	public static String substract_two_dates(String date1, String date2) throws ParseException {
		Date d1, d2, d;
		d1 = format.parse(date1);
		d2 = format.parse(date2);
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.setTime(d1);
		c2.setTime(d2);
		
		Calendar before, after;
		
		before = (Calendar) c1.clone();
		after = (Calendar) c2.clone();
		
		if(!before.before(after)) {
			before = (Calendar) c2.clone();
			after = (Calendar) c1.clone();
		}
		
		int[] fields = {Calendar.YEAR, Calendar.MONTH, Calendar.DATE};
		int[] changes = {0, 0, 0};
		Calendar clone = (Calendar) before.clone();
		int index = 0;
		
		for(int field: fields) {
			int changed = -1; // 0 indexed
			Calendar subs_tracker = (Calendar) clone.clone();
			while(!subs_tracker.after(after)) {
				subs_tracker.add(field, 1);
				changed++;
			}
			changes[index++] = changed;
			clone.add(field, changed);
		}
		
		String answer = changes[2] + "/" + changes[1] + "/" + changes[0];
		clone.setTime(format.parse(answer));
		
		return calendar_to_string(clone);
		
	}
	
	public static String add_units(String date, String n, String unit) throws ParseException {
		int n1 = Integer.parseInt(n);
		Date d = format.parse(date);
		
		Calendar c1 = Calendar.getInstance(), clone = Calendar.getInstance();
		c1.setTime(d);
		
		int field = -1;
		if(unit.contentEquals("days") || unit.contentEquals("weeks")) {
			field = Calendar.DATE;
			if(unit.contentEquals("weeks")) {
				n1 *= 7;
			}
		}
		else if(unit.contentEquals("months")) {
			field = Calendar.MONTH;
		}
		
		c1.add(n1, field);
		
		return calendar_to_string(c1);
		
	}

	public static String _determine_day(String date) throws ParseException {
		Date d = format.parse(date);
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d);
		
		String[] days = {
				"Sunday",
				"Monday",
				"Tuesday",
				"Wednesday",
				"Thrusday",
				"Friday",
				"Saturday"
		};
		
		return days[c1.getTime().getDay()];
	}

	public static int _determine_week_number(String date) throws ParseException {
		Date d = format.parse(date);
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d);
		
		return c1.WEEK_OF_YEAR;
	}
	
	public static String _add_units_to_current_date(String str) {
		int n = -1, field = -1;
		int days = Calendar.DATE, months = Calendar.MONTH, years = Calendar.YEAR;
		int weeks = days*7;
		
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
		
		int[] fields_type1 = {
				days, days, days, days, days, days, days, days,
				months, years, months, years, months, months
		};
		
		int[] ns_type1 = {
				0, 1, 2, -1, -2, -7, -7, 7, 1, 1, -1, -1, 1, -1
		};
		
		int[] fields_type2 = {
				days, months, years, days, weeks, months, years,
		};
		
		int i = 0;
		for(String s: types1) {
			if(s.contentEquals(str)) {
				field = fields_type1[i];
				n = ns_type1[i];
				break;
			}
			i++;
		}
		
		String str1 = _remove_first_word(str);
		if(field == -1) {
			i = 0;
			for(String s: types2) {
				String s1 = _remove_first_word(s);
				if(s1.contentEquals(str1)) {
					n = Integer.parseInt(str.split(" ")[0]);
					field = fields_type2[i];
					if(field == weeks) {
						field = days;
						n *= 7;
					}
				}
				
				i++;
			}
		}
		
		if(field == -1) {
			System.out.println("Something is wrong");
			return "-1";
		}
		
		Calendar c1 = Calendar.getInstance();
		c1.add(n, field);
		
		return calendar_to_string(c1);
		
	}

	private static String _remove_first_word(String str_) {
		String[] str = str_.split(" ");
		int n = str.length - 1;
		String[] str1 = new String[n];
		System.arraycopy(str, 1, str1, 0, n);
		return String.join(", ", str1);
	}
}
