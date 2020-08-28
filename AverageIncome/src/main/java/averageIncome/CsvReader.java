package averageIncome;

import java.util.List;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvReader implements FileReaderInterface {
	public CSVReader cr;
	
	
	public CsvReader(String file_name) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(file_name));
		cr = new CSVReader(br);
	}
	
	public ArrayList<ArrayList<String>> readFile() throws IOException {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		String[] line = cr.readNext();
		
		while((line = cr.readNext()) != null) {
			List<String> row = new ArrayList<String>();
			row = Arrays.asList(line);
			ArrayList<String> row_ = new ArrayList<String>(row);
			data.add(row_);
		}
		
		return data;
		
	}
	
	public static void main(String[] args) throws IOException {
		CsvReader cvr = new CsvReader("src/main/resources/data_Sheet1.csv");
		ArrayList<ArrayList<String>> data = cvr.readFile(), report;
		
		CalAvgIncome calc = new CalAvgIncome(data);
		report = calc.return_incomes();
		
		System.out.println(report);
		
		CsvWriter cwr = new CsvWriter();
		cwr.writeCSVfile(report, "src/main/resources/output.csv");
	}
}
