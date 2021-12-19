package project_start.hr;

import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project_start.collection.DSCSalary;
import project_start.collection.DataSetGeneric;
import project_start.hr.mgmt.Executive;
import project_start.hr.mgmt.ExecutiveDAO;
import project_start.hr.mgmt.ExecutiveFX;
import project_start.hr.mgmt.Manager;
import project_start.hr.mgmt.ManagerDAO;
import project_start.hr.mgmt.ManagerFX;
import project_start.hr.person.Employee;
import project_start.hr.person.EmployeeDAO;
import project_start.hr.person.EmployeeFX;




public class PayrollFX extends Application {
	private DataSetGeneric<Employee> dsg = new DataSetGeneric<>();

	private EmployeeFX employeeFX;
	private ManagerFX managerFX;
	private ExecutiveFX executiveFX;


	public void start(Stage primaryStage) {

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("Employee");
		employeeFX = new EmployeeFX();
		tab1.setContent(employeeFX);

		Tab tab2 = new Tab("Manager");
		managerFX = new ManagerFX();
		tab2.setContent(managerFX);
		
		Tab tab3 = new Tab("Executive");
		executiveFX = new ExecutiveFX();
		tab3.setContent(executiveFX);


		Tab tab4 = new Tab("Listings");
		TextArea textArea4 = new TextArea();

		Button btListAll = new Button("List All");
		btListAll.getStyleClass().add("grid-buttons");
		Button btListRegularEmployees = new Button("Employees");
		btListRegularEmployees.getStyleClass().add("grid-buttons");
		Button btListManagers = new Button("Managers");
		btListManagers.getStyleClass().add("grid-buttons");
		Button btListExecutives = new Button("Executives");
		btListExecutives.getStyleClass().add("grid-buttons");
		Button btHighestSalary = new Button("Highest Salary");
		btHighestSalary.getStyleClass().add("grid-buttons");
		Button btGeneratePayroll = new Button("Generate Payroll");
		btGeneratePayroll.getStyleClass().add("grid-buttons");
		Button btListByName = new Button("List/Name");
		btListByName.getStyleClass().add("grid-buttons");
		Button btListBySalary = new Button("List/Salary");
		btListBySalary.getStyleClass().add("grid-buttons");

		GridPane gp = new GridPane();
		gp.add(btListAll, 0, 0);
		gp.add(btListRegularEmployees, 1, 0);
		gp.add(btListManagers, 2, 0);
		gp.add(btListExecutives, 3, 0);
		gp.add(btHighestSalary, 0, 1);
		gp.add(btGeneratePayroll, 1, 1);
		gp.add(btListByName, 2, 1);
		gp.add(btListBySalary, 3, 1);

		gp.getStyleClass().add("grid");

		BorderPane borderPane4 = new BorderPane();
		borderPane4.setTop(gp);
		borderPane4.setCenter(textArea4);

		Button btLoadDataSet = new Button("Load Data Set");
		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");
		HBox hBoxBottom = new HBox(btLoadDataSet, btClearAnswers, btnExit);

		hBoxBottom.getStyleClass().add("hboxGreen");
		borderPane4.setBottom(hBoxBottom);
		tab4.setContent(borderPane4);

		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(tabPane);

		btListAll.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Everybody\n\n";
				for(Employee emp : new EmployeeDAO().findAll()) {
					output += emp + "\n";
				}
				
				for(Manager mng : new ManagerDAO().findAll()) {
					output += mng + "\n";
				}
				
				for(Executive ex : new ExecutiveDAO().findAll()) {
					output += ex + "\n";
				}
				textArea4.setText(output);
			}
		});
		btListRegularEmployees.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Regular Employees\n\n";
				for(Employee emp : new EmployeeDAO().findAll()) {
					output += emp + "\n";
				}
				textArea4.setText(output);
			}
		});
		btListManagers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Managers\n\n";
				for(Manager mng : new ManagerDAO().findAll()) {
					output += mng + "\n";
				}
				textArea4.setText(output);
			}
		});
		btListExecutives.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Executives\n\n";
				for(Executive ex : new ExecutiveDAO().findAll()) {
					output += ex + "\n";
				}
				textArea4.setText(output);
			}
		});
		btHighestSalary.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Highest Salary\n\n";
				dsg.clear();
				dsg.addAll(new EmployeeDAO().findAll());
				dsg.addAll(new ManagerDAO().findAll());
				dsg.addAll(new ExecutiveDAO().findAll());
				
				output += dsg.getMax().toString();
				
				textArea4.setText(output);
			}
		});
		btGeneratePayroll.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Generate Payroll\n\n";
				dsg.clear();
				dsg.addAll(new EmployeeDAO().findAll());
				dsg.addAll(new ManagerDAO().findAll());
				dsg.addAll(new ExecutiveDAO().findAll());
				
				for(Employee em : dsg) {
					output += em + ": Pay - " + em.getMeasure() + " \n";
				}
				
				textArea4.setText(output);
			}
		});
		btListByName.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "List by Name\n\n";
				dsg.clear();
				dsg.addAll(new EmployeeDAO().findAll());
				dsg.addAll(new ManagerDAO().findAll());
				dsg.addAll(new ExecutiveDAO().findAll());
				
				dsg.sortBy(new Comparator<Employee>() {
					@Override
					public int compare(Employee o1, Employee o2) {
						return (o1.getEmployeeName().compareTo(o2.getEmployeeName()));
					}
					
				});
				
				for(Employee em : dsg) {
					output += em + "\n";
				}
				textArea4.setText(output);
			}
		});
		btListBySalary.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "List by Salary\n\n";
				textArea4.setText(output);
				dsg.clear();
				dsg.addAll(new EmployeeDAO().findAll());
				dsg.addAll(new ManagerDAO().findAll());
				dsg.addAll(new ExecutiveDAO().findAll());
				
				dsg.sortBy(new DSCSalary<>());
				for(Employee em : dsg) {
					output += em + "\n";
				}
				
				textArea4.setText(output);
			}
		});

		btLoadDataSet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Data Set Creation\n\n";
				dsg.clear();

				EmployeeDAO employeeDAO = new EmployeeDAO();
				List<Employee> employees = employeeDAO.findAll();
				for (Employee employee : employees) {
					dsg.add(employee);
				}


				output += "Size of DataSetGeneric " + dsg.size();
				textArea4.setText(output);
			}
		});

		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea4.setText("");
			}
		});

		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 800, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setTitle("Human Resources - CSC251"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}