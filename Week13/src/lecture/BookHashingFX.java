package lecture;

import java.util.ArrayList;
import java.util.List;

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

public class BookHashingFX extends Application {
	List<Book> books = new ArrayList<>();
	
	MyMap<String, Book> booksByType = new MyHashMapBook<>();

	public void start(Stage primaryStage) {

		TextArea textArea = new TextArea();
		textArea.setWrapText(true);

		Button btLoadBooks = new Button("Load Books");
		Button btShowBooks = new Button("Show Books");
		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");

		HBox hBoxTop = new HBox(btLoadBooks, btShowBooks, btClearAnswers, btnExit);
		hBoxTop.setSpacing(10);
		hBoxTop.setAlignment(Pos.CENTER);
		hBoxTop.setPadding(new Insets(5, 5, 5, 5));

		Button btLoadIntoMap = new Button("Load Into Hash Map");
		Button btSortByTitle = new Button("Books/Title");
		Button btSortByPages = new Button("Books/Pages");
		Button btSortByRating = new Button("Movies/Rating");
		Button btSortByScore = new Button("Movies/Score");
		Button btSortByVotes = new Button("Movies/Votes");
		HBox hBoxBottom = new HBox(btLoadIntoMap, btSortByTitle, btSortByPages, btSortByRating, btSortByScore,
				btSortByVotes);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBoxTop);
		borderPane.setBottom(hBoxBottom);
		borderPane.setCenter(textArea);

		btLoadBooks.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				books = DbUtilitiesBook.ReadFromDataBase();
				if (books.size() > 0) {
					output = "books loaded - " + books.size() + "\n";
				} else {
					output = "failure loading books \n";
				}
				textArea.setText(output);
			}
		});
		btShowBooks.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				for (Book book : books) {
					output += String.format("%-55s%-10s\n", book.getTitle(),book.getGenre());
				}
				output += "\n";
				textArea.setText(output);
			}
		});

		btLoadIntoMap.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Load Books into Map";
				long startTime = System.currentTimeMillis();
				for(Book book : books) {
					booksByType.put(book.getGenre(), book);
				}
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to load " + endTime + "\n";
				output += "Size of Map " + booksByType.size() + "\n";
				textArea.setText(output);
			}
		});
		btSortByTitle.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				textArea.setText(output);
			}
		});
		btSortByPages.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				textArea.setText(output);
			}
		});
		btSortByRating.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				textArea.setText(output);
			}
		});
		btSortByScore.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				textArea.setText(output);
			}
		});
		btSortByVotes.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
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
}