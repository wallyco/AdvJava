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

public class ExecutiveFX extends BorderPane{
	private ExecutiveDAO executiveModel;

	private TableView<Executive> tblExecutive;
	private TextField txtId;
	private TextField txtName;
	private TextField txtSalary;
	private TextField txtDepartment;
	private TextField txtBonus;
	private Button btnAddNew;
	private Button btnDelete;
	private Button btnUpdate;
	
	public ExecutiveFX() {
		
		executiveModel = new ExecutiveDAO();
		
		//Table Click
		tblExecutive = new TableView<Executive>();
		getTblExecutive().setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() > 0) {
				if (getTblExecutive().getSelectionModel().getSelectedItem() != null) {
					Executive e = getTblExecutive().getSelectionModel().getSelectedItem();
					getTxtId().setText(String.valueOf(e.getId()));
					getTxtName().setText(e.getEmployeeName());
					getTxtSalary().setText(String.valueOf(e.getSalary()));
					getTxtDepartment().setText(e.getDepartment());
					getTxtBonus().setText(String.valueOf(e.getBonus()));
				}
			}
		});
		ScrollPane pane = new ScrollPane(tblExecutive);
		
		btnAddNew = new Button("Add New");
		getBtnAddNew().setOnAction(e -> {
			String name = getTxtName().getText();
			double salary = Double.parseDouble(getTxtSalary().getText());
			String department = getTxtDepartment().getText();
			double bonus = Double.parseDouble(getTxtBonus().getText());
			Executive ex = new Executive(0, name, salary, department, bonus);
			if (executiveModel.create(ex)) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Add Employee successfull", ButtonType.OK);
				alert.showAndWait();
				getTblExecutive().setItems(FXCollections.observableArrayList(executiveModel.findAll()));
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Add Employee Unsuccessfull", ButtonType.OK);
				alert.showAndWait();
			}
			getTxtId().setText("");
			getTxtSalary().setText("");
			getTxtName().setText("");
			getTxtDepartment().setText("");
			getTxtBonus().setText("");
		});
		
		btnDelete = new Button("Delete");
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int id = Integer.parseInt(getTxtId().getText());
				Executive ex = executiveModel.find(id);
				String selection = ex.getEmployeeName();
				Alert alert = new Alert(AlertType.WARNING, "Delete " + selection + " ?", ButtonType.YES, ButtonType.NO);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
					executiveModel.delete(ex);
					getTblExecutive().setItems(FXCollections.observableArrayList(executiveModel.findAll()));
					getTxtId().setText("");
					getTxtSalary().setText("");
					getTxtName().setText("");
					getTxtDepartment().setText("");
					getTxtBonus().setText("");
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
				double bonus = Double.parseDouble(getTxtBonus().getText());
				Executive m = new Executive(id, name, salary, department, bonus);
				if (executiveModel.edit(m)) {
					Alert alert = new Alert(AlertType.CONFIRMATION, "Edit employee successfull", ButtonType.OK);
					alert.showAndWait();
					getTblExecutive().setItems(FXCollections.observableArrayList(executiveModel.findAll()));
				} else {
					Alert alert = new Alert(AlertType.WARNING, "Edit product failed", ButtonType.OK);
					alert.showAndWait();
				}
				getTxtId().setText("");
				getTxtSalary().setText("");
				getTxtName().setText("");
				getTxtDepartment().setText("");
				getTxtDepartment().setText("");
				getTxtBonus().setText("");
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
		txtBonus = new TextField();
		
		HBox hBoxMiddle = new HBox(new Label("Name:"), txtName,
				new Label("Salary:"), txtSalary,
				new Label("Department:", txtDepartment),
				new Label("Bonus:", txtBonus)); //Needs to be adjusted in css
		hBoxMiddle.getStyleClass().add("hboxWheat");
		
		setTop(pane);
		setCenter(hBoxMiddle);
		setBottom(hBoxBottom);
		FormatTable();
		getTblExecutive().setItems(FXCollections.observableArrayList(executiveModel.findAll()));
				
	}
	
	public void FormatTable() {

		tblExecutive.setEditable(true);

		TableColumn<Executive, Integer> idCol = new TableColumn<>("ID");
		idCol.setMinWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<Executive, Integer>("id"));
		idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		TableColumn<Executive, String> nameCol = new TableColumn<>("Employee Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<Executive, String>("employeeName"));
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

		nameCol.setOnEditCommit(new EventHandler<CellEditEvent<Executive, String>>() {
			@Override
			public void handle(CellEditEvent<Executive, String> t) {
				((Executive) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setEmployeeName(t.getNewValue());
				Executive ex = getTblExecutive().getSelectionModel().getSelectedItem();
				ExecutiveDAO model = new ExecutiveDAO();
				model.edit(ex);
			}
		});

		TableColumn<Executive, Double> salaryCol = new TableColumn<>("Salary");
		salaryCol.setMinWidth(200);
		salaryCol.setCellValueFactory(new PropertyValueFactory<Executive, Double>("Salary"));
		salaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		salaryCol.setOnEditCommit(new EventHandler<CellEditEvent<Executive, Double>>() {
			@Override
			public void handle(CellEditEvent<Executive, Double> t) {
				((Executive) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalary(t.getNewValue());
				Executive ex = getTblExecutive().getSelectionModel().getSelectedItem();
				ExecutiveDAO model = new ExecutiveDAO();
				model.edit(ex);
			}
		});
		
		TableColumn<Executive, String> departmentCol = new TableColumn<>("Department");
		departmentCol.setMinWidth(200);
		departmentCol.setCellValueFactory(new PropertyValueFactory<Executive, String>("department"));
		departmentCol.setCellFactory(TextFieldTableCell.forTableColumn());

		departmentCol.setOnEditCommit(new EventHandler<CellEditEvent<Executive, String>>() {
			@Override
			public void handle(CellEditEvent<Executive, String> t) {
				((Executive) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setEmployeeName(t.getNewValue());
				Executive ex = getTblExecutive().getSelectionModel().getSelectedItem();
				ExecutiveDAO model = new ExecutiveDAO();
				model.edit(ex);
			}
		});
		
		TableColumn<Executive, Double> bonusCol = new TableColumn<>("Bonus");
		bonusCol.setMinWidth(50);
		bonusCol.setCellValueFactory(new PropertyValueFactory<Executive, Double>("bonus"));
		bonusCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		bonusCol.setOnEditCommit(new EventHandler<CellEditEvent<Executive, Double>>() {
			@Override
			public void handle(CellEditEvent<Executive, Double> t) {
				((Executive) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalary(t.getNewValue());
				Executive ex = getTblExecutive().getSelectionModel().getSelectedItem();
				ExecutiveDAO model = new ExecutiveDAO();
				model.edit(ex);
			}
		});
		
		tblExecutive.getColumns().add(0, idCol);
		tblExecutive.getColumns().add(1, nameCol);
		tblExecutive.getColumns().add(2, salaryCol);
		tblExecutive.getColumns().add(3, departmentCol);
		tblExecutive.getColumns().add(4, bonusCol);


	}

	public ExecutiveDAO getexecutiveModel() {
		return executiveModel;
	}
	public void setexecutiveModel(ExecutiveDAO executiveModel) {
		this.executiveModel = executiveModel;
	}
	public TableView<Executive> getTblExecutive() {
		return tblExecutive;
	}
	public void setTblExecutive(TableView<Executive> tblExecutive) {
		this.tblExecutive = tblExecutive;
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
	public TextField getTxtDepartment() {
		return txtDepartment;
	}
	public void setTxtDepartment(TextField txtDepartment) {
		this.txtDepartment = txtDepartment;
	}
	public TextField getTxtBonus() {
		return txtBonus;
	}
	public void setTxtBonus(TextField txtBonus) {
		this.txtBonus = txtBonus;
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

}
