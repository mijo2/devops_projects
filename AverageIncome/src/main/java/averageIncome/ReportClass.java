package averageIncome;

public class ReportClass {
	public String Country;
	public String gender;
	public long income;
	
	public ReportClass(String country, String gender, long income) {
		super();
		setCountry(country);
		setGender(gender);
		setIncome(income);
	}

	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getIncome() {
		return income;
	}
	public void setIncome(long income) {
		this.income = income;
	}
	
}
