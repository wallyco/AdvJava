package project_start.hr.mgmt;

import project_start.collection.Measurable;
import project_start.hr.person.Employee;

public class Executive extends Employee implements Measurable {
	
	private String department;
	private double bonus;
		
	public Executive(int id, String name, double salary,
			String department, double bonus) {
		super(id, name, salary);
		this.department = department;
		this.bonus = bonus;
		
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public int getMeasure() {
		int bpay = (int) ((getSalary() * 100) * bonus); 
		return bpay + (int) getSalary() * 100;
	}

	@Override
	public String toString() {
		return "Executive [id=" + getId() + ", employeeName=" + getEmployeeName() + ", salary=" + getSalary() + ", department=" + department + ", bonus=" + bonus + "]";
	}
	


}
