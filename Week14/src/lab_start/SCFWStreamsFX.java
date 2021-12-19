package lab_start;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SCFWStreamsFX extends Application {

	Dictionary dictionary = new Dictionary();

	ArrayList<String> document = new ArrayList<>();

	Map<String, Integer> wordFrequency = new TreeMap<>();
	Map<String, Integer> map = new TreeMap<>();
	String output;
	StringBuilder stringBuilder = new StringBuilder();

	public void start(Stage primaryStage) throws IOException, SQLException {

		TextArea textArea = new TextArea();
		textArea.setWrapText(true);

		Label nameLabel = new Label("File to Load: ");
		ChoiceBox<String> textfileToCheck = new ChoiceBox<String>();
		textfileToCheck.getItems().addAll("SophieSallyJack.txt", "GreenEggsAndHam.txt", "BillOfRights.txt",
				"greatgatsby.txt", "warandpeace.txt");
		textfileToCheck.getSelectionModel().select(0);
		String message = (LoadTheFile(textfileToCheck.getValue()));
		textfileToCheck.setOnAction(event -> {
			try {
				textArea.setText(LoadTheFile(textfileToCheck.getValue()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		Button btFrequentWords = new Button("Frequent Words");
		Button btSpellCheck = new Button("Spell Check");

		Button btClearAnswers = new Button("Clear");
		Button btnExit = new Button("Exit");

		HBox hBoxTop = new HBox(nameLabel, textfileToCheck, btFrequentWords, btSpellCheck, btClearAnswers, btnExit);
		hBoxTop.setSpacing(10);
		hBoxTop.setAlignment(Pos.CENTER);
		hBoxTop.setPadding(new Insets(5, 5, 5, 5));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(hBoxTop);
		borderPane.setCenter(textArea);

		dictionary = DbConnection.ReadDictionary();

		textArea.setText("Dictionary Loaded with " + dictionary.size() + " words\n");
		textArea.appendText(message + "\n");

		btFrequentWords.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				output = "";
				wordFrequency.clear();
				// TODO replace the following loop with a single statement using streams
		
				document.stream().forEach(s -> {
					String word = s.toLowerCase();
					if (!"".equals(word.trim())) {
						Integer cnt = wordFrequency.get(word);
						if (cnt == null) {
							wordFrequency.put(word, 1);
						} else {
							int icnt = cnt + 1;
							wordFrequency.put(word, icnt);
						}
					}
				});
				MC maxCnt = new MC(0);

				// TODO add a single statement that uses streams to determine maxCnt

				
				document.stream().forEach(s -> {
					Integer cnt = wordFrequency.get(s);
					if (cnt != null) {
						if (cnt > maxCnt.getMax()) {
							maxCnt.setMax(cnt);
						}
					}
				});
				
				// TODO replace the following loop with a single statement using streams
				// that prints the most frequent words in the document

				wordFrequency.keySet().stream().forEach(s -> {
					if(maxCnt.getMax() == wordFrequency.get(s))
						output += " " + s;
				});
				
				textArea.setText(output);
			}
		});
		btSpellCheck.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				output = "";
				map.clear();
				
				AtomicBoolean misspelled = new AtomicBoolean(false);
				MC misspelledCount = new MC(0);

				// TODO replace the following loop with a single statement that uses streams				
				document.stream().forEach(k -> {
					if (k.length() > 0) {
						k = k.toLowerCase();
						if (!dictionary.contains(k)) {
							misspelled.set(true);
							misspelledCount.setMax(misspelledCount.getMax() + 1);
						}
						if (!map.containsKey(k)) {
							if (misspelled.get()) {
								map.put(k, 0);
							} else {
								map.put(k, 1);
							}
						} else {
							int value = map.get(k);
							value++;
							map.put(k, value);
						}
					}
					misspelled.set(false);
				});
				List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
				Collections.sort(entries, (entry1, entry2) -> {
					return entry2.getValue().compareTo(entry1.getValue());
				});

				
				// Find the frequency of the most frequently used word
				MC maxValue = new MC(0);
				
				entries.forEach(entry -> {
					if(entry.getValue() > maxValue.getMax())
						maxValue.setMax(entry.getValue());
				});
				
				output += "--------------MOST FREQUENT WORDS ------ ";
				
				entries.forEach(ent ->{
					if (ent.getValue() == maxValue.getMax())
						output += ent.getKey() + " ";

				});
				
				output += "\n\n";

				output += "--------------FREQUENCY OF WORDS --------------\n ";
				MC lastValue = new MC(0);
				// TODO replace the following loop with a single statement that uses streams
				
				entries.forEach(e1 -> {
					if (!(e1.getValue() == lastValue.getMax())) {
						output += stringBuilder + "\n";
						stringBuilder.setLength(0);
						stringBuilder.append("frequency " + e1.getValue() + "\n" + "");
					}
					if (e1.getValue() == lastValue.getMax()) {
						stringBuilder = stringBuilder.append(", " + e1.getKey());
					} else {
						stringBuilder = stringBuilder.append(e1.getKey());
					}
					lastValue.setMax(e1.getValue());
				});
				
				// process misspelled words
				output += "\n-------------------Misspelled Words-------------Count :" + misspelledCount.getMax() + "\n\n";
				stringBuilder.setLength(0);

				AtomicBoolean beganPrintingMisspelled = new AtomicBoolean(false);
				entries.forEach(mis -> {
					if(mis.getValue() == 0) {
						if(!beganPrintingMisspelled.get()) {
							stringBuilder = stringBuilder.append(mis.getKey());
						}else {
							stringBuilder = stringBuilder.append(", " + mis.getKey());
						}
						beganPrintingMisspelled.set(true);
					}
				});
				output += stringBuilder;
				textArea.setText(output);
				stringBuilder = new StringBuilder();
			}
		});

		btClearAnswers.setOnAction(e ->

		{
			textArea.setText("");
		});

		btnExit.setOnAction(e -> {
			System.exit(0);
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 800, 450);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setTitle("Spell Checking with Frequent Words");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private String LoadTheFile(String fileName) throws SQLException {
		document.clear();
		File f = new File(fileName);
		Scanner scan;
		try {
			scan = new Scanner(f);
			while (scan.hasNext()) {
				String l = scan.next();
				if (l.endsWith(",") || l.endsWith(".") || l.endsWith("!") || l.endsWith("?") || l.endsWith(":")) {
					l = l.substring(0, l.length() - 1);
				}
				l = (l.replaceAll("[^a-zA-Z]", " ")).trim();
				String[] result = l.split(" ");
				for (int i = 0; i < result.length; i++) {
					if (result[i].length() > 0)
						document.add(result[i]);
				}
			}
			scan.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		for (String s : document)
			sb = sb.append(s + " ");
		return fileName + " loaded with " + document.size() + " words\n\n" + sb;
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	class MC{
		int max;
		MC(int x){
			max = x;
		}
		
		int getMax(){
			return max;
		}
		
		void setMax(int x) {
			max = x;
		}
		
		void increment() {
			max = max + 1;
		}
				
	}
}