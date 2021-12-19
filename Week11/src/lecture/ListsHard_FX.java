package lecture;

import java.util.Iterator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListsHard_FX extends Application {

	private MyList<Voter> myArrayList = new MyArrayList<>();
	private MyList<Voter> myLinkedList = new MyLinkedList<>();

	private GenericStack<Voter> myStack = new GenericStack<>();
	private GenericQueue<Voter> myQueue = new GenericQueue<>();

	private MyPriorityQueue<Voter> myPriorityQueue = new MyPriorityQueue<>();

	public void start(Stage primaryStage) {
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("My Array List");
		TextArea textArea1 = new TextArea();
		textArea1.setWrapText(true);

		Button btArrayListInsert = new Button("Insert");
		Button btArrayListIterate = new Button("Iterate");
		Button btArrayListRemove = new Button("Remove");
		Button btArrayListFind = new Button("Find");

		HBox hBoxLists = new HBox(btArrayListInsert, btArrayListIterate, btArrayListRemove, btArrayListFind);
		hBoxLists.setSpacing(10);
		hBoxLists.setAlignment(Pos.CENTER);
		hBoxLists.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane1 = new BorderPane();
		borderPane1.setTop(hBoxLists);
		borderPane1.setCenter(textArea1);
		tab1.setContent(borderPane1);

		Tab tab2 = new Tab("Linked List");
		TextArea textArea2 = new TextArea();
		textArea2.setWrapText(true);
		Button btLinkedListInsert = new Button("Insert");
		Button btLinkedListIterate = new Button("Iterate");
		Button btLinkedListRemove = new Button("Remove");
		Button btLinkedListFind = new Button("Find");

		HBox hBoxSets = new HBox(btLinkedListInsert, btLinkedListIterate, btLinkedListRemove, btLinkedListFind);
		hBoxSets.setSpacing(10);
		hBoxSets.setAlignment(Pos.CENTER);
		hBoxSets.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane2 = new BorderPane();
		borderPane2.setTop(hBoxSets);
		borderPane2.setCenter(textArea2);
		tab2.setContent(borderPane2);

		Tab tab3 = new Tab("Stacks & Queues");
		TextArea textArea3 = new TextArea();
		textArea3.setWrapText(true);
		Button btStackMethods = new Button("Stack Methods");
		Button btStackPop = new Button("Pop First 100 from Stack");
		Button btQueueMethods = new Button("Queue Methods");
		Button btQueueDeQueue = new Button("DeQueue First 100 from Queue");

		HBox hBoxStacks = new HBox(btStackMethods, btStackPop, btQueueMethods, btQueueDeQueue);
		hBoxStacks.setSpacing(10);
		hBoxStacks.setAlignment(Pos.CENTER);
		hBoxStacks.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane3 = new BorderPane();
		borderPane3.setTop(hBoxStacks);
		borderPane3.setCenter(textArea3);
		tab3.setContent(borderPane3);

		Tab tab4 = new Tab("Priority Queues");
		TextArea textArea4 = new TextArea();
		textArea4.setWrapText(true);
		Button btPriorityQueuePop100 = new Button("Pop Top 100 from Priority Queue");
		HBox hBoxQueues = new HBox(btPriorityQueuePop100);
		hBoxQueues.setSpacing(10);
		hBoxQueues.setAlignment(Pos.CENTER);
		hBoxQueues.setPadding(new Insets(5, 5, 5, 5));
		BorderPane borderPane4 = new BorderPane();
		borderPane4.setTop(hBoxQueues);
		borderPane4.setCenter(textArea4);
		tab4.setContent(borderPane4);

		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

		Label lbRecords = new Label("Records To Load : ");
		ChoiceBox<Integer> cbRecordCount = new ChoiceBox<Integer>();
		cbRecordCount.getItems().addAll(40000, 80000, 160000, 320000);
		cbRecordCount.getSelectionModel().select(0);
		
		Button btLoadNamesAndStructures = new Button("Load Names to Lists");
		Button btCopyToStackQueuesAndPQueues = new Button("Copy to myQueue, myStack, myPriorityQueue");
		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");
		HBox hBoxBottom = new HBox(lbRecords,cbRecordCount,btLoadNamesAndStructures, btCopyToStackQueuesAndPQueues, btClearAnswers, btnExit);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(10, 5, 10, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(tabPane);
		borderPane.setBottom(hBoxBottom);

		btArrayListInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Insert Person at Position 0\n";
				Voter voter = new Voter(999999, "Smith", "Joe", "Bob", "Rogers Road", "Wake Forest", "NC", "33333",
						999);
				long startTime = System.currentTimeMillis();
				myArrayList.add(0, voter);
				output += "Time to Insert at position 0 in milliseconds : " + (System.currentTimeMillis() - startTime)
						+ "\n";
				output += myArrayList.get(0) + "\n";
				textArea1.setText(output);
			}
		});
		btArrayListIterate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int recordsToGet = cbRecordCount.getValue();
				String output = "Iterate thru the Array List (" + recordsToGet + ") \n";
				output += "Iterate By Index (Display first 10)\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < myArrayList.size(); i++) {
					Voter voter = myArrayList.get(i);
					if (i < 10)
						output += voter.getLastname() + "\t";
				}
				output += "\nIteration in milliseconds (index) : " + (System.currentTimeMillis() - startTime) + "\n\n";

				// Iterator defined in myArrayList class (required since MyList implements
				// Collection)
				Iterator<Voter> listItr = myArrayList.iterator();
				output += "Iterate By Iterator - Display first 10\n";
				startTime = System.currentTimeMillis();
				int max = 0;
				while (listItr.hasNext()) {
					Voter voter = listItr.next();
					if (max < 10) {
						output += voter.getLastname() + "\t";
						max++;
					}
				}
				output += "\nIteration in milliseconds (Iterator) : " + (System.currentTimeMillis() - startTime) + "\n";
				textArea1.setText(output);
			}
		});
		btArrayListRemove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int recordsToGet = cbRecordCount.getValue();
				final int ITEMS = (int) (recordsToGet * .01);
				String output = "Remove random " + ITEMS + " Items \n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < ITEMS; i++) {
					int position = (int) (Math.random() * 200000) + 1;
					if (position <= myArrayList.size()) {
						@SuppressWarnings("unused")
						Voter voter = myArrayList.remove(position);
					}
				}
				output += "\nRemove in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
				textArea1.setText(output);
			}
		});
		btArrayListFind.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Find in Array List\n\n";
				Voter voter = new Voter(202070, "MCGREGOR", "LEIGH", "MOSURE", "609 ASHBRITTLE DR", "ROLESVILLE", "NC",
						"27571", 69);
				long startTime = System.currentTimeMillis();
				int position = myArrayList.indexOf(voter);
				output += "Find in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
				if (position >= 0) {
					output += "Found at Location : " + position + "\n";
					output += myArrayList.get(position) + "\n";
				} else {
					output += "Not Found\n";
				}
				textArea1.setText(output);
			}
		});

		btLinkedListInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Insert Person at Position 0\n";
				Voter voter = new Voter(999999, "Smith", "Joe", "Bob", "Rogers Road", "Wake Forest", "NC", "33333",
						999);
				long startTime = System.currentTimeMillis();
				myLinkedList.add(2, voter);
				output += "Time to Insert at position 2 in milliseconds : " + (System.currentTimeMillis() - startTime)
						+ "\n";
				output += myLinkedList.get(2) + "\n";
				textArea2.setText(output);
			}
		});
		btLinkedListIterate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int recordsToGet = cbRecordCount.getValue();
				String output = "Iterate thru the Linked List (" + recordsToGet + ") \n";
