package averageIncome;

import java.io.IOException;
import java.util.ArrayList;

public interface FileReaderInterface {
	public ArrayList<ArrayList<String>> readFile() throws IOException;
}
