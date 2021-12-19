package edu.waketech.contactclient.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import edu.waketech.utility.sql.SQL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ClientController implements Initializable{
	
	private ObservableList<Person> initialTableViewData = FXCollections.observableArrayList();
	private ObservableList<Person> adjustedTableViewData;
	private Person selectedPerson;
	
	@FXML
	private BorderPane bpane;
	@FXML
	private VBox vboxMain;
	
	@FXML
	private TableView<Person> tableView;
	
	@FXML 
	private TableColumn<Person, String> columnID;
	@FXML
	private TableColumn<Person, String> columnName;
	@FXML
	private TableColumn<Person, String> columnAddress;
	@FXML
	private TableColumn<Person, String> columnCellPhone;
	@FXML
	private TableColumn<Person, String> columnEmail;
	
	@FXML
	private Button buttonAdd, buttonDelete, buttonUpdate;
	
	@FXML
	private TextField textfID, textfName, textfAddress, textfCell, textfEmail;
	
	private SQL sql = new SQL();
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnID.setCellValueFactory(i -> i.getValue().IDProperty());
		columnName.setCellValueFactory(i -> i.getValue().firstNameProperty());
		columnAddress.setCellValueFactory(i -> i.getValue().addressProperty());
		columnCellPhone.setCellValueFactory(i -> i.getValue().cellPhoneProperty());
		columnEmail.setCellValueFactory(i -> i.getValue().emailProperty());
		
		tableView.getItems().setAll(parseContactList());
		adjustedTableViewData = FXCollections.observableArrayList(initialTableViewData);
		
		//Table selection handler
		{
			EventHandler<MouseEvent> tableSelected =
					new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent arg0) {
							selectedPerson = tableView.getSelectionModel().getSelectedItem();
							if(selectedPerson != null) {
								textfID.setText(selectedPerson.getID());
								textfName.setText(selectedPerson.getFirstName());
								textfAddress.setText(selectedPerson.getAddress());
								textfCell.setText(selectedPerson.getCellPhone());
								textfEmail.setText(selectedPerson.getEmail());
							}
						}
					};
			tableView.setOnMouseClicked(tableSelected);
		}
	}
	
	private List<Person> parseContactList() {
		if(sql.loadDataToResult("contact") && sql.getResultSet() != null);
			try {
				while(sql.getResultSet().next()) {
					initialTableViewData.add
					(new Person(sql.getResultSet().getString(1),
							 sql.getResultSet().getString(2),
							 sql.getResultSet().getString(3),
							 sql.getResultSet().getString(4),
							 sql.getResultSet().getString(5)));
				} 
				
			} catch (SQLException e) {
				System.out.print("Error parsing contact list");
				e.printStackTrace();
				return null;
			}	
		return initialTableViewData;
	}
	
	@FXML
	private void btnAddNewEntry() {
		try {
			if(textfieldCheck() && textfieldIDCheck()) {
				Person p = new Person(textfID.getText(),
						 textfName.getText(),
						 textfAddress.getText(),
						 textfCell.getText(),
						 textfEmail.getText());
				sql.createData(p);
				adjustedTableViewData.add(p);
				displayDataToClient();
				textfieldClear();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void btnDeleteEntry() {
		if(selectedPerson != null) {
			new Alert(AlertType.CONFIRMATION,
				"Are you sure you'd like to delete from the table id: " + selectedPerson.getID(),
				ButtonType.APPLY, ButtonType.CANCEL)
				.showAndWait();
			sql.deleteData(selectedPerson);
			adjustedTableViewData.remove(selectedPerson);
			displayDataToClient();
		}
	}
	
	@FXML
	private void btnUpdateData() {
		selectedPerson.setID(textfID.getText());
		selectedPerson.setFirstName(textfName.getText());
		selectedPerson.setAddress(textfAddress.getText());
		selectedPerson.setCellPhone(textfCell.getText());
		selectedPerson.setEmail(textfEmail.getText());
		new Alert(AlertType.CONFIRMATION,
				"Are you sure you'd like to update table with id: " + selectedPerson.getID(),
				ButtonType.APPLY, ButtonType.CANCEL)
				.showAndWait();
		sql.updateData(selectedPerson);
	}
	
	
	private void displayDataToClient() {
		tableView.getItems().setAll(adjustedTableViewData); 
	}

	private boolean textfieldCheck() throws Exception {
		boolean showAlert = false;
		if(textfID.getText().equals("") || !isInteger(textfID.getText()))
			showAlert = true;
		if(textfName.getText().equals(""))
			showAlert = true;
		if(textfAddress.getText().equals(""))
			showAlert = true;
		if(textfCell.getText().equals(""))
			showAlert = true;
		if(textfEmail.getText().equals(""))
			showAlert = true;
		if(showAlert) {
			new Alert(AlertType.WARNING, "All text fields must be filled in \n and ID must be an integer", ButtonType.OK)
				.showAndWait();
			return false;
		}
		return true;
	}

	private boolean textfieldIDCheck() throws Exception {
		for(Person index : adjustedTableViewData) {
			if(textfID.getText().equals(index.getID())) {
				new Alert(AlertType.WARNING, "That ID is already in the database", ButtonType.OK)
					.showAndWait();
				return false;
			}
			
		} return true;
	}
	
	private void textfieldClear() {
		textfID.clear();
		textfName.clear();
		textfAddress.clear();
		textfCell.clear();
		textfEmail.clear();
	}
	
	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		}catch(NumberFormatException e) {
			return false;
		}catch(NullPointerException e) {
			return false;
		}
		
		return true;
	}
	
}
