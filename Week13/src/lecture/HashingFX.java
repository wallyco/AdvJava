package lecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class HashingFX extends Application {

	Map<String, Integer> hashMap = new HashMap<>();
	Collection<String> hashSet = new HashSet<>();

	List<Person> people = new ArrayList<>();
	Map<Integer, Person> personHashMap = new HashMap<>();
	Set<Person> personHashSet = new HashSet<>();

	MyMap<String, Integer> hashMapRYO = new MyHashMap<>();
	Collection<String> hashSetRYO = new MyHashSet<>();

	MyMap<Integer, Person> personHashMapRYO = new MyHashMap<>();
	Collection<Person> personHashSetRYO = new MyHashSet<>();

	public void start(Stage primaryStage) {

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("Basics");
		TextArea textArea1 = new TextArea();
		textArea1.setWrapText(true);

		Button btHashMapSimple = new Button("Test Hash Map");
		Button btSetMapSimple = new Button("Test Hash Set");

		HBox hBox1 = new HBox(btHashMapSimple, btSetMapSimple);
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		hBox1.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane1 = new BorderPane();
		borderPane1.setTop(hBox1);
		borderPane1.setCenter(textArea1);
		tab1.setContent(borderPane1);

		Tab tab2 = new Tab("Person Hashing");
		TextArea textArea2 = new TextArea();
		textArea2.setWrapText(true);
		Button btLoadPeople = new Button("Load People");
		Button btSearchPersonHashMap = new Button("Search Hash Map");
		Button btSearchPersonHashSet = new Button("Search Hash Set");
		Button btRemovePersonHashMap = new Button("Remove Hash Map");
		Button btRemovePersonHashSet = new Button("Remove Hash Set");

		HBox hBox2 = new HBox(btLoadPeople, btSearchPersonHashMap, btSearchPersonHashSet, btRemovePersonHashMap,
				btRemovePersonHashSet);
		hBox2.setSpacing(10);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane2 = new BorderPane();
		borderPane2.setTop(hBox2);
		borderPane2.setCenter(textArea2);
		tab2.setContent(borderPane2);

		Tab tab3 = new Tab("Simple (RYO)");
		TextArea textArea3 = new TextArea();
		textArea3.setWrapText(true);
		Button btHashMapSimpleRYO = new Button("Test Hash Map (RYO)");
		Button btSetMapSimpleRYO = new Button("Test Hash Set (RYO) ");
		HBox hBox3 = new HBox(btHashMapSimpleRYO, btSetMapSimpleRYO);
		hBox3.setSpacing(10);
		hBox3.setAlignment(Pos.CENTER);
		hBox3.setPadding(new Insets(5, 5, 5, 5));
		BorderPane borderPane3 = new BorderPane();
		borderPane3.setTop(hBox3);
		borderPane3.setCenter(textArea3);
		tab3.setContent(borderPane3);

		Tab tab4 = new Tab("Person Hashing RYO");
		TextArea textArea4 = new TextArea();
		textArea4.setWrapText(true);
		Button btLoadPeopleRYO = new Button("Load People RYO");
		Button btSearchPersonHashMapRYO = new Button("Search Hash Map RYO");
		Button btSearchPersonHashSetRYO = new Button("Search Hash Set RYO");
		Button btRemovePersonHashMapRYO = new Button("Remove Hash Map RYO");
		Button btRemovePersonHashSetRYO = new Button("Remove Hash Set RYO");

		HBox hBox4 = new HBox(btLoadPeopleRYO, btSearchPersonHashMapRYO, btSearchPersonHashSetRYO,
				btRemovePersonHashMapRYO, btRemovePersonHashSetRYO);
		hBox4.setSpacing(10);
		hBox4.setAlignment(Pos.CENTER);
		hBox4.setPadding(new Insets(5, 5, 5, 5));

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

		btHashMapSimple.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Hash Map Demo\n";

				hashMap.put("Smith", 30);
				String myStr = "Smith";
				output += "Hash Code Smith " + myStr.hashCode() + "\n";
				hashMap.put("Anderson", 31);
				myStr = "Anderson";
				output += "Hash Code Anderson " + myStr.hashCode() + "\n";
				hashMap.put("Lewis", 29);
				myStr = "Lewis";
				output += "Hash Code Lewis " + myStr.hashCode() + "\n";
				hashMap.put("Cook", 29);
				myStr = "Cook";
				output += "Hash Code Cook " + myStr.hashCode() + "\n";
				hashMap.put("Smith", 65);
				myStr = "Smith";
				output += "Hash Code Smith " + myStr.hashCode() + "\n";

				output += "HashSet entry order - (Smith,30), (Anderson,31), (Lewis,29), (Cook,29), (Smith,65)\n";
				output += "Entries in hashMap: " + hashMap + "\n";

				output += "The age for " + "Lewis is " + hashMap.get("Lewis") + "\n";
				output += "Is Smith in the map? " + hashMap.containsKey("Smith") + "\n";
				output += "Is age 33 in the map? " + hashMap.containsValue(33) + "\n";

				output += "Remove Smith \n";
				hashMap.remove("Smith");
				output += "Entries in map: " + hashMap;
				output += "\n";

				output += "Clear the Set \n";
				hashMap.clear();
				output += "Entries in map: " + hashMap;
				textArea1.setText(output);
			}
		});
		btSetMapSimple.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Set Map Demo\n";
				hashSet.add("Smith");
				String myStr = "Smith";
				output += "Hash Code Smith " + myStr.hashCode() + "\n";
				hashSet.add("Anderson");
				myStr = "Anderson";
				output += "Hash Code Anderson " + myStr.hashCode() + "\n";
				hashSet.add("Lewis");
				myStr = "Lewis";
				output += "Hash Code Lewis " + myStr.hashCode() + "\n";
				hashSet.add("Cook");
				myStr = "Cook";
				output += "Hash Code Cook " + myStr.hashCode() + "\n";
				hashSet.add("Smith");
				myStr = "Smith";
				output += "Hash Code Smith " + myStr.hashCode() + "\n";

				output += "HashSet entry order - Smith, Anderson,Lewis,Cook,Smith\n" + "\n";
				output += "Elements in set: " + hashSet + "\n";
				output += "Number of elements in set: " + hashSet.size() + "\n";
				output += "Is Smith in set? " + hashSet.contains("Smith") + "\n";

				output += "Remove Smith \n";
				hashSet.remove("Smith");
				output += "Names in set in uppercase are\n";
				for (String s : hashSet)
					output += s.toUpperCase() + "\t";
				output += "\n";

				output += "Clear the Set \n";
				hashSet.clear();
				output += "Elements in set: " + hashSet + "\n";
				textArea1.setText(output);
			}
		});

		btLoadPeople.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Load People into Hash Map & Hash Set\n";
				people = DbUtilitiesPerson.ReadFromDataBase();
				if (people.size() > 0) {
					output = "persons loaded - " + people.size() + "\n";
				} else {
					output = "failure loading persons \n";
				}
				long startTime = System.currentTimeMillis();
				for (Iterator<Person> iterator = people.iterator(); iterator.hasNext();) {
					Person person = (Person) iterator.next();
					personHashMap.put(person.hashCode(), person);
					personHashSet.add(person);
				}
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to load " + endTime + "\n";
				output += "persons loaded into Hash Map- " + personHashMap.size() + "\n";
				output += "persons loaded into Hash Set- " + personHashSet.size() + "\n";
				textArea2.setText(output);
			}
		});
		btSearchPersonHashMap.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Search Person Hash Map\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 500; i++) {
					int position = (int) (Math.random() * 10000);
					Person person = people.get(position);
					Person personToFind = personHashMap.get(person.hashCode());
					output += personToFind.getLastname() + "\t";
				}
				output += "\n";
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to search for 500 random people " + endTime + "\n";
				textArea2.setText(output);
			}
		});
		btSearchPersonHashSet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Search Person Hash Set\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 500; i++) {
					int position = (int) (Math.random() * 10000);
					Person person = people.get(position);
					Boolean personToFind = personHashSet.contains(person);
					output += personToFind + "\t";
				}
				output += "\n";
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to search for 500 random people " + endTime + "\n";
				textArea2.setText(output);
			}
		});
		btRemovePersonHashMap.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Remove Person Hash Map\n";
				output += "Original Size " + personHashMap.size() + "\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 500; i++) {
					int position = (int) (Math.random() * 10000);
					Person person = people.get(position);
					Person personToFind = personHashMap.remove(person.hashCode());
					if (personToFind == null) {
						i--;
//						output += person + "\n\n";						
					} else {
						// output += personToFind.getLastname() + "\t";
					}
				}
				output += "\n";
				long endTime = System.currentTimeMillis() - startTime;
				output += "Final Size " + personHashMap.size() + "\n";
				output += "Time to remove for 500 random people " + endTime + "\n";
				textArea2.setText(output);
			}
		});
		btRemovePersonHashSet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Remove Person Hash Set\n";
				output += "Original Size " + personHashSet.size() + "\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 500; i++) {
					int position = (int) (Math.random() * 10000);
					Person person = people.get(position);
					boolean personToFind = personHashSet.remove(person);
					if (personToFind == false) {
						i--;
						// output += person + "\n\n";
					} else {
						// output += personToFind.getLastname() + "\t";
					}
				}
				output += "\n";
				long endTime = System.currentTimeMillis() - startTime;
				output += "Final Size " + personHashSet.size() + "\n";
				output += "Time to remove for 500 random people " + endTime + "\n";
				textArea2.setText(output);
			}
		});
		btHashMapSimpleRYO.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Hash Map Demo (RYO)\n";

				hashMapRYO.put("Smith", 30);
				String myStr = "Smith";
				output += "Hash Code Smith " + myStr.hashCode() + "\n";
				hashMapRYO.put("Anderson", 31);
				myStr = "Anderson";
				output += "Hash Code Anderson " + myStr.hashCode() + "\n";
				hashMapRYO.put("Lewis", 29);
				myStr = "Lewis";
				output += "Hash Code Lewis " + myStr.hashCode() + "\n";
				hashMapRYO.put("Cook", 29);
				myStr = "Cook";
				output += "Hash Code Cook " + myStr.hashCode() + "\n";
				hashMapRYO.put("Smith", 65);
				myStr = "Smith";
				output += "Hash Code Smith " + myStr.hashCode() + "\n";

				output += "HashSet entry order - (Smith,30), (Anderson,31), (Lewis,29), (Cook,29), (Smith,65)\n";
				output += "Entries in hashMap: " + hashMapRYO + "\n";

