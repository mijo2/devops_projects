package averageIncome;

import java.util.ArrayList;

public interface FileWriterInterface {
	public void writeCSVfile(ArrayList<ArrayList<String>> data, String file_name);
}
