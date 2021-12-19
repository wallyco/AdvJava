	/*Labs	25%
	Review Quizzes	15%
	Mid Term	10%
	Project #1	20%
	Project #2	20%
	Final	10%
	*/
package edu.waketech.mygrade.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import edu.waketech.mygrade.calculations.AverageCalculator;
import edu.waketech.mygrade.alert.IncorrectValue;

public class MyGradeController {

	AverageCalculator averageCalc;
	
	@FXML
	TitledPane titledpaneMyGrade;
	
	@FXML
	Button buttonDisplayGrade, buttonClear, buttonExit;
	
	@FXML
	Label labelActivity, labelFinal, labelGradeWeight, labelAverage,
		  labelProjectTwo, labelProjectOne, labelReviewQuizzes, 
		  labelAssignments, labelMidTerm;
	
	@FXML
	TextField textfieldAssignmentsWeight, textfieldReviewQuizzesWeight,
			  textfieldProjectOneWeight, textfieldProjectTwoWeight, 
			  textfieldMidTermWeight, textfieldFinaWeight, textboxAssignmentAverage,
			  textboxReviewQuizzesAverage, textboxProjectOneAverage, textboxProjectTwoAverage,
			  textboxMidTermAverage, textboxFinalAverage, textboxAverage;
	
	
	@FXML
	public void exit(ActionEvent event) {
	    Stage stage = (Stage) buttonExit.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void clear(ActionEvent event) {
		textboxAssignmentAverage.clear();
		textboxReviewQuizzesAverage.clear();
		textboxProjectOneAverage.clear();
		textboxProjectTwoAverage.clear();
		textboxMidTermAverage.clear();
		textboxFinalAverage.clear();
		textboxAverage.clear();
	}
	
	@FXML
	public void calculateAverage() throws Exception {
		try {
			double assignments = Double.parseDouble(textboxAssignmentAverage.getText());
			double reviewquizzes = Double.parseDouble(textboxReviewQuizzesAverage.getText());
			double projectone = Double.parseDouble(textboxProjectOneAverage.getText());
			double projecttwo = Double.parseDouble(textboxProjectTwoAverage.getText());
			double midterm = Double.parseDouble(textboxMidTermAverage.getText());
			double finaltest = Double.parseDouble(textboxFinalAverage.getText());
			
			averageCalc  = new AverageCalculator(reviewquizzes, midterm, assignments,
						   						projectone, projecttwo, finaltest);

			averageCalc.calculateAverage();
			
			textboxAverage.setText(averageCalc.toString());	
		}catch(Exception e) {
			new IncorrectValue().start(new Stage());
		}
	}
	
}



