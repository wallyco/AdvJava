package lecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbUtilitiesBook {
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

    public static List<Book> ReadFromDataBase() {
		List<Book> list = new ArrayList<>();
		try {
			Connection connection = DbUtilitiesBook.getConnection();
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
					list.add(book);
				}

			connection.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
