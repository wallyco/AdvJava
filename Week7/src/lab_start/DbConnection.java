package lab_start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			// System.out.println("Got here");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/csc251", "scott", "tiger");
			// System.out.println("Database Connected");
			return conn;
		} catch (SQLException e) {
			return null;
		}
	}

	public static DataSetGenericSort<Book> ReadFromDataBaseBooks() {
		try {
			DataSetGenericSort<Book> books = new DataSetGenericSort<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from books");
			while (resultSet.next()) {
				String f1 = resultSet.getString(1);
				String f2 = resultSet.getString(2);
				String f3 = resultSet.getString(3);
				String f4 = resultSet.getString(4);
				int f5 = Integer.parseInt(resultSet.getString(5));
				String f6 = resultSet.getString(6);
				Book book = new Book(f1, f2, f3, f4, f5, f6);
				books.add(book);
			}
			connection.close();
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static DataSetGenericSort<Movie> ReadFromDataBaseMovie() {
		try {
			DataSetGenericSort<Movie> dataobj = new DataSetGenericSort<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from movies");
			while (resultSet.next()) {
				String title = resultSet.getString(1);
				String rating = resultSet.getString(2);
				double score = Double.parseDouble(resultSet.getString(3));
				String star = resultSet.getString(4);
				int votes = Integer.parseInt(resultSet.getString(5));
				int year = Integer.parseInt(resultSet.getString(6));
				Movie obj = new Movie(title, rating, score, star, votes, year);
				dataobj.add(obj);
			}
			connection.close();
			return dataobj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
