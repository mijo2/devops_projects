package averageIncome;

import java.util.ArrayList;
import java.util.Comparator;

public class DataComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		ArrayList<String> a1 = (ArrayList<String>) o1;
		ArrayList<String> a2 = (ArrayList<String>) o2;

		return a1.get(0).compareTo(a2.get(0));
	}
	
}
