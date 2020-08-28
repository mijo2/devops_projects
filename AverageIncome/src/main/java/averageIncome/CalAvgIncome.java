package averageIncome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;

public class CalAvgIncome {
	public ArrayList<ArrayList<String>> data;
	public Hashtable<String, Double> rates;
	
	public CalAvgIncome(ArrayList<ArrayList<String>> data) {
		this.data = data;
		this.rates = new Hashtable<String, Double>();
		rates.put("INR", 1./66);
		rates.put("GBP", 1.5);
		rates.put("USD", 1.);
		rates.put("SGD", 1/1.5);
		rates.put("SGP", 1/1.5);
		rates.put("HKD", 1/8.);
	}
	
	public ArrayList<ArrayList<String>> return_incomes(){
		Hashtable<String, String> incomes = new Hashtable<String, String>();
		Hashtable<String, Integer> ns = new Hashtable<String, Integer>();
		for(ArrayList<String> arr: data) {
			String currency = arr.get(3), income = arr.get(4);
			String country = arr.get(1), city = arr.get(0), gender = arr.get(2);
			String income_in_dollars = _to_dollars(currency, income);
			if(country.isBlank()) country = city;
			String key = country + "," + gender;
			if(incomes.get(key) == null) {
				ns.put(key, 1);
				incomes.put(key, income_in_dollars);
			}
			else {
				int n = ns.get(key);
				double income_ = Double.parseDouble(income_in_dollars) + 
						n * Double.parseDouble(incomes.get(key));
				income_ = income_ / (n+1);
				incomes.put(key, Double.toString(income_));
				ns.put(key, n+1);
			}
		}

		ArrayList<ArrayList<String>> report = new ArrayList<ArrayList<String>>();
		
		Enumeration<String> enu = incomes.keys();
		
		while(enu.hasMoreElements()) {
			ArrayList<String> a = new ArrayList<String>();
			a.add(enu.nextElement());
			report.add(a);
		}
		
		Collections.sort(report, new DataComparator());
		
		for(int i = 0; i < report.size(); i++) {
			ArrayList<String> a2 = new ArrayList<String>();
			String[] elements = report.get(i).get(0).split(",");
			a2.add(elements[0]);
			a2.add(elements[1]);
			a2.add(incomes.get(report.get(i).get(0)));
			report.set(i, a2);
		}
		
		return report;
		
	}

	private String _to_dollars(String currency, String income) {
		double inc = Double.parseDouble(income);
		System.out.println(inc);
		System.out.println(currency);
		inc = (inc * rates.get(currency));
		inc = Math.round(inc);

		return Double.toString(inc);
	}
	
}
