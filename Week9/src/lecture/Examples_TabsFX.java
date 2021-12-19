package lecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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

public class Examples_TabsFX extends Application {

	public void start(Stage primaryStage) {

		PersonDataSetWithKey personDataSet = new PersonDataSetWithKey();

		Map<Integer, PersonWithKey> personDataSetHashMap = new HashMap<>();
		Map<Integer, PersonWithKey> personDataSetTreeMap = new TreeMap<>();

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("Sets (Review)");
		TextArea textArea1 = new TextArea();
		textArea1.setWrapText(true);

		Button btSetSimple = new Button("Set Concepts");
		Button btSetHash = new Button("Hash Set");
		Button btLinkedHashSet = new Button("Linked Hash Set");
		Button btTreeSet = new Button("Tree Set");
		Button btSetListPerformanceTest = new Button("Performance Test");

		Label nameLabel = new Label("Array Size: ");
		ChoiceBox<String> intSize = new ChoiceBox<String>();
		intSize.getItems().addAll("5000", "25000", "50000");
		intSize.getSelectionModel().select(0);

		HBox hBoxLists = new HBox(btSetSimple, btSetHash, btLinkedHashSet, btTreeSet, btSetListPerformanceTest,
				nameLabel, intSize);
		hBoxLists.setSpacing(10);
		hBoxLists.setAlignment(Pos.CENTER);
		hBoxLists.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane1 = new BorderPane();
		borderPane1.setTop(hBoxLists);
		borderPane1.setCenter(textArea1);
		tab1.setContent(borderPane1);

		Tab tab2 = new Tab("Tree Sets Examples");
		TextArea textArea2 = new TextArea();
		textArea2.setWrapText(true);
		Button btTestTreeSet = new Button("Tree Sets with Strings");
//		Button btTab2Button2 = new Button("Tab2 - Button 2");
//		Button btTab2Button3 = new Button("Tab2 - Button 3");
//		Button btTab2Button4 = new Button("Tab2 - Button 4");

		HBox hBoxSets = new HBox(btTestTreeSet);
		hBoxSets.setSpacing(10);
		hBoxSets.setAlignment(Pos.CENTER);
		hBoxSets.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane2 = new BorderPane();
		borderPane2.setTop(hBoxSets);
		borderPane2.setCenter(textArea2);
		tab2.setContent(borderPane2);

		Tab tab3 = new Tab("Maps");
		TextArea textArea3 = new TextArea();
		textArea3.setWrapText(true);
		Button btHashMapSimple = new Button("Hash Maps");
		Button btTreeMapSimple = new Button("Tree Maps");

		HBox hBoxStacks = new HBox(btHashMapSimple, btTreeMapSimple);
		hBoxStacks.setSpacing(10);
		hBoxStacks.setAlignment(Pos.CENTER);
		hBoxStacks.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane3 = new BorderPane();
		borderPane3.setTop(hBoxStacks);
		borderPane3.setCenter(textArea3);
		tab3.setContent(borderPane3);

		Tab tab4 = new Tab("Speed Test");
		TextArea textArea4 = new TextArea();
		textArea4.setWrapText(true);
		Button btLoadFakeNames = new Button("Load the Fake Names(10000)");
		Button btFakeNameToMaps = new Button("Load Names to Maps");
		Button btTestHashMap = new Button("Test Hash Map");
		Button btTestTreeMap = new Button("Test Tree Map");
		HBox hBoxQueues = new HBox(btLoadFakeNames, btFakeNameToMaps, btTestHashMap, btTestTreeMap);
		hBoxQueues.setSpacing(10);
		hBoxQueues.setAlignment(Pos.CENTER);
		hBoxQueues.setPadding(new Insets(5, 5, 5, 5));
		BorderPane borderPane4 = new BorderPane();
		borderPane4.setTop(hBoxQueues);
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
				textArea1.setText(output);
			}
		});
		btSetHash.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Set<User> usersHashSet = new HashSet<>();

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

				textArea1.setText(output);
			}
		});
		btLinkedHashSet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Set<User> usersLinkedHashSet = new LinkedHashSet<>();
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
				textArea1.setText(output);
			}
		});
		btTreeSet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Set<User> usersTreeSet = new TreeSet<>();
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
				textArea1.setText(output);
			}
		});
		btSetListPerformanceTest.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				List<Integer> list = new ArrayList<>();
				int size = Integer.parseInt(intSize.getValue());
				list = StaticMethods.createList(size);
				Collections.shuffle(list); // Shuffle the array list

				// Create a hash set, and test its performance
				Collection<Integer> set1 = new HashSet<>(list);
				output += "Member test time for hash set is " + StaticMethods.getTestTime(set1) + " milliseconds\n";
				output += "Remove element time for hash set is " + StaticMethods.getRemoveTime(set1)
						+ " milliseconds\n\n";

				// Create a linked hash set, and test its performance
				Collection<Integer> set2 = new LinkedHashSet<>(list);
				output += "Member test time for linked hash set is " + StaticMethods.getTestTime(set2)
						+ " milliseconds\n";
				output += "Remove element time for linked hash set is " + StaticMethods.getRemoveTime(set2)
						+ " milliseconds\n\n";

				// Create a tree set, and test its performance
				Collection<Integer> set3 = new TreeSet<>(list);
				output += "Member test time for tree set is " + StaticMethods.getTestTime(set3) + " milliseconds\n";
				output += "Remove element time for tree set is " + StaticMethods.getRemoveTime(set3)
						+ " milliseconds\n\n";

				// Create an array list, and test its performance
				Collection<Integer> list1 = new ArrayList<>(list);
				output += "Member test time for array list is " + StaticMethods.getTestTime(list1) + " milliseconds\n";
				output += "Remove element time for array list is " + StaticMethods.getRemoveTime(list1)
						+ " milliseconds\n\n";

				// Create a linked list, and test its performance
				Collection<Integer> list2 = new LinkedList<>(list);
				output += "Member test time for linked list is " + StaticMethods.getTestTime(list2) + " milliseconds\n";
				output += "Remove element time for linked list is " + StaticMethods.getRemoveTime(list2)
						+ " milliseconds\n";

				textArea1.setText(output);
			}
		});
		btTestTreeSet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Tree Set - used when an special Iteration is needed. \n";
				output += "In this example, the string class natural order is used.\n\n";

				TreeSet<String> treeSet = new TreeSet<>();
				treeSet.add("London");
				treeSet.add("Paris");
				treeSet.add("New York");
				treeSet.add("San Francisco");
				treeSet.add("Beijing");
				treeSet.add("New York");

				output += "Added in order, London, Paris, New York, San Francisco, Beijing, New York.\n\n";

				output += "Sorted tree set: " + treeSet + "\n";

				// Use the methods in SortedSet interface
				output += "first(): " + treeSet.first() + "\n";
				output += "last(): " + treeSet.last() + "\n";
				output += "headSet(\"New York\"): " + treeSet.headSet("New York") + "\n";
				output += "tailSet(\"New York\"): " + treeSet.tailSet("New York") + "\n";

				// Use the methods in NavigableSet interface
				output += "lower(\"P\"): " + treeSet.lower("P") + "\n";
				output += "higher(\"P\"): " + treeSet.higher("P") + "\n";
				output += "floor(\"P\"): " + treeSet.floor("P") + "\n";
				output += "ceiling(\"P\"): " + treeSet.ceiling("P") + "\n";
				output += "pollFirst(): " + treeSet.pollFirst() + "\n";
				output += "pollLast(): " + treeSet.pollLast() + "\n";
				output += "New tree set: " + treeSet + "\n";

				textArea2.setText(output);
			}
		});
