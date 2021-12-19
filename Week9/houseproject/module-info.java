module CSC251_JavaFX_Example {
	
	requires javafx.controls;
	requires javafx.graphics;
	
	requires java.sql;
	requires javafx.base;
	requires org.junit.jupiter.api;
	requires junit;

	opens project_start.hr to javafx.controls, javafx.graphics;
	opens project_start.hr.person to javafx.graphics, javafx.base;
	opens project_start.hr.mgmt to javafx.graphics, javafx.base;
}