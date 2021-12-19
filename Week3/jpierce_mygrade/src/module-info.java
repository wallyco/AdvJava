module MyGrade {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens edu.waketech.mygrade.application to javafx.graphics, javafx.fxml;
	opens edu.waketech.mygrade.controller to javafx.fxml;
}
