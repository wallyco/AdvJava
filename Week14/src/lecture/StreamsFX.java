package lecture;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

public class StreamsFX extends Application {

	List<Person> roster = Person.createRoster();
	List<PersonWithDB> people = PersonWithDB.ReadFromDataBase();
	List<PersonWithDB> peopleChosen = new ArrayList<>();

	public void start(Stage primaryStage) {

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("Introduction");
		TextArea textArea1 = new TextArea();
		textArea1.setWrapText(true);

		Button btTab1Button1 = new Button("Old Method");
		Button btTab1Button2 = new Button("The Stream");
		Button btTab1Button3 = new Button("Pipelines");
		Button btTab1Button4 = new Button("Stream Lecture Part 1");
		Button btTab1Button5 = new Button("Stream Lecture Part 2");

		HBox hBox1 = new HBox(btTab1Button1, btTab1Button2, btTab1Button3, btTab1Button4, btTab1Button5);
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		hBox1.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane1 = new BorderPane();
		borderPane1.setTop(hBox1);
		borderPane1.setCenter(textArea1);
		tab1.setContent(borderPane1);

		Tab tab2 = new Tab("Persons");
		TextArea textArea2 = new TextArea();
		textArea2.setWrapText(true);
		Button btExample1 = new Button("Old");
		Button btExample2 = new Button("Stream");
		Button btExample3 = new Button("Parallel");
		Button btExample4 = new Button("Trace");
		Button btExample5 = new Button("AnyMatch");
		Button btExample6 = new Button("Optional");
		Button btExample7 = new Button("Length");
		Button btExample8 = new Button("Reduce");
		Button btExample9 = new Button("Map Switch");

		HBox hBox2 = new HBox(btExample1, btExample2, btExample3, btExample4, btExample5, btExample6, btExample7,
				btExample8, btExample9);
		hBox2.setSpacing(10);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane2 = new BorderPane();
		borderPane2.setTop(hBox2);
		borderPane2.setCenter(textArea2);
		tab2.setContent(borderPane2);

		Tab tab3 = new Tab("Other Examples");
		TextArea textArea3 = new TextArea();
		textArea3.setWrapText(true);
		Button btTab3Button1 = new Button("Dish - Java 7");
		Button btTab3Button2 = new Button("Dish - Java 8");
		Button btTab3Button3 = new Button("Short Names");

		HBox hBox3 = new HBox(btTab3Button1, btTab3Button2, btTab3Button3);
		hBox3.setSpacing(10);
		hBox3.setAlignment(Pos.CENTER);
		hBox3.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane3 = new BorderPane();
		borderPane3.setTop(hBox3);
		borderPane3.setCenter(textArea3);
		tab3.setContent(borderPane3);

		tabPane.getTabs().addAll(tab1, tab2, tab3);

		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");
		HBox hBoxBottom = new HBox(btClearAnswers, btnExit);
		hBoxBottom.setSpacing(10);
		hBoxBottom.setAlignment(Pos.CENTER);
		hBoxBottom.setPadding(new Insets(10, 5, 10, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(tabPane);
		borderPane.setBottom(hBoxBottom);

		btTab1Button1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Old Method\n\n";
				for (Person p : roster) {
					output += p.getName() + "\n";
				}
				textArea1.setText(output);
			}
		});
		btTab1Button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Simple Stream\n\n";
				roster.stream().forEach(ee -> System.out.println(ee.getName()));

//				List<String> names = roster.stream()
//						.map(ee -> ee.getName())
//						.collect(Collectors.toList());
//				output += names;
				textArea1.setText(output);
			}
		});
		btTab1Button3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Pipelines and streams\n\n";
				output += "A pipeline is a sequence of aggregate operations.\n";
				output += "The following example prints the male members contained in the collection\n";
				output += "roster with a pipeline that consists of the aggregate operations filter and forEach:\n";
				roster.stream().filter(ee -> ee.getGender() == Person.Sex.MALE)
						.forEach(ee -> System.out.println(ee.getName()));
				textArea1.setText(output);
			}
		});
		btTab1Button4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Pipeline Background Part 1\n\n";
				output += "A pipeline contains the following components:\n\n";

				output += "A source: This could be a collection, an array, a generator function, or an I/O channel.\n";
				output += "In this example, the source is the collection roster.\n\n";

				output += "Zero or more intermediate operations.\n";
				output += "An intermediate operation, such as filter, produces a new stream.\n\n";

				output += "A stream is a sequence of elements. Unlike a collection, it is not a\n";
				output += "data structure that stores elements. Instead, a stream carries values from a\n";
				output += "source through a pipeline. This example creates a stream from the collection\n";
				output += "roster by invoking the method stream.\n\n";

				output += "The filter operation returns a new stream that contains elements that\n";
				output += "match its predicate (this operation's parameter). In this example,\n";
				output += "the predicate is the lambda expression e -> e.getGender() == Person.Sex.MALE.\n";
				output += "It returns the boolean value true if the gender field of object e has the value\n";
				output += "Person.Sex.MALE. Consequently, the filter operation in this example returns a\n";
				output += "stream that contains all male members in the collection roster.\n\n";

				output += "A terminal operation. A terminal operation, such as forEach, produces a non-stream\n";
				output += "result, such as a primitive value (like a double value), a collection, or in\n";
				output += "the case of forEach, no value at all. In this example, the parameter of the\n";
				output += "forEach operation is the lambda expression e -> System.out.println(e.getName()),\n";
				output += "which invokes the method getName on the object e. (The Java runtime and compiler\n";
				output += "infer that the type of the object e is Person.)\n\n";

				double average = roster.stream().filter(p -> p.getGender() == Person.Sex.MALE).mapToInt(Person::getAge)
						.average().getAsDouble();
				output += "The average is : " + average + "\n";
				textArea1.setText(output);
			}
		});
		btTab1Button5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Pipeline Background Part 2\n\n";
				output += "Differences Between Aggregate Operations and Iterators Aggregate operations,\n";
				output += "like forEach, appear to be like iterators. However, they have several\n";
				output += "fundamental differences:\n\n";

				output += "They use internal iteration: Aggregate operations do not contain a method\n";
				output += "like next to instruct them to process the next element of the collection.\n";
				output += "With internal delegation, your application determines what collection it\n";
				output += "iterates, but the JDK determines how to iterate the collection. With external\n";
				output += "iteration, your application determines both what collection it iterates and\n";
				output += "how it iterates it. However, external iteration can only iterate over the\n";
				output += "elements of a collection sequentially. Internal iteration does not have this\n";
				output += "limitation. It can more easily take advantage of parallel computing, which\n";
				output += "involves dividing a problem into subproblems, solving those problems\n";
				output += "simultaneously, and then combining the results of the solutions to the\n";
				output += "subproblems. See the section Parallelism for more information.\n\n";

				output += "They process elements from a stream: Aggregate operations process elements\n";
				output += "from a stream, not directly from a collection. Consequently, they are also\n";
				output += "called stream operations.\n\n";

				output += "They support behavior as parameters: You can specify lambda expressions as\n";
				output += "parameters for most aggregate operations. This enables you to customize the\n";
				output += "behavior of a particular aggregate operation\n\n";
				textArea1.setText(output);
			}
		});
		btExample1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";

				output += "Before we explore in detail what you can do with streams, let’s take a look\n";
				output += "at an example so you have a sense of the new programming style with Java SE 8\n";
				output += "streams. Let’s say we need to find all people who lived in Raleigh NC and \n";
				output += "return a list of names sorted in order by last name. \n\n";

				for (PersonWithDB p : people) {
					if (p.getState().equals("NC") && p.getCity().equals("Raleigh")) {
						peopleChosen.add(p);
					}
				}
				Collections.sort(peopleChosen, new Comparator<PersonWithDB>() {
					public int compare(PersonWithDB p1, PersonWithDB p2) {
						return p1.getLastName().compareTo(p2.getLastName());
					}
				});

				// List<String> lastName = new ArrayList<>();
				for (PersonWithDB p : peopleChosen) {
					output += p.getLastName() + " " + p.getFirstName() + " " + p.getCity() + " " + p.getState() + "\n";
				}
