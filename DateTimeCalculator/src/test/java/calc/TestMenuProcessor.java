package calc;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestMenuProcessor {
	
	@Parameterized.Parameter(0)
	public int option;
	
	@Parameterized.Parameter(1)
	public ArrayList dat;
	
	@Parameterized.Parameter(2)
	public String answer;

	@Parameterized.Parameters(name="Passing option {0} and data {1} gives us the date {2}.")
	public static List<Object[]> parameters(){
		return Arrays.asList(new Object[][] {
			{1, new ArrayList(Arrays.asList("12/11/1997", "01/01/0001", "+")), "13/12/1998"},
			{2, new ArrayList(Arrays.asList("weeks", "2", "12/11/1997")), "26/11/1997"},
			{3, new ArrayList(Arrays.asList("12/11/1997")), "Wednesday"},
			{4, new ArrayList(Arrays.asList("12/11/1997")), "3"},
			{5, new ArrayList(Arrays.asList("5 days from now")), "27/8/2020"}
		});
	}

	@Test
	public void isCorrect() {
		Hashtable d = new Hashtable();
		d.put("option", option);
		d.put("data", dat);
		try {
			assertEquals(new MenuProcessor(d).process(), answer);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
