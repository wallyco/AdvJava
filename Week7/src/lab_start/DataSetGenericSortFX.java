package lab_start;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

public class DataSetGenericSortFX extends Application {

	private DataSetGenericSort<Book> books = new DataSetGenericSort<>();
	private DataSetGenericSort<Movie> movies = new DataSetGenericSort<>();


	public void start(Stage primaryStage) {

		TextArea textArea = new TextArea();
		Button btLoadShowBooks = new Button("Load/Show Books");
		Button btLoadShowMovies = new Button("Load/Show Movies");
		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");

		HBox hBoxTop = new HBox(btLoadShowBooks,  btLoadShowMovies,  btClearAnswers, btnExit);
		hBoxTop.setSpacing(10);
		hBoxTop.setAlignment(Pos.CENTER);
		hBoxTop.setPadding(new Insets(5, 5, 5, 5));

		Button btSortByAuthor = new Button("Books/Author");
		Button btSortByTitle = new Button("Books/Title");
		Button btSortByPages = new Button("Books/Pages");
		Button btSortByRating = new Button("Movies/Rating");
		Button btSortByScore = new Button("Movies/Score");
		Button btSortByVotes = new Button("Movies/Votes");
		HBox hBoxBottom = new HBox(btSortByAuthor, btSortByTitle, btSortByPages, btSortByRating, btSortByScore,
				btSortByVotes);
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
				if (books.size() > 0 ) {
					output = "books loaded - " + books.size() + "\n";
					for (Book book : books) {
						output += book + "\n";
					}
				} else {
					output = "failure loading books \n";
				}
				textArea.setText(output);
			}
		});
		btLoadShowMovies.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				movies = DbConnection.ReadFromDataBaseMovie();
				if (movies.size() > 0 ) {
					output = "books loaded - " + movies.size() + "\n";
					for (Movie obj : movies) {
						output += obj + "\n";
					}
				} else {
					output = "failure loading books \n";
				}
				textArea.setText(output);
			}
		});

		btSortByAuthor.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				//TODO Delete books = DbConnection.ReadFromDataBaseBooks();
				Collections.sort(books, new InnerComparator<>());
				for(Book obj : books) {
					output += obj + "\n";
				}
				textArea.setText(output);
			}
		});
		btSortByTitle.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				books.sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
				for(Book obj : books) {
					output += obj + "\n";
				}

				textArea.setText(output);
			}
		});
		btSortByPages.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				Collections.sort(books, new DataSetGenericSort<>());
				for(Book obj : books) {
					output += obj + "\n";
				}
				textArea.setText(output);
			}
		});
		btSortByRating.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				Collections.sort(movies, new InnerComparator<>());
				for(Movie obj : movies) {
					output += obj + "\n";
				}
				textArea.setText(output);
			}
		});
		btSortByScore.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				movies.sort((m1, m2) -> m1.getScore() > m2.getScore() ? 1 : m1.getScore() < m2.getScore() ? -1 : 0);
				for(Movie obj : movies) {
					output += obj + "\n";
				}
				
				textArea.setText(output);
			}
		});
		btSortByVotes.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				Collections.sort(movies, new DataSetGenericSort<>());
				for(Movie obj : movies) {
					output += obj + "\n";
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
		Scene scene = new Scene(borderPane, 650, 450);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setTitle("Generic Data Set with Sorting");
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
	
	class InnerComparator<E extends Measurable> extends ArrayList<E> 
	implements Comparator<E> {
		private static final long serialVersionUID = 1L;

		public InnerComparator() { }
		
		//Inner sort used for author
		public int compareAuthor(E o1, E o2) {
			return  ((Book) o1).getAuthor().compareTo(((Book) o2).getAuthor());
		}
		
		public int compareRating(E o1, E o2) {
			return ((Movie) o1).getRating().compareTo(((Movie) o2).getRating());
		}

		@Override
		public int compare(E o1, E o2) {
			if(o1 instanceof Book) {
				return compareAuthor(o1, o2);
			}else {
				return compareRating(o1, o2);
			}
		}

	}
}