//				LinkedList<MyMap.Entry<String, Integer>>[] table = ((MyHashMap<String, Integer>) hashMapRYO).getTable();
//				for (int i = 0; i < table.length; i++)
//					System.out.println(table[i]);

				output += "The age for " + "Lewis is " + hashMapRYO.get("Lewis") + "\n";
				output += "Is Smith in the map? " + hashMapRYO.containsKey("Smith") + "\n";
				output += "Is age 33 in the map? " + hashMapRYO.containsValue(33) + "\n";

				output += "Remove Smith \n";
				hashMapRYO.remove("Smith");
				output += "Entries in map: " + hashMapRYO;
				output += "\n";

				output += "Clear the Set \n";
				hashMapRYO.clear();
				output += "Entries in map: " + hashMapRYO;
				textArea3.setText(output);
			}
		});
		btSetMapSimpleRYO.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Set Map Demo (RYO) \n";
				hashSetRYO.add("Smith");
				String myStr = "Smith";
				output += "Hash Code Smith " + myStr.hashCode() + "\n";
				hashSetRYO.add("Anderson");
				myStr = "Anderson";
				output += "Hash Code Anderson " + myStr.hashCode() + "\n";
				hashSetRYO.add("Lewis");
				myStr = "Lewis";
				output += "Hash Code Lewis " + myStr.hashCode() + "\n";
				hashSetRYO.add("Cook");
				myStr = "Cook";
				output += "Hash Code Cook " + myStr.hashCode() + "\n";
				hashSetRYO.add("Smith");
				myStr = "Smith";
				output += "Hash Code Smith " + myStr.hashCode() + "\n";

				output += "HashSet entry order - Smith, Anderson,Lewis,Cook,Smith\n\n";
				output += "Elements in set (RYO) : " + hashSetRYO + "\n";
				output += "Number of elements in set (RYO): " + hashSetRYO.size() + "\n";
				output += "Is Smith in set? " + hashSetRYO.contains("Smith") + "\n";

				output += "Remove Smith \n";
				hashSetRYO.remove("Smith");
				output += "Names in set in uppercase are\n";
				for (String s : hashSetRYO)
					output += s.toUpperCase() + "\t";
				output += "\n";

				output += "Clear the Set \n";
				hashSetRYO.clear();
				output += "Elements in set: " + hashSetRYO + "\n";
				textArea3.setText(output);
			}
		});
		btLoadPeopleRYO.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Load People into Hash Map & Hash Set (RYO) \n";
				people = DbUtilitiesPerson.ReadFromDataBase();
				if (people.size() > 0) {
					output = "persons loaded - " + people.size() + "\n";
				} else {
					output = "failure loading persons \n";
				}
				long startTime = System.currentTimeMillis();
				for (Iterator<Person> iterator = people.iterator(); iterator.hasNext();) {
					Person person = (Person) iterator.next();
					personHashMapRYO.put(person.hashCode(), person);
					personHashSetRYO.add(person);
				}
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to load " + endTime + "\n";
				output += "persons loaded into Hash Map (RYO) - " + personHashMapRYO.size() + "\n";
				output += "persons loaded into Hash Set (RYO) - " + personHashSetRYO.size() + "\n";

