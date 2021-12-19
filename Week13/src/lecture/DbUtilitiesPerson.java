package lecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbUtilitiesPerson {
    private static Connection getConnection() {
        try {
        	//System.out.println("Got here");
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/csc251", "scott", "tiger");
            //System.out.println("Database Connected");
    		return conn;
        } catch (SQLException e) {
            return null;
        }
    }
	public static List<Person> ReadFromDataBase() {
		List<Person> list = new ArrayList<>();
		try {
			Connection connection = DbUtilitiesPerson.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from fakenames10000 order by " + 
										"lastname, firstname limit 10000 ");
			while (resultSet.next()) {
				String firstName = resultSet.getString(1);
				String lastName = resultSet.getString(2);
				String address = resultSet.getString(3);
				String city = resultSet.getString(4);
				String state = resultSet.getString(5);
				String zipcode = resultSet.getString(6);
				Person person = new Person(firstName, lastName, address, city, state, zipcode);
				list.add(person);
			}
			connection.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
