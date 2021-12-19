package project_start.hr.mgmt;

import project_start.collection.Measurable;
import project_start.hr.person.Employee;

public class Manager extends Employee implements Measurable {
	
	private String department;

	public Manager(int id, String name, double salary,
			String department) {
		super(id, name, salary);
		this.department = department;
		
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public int getMeasure() {
		return (int) getSalary() * 100;
	}

	@Override
	public String toString() {
		return "Manager [id=" + getId() + ", employeeName=" + getEmployeeName() + ", salary=" + getSalary() + ", department=" + department + "]";
	}


	


}
