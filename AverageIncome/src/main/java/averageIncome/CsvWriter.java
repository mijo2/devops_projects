package averageIncome;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVWriter;

public class CsvWriter implements FileWriterInterface {

	public void writeCSVfile(ArrayList<ArrayList<String>> data, String file_name) {
		File file = new File(file_name);
		FileWriter outfile;
		try {
			outfile = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outfile);
			
			String[] header = {"City/Country", "Gender", "Average Income(in USD"};
			writer.writeNext(header);
			
			for(ArrayList<String> row: data) {
				writer.writeNext(GetStringArray(row));
			}
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
    public static String[] GetStringArray(ArrayList<String> arr) 
    { 
  
        // Convert ArrayList to object array 
        Object[] objArr = arr.toArray(); 
  
        // convert Object array to String array 
        String[] str = Arrays 
                           .copyOf(objArr, objArr 
                                               .length, 
                                   String[].class); 
  
        return str; 
    }

}