//				List<String> lastName = new ArrayList<>();
//				for (PersonWithDB p : peopleChosen) {
//					lastName.add(p.getLastName());
//				}
//
//				for (Iterator<String> iterator = lastName.iterator(); iterator.hasNext();) {
//					System.out.println((String) iterator.next());
//				}
				output += "\nCount of People Chosen " + peopleChosen.size() + "\n";
				textArea2.setText(output);
			}
		});
		btExample2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "Size of Original " + people.size() + "\n";
				List<String> lastNameFromNC = people.stream()
						.filter(p -> p.getState().equals("NC") && p.getCity().equals("Raleigh"))
						.sorted(Comparator.comparing(PersonWithDB::getLastName).reversed())
						.map(PersonWithDB::getLastName).collect(Collectors.toList());
				output += "Size of Found Names " + lastNameFromNC.size() + "\n";
				for (String ln : lastNameFromNC) {
					output += ln + "\t";
				}
				output += "\n";
				textArea2.setText(output);
			}
		});
		btExample3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "";
				output += "Size of Original " + people.size() + "\n";
				List<String> lastNameFromNC = people.parallelStream()
						.filter(p -> p.getState().equals("NC") && p.getCity().equals("Raleigh"))
						.sorted(Comparator.comparing(PersonWithDB::getLastName).reversed())
						.map(PersonWithDB::getLastName).collect(Collectors.toList());
				output += "Size of Found Names " + lastNameFromNC.size() + "\n";
				for (String ln : lastNameFromNC) {
					output += ln + "\t";
				}
				output += "\n";
				textArea2.setText(output);
			}
		});
		btExample4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Tracing a Stream\n\n";
				List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
				List<Integer> twoEvenSquares = numbers.stream().filter(n -> {
					// System.out.println("filtering " + n);
					return n % 2 == 0;
				}).map(n -> {
					// System.out.println("mapping " + n);
					return n * n;
				}).limit(5).collect(Collectors.toList());

				for (Integer i : twoEvenSquares) {
					output += i + "\n";
				}
				textArea2.setText(output);
			}
		});
		btExample5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Long Names exists (length > 12) is ";
				boolean weirdLongName = people.stream().anyMatch(t -> t.getLastName().length() > 12);
				output += weirdLongName;
				textArea2.setText(output);
			}
		});
		btExample6.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Find Any with Optional\n\n";
				Optional<PersonWithDB> listOfOptionals = people.stream().filter(t -> t.getState().equals("NC"))
						.findAny();
				output += listOfOptionals.toString() + "\n";
				textArea2.setText(output);
			}
		});
		btExample7.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Length\n\n";
				List<String> words = Arrays.asList("Oracle", "Java", "Magazine");
				List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());
				for (Iterator<Integer> iterator = wordLengths.iterator(); iterator.hasNext();) {
					Integer integer = (Integer) iterator.next();
					output += integer + "\n";
				}
