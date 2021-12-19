package lecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    public static boolean VerifyDBClassLoaded() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static Connection getConnection() {
        try {
        	//System.out.println("Got here");
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/csc251", "scott", "tiger");
            //System.out.println("Database Connected");
    		return conn;
        } catch (SQLException e) {
            return null;
        }
    }
	public static List<Voter> ReadFromDataBaseArrayList(int recordsToGet) {
		List<Voter> list = new ArrayList<>();
		try {
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from voters order by " + 
//										"lastname, firstname limit " + recordsToGet + " ");
			ResultSet resultSet = statement.executeQuery("select * from voters " + 
					"limit " + recordsToGet + " ");
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(1));
				String lastName = resultSet.getString(2);
				String firstName = resultSet.getString(3);
				String middleName = resultSet.getString(4);
				String address = resultSet.getString(5);
				String city = resultSet.getString(6);
				String state = resultSet.getString(7);
				String zipcode = resultSet.getString(8);
				int age = Integer.parseInt(resultSet.getString(9));
				Voter voter = new Voter(id,  lastName, firstName,middleName, address, city, state, 
						zipcode, age);
				list.add(voter);
			}
			connection.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	

}
