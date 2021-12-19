package edu.waketech.utility.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import edu.waketech.contactclient.controller.Person;

public class SQL {
	private String dbURL = "jdbc:mysql://localhost/csc251"; //for csc251
	private String dbUser = "scott"; //for csc251
	private String dbPass= "tiger"; //for csc251
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public SQL() {
		try {
			connection = DriverManager
					.getConnection(this.dbURL, this.dbUser, this.dbPass);
			System.out.println("Connection Successful");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public SQL(String url, String user, String pass) {
		this.dbURL = url;
		this.dbUser = user;
		this.dbPass = pass;
		try {
			connection = DriverManager
					.getConnection(this.dbURL, this.dbUser, this.dbPass);
			System.out.println("Connection Successful");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean loadDataToResult(String dbSchema) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery
				("select * from " + dbSchema);
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteData(Person p) {
		try{
			PreparedStatement ps = connection.prepareStatement
				("DELETE FROM csc251.contact WHERE (id = ?)");
			ps.setInt(1, Integer.parseInt(p.getID()));
			
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean createData(Person p) {
		try {		
			PreparedStatement ps = connection.prepareStatement
				(("INSERT INTO csc251.contact " + "VALUES (?, ?, ?, ?, ?)"));	
			ps.setString(2, p.getFirstName());
			ps.setString(3, p.getAddress());
			ps.setString(4, p.getCellPhone());
			ps.setString(5, p.getEmail());
			ps.setInt(1, Integer.parseInt(p.getID()));
			
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateData(Person p) {
		if(p != null) {
			try {
				PreparedStatement ps = connection.prepareStatement
						("UPDATE csc251.contact SET id=?,name=?,address=?,cellphone=?,email=?" + "WHERE id = ?");
				ps.setInt(1, Integer.parseInt(p.getID()));
				ps.setString(2, p.getFirstName());
				ps.setString(3, p.getAddress());
				ps.setString(4, p.getCellPhone());
				ps.setString(5, p.getEmail());
				ps.setInt(06, Integer.parseInt(p.getID()));
				return ps.executeUpdate() > 0;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
			
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	
}


// I made this before the email, just wanted to keep it for later reference
//public boolean updateData(List<Person> old, List<Person> updated, List<Person> deleted) {	//specified for csc251 contactclient
//boolean shouldInsert;
//try {
//	for(Person index : updated) {
//		shouldInsert = true;
//		for(Person oldindex : old) {
//			if(index.getID() == oldindex.getID()) {
//				shouldInsert = false;
//				break;
//			}
//		}
//		if(shouldInsert) {
//			String updatedString =
//					("INSERT INTO csc251.contact " + "VALUES (?, ?, ?, ?, ?)");
//					//("ALTER TABLE contact MODIFY id INTEGER NOT NULL AUTO_INCREMENT"); //TODO Delete
//			PreparedStatement ps = connection.prepareStatement(updatedString);	
//			ps.setString(2, index.getFirstName());
//			ps.setString(3, index.getAddress());
//			ps.setString(4, index.getCellPhone());
//			ps.setString(5, index.getEmail());
//			ps.setInt(1, Integer.parseInt(index.getID()));
//			
//			ps.executeUpdate();
//		}
//	}
//	
//	if(deleted != null) {
//		for(Person index : deleted) {
//			PreparedStatement ps = connection.prepareStatement
//					("DELETE FROM csc251.contact WHERE (id = ?)");
//			ps.setInt(1, Integer.parseInt(index.getID()));
//			ps.execute();
//
//		}
//	}
//	return true;
//
//}catch(SQLException e) {
//	e.printStackTrace();
//	return false;
//}
//}