//				LinkedList<MyMap.Entry<Integer, Person>>[] table = ((MyHashMap<Integer, Person>) personHashMapRYO)
//						.getTable();
//				System.out.println("Table length : " + table.length);
//				for (int i = 0; i < 10; i++)
//					System.out.println(table[i]);

				textArea4.setText(output);
			}
		});
		btSearchPersonHashMapRYO.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Search Person Hash Map (RYO)\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 500; i++) {
					int position = (int) (Math.random() * 10000);
					Person person = people.get(position);
					Person personToFind = personHashMapRYO.get(person.hashCode());
					output += personToFind.getLastname() + "\t";
				}
				output += "\n";
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to search for 500 random people " + endTime + "\n";
				textArea4.setText(output);
			}
		});
		btSearchPersonHashSetRYO.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Search Person Hash Set (RYO)\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 500; i++) {
					int position = (int) (Math.random() * 10000);
					Person person = people.get(position);
					Boolean personToFind = personHashSetRYO.contains(person);
					output += personToFind + "\t";
				}
				output += "\n";
				long endTime = System.currentTimeMillis() - startTime;
				output += "Time to search for 500 random people " + endTime + "\n";
				textArea4.setText(output);
			}
		});
		btRemovePersonHashMapRYO.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Remove Person Hash Map (RYO)\n";
				output += "Original Size " + personHashMapRYO.size() + "\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 500; i++) {
					int position = (int) (Math.random() * 10000);
					Person person = people.get(position);
					// Person personToFind = personHashMapRYO.remove(person.hashCode());
					personHashMapRYO.remove(person.hashCode());
					// if (personToFind == null) {
					// i--;
//						output += person + "\n\n";						
					// } else {
					// output += personToFind.getLastname() + "\t";
					// }
				}
				output += "\n";
				long endTime = System.currentTimeMillis() - startTime;
				output += "Final Size " + personHashMapRYO.size() + "\n";
				output += "Time to remove for 500 random people " + endTime + "\n";
				textArea4.setText(output);
			}
		});
		btRemovePersonHashSetRYO.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Remove Person Hash Set (RYO)\n";
				output += "Original Size " + personHashSetRYO.size() + "\n";
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < 500; i++) {
					int position = (int) (Math.random() * 10000);
					Person person = people.get(position);
					boolean personToFind = personHashSetRYO.remove(person);
					if (personToFind == false) {
						i--;
						// output += person + "\n\n";
					} else {
						// output += personToFind.getLastname() + "\t";
					}
				}
				output += "\n";
				long endTime = System.currentTimeMillis() - startTime;
				output += "Final Size " + personHashSetRYO.size() + "\n";
				output += "Time to remove for 500 random people " + endTime + "\n";
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
		primaryStage.setTitle("Hashing"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}