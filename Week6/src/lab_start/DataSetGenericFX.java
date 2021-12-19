package lab_start;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DataSetGenericFX extends Application {

	DataSetGeneric<Book> books = new DataSetGeneric<>();
	DataSetGeneric<Movie> movies = new DataSetGeneric<>();
	DataSetGeneric<TheOffice> to = new DataSetGeneric<>();
	DataSetGeneric<Simpson> ts = new DataSetGeneric<>();



	public void start(Stage primaryStage) {

		TextArea textArea = new TextArea();
		textArea.setWrapText(true);
		Button btLoadShowBooks = new Button("Load/Show Books");
		Button btLoadShowMovies = new Button("Load/Show Movies");
		Button btLoadShowTheOffice = new Button("Load/Show 'The Office'");
		Button btLoadShowSimpsons = new Button("Load/Show 'Simpsons'");

		HBox hBoxTop = new HBox(btLoadShowBooks, btLoadShowMovies, btLoadShowTheOffice, btLoadShowSimpsons);
		hBoxTop.setSpacing(10);
		hBoxTop.setAlignment(Pos.CENTER);
		hBoxTop.setPadding(new Insets(5, 5, 5, 5));

		Button btShowMinMaxBook = new Button("Min/Max Book");
		Button btShowMinMaxMovie = new Button("Min/Max Movie");
		Button btShowMinMaxTheOffice = new Button("Min/Max 'The Office'");
		Button btShowMinMaxSimpsons = new Button("Min/Max 'Simpsons'");

		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");
		HBox hBoxBottom = new HBox(btShowMinMaxBook, btShowMinMaxMovie, btShowMinMaxTheOffice, btShowMinMaxSimpsons,
				btClearAnswers, btnExit);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBoxTop);
		borderPane.setBottom(hBoxBottom);
		borderPane.setCenter(textArea);

		btLoadShowBooks.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				books = DbConnection.ReadFromDataBaseBooks();
				if (books.size() > 0) {
					output = "books loaded - " + books.size() + "\n";
				} else {
					output = "failure loading books \n";
				}
				for (Book book : books) {
					output += book + "\n";
				}
				textArea.setText(output);
			}
		});
		btLoadShowMovies.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				movies = DbConnection.ReadFromDataBaseMovie();
				if (movies.size() > 0) {
					output = "movies loaded - " + movies.size() + "\n";
				} else {
					output = "failure loading movies \n";
				}
				for (Movie obj : movies) {
					output += obj + "\n";
				}
				textArea.setText(output);
			}
		});

		btLoadShowTheOffice.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				to = DbConnection.ReadFromDataBaseTheOffice();
				if (to.size() > 0) {
					output = "The Office episodes loaded - " + to.size() + "\n";
				} else {
					output = "failure loading episodes \n";
				}
				for (TheOffice obj : to) {
					output += obj + "\n";
				}
				textArea.setText(output);
			}
		});

		btLoadShowSimpsons.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				ts = DbConnection.ReadFromDataBaseTheSimpsons();
				if (ts.size() > 0) {
					output = "The Simpson episodes loaded - " + ts.size() + "\n";
				} else {
					output = "failure loading episodes \n";
				}
				for (Simpson obj : ts) {
					output += obj + "\n";
				}
				textArea.setText(output);
			}
		});

		btShowMinMaxBook.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			int min = books.getMin();
			int max = books.getMax();
			int temp = 0;
			String output = "Min\n";
			DataSetGeneric<Book> book = DbConnection.GetBookMinMax(min, max);
			for(Book bk : book) {
				output += bk + "\n";
				if(temp == 0) {
					output += "Max \n";
					temp++;
				}
			}
			textArea.setText(output);
		}
	});

		btShowMinMaxMovie.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int min = movies.getMin();
				int max = movies.getMax();
				String output = "Min\n";
				int temp = 0;
				DataSetGeneric<Movie> movie = DbConnection.GetMovieMinMax(min, max);
				for(Movie bk : movie) {
					output += bk + "\n";
					if(temp == 0) {
						output += "Max \n";
						temp++;
					}
				}
				textArea.setText(output);
			}
		});


		btShowMinMaxTheOffice.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int min = to.getMin();
				int max = to.getMax();
				String output = "Min\n";
				int temp = 0;
				DataSetGeneric<TheOffice> off = DbConnection.GetTheOfficeMinMax(min, max);
				for(TheOffice bk : off) {
					output += bk + "\n";
					if(temp == 0) {
						output += "Max \n";
						temp++;
					}
				}
				textArea.setText(output);
			}
		});

		btShowMinMaxSimpsons.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int min = ts.getMin();
				int max = ts.getMax();
				int temp = 0;
				String output = "Min\n";
				DataSetGeneric<Simpson> simpson = DbConnection.GetTheSimpsonsMinMax(min, max);
				for(Simpson bk : simpson) {
					output += bk + "\n";
					if(temp == 0) {
						output += "Max \n";
						temp++;
					}
				}
				textArea.setText(output);
			}
		});

		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			@Override // Override the handle method
			public void handle(ActionEvent e) {
				textArea.setText("");
			}
		});

		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 750, 400);
		primaryStage.setTitle("Generic Data Set");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}