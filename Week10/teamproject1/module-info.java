module CSC251_JavaFX_and_MySQL_Project_Template {
	
	// JavaFX
	requires javafx.controls;
	requires javafx.graphics;
	
	// MySQL
	requires java.sql;
	requires org.junit.jupiter.api;
	requires junit;
	
	// This package only uses MySQL
	opens edu.waketech.csc251.validate.mysql to java.sql;
	
	// This package uses JavaFX and MySQL
	opens edu.waketech.csc251.validate.mysql_and_fx to javafx.graphics, javafx.fxml, java.sql;
	
	opens chapter22 to javafx.graphics;
	opens chapter23 to javafx.graphics;
	opens experimentFX to javafx.graphics;
	opens lecture to javafx.graphics;
}