//				List<Integer> lastNameLengths = people.stream()
//						.map(PersonWithDB::getLastName)
//						.map(String::length)
//						.collect(Collectors.toList());
//				Set<Integer> lastNameLengths = people.stream()
//						.map(PersonWithDB::getLastName)
//						.map(String::length)
//						.collect(Collectors.toSet());
//				for (Iterator<Integer> iterator = lastNameLengths.iterator(); iterator.hasNext();) {
//					Integer integer = (Integer) iterator.next();
//					output += integer + "\n";
//				}

				textArea2.setText(output);
			}
		});
		btExample8.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Reduce\n\n";
				ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

				output += "The ArrayList of Integer is 1,2,3,4,5,6,7,8\n\n";
				int sum = list.stream().reduce(0, (a, b) -> a + b);
				output += "The sum is " + sum + "\n";

				int product = list.stream().reduce(1, (a, b) -> a * b);
				int max = list.stream().reduce(1, Integer::max);
				output += "The product is " + product + "\n";
				output += "The max is " + max + "\n";

				textArea2.setText(output);
			}
		});
		btExample9.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Map Split\n\n";
				Stream<String> words = Stream.of("Java", "Magazine", "is", "the", "best");

				Map<String, Long> letterToCount = words.map(w -> w.split("")).flatMap(Arrays::stream)
						.collect(groupingBy(identity(), counting()));
				output += letterToCount + "\n";
				textArea2.setText(output);
			}
		});
		btTab3Button1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Java 7 - Low Calories\n\n";
				List<Dish> dishes = Dish.menu;
				List<Dish> lowCaloricDishes = new ArrayList<>();
				for (Dish d : dishes) {
					if (d.getCalories() < 400) {
						lowCaloricDishes.add(d);
					}
				}
				List<String> lowCaloricDishesName = new ArrayList<>();
				Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
					public int compare(Dish d1, Dish d2) {
						return Integer.compare(d1.getCalories(), d2.getCalories());
					}
				});
				for (Dish d : lowCaloricDishes) {
					lowCaloricDishesName.add(d.getName());
				}
				output += lowCaloricDishesName + "\n";
				textArea3.setText(output);
			}
		});
		btTab3Button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Java 8 - Low Calories\n\n";
				List<Dish> dishes = Dish.menu;
				List<String> lowCaloricDishes = new ArrayList<>();
				lowCaloricDishes = dishes.stream().filter(d -> d.getCalories() < 400)
						.sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
				output += lowCaloricDishes + "\n";
				textArea3.setText(output);
			}
		});
		btTab3Button3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String output = "Short Names \n\n";

//				List<String> lastName = new ArrayList<>();
//				for(PersonWithDB personWithDB : people) {
//					lastName.add(personWithDB.getLastName());
//				}
				
//				lastName.stream()
//					.map(ee -> ee.toLowerCase().trim())
//					.sorted()
//					.filter(ee -> ee.length() < 3)
//					.forEach(ee -> System.out.print(ee + " "));
				
//				List<String> lastNameShort =lastName.stream()
//						.map(ee -> ee.toLowerCase().trim())
//						.sorted()
//						.filter(ee -> ee.length() < 3)
//						.collect(Collectors.toList());
				List<String> lastNameShort =people.stream()
						.map(PersonWithDB::getLastName)	
						.map(ee -> ee.toLowerCase().trim())
						.sorted()
						.filter(ee -> ee.length() < 3)
						.collect(Collectors.toList());
				output += lastNameShort;			
				textArea3.setText(output);
			}
		});

		btClearAnswers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				textArea1.setText("");
				textArea2.setText("");
				textArea3.setText("");
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
		primaryStage.setTitle("Streams"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}