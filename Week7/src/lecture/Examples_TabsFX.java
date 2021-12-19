package lecture;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Examples_TabsFX extends Application {

	List<User> users = new LinkedList<>();
	Set<User> usersHashSet = new HashSet<>();
	Set<User> usersLinkedHashSet = new LinkedHashSet<>();
	Set<User> usersTreeSet = new TreeSet<>();

	Stack<String> stackOfCards = new Stack<>();
	Stack<User> stackOfUsers = new Stack<>();

	public void start(Stage primaryStage) {

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("Lists");
		TextArea textArea1 = new TextArea();
		textArea1.setWrapText(true);

		Button btAddUsers = new Button("Add Users");
		Button btListUsers = new Button("List Users with iterator");
		Button btListConcepts = new Button("List Concepts");
		Button btIteratorSpeed = new Button("Iterator Speed");

		HBox hBoxLists = new HBox(btListConcepts, btAddUsers, btListUsers, btIteratorSpeed);
		hBoxLists.setSpacing(10);
		hBoxLists.setAlignment(Pos.CENTER);
		hBoxLists.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane1 = new BorderPane();
		borderPane1.setTop(hBoxLists);
		borderPane1.setCenter(textArea1);
		tab1.setContent(borderPane1);

		Tab tab2 = new Tab("Sets");
		TextArea textArea2 = new TextArea();
		textArea2.setWrapText(true);
		Button btSetSimple = new Button("Set Concepts");
		Button btSetHash = new Button("Hash Set");
		Button btLinkedHashSet = new Button("Linked Hash Set");
		Button btTreeSet = new Button("Tree Set");

		HBox hBoxSets = new HBox(btSetSimple, btSetHash, btLinkedHashSet, btTreeSet);
		hBoxSets.setSpacing(10);
		hBoxSets.setAlignment(Pos.CENTER);
		hBoxSets.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane2 = new BorderPane();
		borderPane2.setTop(hBoxSets);
		borderPane2.setCenter(textArea2);
		tab2.setContent(borderPane2);

		Tab tab3 = new Tab("Stacks");
		TextArea textArea3 = new TextArea();
		textArea3.setWrapText(true);
		Button btSimpleStack = new Button("Simple Stack");
		Button btStackOfUsers = new Button("Stack of Users");

		HBox hBoxStacks = new HBox(btSimpleStack, btStackOfUsers);
		hBoxStacks.setSpacing(10);
		hBoxStacks.setAlignment(Pos.CENTER);
		hBoxStacks.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane3 = new BorderPane();
		borderPane3.setTop(hBoxStacks);
		borderPane3.setCenter(textArea3);
		tab3.setContent(borderPane3);

		Tab tab4 = new Tab("Queues");
		TextArea textArea4 = new TextArea();
		textArea4.setWrapText(true);
		Button btSimpleQueue = new Button("Simple Queue");
		Button btMoreQueue = new Button("More Queue Examples");
		Button btQueueOfUsers = new Button("Queue Of Users");
		HBox hBoxQueues = new HBox(btSimpleQueue, btMoreQueue, btQueueOfUsers);
		hBoxQueues.setSpacing(10);
		hBoxQueues.setAlignment(Pos.CENTER);
		hBoxQueues.setPadding(new Insets(5, 5, 5, 5));
		BorderPane borderPane4 = new BorderPane();
		borderPane4.setTop(hBoxQueues);
		borderPane4.setCenter(textArea4);
		tab4.setContent(borderPane4);

		Tab tab5 = new Tab("Priority Queues");
		TextArea textArea5 = new TextArea();
		textArea5.setWrapText(true);
		Button btSimplePQueues = new Button("Simple Priority Queue");
		Button btPQueueOfUsers = new Button("Priority Queue of Users");
		HBox hBoxPriorityQueues = new HBox(btSimplePQueues, btPQueueOfUsers);
		hBoxPriorityQueues.setSpacing(10);
		hBoxPriorityQueues.setAlignment(Pos.CENTER);
		hBoxPriorityQueues.setPadding(new Insets(5, 5, 5, 5));
		BorderPane borderPane5 = new BorderPane();
		borderPane5.setTop(hBoxPriorityQueues);

		borderPane5.setCenter(textArea5);
		tab5.setContent(borderPane5);

		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5);

		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");
		HBox hBoxBottom = new HBox(btClearAnswers, btnExit);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(10, 5, 10, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(tabPane);
		borderPane.setBottom(hBoxBottom);

		btAddUsers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Users added : ";
				users.add(new User("Michael", 20));
				users.add(new User("Nan", 25));
				users.add(new User("Anthony", 25));
				output += users.size() + "\n";
				for (User u : users) {
					output += "Name : " + u.getName() + ", Age : " + u.getAge() + "\n";
				}
				textArea1.setText(output);
			}
		});
		btListUsers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Added order : \n";
				Iterator<User> iterator = users.iterator();
				while (iterator.hasNext()) {
					output += iterator.next().getName() + "\n";
				}
				output += "--------------------------\n";

				output += "Reverse order : \n";

				for (ListIterator<User> it = users.listIterator(users.size()); it.hasPrevious();) {
					User u = it.previous();
					output += (u.getName() + "\n");
				}
				textArea1.setText(output);
			}
		});
		btListConcepts.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				try {
					Scanner input = new Scanner(new File("lists.txt"));
					while (input.hasNext()) {
						output += input.nextLine() + "\n";
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				textArea1.setText(output);
			}
		});
		btIteratorSpeed.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				LinkedList<Integer> list = new LinkedList<Integer>();
				for (int i = 0; i < 100000; i++)
					list.add(i);
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < list.size(); i++)
					list.get(i);
				long endTime = System.currentTimeMillis();
				output += "Travese time using index is " + (endTime - startTime) + "\n";

				startTime = System.currentTimeMillis();
				Iterator<Integer> listItr = list.iterator();
				while (listItr.hasNext()) {
					listItr.next();
				}
				endTime = System.currentTimeMillis();
				output += "Travese time using iterator is " + (endTime - startTime) + "\n";
				textArea1.setText(output);
			}
		});

		btSetSimple.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "A Set is a Collection that cannot contain duplicate elements.\n";
				output += "It models the mathematical set abstraction.\n\n";

				output += "When to use HashSet, LinkedHashSet and TreeSet in Java:\n";
				output += "1 HashSet: If you don’t want to maintain insertion order but want to store unique objects.\n";
				output += "2 LinkedHashSet: If you want to maintain the insertion order of elements then you can use LinkedHashSet.\n";
				output += "3 TreeSet: If you want to sort the elements according to some Comparator then use TreeSet.\n";

				Set<Integer> uniquesHashSet = new HashSet<>();
				Set<Integer> uniquesLinkedHashSet = new LinkedHashSet<>();
				Set<Integer> uniquesTreeSet = new TreeSet<>();

				for (int i = 0; i < 500000; i++) {
					int r = (int) (Math.random() * 50);
					uniquesHashSet.add(r);
					uniquesLinkedHashSet.add(r);
					uniquesTreeSet.add(r);
				}
				output += "Size of uniques Hash Set is : " + uniquesHashSet.size() + "\n";
				output += "Size of uniques Linked Hash Set is : " + uniquesLinkedHashSet.size() + "\n";
				output += "Size of uniques Tree Set is : " + uniquesTreeSet.size() + "\n";
				textArea2.setText(output);
			}
		});
		btSetHash.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";

				output += "A HashSet uses .hashCode() to determine the hash bucket in which to place "
						+ "a new entry; if there are already entries in this bucket, it uses .equals() "
						+ "on each entry of the bucket to see if the entry already exists.\n\n";

				output += "In this case, the User class must have a hashCode() and an equal() method to override "
						+ "the parent object method.\n\n";

				User user1 = new User("Michael-1", 20);
				User user2 = new User("Nan", 25);
				User user3 = new User("Anthony", 25);
				User user4 = new User("Anthony", 25);
				output += "Hash Code for Michael \t" + user1.hashCode() + "\n";
				output += "Hash Code for Nan \t" + user2.hashCode() + "\n";
				output += "Hash Code for Anthony \t" + user3.hashCode() + "\n";
				output += "Hash Code for Anthony \t" + user4.hashCode() + "\n";

				output += "Is Anthony equals to Anthony \t" + user3.equals(user4) + "\n";
				usersHashSet.add(user1);
				usersHashSet.add(user2);
				usersHashSet.add(user3);
				usersHashSet.add(user4);

				output += "Size of Hash Set " + usersHashSet.size() + "\n\n";

				output += "Order may or may not be maintained when using HashSet\n";
				Iterator<User> iterator = usersHashSet.iterator();
				while (iterator.hasNext()) {
					output += iterator.next().getName() + "\t";
				}

				textArea2.setText(output);
			}
		});
		btLinkedHashSet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Linked Hash Set - used when insertion order must be maintain \n\n";
				for (int i = 1; i <= 26; i++) {
					String name = String.valueOf((char) (i + 64));
					int age = (int) (Math.random() * 99);
					usersLinkedHashSet.add(new User(name, age));
				}
				output += "Insertion order will be maintained when using Linked Hash Set.\n"
						+ "To verify change Linked Hash set to HashSet.\n\n";
				Iterator<User> iterator = usersLinkedHashSet.iterator();
				while (iterator.hasNext()) {
					output += iterator.next().getName() + "  ";
				}
				textArea2.setText(output);
			}
		});
		btTreeSet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Tree Set - used when an special Iteration is needed \n\n";
				for (int i = 1; i <= 26; i++) {
					int age = (int) (Math.random() * 99);
					String name = String.valueOf((char) (i + 64));
					usersTreeSet.add(new User(name, age));
				}
				output += "The User class must implement a Comparable Interface and \n"
						+ "a compareTo method to determine insertion order.\n\n";
				Iterator<User> iterator = usersTreeSet.iterator();
				while (iterator.hasNext()) {
					output += iterator.next().getAge() + "  ";
				}
				textArea2.setText(output);
			}
		});
		btSimpleStack.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "A stack is a collection that is based on the last-in-first-out (LIFO) policy.\n";
				output += "We name the stack insert method push() and the stack remove operation pop().\n\n";

				output += "Pushed Jack, Queen, King and Ace \n\n";
				stackOfCards.push("Jack");
				stackOfCards.push("Queen");
				stackOfCards.push("King");
				stackOfCards.push("Ace");

				output += "Size of Stack " + stackOfCards.size() + "\n";

				int position = stackOfCards.search("Queen");
				output += "Queen found at position " + position + "\n";

				String cardAtTop = stackOfCards.pop();
				output += "Element popped off  " + cardAtTop + "\n";

				cardAtTop = stackOfCards.pop();
				output += "Element popped off  " + cardAtTop + "\n";
				output += "Size of Stack " + stackOfCards.size() + "\n";
				cardAtTop = stackOfCards.peek();
				output += "Element peeked at  " + cardAtTop + "\n";
				output += "Size of Stack " + stackOfCards.size() + "\n";
				textArea3.setText(output);
			}
		});
		btStackOfUsers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				for (int i = 1; i <= 26; i++) {
					int age = (int) (Math.random() * 99);
					String name = String.valueOf((char) (i + 64));
					stackOfUsers.add(new User(name, age));
				}
				textArea3.appendText("Size of User Stack " + stackOfUsers.size() + "\n");
				for (int i = 1; i <= 26; i++) {
					User topOne = stackOfUsers.pop();
					output += "User popped off  " + topOne + "\n";
				}
				textArea3.setText(output);
			}
		});
		btSimpleQueue.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea4.setText("Queues typically, but not necessarily, order elements in a FIFO"
						+ "(first-in-first-out) manner.\n\n");
				int time = 10;
				Queue<Integer> queue = new LinkedList<Integer>();

				for (int i = time; i >= 0; i--)
					queue.add(i);

				while (!queue.isEmpty()) {
					// System.out.println(queue.remove());
					textArea4.appendText(queue.remove() + "\n");
					try {
						Thread.sleep(100);
					} catch (InterruptedException ee) {
						ee.printStackTrace();
					}
				}
				textArea4.appendText("Program Complete\n");
			}
		});
		btMoreQueue.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "More Examples\n\n";
				Queue<String> waitingQueue = new LinkedList<>();

				// Adding new elements to the Queue (The Enqueue operation)
				output += "Adding Billy, Chris, John, Mark and Steven to the Queue \n";
				waitingQueue.add("Billy");
				waitingQueue.add("Chris");
				waitingQueue.add("John");
				waitingQueue.add("Mark");
				waitingQueue.add("Steven");

				output += "WaitingQueue : " + waitingQueue + "\n";

				output += "Removing an element from the Queue using remove() (The Dequeue operation)\n";
				output += "The remove() method throws NoSuchElementException if the Queue is empty\n";
				String name = waitingQueue.remove();
				output += "Removed from WaitingQueue : " + name + " | New WaitingQueue : " + waitingQueue + "\n";

				// Removing an element from the Queue using poll()
				// The poll() method is similar to remove() except that it returns null if the
				// Queue is empty.
				name = waitingQueue.poll();
				output += ("Removed from WaitingQueue : " + name + " | New WaitingQueue : " + waitingQueue);

				// Check is a Queue is empty
				output += "\n\nis waitingQueue empty? : " + waitingQueue.isEmpty() + "\n";

				// Find the size of the Queue
				output += "\nSize of waitingQueue : " + waitingQueue.size() + "\n";

				// Check if the Queue contains an element
				name = "Johnny";
				if (waitingQueue.contains(name)) {
					output += "WaitingQueue contains " + name + "\n";
				} else {
					output += "Waiting Queue doesn't contain " + name + "\n";
				}

				// Get the element at the front of the Queue without removing it using element()
				// The element() method throws NoSuchElementException if the Queue is empty
				String firstPersonInTheWaitingQueue = waitingQueue.element();
				output += "\nFirst Person in the Waiting Queue (element()) : " + firstPersonInTheWaitingQueue;

				// Get the element at the front of the Queue without removing it using peek()
				// The peek() method is similar to element() except that it returns null if the
				// Queue is empty
				firstPersonInTheWaitingQueue = waitingQueue.peek();
				output += ("\nFirst Person in the Waiting Queue : " + firstPersonInTheWaitingQueue + "\n");

				output += "\n=== Iterating over a Queue using iterator() ===";
				Iterator<String> waitingQueueIterator = waitingQueue.iterator();
				while (waitingQueueIterator.hasNext()) {
					String name2 = waitingQueueIterator.next();
					output += name2 + "\n";
				}
				output += "\n=== Iterating over a Queue using simple for-each loop ===";
				for (String name2 : waitingQueue) {
					output += name2 + "\n";
				}

				textArea4.setText(output);
			}
		});
		btQueueOfUsers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				String output = "Queue of Users";

				Queue<User> queueOfUsers = new LinkedList<>();
				User user1 = new User("Barbara", 20);
				User user2 = new User("Nan", 25);
				User user3 = new User("Anthony", 28);
				User user4 = new User("George", 22);

				queueOfUsers.add(user1);
				queueOfUsers.add(user2);
				queueOfUsers.add(user3);
				queueOfUsers.add(user4);

				output += "Queue of Users      : " + queueOfUsers + "\n";
				output += "Size of Users Queue : " + queueOfUsers.size() + "\n";
				while (!queueOfUsers.isEmpty()) {
					output += "Removing - " + queueOfUsers.poll() + "\n";
				}

				output += "Size of Users Queue : " + queueOfUsers.size() + "\n";

				textArea4.setText(output);
			}
		});
		btSimplePQueues.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Simple Priority Queue\n";

				output += "A priority queue of strings uses the strings natural order "
						+ "to maintain order in the queue.  " + "Strings are place in alphabetical order\n";
				PriorityQueue<String> namePriorityQueue = new PriorityQueue<>();

				// Add items to a Priority Queue (ENQUEUE)
				namePriorityQueue.add("Lisa");
				namePriorityQueue.add("Robert");
				namePriorityQueue.add("John");
				namePriorityQueue.add("Chris");
				namePriorityQueue.add("Angelina");
				namePriorityQueue.add("Joe");

				// Remove items from the Priority Queue (DEQUEUE)
				while (!namePriorityQueue.isEmpty()) {
					output += namePriorityQueue.remove() + "\n";
				}

				PriorityQueue<Double> numberPriorityQueue = new PriorityQueue<>();

				// Add items to a Priority Queue (ENQUEUE)
				numberPriorityQueue.add(Math.random() * 100);
				numberPriorityQueue.add(Math.random() * 100);
				numberPriorityQueue.add(Math.random() * 100);
				numberPriorityQueue.add(Math.random() * 100);
				numberPriorityQueue.add(Math.random() * 100);
				numberPriorityQueue.add(Math.random() * 100);

				// Remove items from the Priority Queue (DEQUEUE)
				while (!numberPriorityQueue.isEmpty()) {
					output += numberPriorityQueue.remove() + "\n";
				}

				textArea5.setText(output);
			}
		});
		btPQueueOfUsers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Priorty Queue of Users\n";
				output += "The requirement for a PriorityQueue of user defined objects is that \n";
				output += "\t1. Either the class should implement the Comparable interface and \n";
				output += "\tprovide the implementation for the compareTo() function\n";
				output += "\t2. Or you should provide a custom Comparator while creating \n";
				output += "\tthe PriorityQueue.\n\n\n";

				// Create a PriorityQueue
				PriorityQueue<User> userPriorityQueue = new PriorityQueue<>();
				userPriorityQueue.add(new User("Johnny", 25));
				userPriorityQueue.add(new User("Randy", 34));
				userPriorityQueue.add(new User("Billy Bob", 30));
				userPriorityQueue.add(new User("Bryan", 19));
				/*
				 * The compareTo() method implemented in the User class is used to determine in
				 * what order the objects should be dequeued.
				 */
				while (!userPriorityQueue.isEmpty()) {
					output += userPriorityQueue.remove() + "\n";
				}
				textArea5.setText(output);
			}
		});

		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea1.setText("");
				textArea2.setText("");
				textArea3.setText("");
				textArea4.setText("");
				textArea5.setText("");
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
		primaryStage.setTitle("Lists, Sets, Stacks, Queues, Priority Queues"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}