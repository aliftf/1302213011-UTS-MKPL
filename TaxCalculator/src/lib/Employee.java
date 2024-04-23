package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	
	public enum Gender {
		MALE,
		FEMALE
	}

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private LocalDate dateJoined;
	
	private boolean isForeigner;
	private Gender gender;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	public int getMonthlySalary() {
		return this.monthlySalary;
	}
	
	public int getOtherMonthlyIncome() {
		return this.otherMonthlyIncome;
	}
	
	public int getAnnualDeductible() {
		return this.annualDeductible;
	}
	
	public boolean getIsMarried() {
		return spouseIdNumber.equals("");
	}
	
	public int getNumberOfChildren() {
		return childIdNumbers.size();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
		}else if (grade == 2) {
			monthlySalary = 5000000;
		}else if (grade == 3) {
			monthlySalary = 7000000;
		}
		
		if (isForeigner) {
			monthlySalary = (int) (3000000 * 1.5);
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		int monthWorkingInYear = (date.getYear() == dateJoined.getYear()) ? date.getMonthValue() - dateJoined.getMonthValue() : 12;
		
		return TaxFunction.calculateTax(this, monthWorkingInYear);
	}
}