//				output += "Iterate By Index (Display first 10)\n";
//				long startTime = System.currentTimeMillis();
//				for (int i = 0; i < myLinkedList.size(); i++) {
//					Voter voter = myLinkedList.get(i);
//					if (i < 10)
//						output += voter.getLastname() + "\t";
//				}
//				output += "\nIteration in milliseconds (index) : " + (System.currentTimeMillis() - startTime) + "\n\n";

				// Iterator defined in myLinkedList class (required since MyList implements
				// Collection)
				Iterator<Voter> listItr = myLinkedList.iterator();
				output += "Iterate By Iterator - Display first 10\n";
				long startTime = System.currentTimeMillis();
				int max = 0;
				while (listItr.hasNext()) {
					Voter voter = listItr.next();
					if (max < 10) {
						output += voter.getLastname() + "\t";
						max++;
					}
				}
				output += "\nIteration in milliseconds (Iterator) : " + (System.currentTimeMillis() - startTime) + "\n";
				textArea2.setText(output);
			}
		});
		btLinkedListRemove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int recordsToGet = cbRecordCount.getValue();
				final int ITEMS = (int) (recordsToGet * .01);
				String output = "Remove random " + ITEMS + " Items \n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < ITEMS; i++) {
					int position = (int) (Math.random() * 200000) + 1;
					if (position <= myLinkedList.size()) {
						@SuppressWarnings("unused")
						Voter voter = myLinkedList.remove(position);
					}
				}
				output += "\nRemove in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
				textArea2.setText(output);
			}
		});
		btLinkedListFind.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Find in Linked List \n\n";
				Voter voter = new Voter(202070, "MCGREGOR", "LEIGH", "MOSURE", "609 ASHBRITTLE DR", "ROLESVILLE", "NC",
						"27571", 69);
				long startTime = System.currentTimeMillis();
				int position = myLinkedList.indexOf(voter);
				output += "Find in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
				if (position >= 0) {
					output += "Found at Location : " + position + "\n";
					output += myArrayList.get(position) + "\n";
				} else {
					output += "Not Found\n";
				}
				textArea2.setText(output);
			}
		});
		btStackMethods.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Size of Stack " + myStack.getSize() + "\n";

				// Added a get method to GenericStack
				Voter voter1 = myStack.get(50000);
				output += "Voter at position 50000 " + voter1.getLastname()  + "\n";
				
				// Added a remove method to GenericStack
				Voter voter2 = myStack.remove(50000);
				output += "Voter at position 50000 removed" + voter2.getLastname()  +  "\n";
				output += "Size of Stack after remove " + myStack.getSize() + "\n";
				
				// Added a isEmpty method to GenericStack
				output += "Is Stack empty? " + myStack.isEmpty() + "\n";

				textArea3.setText(output);
			}
		});
		btStackPop.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Pop First 100 from Stack\n";
				output += "Size of Stack " + myStack.getSize() + "\n";
				for (int i = 0; i < 100; i++) {
					output += myStack.pop().getLastname() + "\t";
				}
				output += "\n\nSize of Stack " + myStack.getSize();
				textArea3.setText(output);
			}
		});
		btQueueMethods.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Queue Methods\n";
				output += "Size of Queue " + myQueue.getSize() + "\n";

				// Added a get method to GenericQueue
				Voter voter1 = myQueue.get(50000);
				output += "Voter at position 50000 " + voter1.getLastname()  + "\n";
				
				// Added a remove method to GenericQueue
				Voter voter2 = myQueue.remove(50000);
				output += "Voter at position 50000 " + voter2.getLastname()  + "\n";
				output += "Size of Queue after remove " + myQueue.getSize() + "\n";
				// Added a isEmpty method to GenericQueue
				output += "Is Stack empty? " + myQueue.isEmpty() + "\n";
				textArea3.setText(output);
			}
		});
		btQueueDeQueue.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "DeQueue First 100 from Queue\n";
				output += "Size of Queue " + myQueue.getSize() + "\n";
				for (int i = 0; i < 100; i++) {
					output += myQueue.dequeue().getLastname() + "\t";
				}
				output += "\n\nSize of Queue " + myQueue.getSize();
				textArea3.setText(output);
			}
		});
		btPriorityQueuePop100.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Remove first 100 voters from Priority Queue\n";
				output += "Size before remove " + myPriorityQueue.getSize() + "\n";
			    for (int i = 0; i < 100; i++)
			        output += myPriorityQueue.dequeue().getAge() + "\t";
				output += "\nSize after remove " + myPriorityQueue.getSize() + "\n";
				textArea4.setText(output);
			}
		});

		btLoadNamesAndStructures.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				long startTime = System.currentTimeMillis();
				myArrayList.clear();
				myLinkedList.clear();
				int recordsToGet = cbRecordCount.getValue();
				myArrayList = DbUtilitiesVoter.ReadFromDataBaseArrayList(recordsToGet);
				output += "Time to Load Array List milliseconds : " + (System.currentTimeMillis() - startTime) + "\n";
				startTime = System.currentTimeMillis();
				myLinkedList = DbUtilitiesVoter.ReadFromDataBaseLinkedList(recordsToGet);
				output += "Time to Load Linked List milliseconds : " + (System.currentTimeMillis() - startTime) + "\n";
				if (myArrayList.size() > 0) {
					output += "voters loaded myArrayList  - " + myArrayList.size() + "\n";
					output += "voters loaded myLinkedList - " + myLinkedList.size() + "\n";
				} else {
					output = "failure loading votes \n";
				}
				textArea1.setText(output);
			}
		});

		btCopyToStackQueuesAndPQueues.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				myStack.clear();
				myQueue.clear();
			    while (myPriorityQueue.getSize() > 0) {
			       myPriorityQueue.dequeue();
			    }
				long startTime = System.currentTimeMillis();
				// which is faster? myArrayList or myLinkedList?
				for (Voter voter : myLinkedList) {
					myStack.push(voter);
				}
				if (myStack.getSize() > 0) {
					output += "voters loaded myStack  - " + myStack.getSize() + "\n";
				} else {
					output = "failure loading myStack \n";
				}
				output += "Time to Load myStack in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n";

				startTime = System.currentTimeMillis();
				for (Voter voter : myLinkedList) {
					myQueue.enqueue(voter);
				}
				if (myQueue.getSize() > 0) {
					output += "Voters loaded myQueue  - " + myQueue.getSize() + "\n";
				} else {
					output = "failure loading myQueue \n";
				}
				output += "Time to Load myQueue in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n";

				startTime = System.currentTimeMillis();
				for (Voter voter : myLinkedList) {
					myPriorityQueue.enqueue(voter);
				}
				if (myPriorityQueue.getSize() > 0) {
					output += "Voters loaded myPriorityQueue  - " + myPriorityQueue.getSize() + "\n";
				} else {
					output = "failure loading myPriorityQueue \n";
				}
				output += "Time to Load myPriorityQueue in milliseconds : " + (System.currentTimeMillis() - startTime)
						+ "\n";

//				output += myStack.pop();
//				output += myQueue.dequeue();
//				output += myPriorityQueue.dequeue();
				textArea1.setText(output);
			}
		});

		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea1.setText("");
				textArea2.setText("");
				textArea3.setText("");
				textArea4.setText("");
			}
		});
		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 750, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setTitle("Generic Tab Program"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}