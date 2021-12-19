package lecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonWithDB {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zipCode;

	public PersonWithDB() {
	}

	public PersonWithDB(String firstName, String lastName, String address, String city, String state, String zipCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "People [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + "]";
	}

//	public static List<Person> createRoster() throws FileNotFoundException {
//		List<Person> people = new ArrayList<>();
//		java.io.File file = new java.io.File("FakeNames10000.csv");
//		Scanner input = new Scanner(file);
//
//		while (input.hasNext()) {
//			String line = input.nextLine();
//			String[] fields = line.split(",");
//			Person p = new Person(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
//			people.add(p);
//		}
//		input.close();
//
//		return people;
//	}

//	public int compareTo(PersonWithDB p) {
//		return this.lastName.compareTo(p.lastName);
//	}

	private static Connection getConnection() {
		try {
			// System.out.println("Got here");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/csc251", "scott", "tiger");
			// System.out.println("Database Connected");
			return conn;
		} catch (SQLException e) {
			return null;
		}
	}

	public static List<PersonWithDB> ReadFromDataBase() {
		List<PersonWithDB> list = new ArrayList<>();
		try {
			Connection connection = PersonWithDB.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from fakenames10000 " + "limit 10000 ");
			while (resultSet.next()) {
				String firstName = resultSet.getString(1);
				String lastName = resultSet.getString(2);
				String address = resultSet.getString(3);
				String city = resultSet.getString(4);
				String state = resultSet.getString(5);
				String zipcode = resultSet.getString(6);
				PersonWithDB person = new PersonWithDB(firstName, lastName, address, city, state, zipcode);
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
