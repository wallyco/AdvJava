package lecture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class Sorting_AlgorithmsFX extends Application {

	List<Voter> voters = new ArrayList<>();

	public void start(Stage primaryStage) {

		TextArea textArea1 = new TextArea();
		textArea1.setWrapText(true);

		Button btBubbleSort = new Button("Bubble Sort");
		Button btHeapSort = new Button("Heap Sort");
		Button btInsertionSort = new Button("Insertion Sort");
		Button btMergeSort = new Button("Merge Sort");
		Button btQuickSort = new Button("Quick Sort");
		Button btJavaSort = new Button("Java Sort");

		HBox hBox1 = new HBox(btBubbleSort, btHeapSort, btInsertionSort, btMergeSort, btQuickSort, btJavaSort);
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		hBox1.setPadding(new Insets(5, 5, 5, 5));

		Label lbRecords = new Label("Records To Load : ");
		ChoiceBox<Integer> cbRecordCount = new ChoiceBox<Integer>();
		cbRecordCount.getItems().addAll(2000, 40000, 80000, 160000, 320000);
		cbRecordCount.getSelectionModel().select(0);

		Button btLoadNamesAndStructures = new Button("Load Voters to ArrayList");
		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");
		HBox hBoxBottom = new HBox(lbRecords, cbRecordCount, btLoadNamesAndStructures, btClearAnswers, btnExit);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(10, 5, 10, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBox1);
		borderPane.setCenter(textArea1);
		borderPane.setBottom(hBoxBottom);

		btBubbleSort.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Bubble Sort\n";
				long startTime = System.currentTimeMillis();
				if (voters.size() > 0) {
					StaticSorts.bubbleSort(voters);
					output += "\nBubble Sort in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
					for (int i = 10; i < 2000; i = i + 20) {
						output += "Voter - " + voters.get(i).getLastname() + "," + voters.get(i).getAge() + "\n";
					}
				} else {
					output += "Voters not loaded";
				}
				textArea1.setText(output);
			}
		});
		btHeapSort.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Heap Sort \n";
				if (voters.size() > 0) {
					long startTime = System.currentTimeMillis();
					StaticSorts.heapSort(voters);
					output += "\nHeap Sort in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
					for (int i = 10; i < 2000; i = i + 20) {
						output += "Voter - " + voters.get(i).getLastname() + "," + voters.get(i).getAge() + "\n";
					}
				} else {
					output += "Voters not loaded";
				}
				textArea1.setText(output);
			}
		});
		btInsertionSort.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Insertion Sort \n";
				if (voters.size() > 0) {
					long startTime = System.currentTimeMillis();
					StaticSorts.insertionSort(voters);
					output += "\nInsert Sort in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
					for (int i = 10; i < 2000; i = i + 20) {
						output += "Voter - " + voters.get(i).getLastname() + "," + voters.get(i).getAge() + "\n";
					}
				} else {
					output += "Voters not loaded";
				}
				textArea1.setText(output);
			}
		});
		btMergeSort.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Merge Sort \n";
				if (voters.size() > 0) {
					long startTime = System.currentTimeMillis();
					StaticSorts.mergeSort(voters);
					output += "\nMerge Sort in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
					for (int i = 10; i < 2000; i = i + 25) {
						output += "Voter - " + voters.get(i).getLastname() + "," + voters.get(i).getAge() + "\n";
					}
				} else {
					output += "Voters not loaded";
				}
				textArea1.setText(output);
			}
		});
		btQuickSort.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Quick Sort \n";
				if (voters.size() > 0) {
					long startTime = System.currentTimeMillis();
					StaticSorts.quickSort(voters);
					output += "\nMerge Sort in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
					for (int i = 10; i < 2000; i = i + 20) {
						output += "Voter - " + voters.get(i).getLastname() + "," + voters.get(i).getAge() + "\n";
					}
				} else {
					output += "Voters not loaded";
				}
				textArea1.setText(output);
			}
		});

		btJavaSort.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Java Collections Sort \n";
				if (voters.size() > 0) {
					long startTime = System.currentTimeMillis();

					Collections.sort(voters);

					// Comparator<Voter> lastNameCompare = (s1, s2) ->
					// (s1.getLastname().compareTo(s2.getLastname()));
					// Collections.sort(voters,lastNameCompare);

					output += "\nJava Collections Sort in milliseconds : " + (System.currentTimeMillis() - startTime)
							+ "\n\n";
					for (int i = 0; i < 2000; i = i + 20) {
						output += "Voter - " + voters.get(i).getLastname() + "," + voters.get(i).getAge() + "\n";
					}
				} else {
					output += "Voters not loaded";
				}
				textArea1.setText(output);

			}
		});

		btLoadNamesAndStructures.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				long startTime = System.currentTimeMillis();
				voters.clear();
				int recordsToGet = cbRecordCount.getValue();
				voters = DbConnection.ReadFromDataBaseArrayList(recordsToGet);
				output += "Time to Load Array List milliseconds : " + (System.currentTimeMillis() - startTime) + "\n";
				if (voters.size() > 0) {
					output += "voters loaded ArrayList  - " + voters.size() + "\n";
				} else {
					output = "failure loading votes \n";
				}
				textArea1.setText(output);
			}
		});

		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea1.setText("");
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
		primaryStage.setTitle("Algorithms"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}