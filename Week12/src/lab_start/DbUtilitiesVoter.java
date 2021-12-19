package lab_start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtilitiesVoter {
    private static Connection getConnection() {
        try {
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/csc251", "scott", "tiger");
    		return conn;
        } catch (SQLException e) {
            return null;
        }
    }
	public static Tree<Voter> ReadFromDataBase(int recordsToGet) {
		Tree<Voter> bst = new BST<>();
		try {
			Connection connection = DbUtilitiesVoter.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from voters order by " + 
										"lastname, firstname limit " + recordsToGet + " ");
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
				bst.add(voter);
			}
			connection.close();
			return bst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
