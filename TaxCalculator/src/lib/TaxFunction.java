package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(Employee employee, int numberOfMonthWorking) {
		
		int tax = 0;
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		int totalMonthlyIncome = (employee.getMonthlySalary() + employee.getOtherMonthlyIncome()) * numberOfMonthWorking;
		int taxFreeIncome = 54000000;
		
		if (employee.getFamily().getIsMarried()) {
			taxFreeIncome += 4500000 + (Math.min(employee.getFamily().getNumberOfChildren(), 3) * 1500000);
		}
		
		tax = (int) Math.round(0.05 * (totalMonthlyIncome - employee.getAnnualDeductible() - taxFreeIncome));
		
		return Math.max(tax, 0); 
	}
	
}
