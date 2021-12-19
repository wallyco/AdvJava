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
	public static DataSetGeneric<Book> ReadFromDataBaseBooks() {
		try {
			DataSetGeneric<Book> books = new DataSetGeneric<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from books");
			while (resultSet.next()) {
				String title = resultSet.getString(1);
				String author = resultSet.getString(2);
				String genre = resultSet.getString(3);
				String subgenre = resultSet.getString(4);
				int pages = Integer.parseInt(resultSet.getString(5));
				String publisher = resultSet.getString(6);
				Book book = new Book(title, author, genre, subgenre, pages, publisher);
				books.add(book);
			}
			connection.close();
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static DataSetGeneric<Movie> ReadFromDataBaseMovie() {
		try {
			DataSetGeneric<Movie> dataobj = new DataSetGeneric<>();
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
	
	public static DataSetGeneric<TheOffice> ReadFromDataBaseTheOffice() {
		try {
			DataSetGeneric<TheOffice> dataobj = new DataSetGeneric<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from theoffice");
			while (resultSet.next()) {
				int rec = Integer.parseInt(resultSet.getString(1));
				int season = Integer.parseInt(resultSet.getString(2));
				String epi = resultSet.getString(3);
				String about = resultSet.getString(4);
				double rating = Double.parseDouble(resultSet.getString(5));
				int votes = Integer.parseInt(resultSet.getString(6));
				double view = Double.parseDouble(resultSet.getString(7));
				int dur = Integer.parseInt(resultSet.getString(8));
				String date = resultSet.getString(9);
				String guest = resultSet.getString(10);
				String director = resultSet.getString(11);
				String writers = resultSet.getString(12);
				TheOffice obj = new TheOffice(rec, season, epi, about, rating, votes, view,
						dur, date, guest, director, writers);
				dataobj.add(obj);
			}
			connection.close();
			return dataobj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static DataSetGeneric<Simpson> ReadFromDataBaseTheSimpsons() {
		try {
			DataSetGeneric<Simpson> dataobj = new DataSetGeneric<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from simpsons");
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(1));
				String img = resultSet.getString(2);
				double rating = Double.parseDouble(resultSet.getString(3));
				int votes = Integer.parseInt(resultSet.getString(4));
				int numSeason = Integer.parseInt(resultSet.getString(5));
				int numSeries = Integer.parseInt(resultSet.getString(6));
				String date = resultSet.getString(7);
				int year = Integer.parseInt(resultSet.getString(8));
				int season = Integer.parseInt(resultSet.getString(9));
				String title = resultSet.getString(10);
				double views = Double.parseDouble(resultSet.getString(11));
				Simpson obj = new Simpson(id, img, rating, votes, numSeason, numSeries, date, year, season, title, views);
				dataobj.add(obj);
			}
			connection.close();
			return dataobj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static DataSetGeneric<Simpson> GetTheSimpsonsMinMax(int minpages, int maxpages){
		try{
			DataSetGeneric<Simpson> dataobj = new DataSetGeneric<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from simpsons where imdbVotes =" + minpages);
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(1));
				String img = resultSet.getString(2);
				double rating = Double.parseDouble(resultSet.getString(3));
				int votes = Integer.parseInt(resultSet.getString(4));
				int numSeason = Integer.parseInt(resultSet.getString(5));
				int numSeries = Integer.parseInt(resultSet.getString(6));
				String date = resultSet.getString(7);
				int year = Integer.parseInt(resultSet.getString(8));
				int season = Integer.parseInt(resultSet.getString(9));
				String title = resultSet.getString(10);
				double views = Double.parseDouble(resultSet.getString(11));
				Simpson obj = new Simpson(id, img, rating, votes, numSeason, numSeries, date, year, season, title, views);
				dataobj.add(obj);
			}

			resultSet = statement.executeQuery("select * from simpsons where imdbVotes =" + maxpages);
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(1));
				String img = resultSet.getString(2);
				double rating = Double.parseDouble(resultSet.getString(3));
				int votes = Integer.parseInt(resultSet.getString(4));
				int numSeason = Integer.parseInt(resultSet.getString(5));
				int numSeries = Integer.parseInt(resultSet.getString(6));
				String date = resultSet.getString(7);
				int year = Integer.parseInt(resultSet.getString(8));
				int season = Integer.parseInt(resultSet.getString(9));
				String title = resultSet.getString(10);
				double views = Double.parseDouble(resultSet.getString(11));
				Simpson obj = new Simpson(id, img, rating, votes, numSeason, numSeries, date, year, season, title, views);
				dataobj.add(obj);
			}
			connection.close();
			return dataobj;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static DataSetGeneric<TheOffice> GetTheOfficeMinMax(int minpages, int maxpages){
		try{
			DataSetGeneric<TheOffice> dataobj = new DataSetGeneric<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from theoffice where votes =" + minpages);
			while (resultSet.next()) {
				int rec = Integer.parseInt(resultSet.getString(1));
				int season = Integer.parseInt(resultSet.getString(2));
				String epi = resultSet.getString(3);
				String about = resultSet.getString(4);
				double rating = Double.parseDouble(resultSet.getString(5));
				int votes = Integer.parseInt(resultSet.getString(6));
				double view = Double.parseDouble(resultSet.getString(7));
				int dur = Integer.parseInt(resultSet.getString(8));
				String date = resultSet.getString(9);
				String guest = resultSet.getString(10);
				String director = resultSet.getString(11);
				String writers = resultSet.getString(12);
				TheOffice obj = new TheOffice(rec, season, epi, about, rating, votes, view,
						dur, date, guest, director, writers);
				dataobj.add(obj);
			}

			resultSet = statement.executeQuery("select * from theoffice where votes =" + maxpages);
			while (resultSet.next()) {
				int rec = Integer.parseInt(resultSet.getString(1));
				int season = Integer.parseInt(resultSet.getString(2));
				String epi = resultSet.getString(3);
				String about = resultSet.getString(4);
				double rating = Double.parseDouble(resultSet.getString(5));
				int votes = Integer.parseInt(resultSet.getString(6));
				double view = Double.parseDouble(resultSet.getString(7));
				int dur = Integer.parseInt(resultSet.getString(8));
				String date = resultSet.getString(9);
				String guest = resultSet.getString(10);
				String director = resultSet.getString(11);
				String writers = resultSet.getString(12);
				TheOffice obj = new TheOffice(rec, season, epi, about, rating, votes, view,
						dur, date, guest, director, writers);
				dataobj.add(obj);
			}
			connection.close();
			return dataobj;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static DataSetGeneric<Movie> GetMovieMinMax(int minpages, int maxpages){
		try{
			DataSetGeneric<Movie> dataobj = new DataSetGeneric<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from movies where votes =" + minpages);
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

			resultSet = statement.executeQuery("select * from movies where votes =" + maxpages);
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
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static DataSetGeneric<Book> GetBookMinMax(int minpages, int maxpages){
		try{
			DataSetGeneric<Book> books = new DataSetGeneric<>();
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from books where pages =" + minpages);
			while (resultSet.next()) {
				String title = resultSet.getString(1);
				String author = resultSet.getString(2);
				String genre = resultSet.getString(3);
				String subgenre = resultSet.getString(4);
				int pages = Integer.parseInt(resultSet.getString(5));
				String publisher = resultSet.getString(6);
				Book book = new Book(title, author, genre, subgenre, pages, publisher);
				books.add(book);
			}

			resultSet = statement.executeQuery("select * from books where pages =" + maxpages);
			while (resultSet.next()) {
				String title = resultSet.getString(1);
				String author = resultSet.getString(2);
				String genre = resultSet.getString(3);
				String subgenre = resultSet.getString(4);
				int pages = Integer.parseInt(resultSet.getString(5));
				String publisher = resultSet.getString(6);
				Book book = new Book(title, author, genre, subgenre, pages, publisher);
				books.add(book);
			}
			connection.close();
			return books;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
