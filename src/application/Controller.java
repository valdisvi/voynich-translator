package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class Controller {

	// Controller elements for Transliterate tab
	@FXML
	Button buttonTransliterate;
	@FXML
	TextArea inputText;
	@FXML
	TextArea outputText;
	@FXML
	ComboBox comboBoxSource;
	@FXML
	TableView<Map.Entry<String, String>> tableTranslitRules;
	@FXML
	TableColumn<Map.Entry<String, String>, String> tableColumnFrom;
	@FXML
	TableColumn<Map.Entry<String, String>, String> tableColumnTo;
	@FXML
	Button webButton;
	@FXML
	AnchorPane anchorPane1;
	@FXML
	Button addButton;
	@FXML
	Button deleteButton;
	@FXML
	Button toVoynich;

	// Controller elements for Web tab
	@FXML
	ComboBox<String> comboBoxSelect;
	@FXML
	Button buttonGetResult;
	@FXML
	Button buttonNextPage;
	@FXML
	Button buttonPreviousPage;
	@FXML
	Button buttonDelete;
	@FXML
	Label labelPage;
	@FXML
	Label labelSource;
	@FXML
	TextArea webInputText;
	@FXML
	TextArea webOutputText;
	@FXML
	AnchorPane anchorPane2;
	@FXML
	DialogPane allertWindow;
	@FXML
	TextField pageNumber;
	@FXML
	ComboBox webComboBoxSource;
	@FXML
	Button webButtonTransliterate;

	StringBuilder str = new StringBuilder();
	String userSelect = null; // selected author
	int pageId = 1; // page number
	String page = String.valueOf(pageId);

	public static String path2 = "";
	public static String webPath2 = "";

	public void initialize() throws IOException {

		// WebController initialize
		pageNumber.setText(page);
		labelPage.setTextAlignment(TextAlignment.CENTER);
		labelPage.setText("Page number: " + page);
		buttonGetResult.setText("Get text");
		buttonPreviousPage.setText("Previous page");
		buttonNextPage.setText("Next page");
		buttonDelete.setText("Delete");
		comboBoxSelect.getItems().addAll("H", "C", "F", "D", "U");
		comboBoxSelect.setPromptText("Choose resource");
		labelSource.setText("Source: " + "none");

		File[] allProperties = this.finder(Main.dataFolder);

		File test = new File(Main.dataFolder + "/Currier.properties");

		System.out.println(test.getAbsolutePath() + " " + test.exists());
		InputStream Currier = this.getClass().getResourceAsStream(Main.dataFolder + "/Currier.properties");
		InputStream FSG = this.getClass().getResourceAsStream(Main.dataFolder + "/FSG.properties");
		InputStream Bennett_to_FSG = this.getClass()
				.getResourceAsStream(Main.dataFolder + "/Bennett_to_FSG.properties");
		InputStream Bennett = this.getClass().getResourceAsStream(Main.dataFolder + "/Bennett.properties");
		InputStream BasicEVA_to_ASCIIsounds = this.getClass()
				.getResourceAsStream(Main.dataFolder + "/BasicEVA_to_ASCIIsounds.properties");
		if (allProperties == null) {
			new File(Main.dataFolder).mkdirs();
			BufferedReader Currierbr = new BufferedReader(new InputStreamReader(Currier));
			StringBuilder sb = new StringBuilder();
			String line = Currierbr.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = Currierbr.readLine();
			}
			File file = new File(Main.dataFolder + "/Currier.properties");
			FileWriter writer = new FileWriter(file, false);
			PrintWriter printer = new PrintWriter(writer);
			printer.append(sb.toString());
			printer.close();
			this.setTransliterationComboBox();
			this.setTransliterationTable();
			BufferedReader FSGbr = new BufferedReader(new InputStreamReader(FSG));
			StringBuilder sb2 = new StringBuilder();
			String line2 = FSGbr.readLine();
			while (line2 != null) {
				sb2.append(line2);
				sb2.append(System.lineSeparator());
				line2 = FSGbr.readLine();
			}
			File file2 = new File(Main.dataFolder + "/FSG.properties");
			FileWriter writer2 = new FileWriter(file2, false);
			PrintWriter printer2 = new PrintWriter(writer2);
			printer2.append(sb2.toString());
			printer2.close();
			this.setTransliterationComboBox();
			this.setTransliterationTable();
			BufferedReader Bennett_to_FSGbr = new BufferedReader(new InputStreamReader(Bennett_to_FSG));
			StringBuilder sb3 = new StringBuilder();
			String line3 = Bennett_to_FSGbr.readLine();
			while (line3 != null) {
				sb3.append(line3);
				sb3.append(System.lineSeparator());
				line3 = Bennett_to_FSGbr.readLine();
			}
			File file3 = new File(Main.dataFolder + "/Bennett_to_FSG.properties");
			FileWriter writer3 = new FileWriter(file3, false);
			PrintWriter printer3 = new PrintWriter(writer3);
			printer3.append(sb3.toString());
			printer3.close();
			this.setTransliterationComboBox();
			this.setTransliterationTable();
			BufferedReader Bennettbr = new BufferedReader(new InputStreamReader(Bennett));
			StringBuilder sb4 = new StringBuilder();
			String line4 = Bennettbr.readLine();
			while (line4 != null) {
				sb4.append(line4);
				sb4.append(System.lineSeparator());
				line4 = Bennettbr.readLine();
			}
			File file4 = new File(Main.dataFolder + "/Bennett.properties");
			FileWriter writer4 = new FileWriter(file4, false);
			PrintWriter printer4 = new PrintWriter(writer4);
			printer4.append(sb4.toString());
			printer4.close();
			this.setTransliterationComboBox();
			this.setTransliterationTable();
			BufferedReader BasicEVA_to_ASCIIsoundsbr = new BufferedReader(
					new InputStreamReader(BasicEVA_to_ASCIIsounds));
			StringBuilder sb5 = new StringBuilder();
			String line5 = BasicEVA_to_ASCIIsoundsbr.readLine();
			while (line5 != null) {
				sb5.append(line5);
				sb5.append(System.lineSeparator());
				line5 = BasicEVA_to_ASCIIsoundsbr.readLine();
			}
			File file5 = new File(Main.dataFolder + "/BasicEVA_to_ASCIIsounds.properties");
			FileWriter writer5 = new FileWriter(file5, false);
			PrintWriter printer5 = new PrintWriter(writer5);
			printer5.append(sb5.toString());
			printer5.close();
			this.setTransliterationComboBox();
			this.setTransliterationTable();
		}

	}

	public void actionNextPage() {
		pageId = pageId + 1;
		page = "" + pageId;
		pageNumber.setText(page);
		labelPage.setTextAlignment(TextAlignment.CENTER);
		labelPage.setText("Page number: " + page);
		getResult();
	}

	public void actionPreviousPage() {
		if (pageId >= 2) {
			pageId = pageId - 1;
			page = "" + pageId;
			pageNumber.setText(page);
			labelPage.setTextAlignment(TextAlignment.CENTER);
			labelPage.setText("Page number: " + page);
			getResult();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error cannot access selected page");
			String s = "Selected page does not exist.";
			alert.setContentText(s);
			alert.show();
		}
	}

	public void actionDeleteButton() {
		webInputText.setText(null);
		webOutputText.setText(null);
	}

	public void actionResultButton() {
		getResult();
	}

	public void actionComboBox() {
		authorSelect();
		labelSource.setText("Source: " + comboBoxSelect.getValue());
	}

	private void authorSelect() {
		userSelect = comboBoxSelect.getValue();
	}

	private void getResult() {
		String webpage = "http://www.voynich.nu/q01/f00" + pageNumber.getText() + "v_tr.txt"; // source
		labelPage.setText("Page number: " + pageId);
		try {
			pageId = Integer.valueOf(pageNumber.getText());
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error cannot access webpage");
			String s = "Page number cannot be a letter";
			alert.setContentText(s);
			alert.show();
			return;
		}
		try {
			// sets connection to web page
			URL url = new URL(webpage);
			URLConnection hc = url.openConnection();
			hc.setRequestProperty("User-Agent", "Java URLConnection");
			// creates input stream reader
			BufferedReader in = new BufferedReader(new InputStreamReader(hc.getInputStream()));
			// variables
			String inputLine;
			String removeStartsWith = "#";
			// prints out only selected author text without any comments and
			// other unnecessary things
			while ((inputLine = in.readLine()) != null) {
				// removes like if it start with #
				if (inputLine.startsWith(removeStartsWith)) {
					inputLine = inputLine.replace(System.getProperty("line.separator"), "");
				}
				// removes source info part that start with <f1v.P.1;H> (as
				// example)
				if (inputLine.matches("<f\\d+[v|r]\\.P\\.\\d+;" + userSelect + ">\\s.*")) {
					inputLine = inputLine.replaceAll("<f\\d+[v|r]\\.P\\.\\d+;" + userSelect + ">\\s*", "");
					inputLine = inputLine.replace("{plant}", "");
					str.append(inputLine + "\n");
				}
			}
			webInputText.setText(str.toString());
			str.setLength(0);

			if (userSelect == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error no resource selected");
				String s = "Please choose resource from the list.";
				alert.setContentText(s);
				alert.show();
			} else if (webInputText.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Selected resource does not exist in this page");
				String s = "Please choose different resource from the list.";
				alert.setContentText(s);
				alert.show();
			}
			// closes connection to web page
			in.close();
		} catch (IOException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error cannot access webpage");
			String s = "Selected web page does not exist" + "\n" + "or there is a problem with the connection.";
			alert.setContentText(s);
			alert.show();
		}
	}

	public void setTransliterationComboBox() throws IOException {
		File[] allProperties = this.finder(Main.dataFolder);
		ObservableList transliterationRulesObsList = FXCollections.observableArrayList();
		File[] arrfile = allProperties;
		int n = arrfile.length;
		int n2 = 0;
		while (n2 < n) {
			File properties = arrfile[n2];
			String path = properties.getPath();
			String name = properties.getName();
			PropertyManager pManager = new PropertyManager(name, path);
			Transliteration rules = pManager.getRules();
			transliterationRulesObsList.add((Object) rules);
			++n2;
		}
		this.comboBoxSource.setItems(transliterationRulesObsList);
		this.comboBoxSource.setValue(transliterationRulesObsList.get(0));
	}

	public void setTransliterationTable() {

		this.tableColumnFrom.setCellValueFactory(
				cellData -> new SimpleStringProperty((String) ((Map.Entry) cellData.getValue()).getKey()));
		this.tableColumnTo.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(((Map.Entry) cellData.getValue()).getValue())));
		Transliteration chosenTransliteration = (Transliteration) this.comboBoxSource.getValue();
		LinkedHashMap<String, String> transliterationRules = chosenTransliteration.getList();
		this.tableTranslitRules.getItems().clear();
		this.tableTranslitRules.getItems().addAll(transliterationRules.entrySet());
		this.tableColumnFrom.setText("From");
		this.tableColumnTo.setText("To");

	}

	public void refreshTransliterationComboBox() throws IOException {

		Transliteration lastValue = (Transliteration) comboBoxSource.getValue();
		String name = lastValue.getName();
		ObservableList<Transliteration> oldItems = comboBoxSource.getItems();

		setTransliterationComboBox();

		ObservableList<Transliteration> newItems = comboBoxSource.getItems();
		Transliteration newItem = null;

		List<Transliteration> oldItemList = new ArrayList<Transliteration>(oldItems);
		List<Transliteration> newItemList = new ArrayList<Transliteration>(newItems);

		for (Transliteration newI : newItemList) {
			if (newI.isUnseen(oldItemList)) {
				newItem = newI;
				break;
			}
		}
		if (newItem == null) {
			for (Transliteration item : newItems) {
				System.out.println(item.getName() + " == " + name);
				if (item.getName().equals(name)) {
					comboBoxSource.setValue(item);
					break;
				}
			}
		} else {
			comboBoxSource.setValue(newItem);
		}
		setTransliterationTable();
	}

	public File[] finder(String dirName) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".properties");
			}
		});
	}

	public void addButtonAction() throws IOException {
		Stage addStage = new Stage();
		Parent webroot = FXMLLoader.load(getClass().getResource("add.fxml"));
		Font.loadFont(Main.class.getResource("voynich.ttf").toExternalForm(), 10);
		addStage.setMinWidth(420);
		addStage.setMinHeight(620);
		addStage.setTitle("Transliterate text");
		addStage.setScene(new Scene(webroot));
		addStage.show();

		addStage.setOnHiding(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						try {
							refreshTransliterationComboBox();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}

	public void deleteButtonAction() {
		// deletes selected table
		try {
			File file = new File(Main.dataFolder + "/" + comboBoxSource.getValue());
			if (comboBoxSource.getValue().toString().equals("Currier.properties")
					|| comboBoxSource.getValue().toString().equals("BasicEVA_to_ASCIIsounds.properties")
					|| comboBoxSource.getValue().toString().equals("Bennett_to_FSG.properties")
					|| comboBoxSource.getValue().toString().equals("Bennett.properties")
					|| comboBoxSource.getValue().toString().equals("FSG.properties")) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Cannot delete default table");
				alert.show();
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirm delete table");
				alert.setHeaderText("Do you want to delete " + comboBoxSource.getValue() + " table?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					file.delete();
					Alert alert2 = new Alert(AlertType.INFORMATION);
					alert2.setTitle("Success");
					alert2.setHeaderText("Table " + file.getName() + " was successfully deleted!");
					alert2.show();
					refreshOnDelete();
				}
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not delete table");
			String s = "There was a problem with delete command";
			alert.setContentText(s);
			alert.show();
			;
		}
	}

	public void refreshOnDelete() throws IOException {
		setTransliterationComboBox();
		ObservableList items = comboBoxSource.getItems();
		comboBoxSource.setValue(items.get(0));

	}

	public void transliterate() throws IOException, InterruptedException {
		String path = Main.dataFolder + "/" + comboBoxSource.getValue();
		TransliterationProcess tp = new TransliterationProcess(path, "Currier");
		outputText.setText(tp.transliterate(inputText.getText()));
	}

	public void webTransliterate() throws IOException, InterruptedException {
		String path = Main.dataFolder + "/" + comboBoxSource.getValue();
		TransliterationProcess tp = new TransliterationProcess(path, "Currier");
		webOutputText.setText(tp.transliterate(webInputText.getText()));
	}

	public void setFontToVoynich() {
		if (inputText.getFont().toString().contains("System")) {
			inputText.setFont(Font.font("Voynich"));
			toVoynich.setText("Latin");
			tableColumnFrom.setStyle("-fx-font-family: \"Voynich\"");
			tableColumnFrom.setCellFactory(getCustomCellFactory("voynich"));
		} else {
			inputText.setFont(Font.font("System"));
			toVoynich.setText("Voynich");
			tableColumnFrom.setStyle("-fx-font-family: \"System\"");
			tableColumnFrom.setCellFactory(getCustomCellFactory("System"));
		}
	}

	private Callback<TableColumn<Map.Entry<String, String>, String>, TableCell<Map.Entry<String, String>, String>> getCustomCellFactory(
			final String font) {
		return new Callback<TableColumn<Map.Entry<String, String>, String>, TableCell<Map.Entry<String, String>, String>>() {
			@Override
			public TableCell<Map.Entry<String, String>, String> call(
					TableColumn<Map.Entry<String, String>, String> param) {
				TableCell<Map.Entry<String, String>, String> cell = new TableCell<Map.Entry<String, String>, String>() {
					@Override
					public void updateItem(final String item, boolean empty) {
						if (item != null) {
							setText(item);
							setStyle("-fx-font-family: " + font + ";");
						}
					}
				};
				return cell;
			}
		};
	}
}
