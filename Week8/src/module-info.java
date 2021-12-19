module CSC251_JavaFX_Example {
	
	// JavaFX
	requires javafx.controls;
	requires javafx.graphics;

	opens edu.waketech.csc251.validate.fx to javafx.controls, javafx.graphics;
	opens lab_starter to javafx.graphics;
}