//		btTab2Button2.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				String output = "Tab2 - Button 2";
//				textArea2.setText(output);
//			}
//		});
//		btTab2Button3.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				String output = "Tab2 - Button 3";
//				textArea2.setText(output);
//			}
//		});
//		btTab2Button4.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent e) {
//				String output = "Tab2 - Button 4\n\n";
//				textArea2.setText(output);
//			}
//		});
		btHashMapSimple.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Hash Map Simple - add order is not maintained\n\n";

				// switch implementations to see that both tree and hash work
				// Map<String, String> anyMap = new TreeMap<>();
				Map<String, String> anyMap = new HashMap<>();

				String[] l = { "sophie", "sally", "jack", "sally", "zipp" };

				// populate the map
				for (String s : l) {
					output += "adding " + s + "\n";
					anyMap.put(s, s + "x");
				}
				output += "\n";
				// print the entire map object
				// note that the entire map is just an object, and it can be
				// manipulated--serialized, put into a set or hashed like any other object

				// set up iterator and iterate over it
				Set<String> ks = anyMap.keySet();
				Iterator<String> iter = ks.iterator();
				while (iter.hasNext()) {
					String s = iter.next();
					output += "iter key: " + s + " value: " + anyMap.get(s) + "\n";
				}

				output += "anySet " + anyMap + "\n";
				output += "contains key sally " + anyMap.containsKey("sally") + "\n";
				output += "contains key zipper " + anyMap.containsKey("zipper") + "\n";
				output += "contains value sallyx " + anyMap.get("sallyx") + "\n";

				// add something
				// remove something
				anyMap.remove(new String("zipp"));
				output += "zipp removed " + anyMap + "\n";

				// adding a duplicate does not cause an error : just replacement
				anyMap.put("zipp", "ver 1");
				anyMap.put("zipp", "ver 2");
				output += "zipp put twice " + anyMap + "\n";

				// get size
				output += "The size of the map is " + anyMap.size() + "\n";
				textArea3.setText(output);
			}
		});
		btTreeMapSimple.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Hash Map Simple - adding is done natural order\n\n";
				Map<String, String> anyMap = new TreeMap<>();
				String[] l = { "sophie", "sally", "jack", "sally", "zipp" };

				// populate the map
				for (String s : l) {
					output += "adding " + s + "\n";
					anyMap.put(s, s + "x");
				}
				output += "\n";
				// print the entire map object
				// note that the entire map is just an object, and it can be
				// manipulated--serialized, put into a set or hashed like any other object

				// set up iterator and iterate over it
				Set<String> ks = anyMap.keySet();
				Iterator<String> iter = ks.iterator();
				while (iter.hasNext()) {
					String s = iter.next();
					output += "iter key: " + s + " value: " + anyMap.get(s) + "\n";
				}

				output += "anySet " + anyMap + "\n";
				output += "contains key sally " + anyMap.containsKey("sally") + "\n";
				output += "contains key zipper " + anyMap.containsKey("zipper") + "\n";
				output += "contains value sally " + anyMap.get("sally") + "\n";

				// add something
				// remove something
				anyMap.remove(new String("zipp"));
				output += "zipp removed " + anyMap + "\n";

				// adding a duplicate does not cause an error : just replacement
				anyMap.put("zipp", "ver 1");
				anyMap.put("zipp", "ver 2");
				output += "zipp put twice " + anyMap + "\n";

				// get size
				output += "The size of the map is " + anyMap.size() + "\n";
				textArea3.setText(output);
			}
		});
		btLoadFakeNames.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Load the Office Series\n";
				boolean success = personDataSet.ReadFromDataBase();
				if (success) {
					output = "persons loaded - " + personDataSet.size() + "\n";
				} else {
					output = "failure loading persons \n";
				}
				textArea4.setText(output);
			}
		});
		btFakeNameToMaps.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Load Hash Map and Tree Map\n";
				long startTime = System.currentTimeMillis();
				for (Iterator<PersonWithKey> iterator = personDataSet.iterator(); iterator.hasNext();) {
					PersonWithKey person = (PersonWithKey) iterator.next();
					personDataSetHashMap.put(person.getId(), person);
					personDataSetTreeMap.put(person.getId(), person);
				}
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to load " + endTime + "\n";
				output += "persons loaded into Hash Map- " + personDataSetHashMap.size() + "\n";
				output += "persons loaded into Tree Map- " + personDataSetTreeMap.size() + "\n";
				textArea4.setText(output);
			}
		});
		btTestHashMap.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Retrive All People from Hash Map\n";

				long startTime = System.currentTimeMillis();
				for (int i = 0; i < personDataSetHashMap.size(); i++) {
					personDataSetHashMap.get(i);
				}
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to Get " + endTime + "\n";
				textArea4.setText(output);
			}
		});
		btTestTreeMap.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Retrive All People from Tree Map\n";

				long startTime = System.currentTimeMillis();
				for (int i = 0; i < personDataSetTreeMap.size(); i++) {
					personDataSetTreeMap.get(i);
				}
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to Get " + endTime + "\n";
				textArea4.setText(output);
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
		primaryStage.setTitle("Sets and Maps"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}