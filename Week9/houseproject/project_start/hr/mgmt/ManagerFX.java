package project_start.hr.mgmt;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class ManagerFX extends BorderPane {
	private ManagerDAO managerModel;

	private TableView<Manager> tblManager;
	private TextField txtId;
	private TextField txtName;
	private TextField txtSalary;
	private TextField txtDepartment;

	private Button btnAddNew;
	private Button btnDelete;
	private Button btnUpdate;
	
	public ManagerFX() {
		managerModel = new ManagerDAO();
		
		//Table Click
		tblManager = new TableView<Manager>();
		getTblManager().setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() > 0) {
				if (getTblManager().getSelectionModel().getSelectedItem() != null) {
					Manager manager = getTblManager().getSelectionModel().getSelectedItem();
					getTxtId().setText(String.valueOf(manager.getId()));
					getTxtName().setText(manager.getEmployeeName());
					getTxtSalary().setText(String.valueOf(manager.getSalary()));
					getTxtDepartment().setText(manager.getDepartment());
				}
			}
		});
		ScrollPane pane = new ScrollPane(tblManager);
		
		//Add new button
		btnAddNew = new Button("Add New");
		getBtnAddNew().setOnAction(e -> {
			String name = getTxtName().getText();
			double salary = Double.parseDouble(getTxtSalary().getText());
			String department = getTxtDepartment().getText();
			Manager m = new Manager(0, name, salary, department);
			if (managerModel.create(m)) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Add Employee successfull", ButtonType.OK);
				alert.showAndWait();
				getTblManager().setItems(FXCollections.observableArrayList(managerModel.findAll()));
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Add Employee Unsuccessfull", ButtonType.OK);
				alert.showAndWait();
			}
			getTxtId().setText("");
			getTxtSalary().setText("");
			getTxtName().setText("");
			getTxtDepartment().setText("");
		});
		
		//Delete Button
		btnDelete = new Button("Delete");
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int id = Integer.parseInt(getTxtId().getText());
				Manager m = managerModel.find(id);
				String selection = m.getEmployeeName();
				Alert alert = new Alert(AlertType.WARNING, "Delete " + selection + " ?", ButtonType.YES, ButtonType.NO);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
					managerModel.delete(m);
					getTblManager().setItems(FXCollections.observableArrayList(managerModel.findAll()));
					getTxtId().setText("");
					getTxtSalary().setText("");
					getTxtName().setText("");
					getTxtDepartment().setText("");
				}
			}
		});
		
		//Update Button
		btnUpdate = new Button("Update");
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int id = Integer.parseInt(getTxtId().getText());
				String name = getTxtName().getText();
				double salary = Double.parseDouble(getTxtSalary().getText());
				String department = getTxtDepartment().getText();
				Manager m = new Manager(id, name, salary, department);
				if (managerModel.edit(m)) {
					Alert alert = new Alert(AlertType.CONFIRMATION, "Edit employee successfull", ButtonType.OK);
					alert.showAndWait();
					getTblManager().setItems(FXCollections.observableArrayList(managerModel.findAll()));
				} else {
					Alert alert = new Alert(AlertType.WARNING, "Edit product failed", ButtonType.OK);
					alert.showAndWait();
				}
				getTxtId().setText("");
				getTxtSalary().setText("");
				getTxtName().setText("");
				getTxtDepartment().setText("");
			}
		});
		
		HBox hBoxBottom = new HBox(btnAddNew, btnDelete, btnUpdate);
		hBoxBottom.getStyleClass().add("hboxGreen");
		
		txtId = new TextField();
		txtId.setEditable(false);
		txtId.setVisible(false);
		txtName = new TextField();
		txtSalary = new TextField();
		txtDepartment = new TextField();
		
		HBox hBoxMiddle = new HBox(new Label("Name:"), txtName, new Label("Salary:"), txtSalary, new Label("Department:", txtDepartment)); //Needs to be adjusted in css
		hBoxMiddle.getStyleClass().add("hboxWheat");
		
		setTop(pane);
		setCenter(hBoxMiddle);
		setBottom(hBoxBottom);
		FormatTable();
		getTblManager().setItems(FXCollections.observableArrayList(managerModel.findAll()));
		
	}
	
	public void FormatTable() {

		tblManager.setEditable(true);

		TableColumn<Manager, Integer> idCol = new TableColumn<>("ID");
		idCol.setMinWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<Manager, Integer>("id"));
		idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		TableColumn<Manager, String> nameCol = new TableColumn<>("Employee Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<Manager, String>("employeeName"));
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

		nameCol.setOnEditCommit(new EventHandler<CellEditEvent<Manager, String>>() {
			@Override
			public void handle(CellEditEvent<Manager, String> t) {
				((Manager) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setEmployeeName(t.getNewValue());
				Manager m = getTblManager().getSelectionModel().getSelectedItem();
				ManagerDAO model = new ManagerDAO();
				model.edit(m);
			}
		});

		TableColumn<Manager, Double> salaryCol = new TableColumn<>("Salary");
		salaryCol.setMinWidth(200);
		salaryCol.setCellValueFactory(new PropertyValueFactory<Manager, Double>("Salary"));
		salaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		salaryCol.setOnEditCommit(new EventHandler<CellEditEvent<Manager, Double>>() {
			@Override
			public void handle(CellEditEvent<Manager, Double> t) {
				((Manager) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalary(t.getNewValue());
				Manager m = getTblManager().getSelectionModel().getSelectedItem();
				ManagerDAO model = new ManagerDAO();
				model.edit(m);
			}
		});
		
		TableColumn<Manager, String> departmentCol = new TableColumn<>("Department");
		departmentCol.setMinWidth(200);
		departmentCol.setCellValueFactory(new PropertyValueFactory<Manager, String>("department"));
		departmentCol.setCellFactory(TextFieldTableCell.forTableColumn());

		departmentCol.setOnEditCommit(new EventHandler<CellEditEvent<Manager, String>>() {
			@Override
			public void handle(CellEditEvent<Manager, String> t) {
				((Manager) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setEmployeeName(t.getNewValue());
				Manager m = getTblManager().getSelectionModel().getSelectedItem();
				ManagerDAO model = new ManagerDAO();
				model.edit(m);
			}
		});
		tblManager.getColumns().add(0, idCol);
		tblManager.getColumns().add(1, nameCol);
		tblManager.getColumns().add(2, salaryCol);
		tblManager.getColumns().add(3, departmentCol);

	}

	public ManagerDAO getEmployeeModel() {
		return managerModel;
	}

	public void setEmployeeModel(ManagerDAO managerModel) {
		this.managerModel = managerModel;
	}

	public TableView<Manager> getTblManager() {
		return tblManager;
	}

	public void setTblManager(TableView<Manager> tblManager) {
		this.tblManager = tblManager;
	}

	public TextField getTxtId() {
		return txtId;
	}

	public void setTxtId(TextField txtId) {
		this.txtId = txtId;
	}

	public TextField getTxtName() {
		return txtName;
	}

	public void setTxtName(TextField txtName) {
		this.txtName = txtName;
	}

	public TextField getTxtSalary() {
		return txtSalary;
	}

	public void setTxtSalary(TextField txtSalary) {
		this.txtSalary = txtSalary;
	}

	public Button getBtnAddNew() {
		return btnAddNew;
	}

	public void setBtnAddNew(Button btnAddNew) {
		this.btnAddNew = btnAddNew;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}

	public Button getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(Button btnUpdate) {
		this.btnUpdate = btnUpdate;
	}
	public TextField getTxtDepartment() {
		return txtDepartment;
	}

	public void setTxtDepartment(TextField txtDepartment) {
		this.txtDepartment = txtDepartment;
	}
	

}
