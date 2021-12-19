package project_start.hr.mgmt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import project_start.tools.DbConnection;

public class ManagerDAO {
	public List<Manager> findAll() {
		try {
			List<Manager> listEmployees = new ArrayList<>(); 
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT * from Manager"); 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = (rs.getString("employeeName"));
				Double salary = rs.getDouble("salary");
				String department = rs.getString("Department");
				Manager m = new Manager(id, name, salary, department); 
				listEmployees.add(m);
			}
			return listEmployees;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Manager find(int id) {
		try {
			Manager p = null;
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT * from Manager WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = (rs.getString("EmployeeName"));
				Double salary = rs.getDouble("salary");
				String department = rs.getString("department");
				p = new Manager(id, name, salary, department);;
			}
			return p;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean create(Manager p) {
		try {
			PreparedStatement ps = DbConnection.getConnection()
					.prepareStatement("INSERT INTO Manager(EmployeeName,salary,Department) values(?,?,?);");
			ps.setString(1, p.getEmployeeName());
			ps.setDouble(2, p.getSalary());
			ps.setString(3, p.getDepartment());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean edit(Manager p) {
		try {
			PreparedStatement ps = DbConnection.getConnection()
					.prepareStatement("UPDATE Manager set EmployeeName=?,salary=?,Department=?" + " where id=?");
			ps.setString(1, p.getEmployeeName());
			ps.setDouble(2, p.getSalary());
			ps.setString(3, p.getDepartment());
			ps.setInt(4, p.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean delete(Manager p) {
		try {
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("DELETE FROM Manager where id=?");
			ps.setInt(1, p.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
}
