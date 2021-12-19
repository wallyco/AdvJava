package experimentFX;

import java.io.IOException;

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

public class AlgorithmAnalysisFX extends Application {

	public void start(Stage primaryStage) throws IOException {

		TextArea textArea1 = new TextArea();
		textArea1.setWrapText(true);

		Button btTest1 = new Button("Program #1");
		Button btTest2 = new Button("Program #2");
		Button btTest3 = new Button("Program #3");
		Button btTest4 = new Button("Program #4");
		Button btTest5 = new Button("Program #5");
		Button btRunTest = new Button("T1");
		Button btRunTest1 = new Button("T2");

		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");

		HBox hBoxTop = new HBox(btTest1, btTest2, btTest3, btTest4, btTest5, btClearAnswers, btnExit, btRunTest, btRunTest1);
		hBoxTop.setSpacing(10);
		hBoxTop.setAlignment(Pos.CENTER);
		hBoxTop.setPadding(new Insets(5, 5, 5, 5));

//		HBox hBoxMiddle = new HBox(textArea1);
//		hBoxMiddle.setSpacing(5);
//		hBoxMiddle.setAlignment(Pos.CENTER);
//		hBoxMiddle.setPadding(new Insets(5, 5, 5, 5));
		
		
		int[] sc = {75000, 150000, 300000};
		
		
		Label lbSearches = new Label("Searches : ");
		ChoiceBox<Integer> cbSearches = new ChoiceBox<Integer>();
		cbSearches.getItems().addAll(sc[0],sc[1],sc[2]);
		cbSearches.getSelectionModel().select(0);

		int[] lc = {125000, 250000, 500000};

		Label lbLoad = new Label("Elements to Sort : ");
		ChoiceBox<Integer> cbLoad = new ChoiceBox<Integer>();
		cbLoad.getItems().addAll(lc[0], lc[1], lc[2]);
		cbLoad.getSelectionModel().select(0);

		HBox hBoxBottom = new HBox(lbSearches, cbSearches, lbLoad, cbLoad);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBoxTop);
		borderPane.setCenter(textArea1);
		borderPane.setBottom(hBoxBottom);

		btTest1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SortMethods.tweakJit();
				double elapsed = SortMethods.methC(cbLoad.getValue());
				String output = "Program 1 - with " + cbLoad.getValue() +" elements took " + elapsed + " milliseconds\n";
				textArea1.appendText(output);
			}
		});
		btTest2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SortMethods.tweakJit();
				double elapsed = SortMethods.methA(cbLoad.getValue(), cbSearches.getValue());
				String output = "Program 2 - with " + cbLoad.getValue() +" elements and " + 
						cbSearches.getValue() + " searches took "  + elapsed + " milliseconds\n";
				textArea1.appendText(output);
			}
		});
		btTest3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SortMethods.tweakJit();
				double elapsed = SortMethods.methB(cbLoad.getValue(), cbSearches.getValue());
				String output = "Program 3 - with " + cbLoad.getValue() +" elements and " + 
						cbSearches.getValue() + " searches took "  + elapsed + " milliseconds\n";
				textArea1.appendText(output);
			}
		});
		btTest4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SortMethods.tweakJit();
				double elapsed = SortMethods.methD(cbLoad.getValue());
				String output = "Program 4 - with " + cbLoad.getValue() +" elements took " + elapsed + " milliseconds\n";
				textArea1.appendText(output);
			}
		});
		btTest5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SortMethods.tweakJit();
				double elapsed = SortMethods.methE(cbLoad.getValue(), cbSearches.getValue());
				String output = "Program 5 - with " + cbLoad.getValue() +" elements and " + 
							cbSearches.getValue() + " searches took "  + elapsed + " milliseconds\n";
				textArea1.appendText(output);
			}
		});
		btRunTest.setOnAction(new EventHandler<ActionEvent>() {
			//Author Joshua Pierce
			
			@Override
			public void handle(ActionEvent arg0) {
				SortMethods.tweakJit();
				String[] output = new String[]{"","\n","\n","\n"};
				
				output[0] = "\tProgram 1 "; 
				for(int i = 0; i < 3; i++) {
					double elapsed = SortMethods.methC(lc[i]);
					output[i+1] += lc[i] + ": " + elapsed + " milliseconds";
				}
				
				output[0] += "\t\t\t Program 4 ";
				for(int i = 0; i < 3; i++) {
					double elapsed = SortMethods.methD(lc[i]);
					output[i+1] += "\t\t" + elapsed + " milliseconds";
				}
				
				String fout = output[0] + output[1] + output[2] + output[3];
				textArea1.setText(fout);
				
			}	
		});
		
		btRunTest1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				SortMethods.tweakJit();
				String[] output = new String[]{"","\n","\n","\n"};
				
				output[0] = "\t\t\t\tProgram 2 "; 
				for(int i = 0; i < 3; i++) {
					double elapsed = SortMethods.methA(lc[i], sc[i]);
					output[i+1] += "Sort: " + lc[i] + ", Search: " + sc[i] + ": " +
					elapsed + " milliseconds";
				}
				
				output[0] += "\t\t\t Program 3 ";
				for(int i = 0; i < 3; i++) {
					double elapsed = SortMethods.methB(lc[i], sc[i]);
					output[i+1] += "\t\t" + elapsed + " milliseconds";
				}
				
				output[0] += "\t\t\t Program 5 ";
				for(int i = 0; i < 3; i++) {
					double elapsed = SortMethods.methE(lc[i], sc[i]);
					output[i+1] += "\t\t" + elapsed + " milliseconds";
				}
				
				String fout = output[0] + output[1] + output[2] + output[3];
				textArea1.setText(fout);
			}
			
		});
		
		//-

		btClearAnswers.setOnAction(e -> {
			textArea1.setText("");
		});

		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 700, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setTitle("Algorithm Analysis");
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