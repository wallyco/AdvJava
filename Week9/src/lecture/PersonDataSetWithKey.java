package lecture;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonDataSetWithKey extends ArrayList<PersonWithKey> {
	private static final long serialVersionUID = 1L;

	public PersonDataSetWithKey() {
	}
	public int size() {
		return super.size();
	}
	public boolean ReadFromDataBase() {
		try {
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from fakenames10000_key");
				while (resultSet.next()) {
					int id= Integer.parseInt(resultSet.getString(1));
					String firstName = resultSet.getString(2);
					String lastName = resultSet.getString(3);
					String address = resultSet.getString(4);
					String city = resultSet.getString(5);
					String state = resultSet.getString(6);
					String zipcode = resultSet.getString(7);
					PersonWithKey person = new PersonWithKey(id, firstName, lastName, address, city, state, zipcode);
					add(person);
				}

			connection.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}