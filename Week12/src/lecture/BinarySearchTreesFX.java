package lecture;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BinarySearchTreesFX extends Application {

	private Tree<Voter> voterBST = new BST<>();
	private Tree<String> stringBST = new BST<>();
	private Tree<Integer> integerBST = new BST<>();

	private BST<String> stringBSTNon = new BST<>();
	private BST<Integer> integerBSTNon = new BST<>();

	private BST<Integer> tree = new BST<>();

	public void start(Stage primaryStage) {
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("Strings & Integers");
		TextArea textArea1 = new TextArea();
		textArea1.setWrapText(true);
		Button btBSTStrings = new Button("Strings");
		Button btBSTIntegers = new Button("Integers");
		Button btBSTInOrder = new Button("In Order");
		Button btBSTPreOrder = new Button("Pre Order");
		Button btBSTPostOrder = new Button("Post Order");
		HBox hBox1 = new HBox(btBSTStrings, btBSTIntegers, btBSTInOrder, btBSTPreOrder, btBSTPostOrder);
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		hBox1.setPadding(new Insets(5, 5, 5, 5));

		Tab tab2 = new Tab("Animation Tree");

		BorderPane treePane = new BorderPane();
		BSTView view = new BSTView(tree);
		treePane.setCenter(view);
		
		TextField tfKey = new TextField();
		tfKey.setPrefColumnCount(3);
		tfKey.setAlignment(Pos.BASELINE_RIGHT);
		Button btInsertNode = new Button("Insert");
		Button btDeleteNode = new Button("Delete");

		HBox hBox2 = new HBox();
		hBox2.setSpacing(10);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.setPadding(new Insets(5, 5, 5, 5));
		hBox2.getChildren().addAll(new Label("Enter a key: "), tfKey, btInsertNode, btDeleteNode);

		Tab tab3 = new Tab("BST Voters");
		TextArea textArea3 = new TextArea();
		textArea3.setWrapText(true);
		Label lbRecords = new Label("Records To Load : ");
		ChoiceBox<Integer> cbRecordCount = new ChoiceBox<Integer>();
		cbRecordCount.getItems().addAll(10000, 40000, 80000, 160000, 320000);
		cbRecordCount.getSelectionModel().select(0);
		Button btLoadVoters = new Button("Load Voters");

		Button btInsertVoter = new Button("Insert Voter");
		Button btRemoveVoters = new Button("Remove Voters");
		Button btSearchForVoters = new Button("Search Voters");

		HBox hBox3 = new HBox(lbRecords, cbRecordCount, btLoadVoters, btInsertVoter, btRemoveVoters, btSearchForVoters);
		hBox3.setSpacing(10);
		hBox3.setAlignment(Pos.CENTER);
		hBox3.setPadding(new Insets(5, 5, 5, 5));

		Tab tab4 = new Tab("Non Recursive");
		TextArea textArea4 = new TextArea();
		textArea4.setWrapText(true);
		Button btBSTStringsIntegersNon = new Button("Load Strings & Integers");
		Button btBSTInOrderNon = new Button("In Order Non Recursive");
		Button btBSTPreOrderNon = new Button("Pre Order Non Recursive");
		Button btBSTPostOrderNon = new Button("Post Order Non Recursive");

		HBox hBox4 = new HBox(btBSTStringsIntegersNon, btBSTInOrderNon, btBSTPreOrderNon, btBSTPostOrderNon);
		hBox4.setSpacing(10);
		hBox4.setAlignment(Pos.CENTER);
		hBox4.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane1 = new BorderPane();
		borderPane1.setTop(hBox1);
		borderPane1.setCenter(textArea1);
		tab1.setContent(borderPane1);

		BorderPane borderPane2 = new BorderPane();
		borderPane2.setTop(hBox2);
		borderPane2.setCenter(treePane);
		tab2.setContent(borderPane2);

		BorderPane borderPane3 = new BorderPane();
		borderPane3.setTop(hBox3);
		borderPane3.setCenter(textArea3);
		tab3.setContent(borderPane3);

		BorderPane borderPane4 = new BorderPane();
		borderPane4.setTop(hBox4);
		borderPane4.setCenter(textArea4);
		tab4.setContent(borderPane4);

		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");
		HBox hBoxBottom = new HBox(btClearAnswers, btnExit);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(10, 5, 10, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(tabPane);
		borderPane.setBottom(hBoxBottom);

		btBSTStrings.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				stringBST.insert("Daniel");
				stringBST.insert("Adam");
				stringBST.insert("Michael");
				stringBST.insert("Tom");
				stringBST.insert("Jones");
				stringBST.insert("Peter");

				output += "Insert Order - Daniel, Adam, Michael, Tom, Jones, Peter\n";
				output += "Using an iterator\n";
				for (String s : stringBST)
					output += s.toUpperCase() + "\t";
				textArea1.setText(output);
			}
		});
		btBSTIntegers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				integerBST.insert(10);
				integerBST.insert(5);
				integerBST.insert(15);
				integerBST.insert(12);
				integerBST.insert(20);
				integerBST.insert(18);
				output += "Insert Order - 10, 5, 15, 12, 20, 18\n";
				output += "Using an iterator\n";
				for (Integer s : integerBST)
					output += s + "\t";
				textArea1.setText(output);
			}
		});
		btBSTInOrder.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "Depth First Traversals:\n";
				output += "\tInorder (Left, Root, Right)\n\n";
				output += "The number of nodes is (String) " + stringBST.getSize() + "\n";
				long startTime = System.currentTimeMillis();
				System.out.println("In Order Strings");
				stringBST.inorder();
				System.out.println();
				output += "Inorder in milliseconds : (Strings) " + (System.currentTimeMillis() - startTime) + "\n\n";

				output += "The number of nodes is (Integer) " + integerBST.getSize() + "\n";
				startTime = System.currentTimeMillis();
				System.out.println("In Order Integers");
				integerBST.inorder();
				System.out.println();
				output += "Inorder in milliseconds : (Integer) " + (System.currentTimeMillis() - startTime) + "\n\n";
				textArea1.setText(output);
			}
		});
		btBSTPreOrder.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "Depth First Traversals:\n";
				output += "\tPreorder (Root, Left, Right)\n\n";
				output += "The number of nodes is (String) " + stringBST.getSize() + "\n";
				long startTime = System.currentTimeMillis();
				System.out.println("Pre Order Strings");
				stringBST.preorder();
				System.out.println();
				output += "Pre order in milliseconds : (Strings) " + (System.currentTimeMillis() - startTime) + "\n\n";

				output += "The number of nodes is (Integer) " + integerBST.getSize() + "\n";
				startTime = System.currentTimeMillis();
				System.out.println("Pre Order Integers");
				integerBST.preorder();
				System.out.println();
				output += "Preorder in milliseconds : (Integer) " + (System.currentTimeMillis() - startTime) + "\n\n";
				textArea1.setText(output);
			}
		});
		btBSTPostOrder.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "Depth First Traversals:\n";
				output += "\tPostorder (Left, Right, Root)\n\n";
				output += "The number of nodes is (String) " + stringBST.getSize() + "\n";
				long startTime = System.currentTimeMillis();
				System.out.println("Post Order Strings");
				stringBST.postorder();
				System.out.println();
				output += "Post order in milliseconds : (Strings) " + (System.currentTimeMillis() - startTime) + "\n\n";

				output += "The number of nodes is (Integer) " + integerBST.getSize() + "\n";
				startTime = System.currentTimeMillis();
				System.out.println("Post Order Integers");
				integerBST.postorder();
				System.out.println();
				output += "Post order in milliseconds : (Integer) " + (System.currentTimeMillis() - startTime) + "\n\n";
				textArea1.setText(output);
			}
		});

		btInsertNode.setOnAction(e -> {
			int key = Integer.parseInt(tfKey.getText());
			if (tree.search(key)) { // key is in the tree already
				view.displayTree();
				view.setStatus(key + " is already in the tree");
			} else {
				tree.insert(key); // Insert a new key
				view.displayTree();
				view.setStatus(key + " is inserted in the tree");
			}
		});

		btDeleteNode.setOnAction(e -> {
			int key = Integer.parseInt(tfKey.getText());
			if (!tree.search(key)) { // key is not in the tree
				view.displayTree();
				view.setStatus(key + " is not in the tree");
			} else {
				tree.delete(key); // Delete a key
				view.displayTree();
				view.setStatus(key + " is deleted from the tree");
			}
		});

		btLoadVoters.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				long startTime = System.currentTimeMillis();
				voterBST.clear();
				int recordsToGet = cbRecordCount.getValue();
				voterBST = DbUtilitiesVoter.ReadFromDataBase(recordsToGet);
				output += "Time to Load Binary Search Tree (milliseconds) : " + (System.currentTimeMillis() - startTime)
						+ "\n";
				if (voterBST.size() > 0) {
					output += "voters loaded voterBST  - " + voterBST.size() + "\n";
				} else {
					output = "failure loading votes \n";
				}
				textArea3.setText(output);
			}
		});
		btInsertVoter.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Insert Voter at Position 0\n";
				Voter voter = new Voter(999999, "Smith", "Joe", "Bob", "Rogers Road", "Wake Forest", "NC", "33333",
						999);
				long startTime = System.currentTimeMillis();
				voterBST.add(voter);
				output += "Time to Insert at position 0 in milliseconds : " + (System.currentTimeMillis() - startTime)
						+ "\n";
				output += "Inserted Voter found? " + voterBST.search(voter) + "\n";
				textArea3.setText(output);
			}
		});
		btRemoveVoters.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Remove 10 Voters\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 10; i++) {
					int size = voterBST.getSize();
					int position = (int) (Math.random() * size);
					Voter voter = new Voter(position, "", "", "", "", "", "", "", 0);
					boolean success = voterBST.delete(voter);
					if (success) {
						output += "Deleted id " + position + "\n";
					} else {
						output += "Not Found\n";
					}
				}
				output += "\nRemove in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
				textArea3.setText(output);
			}
		});
		btSearchForVoters.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Search in voterBST for 10 Random Records\n\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 10; i++) {
					int size = voterBST.getSize();
					int position = (int) (Math.random() * size);
					Voter voter = new Voter(position, "", "", "", "", "", "", "", 0);
					boolean voterResult = voterBST.search(voter);
					if (voterResult) {
						output += "Found id " + position + "\t";
					} else {
						output += "Not Found\n";
					}
				}
				output += "\nRemove in milliseconds : " + (System.currentTimeMillis() - startTime) + "\n\n";
				textArea3.setText(output);
			}
		});

		btBSTStringsIntegersNon.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				stringBSTNon.insert("Daniel");
				stringBSTNon.insert("Adam");
				stringBSTNon.insert("Michael");
				stringBSTNon.insert("Tom");
				stringBSTNon.insert("Jones");
				stringBSTNon.insert("Peter");
				output += "Insert Order - Daniel, Adam, Michael, Tom, Jones, Peter\n";
				output += "Using an iterator\n";
				for (String s : stringBSTNon)
					output += s.toUpperCase() + "\t";
				output += "\n";
				integerBSTNon.insert(10);
				integerBSTNon.insert(5);
				integerBSTNon.insert(15);
				integerBSTNon.insert(12);
				integerBSTNon.insert(20);
				integerBSTNon.insert(18);
				output += "Insert Order - 10, 5, 15, 12, 20, 18\n";
				output += "Using an iterator\n";
				for (Integer s : integerBSTNon)
					output += s + "\t";
				output += "\n";
				textArea4.setText(output);
			}
		});

		btBSTInOrderNon.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "Using an iterator\n";
				for (String s : stringBSTNon)
					output += s.toUpperCase() + "\t";
				output += "\n";
				output += "Using an iterator\n";
				for (Integer s : integerBSTNon)
					output += s + "\t";
				output += "\n\n";

				output += "In Order (Non Recursive Strings) \n";
				output += stringBSTNon.nonRecursiveInorder() + "\n";
				output += "In Order (Non Recursive Integers) \n";
				output += integerBSTNon.nonRecursiveInorder() + "\n";
				textArea4.setText(output);
			}
		});
		btBSTPreOrderNon.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "Using an iterator\n";
				for (String s : stringBSTNon)
					output += s.toUpperCase() + "\t";
				output += "\n";
				output += "Using an iterator\n";
				for (Integer s : integerBSTNon)
					output += s + "\t";
				output += "\n\n";

				output += "Pre Order (Non Recursive Strings) \n";
				output += stringBSTNon.nonRecursivePreorder();
				output += "Pre Order (Non Recursive Integers) \n";
				output += integerBSTNon.nonRecursivePreorder();
				textArea4.setText(output);
			}
		});
		btBSTPostOrderNon.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "Using an iterator\n";
				for (String s : stringBSTNon)
					output += s.toUpperCase() + "\t";
				output += "\n";
				output += "Using an iterator\n";
				for (Integer s : integerBSTNon)
					output += s + "\t";
				output += "\n\n";

				output += "Post Order (Non Recursive Strings) \n";
				output += stringBSTNon.nonRecursivePostorder();
				output += "Post Order (Non Recursive Integers) \n";
				output += integerBSTNon.nonRecursivePostorder();
				textArea4.setText(output);
			}
		});
		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea1.setText("");
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
		primaryStage.setTitle("Binary Search Trees"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}