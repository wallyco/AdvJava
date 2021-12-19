package lab_start;

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

public class BST_LabFX extends Application {

	private Tree<Voter> voterBST = new BST<>();
	private Tree<String> stringBST = new BST<>();
	//private Tree<Integer> integerBST = new BST<>();

	public void start(Stage primaryStage) {
		TextArea textArea = new TextArea();
		textArea.setWrapText(true);
		Button btCreateTree = new Button("Create Tree");
		Button btTreeHeight = new Button("Tree Height Small");
		Button btBSTLeavesSmall = new Button("Leaves Small");
		Button btVoterTreeHeight = new Button("Voter Tree Height");
		Button btVoterTreeLeaves = new Button("Voter Tree Leave");
		HBox hBox1 = new HBox(btCreateTree, btTreeHeight, btBSTLeavesSmall, btVoterTreeHeight, btVoterTreeLeaves);
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		hBox1.setPadding(new Insets(5, 5, 5, 5));

		
		ChoiceBox<Integer> cbRecordCount = new ChoiceBox<Integer>();
		cbRecordCount.getItems().addAll(10000, 40000, 80000, 160000, 320000);
		cbRecordCount.getSelectionModel().select(0);
		Button btLoadVoters = new Button("Load Voters");


		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");
		HBox hBoxBottom = new HBox(new Label("Voters To Load : "),cbRecordCount,
				btLoadVoters, btClearAnswers, btnExit);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(10, 5, 10, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBox1);
		borderPane.setCenter(textArea);
		borderPane.setBottom(hBoxBottom);

		btCreateTree.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				stringBST.insert("Daniel");
				stringBST.insert("Adam");
				stringBST.insert("Michael");
				stringBST.insert("Tom");
				stringBST.insert("Jones");
				stringBST.insert("Peter");
				output += "Insert Order - Daniel, Adam, Michael, Tom, Jones, Peter\n";
				output += "Using an iterator\n";
				for (String s : stringBST)
					output += s.toUpperCase() + "\t";
				textArea.setText(output);
			}
		});
		btTreeHeight.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Tree Height Small: ";
				output += String.valueOf(stringBST.height());
				textArea.setText(output);
			}
		});
		btBSTLeavesSmall.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Tree Leaves Small ";
				output += String.valueOf(stringBST.getNumberOfLeaves())
;				textArea.setText(output);
			}
		});
		btVoterTreeHeight.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Voter Tree Height ";
				output += String.valueOf(voterBST.height());
				textArea.setText(output);
			}
		});
		btVoterTreeLeaves.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Voter Tree Leaves ";
				long startTime = System.currentTimeMillis();
				output += String.valueOf(voterBST.getNumberOfLeaves());
				output += "\nVoter Tree Leaves in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
				textArea.setText(output);
			}
		});


		btLoadVoters.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				long startTime = System.currentTimeMillis();
				voterBST.clear();
				int recordsToGet = cbRecordCount.getValue();
				voterBST = DbUtilitiesVoter.ReadFromDataBase(recordsToGet);
				output += "Time to Load Binary Search Tree (milliseconds) : " + (System.currentTimeMillis() - startTime)
						+ "\n";
				if (voterBST.size() > 0) {
					output += "voters loaded voterBST  - " + voterBST.size() + "\n";
				} else {
					output = "failure loading votes \n";
				}
				textArea.setText(output);
			}
		});
		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 750, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setTitle("Binary Search Trees"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}