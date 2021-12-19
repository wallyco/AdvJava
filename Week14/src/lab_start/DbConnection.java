package lab_start;

import java.io.IOException;
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
        	//System.out.println("Got here");
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/csc251", "scott", "tiger");
            //System.out.println("Database Connected");
    		return conn;
        } catch (SQLException e) {
            return null;
        }
    }
	public static Dictionary ReadDictionary() throws IOException {
		try {
			Dictionary dictionary = new Dictionary();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from dictionary");
				while (resultSet.next()) {
					String f1 = resultSet.getString(1);
					if (f1 == null) {
						// The database table has a null Value
						//System.out.println("Null " + f1);
					} else {
						dictionary.add(f1);
					}
				}
			connection.close();
			return dictionary;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
