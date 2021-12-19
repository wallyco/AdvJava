package lab_starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SpellCheckFrequentWordsFX extends Application {

	Dictionary dictionary = new Dictionary();
	ArrayList<String> document = new ArrayList<>();
	ChoiceBox<String> textfileToCheck = new ChoiceBox<String>();



	public void start(Stage primaryStage) throws IOException, SQLException {

		TextArea textArea = new TextArea();
		textArea.setWrapText(true);

		Label nameLabel = new Label("File to Load: ");
		textfileToCheck.getItems().addAll("SophieSallyJack.txt", "GreenEggsAndHam.txt", "BillOfRights.txt",
				"greatgatsby.txt", "warandpeace.txt");
		textfileToCheck.getSelectionModel().select(0);
		String message = (LoadTheTextFile(textfileToCheck.getValue()));
		textfileToCheck.setOnAction(event -> {
			try {
				textArea.setText(LoadTheTextFile(textfileToCheck.getValue()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		Button btFrequentWords = new Button("Frequent Words");
		Button btSpellCheck = new Button("Spell Check");

		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");

		HBox hBoxTop = new HBox(nameLabel, textfileToCheck, btFrequentWords, btSpellCheck, btClearAnswers, btnExit);
		hBoxTop.setSpacing(10);
		hBoxTop.setAlignment(Pos.CENTER);
		hBoxTop.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBoxTop);
		borderPane.setCenter(textArea);

		dictionary = DbConnection.ReadDictionary();
		// Dictionary Requires a Size Method
		textArea.setText("Dictionary Loaded with " + dictionary.size() + " words\n");
		textArea.appendText(message + "\n");


		btFrequentWords.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				setDocument();
				textArea.setText(new LabTools(document.toArray()).frequency()) ;

			}
		});
		btSpellCheck.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setDocument();
				String output = new LabTools(document.toArray()).spellcheck();
				textArea.setText(output);
			}
		});

		btClearAnswers.setOnAction(e -> {
			textArea.setText("");
		});

		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 800, 450);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setTitle("Spell Checking with Frequent Words");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private String LoadTheTextFile(String fileName) throws SQLException {
		document.clear();
		File f = new File(fileName);
		Scanner scan;
		try {
			scan = new Scanner(f);
			while (scan.hasNext()) {
				String l = scan.next();
				if (l.endsWith(",") || l.endsWith(".") || l.endsWith("!") || l.endsWith("?") || l.endsWith(":")) {
					l = l.substring(0, l.length() - 1);
				}
				l = (l.replaceAll("[^a-zA-Z]", " ")).trim();
				String[] result = l.split(" ");
				for (int i = 0; i < result.length; i++) {
					if (result[i].length() > 0)
						document.add(result[i]);
				}
			}
			scan.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		for (String s : document)
			sb = sb.append(s + " ");
		return fileName + " loaded with " + document.size() + " words\n\n" + sb;
	}
	
	private void setDocument() {
		String choice = textfileToCheck.getValue();
		try {
			LoadTheTextFile(choice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}