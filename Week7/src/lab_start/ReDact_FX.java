package lab_start;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReDact_FX extends Application {
	
	Redact redact;

	public void start(Stage primaryStage) {
		TextArea textArea = new TextArea();
		textArea.setWrapText(true);

		ChoiceBox<String> textfileToCheck = new ChoiceBox<String>();
		textfileToCheck.getItems().addAll("sample.txt", "BillOfRights.txt", "greatgatsby.txt");
		textfileToCheck.getSelectionModel().select(0);

		Button btShowTextFile = new Button("Show File");
		Button btReDact = new Button("ReDact");

		TextField txTriggerWord = new TextField();
		txTriggerWord.setMaxWidth(80);
		txTriggerWord.setText("not");

		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");

		HBox hBoxTop = new HBox(new Label("File to Load: "), textfileToCheck, btShowTextFile,
				new Label("Target Word: "), txTriggerWord, btReDact, btClearAnswers, btnExit);

		hBoxTop.setSpacing(10);
		hBoxTop.setAlignment(Pos.CENTER);
		hBoxTop.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBoxTop);
		borderPane.setCenter(textArea);

		btShowTextFile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "File to redact --- " + textfileToCheck.getValue() + "\n";
				try {
					Scanner input = new Scanner(new File(textfileToCheck.getValue()));
					while (input.hasNext()) {
						output += input.nextLine() + "\n";
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				Redact.setFr(true);
				textArea.setText(output);
			}
		});
		
		btReDact.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(textArea != null) {
					String output = "";
					redact = new Redact(txTriggerWord.getText());
					output = redact.traverseRedact(textArea.getText());
					textArea.setText(output);
				}
			}
		});

		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				try {
					Scanner input = new Scanner(new File(textfileToCheck.getValue()));
					while (input.hasNext()) {
						output += input.nextLine() + "\n";
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				textArea.setText(output);
				Redact.setFr(true);
			}
		});

		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 700, 450);
		primaryStage.setTitle("ReDact Text"); // Set title
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);

		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}