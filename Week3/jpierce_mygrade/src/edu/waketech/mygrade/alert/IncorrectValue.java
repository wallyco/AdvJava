package edu.waketech.mygrade.alert;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IncorrectValue extends Application {

	@Override
	public void start(Stage alertStage) throws Exception {
		alertStage.setTitle("Alert: Incorrect Input");
		TilePane tilePane = new TilePane();
		
		Button buttonOk = new Button("Ok");
		EventHandler<ActionEvent> ok =
				new EventHandler<ActionEvent>() 
				{
					@Override
					public void handle(ActionEvent arg) {
						alertStage.close();
					}
			
				};		
		buttonOk.setOnAction(ok);
		
		Label alertMessage = new Label("    All text boxes must be filled in and contain only numbers");
		
		tilePane.getChildren().add(alertMessage);
		tilePane.getChildren().add(buttonOk);

		Scene alertScene= new Scene(tilePane, 330, 50);
		alertStage.setScene(alertScene);
		alertStage.show();
	}
	
    public static void main(String args[]) {
        launch(args);
    }
}
		 
	
	
