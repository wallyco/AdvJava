module CSC251_JavaFX_and_MySQL_Project_Template {
	
	// JavaFX
	requires javafx.controls;
	requires javafx.graphics;
	
	// MySQL
	requires java.sql;
	requires org.junit.jupiter.api;
	
	// This package only uses MySQL
	opens edu.waketech.csc251.validate.mysql to java.sql;
	
	// This package uses JavaFX and MySQL
	opens edu.waketech.csc251.validate.mysql_and_fx to javafx.graphics, java.sql;
	
	opens lab_start to javafx.graphics;
}