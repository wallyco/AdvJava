package project_start.hr.mgmt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project_start.tools.DbConnection;

public class ExecutiveDAO {
	public List<Executive> findAll() {
		try {
			List<Executive> listEmployees = new ArrayList<>();
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT * from Executive");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = (rs.getString("EmployeeName"));
				Double salary = rs.getDouble("salary");
				String department = rs.getString("Department");
				double bonus = rs.getDouble("bonus");
				Executive p = new Executive(id, name, salary, department, bonus);
				listEmployees.add(p);
			}
			return listEmployees;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Executive find(int id) {
		try {
			Executive p = null;
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("SELECT * from Executive WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = (rs.getString("EmployeeName"));
				Double salary = rs.getDouble("salary");
				String department = rs.getString("Department");
				double bonus = rs.getDouble("bonus");
				p = new Executive(id, name, salary, department, bonus);
			}
			return p;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean create(Executive p) {
		try {
			PreparedStatement ps = DbConnection.getConnection()
					.prepareStatement("INSERT INTO Executive(EmployeeName,salary,Department,bonus) values(?,?,?,?);");
			ps.setString(1, p.getEmployeeName());
			ps.setDouble(2, p.getSalary());
			ps.setString(3, p.getDepartment());
			ps.setDouble(4, p.getBonus());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean edit(Executive p) {
		try {
			PreparedStatement ps = DbConnection.getConnection()
					.prepareStatement("UPDATE Executive set EmployeeName=?,salary=?,Department=?,bonus=?" + " where id=?");
			ps.setString(1, p.getEmployeeName());
			ps.setDouble(2, p.getSalary());
			ps.setString(3, p.getDepartment());
			ps.setDouble(4, p.getBonus());
			ps.setInt(5, p.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean delete(Executive p) {
		try {
			PreparedStatement ps = DbConnection.getConnection().prepareStatement("DELETE FROM Executive where id=?");
			ps.setInt(1, p.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
}
