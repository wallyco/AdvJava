module ContactClient {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	requires java.sql;
	requires java.sql.rowset;
	requires javafx.base;
	
	opens edu.waketech.contactclient to javafx.graphics, javafx.fxml, java.sql;
	opens edu.waketech.contactclient.controller to javafx.fxml;
}
