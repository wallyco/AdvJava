package lab_starter;

import java.util.function.Predicate;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FundScreenFX extends Application {

	public void start(Stage primaryStage) {

		Portfolio portfolio = new Portfolio();
		// System.out.println("Size : " + portfolio.size());
		TextArea textArea = new TextArea();
		Button btKeepByTicker = new Button("Ticker");
		Button btKeepByHoldingSize = new Button("Holding Size");
		Button btKeepByMinimumInvestment = new Button(" Min Invest");
		Button btKeepByValue = new Button("Value");
		Button btKeepByLocation = new Button("Location");

		Button btExcludeByTicker = new Button("Ticker");
		Button btExcludeByHoldingSize = new Button("Holding Size");
		Button btExcludeByMinimumInvestment = new Button("Min Invest");
		Button btExcludeByValue = new Button("Value");
		Button btExcludeByLocation = new Button("Location");

		HBox hBoxTopLeft = new HBox(new Label("Keep by"), btKeepByTicker, btKeepByHoldingSize,
				btKeepByMinimumInvestment, btKeepByValue, btKeepByLocation);
		hBoxTopLeft.setSpacing(10);
		hBoxTopLeft.setAlignment(Pos.CENTER);
		hBoxTopLeft.setPadding(new Insets(5, 5, 5, 5));

		HBox hBoxTopRight = new HBox(new Label("Exclude by"), btExcludeByTicker, btExcludeByHoldingSize,
				btExcludeByMinimumInvestment, btExcludeByValue, btExcludeByLocation);
		hBoxTopRight.setSpacing(10);
		hBoxTopRight.setAlignment(Pos.CENTER);
		hBoxTopRight.setPadding(new Insets(5, 5, 5, 5));

		GridPane gridPaneTop = new GridPane();
		gridPaneTop.add(hBoxTopLeft, 0, 0);
		gridPaneTop.add(hBoxTopRight, 1, 0);

		Button btListPortfolio = new Button("List Portfolio");
		Button btResetPortfolio = new Button("Reset Portfolio");
		TextField txTicker = new TextField();
		txTicker.setPrefWidth(50);
		TextField txFloor = new TextField();
		txFloor.setPrefWidth(50);
		TextField txCeiling = new TextField();
		txCeiling.setPrefWidth(50);

		ChoiceBox<String> cbdomicile = new ChoiceBox<String>();
		cbdomicile.getItems().addAll("Domestic", "International", "Global");
		cbdomicile.getSelectionModel().select(0);
		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");

		HBox hBoxBottom = new HBox(btListPortfolio, btResetPortfolio, new Label("Ticker"), txTicker, new Label("Floor"),
				txFloor, new Label("Ceiling"), txCeiling, new Label("Location"), cbdomicile, btClearAnswers, btnExit);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(gridPaneTop);
		borderPane.setBottom(hBoxBottom);
		borderPane.setCenter(textArea);

		btListPortfolio.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Current Portfolio\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btResetPortfolio.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				portfolio.refresh();
				String output = "Current Portfolio\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});

		btKeepByTicker.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String ticker = txTicker.getText().toUpperCase();
				String output = "Keep Ticker  -- " + ticker + "\n";
	
				portfolio.removeIf(new Predicate<MutualFund>() {

					@Override
					public boolean test(MutualFund t) {
						return !t.getTicker().equals(ticker);
					}
					
				});
				
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btKeepByValue.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				double floor;
				double ceiling;
				try {
					floor = Double.parseDouble(txFloor.getText());
					ceiling = Double.parseDouble(txCeiling.getText());
				}catch(Exception ex) {
					return;
				}
				
				portfolio.removeIf(new Predicate<MutualFund>() {

					@Override
					public boolean test(MutualFund t) {
						if(t.getValueMeasure() >= floor && t.getValueMeasure() <= ceiling) {
							return false;
						}else return true;
					}

				});
				
				String output = "Keep by Value\n";
				output += "Floor " + floor + " Ceiling " + ceiling + "\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btKeepByLocation.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				MutualFund.MARKET mkt = MutualFund.MARKET.valueOf(cbdomicile.getValue().toUpperCase());
				// this one is supplied for you
				portfolio.removeIf(new Predicate<MutualFund>() {
					@Override
					public boolean test(MutualFund t) {
						return !(mkt == t.getDomicile());
					}
				});
				String output = "Current Portfolio\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btKeepByHoldingSize.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double floor;
				double ceiling;
				try {
					floor = Double.parseDouble(txFloor.getText());
					ceiling = Double.parseDouble(txCeiling.getText());
				}catch(Exception ex) {
					return;
				}
				
				portfolio.removeIf(new Predicate<MutualFund>() {
					@Override
					public boolean test(MutualFund t) {
						if (t.getAvgHoldingSize() >= floor && t.getAvgHoldingSize() <= ceiling) return false;
						else return true;
					}
					
				});
				
				String output = "Keep Holding Size\n";
				output += "Floor " + floor + " Ceiling " + ceiling + "\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btKeepByMinimumInvestment.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double floor;
				double ceiling;
				try {
					floor = Double.parseDouble(txFloor.getText());
					ceiling = Double.parseDouble(txCeiling.getText());
				}catch(Exception ex) {
					return;
				}

				
				portfolio.removeIf(new Predicate<MutualFund>() {

					@Override
					public boolean test(MutualFund t) {
						return !(t.getMinimumInvestment() >= floor 
								&& t.getMinimumInvestment() <= ceiling);
					}
					
				});
		
				
				String output = "Keep by Minimum Investment\n";
				output += "Floor " + floor + " Ceiling " + ceiling + "\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btExcludeByTicker.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String ticker = txTicker.getText().toUpperCase();
				String output = "Exclude by  Ticker  -- " + ticker + "\n";
				portfolio.removeIf((MutualFund t) ->
					t.getTicker().equals(ticker));
			
			for (int i = 0; i < portfolio.size(); i++) {
				output += "[" + i + "] " + portfolio.get(i) + "\n";
			}
				textArea.setText(output);
			}
		});
		btExcludeByValue.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				double floor;
				double ceiling;
				try {
					floor = Double.parseDouble(txFloor.getText());
					ceiling = Double.parseDouble(txCeiling.getText());
				}catch(Exception ex) {
					return;
				}
				
				portfolio.removeIf((MutualFund t) -> {
					if(t.getValueMeasure() < floor) {
						return false;
					}else if (t.getValueMeasure() > ceiling) {
						return false;
					}else return true;
				});
		
				String output = "Exclude by Value\n";
				output += "Floor " + floor + " Ceiling " + ceiling + "\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btExcludeByLocation.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				MutualFund.MARKET mkt = MutualFund.MARKET.valueOf(cbdomicile.getValue().toUpperCase());
				// this one is supplied for you
				portfolio.removeIf(new Predicate<MutualFund>() {
					@Override
					public boolean test(MutualFund t) {
						return mkt == t.getDomicile();
					}
				});
				String output = "Current Portfolio\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btExcludeByHoldingSize.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double floor;
				double ceiling;
				try {
					floor = Double.parseDouble(txFloor.getText());
					ceiling = Double.parseDouble(txCeiling.getText());
				}catch(Exception ex) {
					return;
				}
				
				portfolio.removeIf((MutualFund t) -> {
					if(t.getAvgHoldingSize() < floor) {
						return false;
					}else if (t.getAvgHoldingSize() > ceiling) {
						return false;
					}else return true;
				});
				
				String output = "Exclude Holding Size\n";
				output += "Floor " + floor + " Ceiling " + ceiling + "\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
				}
				textArea.setText(output);
			}
		});
		btExcludeByMinimumInvestment.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double floor;
				double ceiling;
				try {
					floor = Double.parseDouble(txFloor.getText());
					ceiling = Double.parseDouble(txCeiling.getText());
				}catch(Exception ex) {
					return;
				}
				
				portfolio.removeIf((MutualFund t) -> {
					if(t.getMinimumInvestment() < floor) {
						return false;
					}else if (t.getMinimumInvestment() > ceiling) {
						return false;
					}else return true;
				});
				
				String output = "Exclude min invest\n";
				output += "Floor " + floor + " Ceiling " + ceiling + "\n";
				for (int i = 0; i < portfolio.size(); i++) {
					output += "[" + i + "] " + portfolio.get(i) + "\n";
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
		Scene scene = new Scene(borderPane, 900, 350);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setTitle("Fund Screen");
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