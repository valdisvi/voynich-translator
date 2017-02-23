package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Controller {
	
	// Controller
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
	

	// Web Controller
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
	Label labelError;
	@FXML
	TextArea webInputText;
	@FXML
	TextArea webOutputText;
	@FXML
	TextArea inputText1;
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

	public void initialize() {

		// WebController initialize
		pageNumber.setText(page);
		labelPage.setTextAlignment(TextAlignment.CENTER);
		labelPage.setText("Page number: " + page);
		labelError.setText("");
		buttonGetResult.setText("Get text");
		buttonPreviousPage.setText("Previous page");
		buttonNextPage.setText("Next page");
		buttonDelete.setText("Delete");
		comboBoxSelect.getItems().addAll("H", "C", "F", "D", "U");
		comboBoxSelect.setPromptText("Choose resource");
		labelSource.setText("Source: " + "none");

		try {
			setTransliterationComboBox();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTransliterationTable();

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
		pageId = Integer.valueOf(pageNumber.getText());
		labelPage.setText("Page number: " + pageId);
		labelError.setText(null);
		String webpage = "http://www.voynich.nu/q01/f00" + pageNumber.getText() + "v_tr.txt"; // source
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
			// prints out only selected author text without any comments and other unnecessary things
			while ((inputLine = in.readLine()) != null) {
				// removes like if it start with #
				if (inputLine.startsWith(removeStartsWith)) {
					inputLine = inputLine.replace(System.getProperty("line.separator"), "");
				}
				// removes source info part that start with <f1v.P.1;H> (as example)
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
			}else if (webInputText.getText().equals("")){
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

		File[] allProperties = finder("../VoynichData");
		ObservableList<Transliteration> transliterationRulesObsList = FXCollections.observableArrayList();
		for (File properties : allProperties) {
			String path = properties.getPath();
			String name = properties.getName();
			PropertyManager pManager = new PropertyManager(name, path);
			Transliteration rules = pManager.getRules();
			transliterationRulesObsList.add(rules);
		}
		// Sets transliterating rule list for combobox
		comboBoxSource.setItems(transliterationRulesObsList);
		comboBoxSource.setValue(transliterationRulesObsList.get(0));
	}

	public void setTransliterationTable() {

		// Populates relativity table
		tableColumnFrom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
		tableColumnTo.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getValue())));

		Transliteration chosenTransliteration = (Transliteration) comboBoxSource.getValue();
		LinkedHashMap<String, String> transliterationRules = chosenTransliteration.getList();
		tableTranslitRules.getItems().clear();
		tableTranslitRules.getItems().addAll(transliterationRules.entrySet());
		tableColumnFrom.setText("From");
		tableColumnTo.setText("To");

		// TODO check if Voynich font needed
		//tableColumnFrom.setStyle("-fx-font-family: \"Voynich\"");
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
		comboBoxSource.setValue("Press To Select Created Table");
	}
	
	public void comboBoxAction() throws IOException{
		setTransliterationComboBox();
	}
	
	public void deleteButtonAction(){
		//deletes selected table
		try{
			File file = new File("../VoynichData/"+comboBoxSource.getValue());
    		if(comboBoxSource.getValue().toString().equals("currier.properties") 
					|| comboBoxSource.getValue().toString().equals("Mayonez.properties") 
					|| comboBoxSource.getValue().toString().equals("test.properties")){
    			Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Cannot delete default table");
				alert.show();
    		}else{
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confirm delete table");
    			alert.setHeaderText("Do you want to delete " + comboBoxSource.getValue() + " table?");
    			Optional<ButtonType> result = alert.showAndWait();
    			if (result.get() == ButtonType.OK){
    				file.delete();
    				Alert alert2 = new Alert(AlertType.INFORMATION);
    				alert2.setTitle("Success");
    				alert2.setHeaderText("Table " + file.getName() + " was successfully deleted!");
    				alert2.show();
    				setTransliterationComboBox();
    			}
    		}
    	}catch(Exception e){
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not delete table");
			String s = "There was a problem with delete command";
			alert.setContentText(s);
			alert.show();;
    	}
	}
	
	public void transliterate() throws IOException, InterruptedException {
		String path = "../VoynichData/"+comboBoxSource.getValue();
		TransliterationProcess tp = new TransliterationProcess(path, "Currier");
		outputText.setText(tp.transliterate(inputText.getText()));
	}
	
	public void webTransliterate() throws IOException, InterruptedException{
		String path = "../VoynichData/"+comboBoxSource.getValue();
		TransliterationProcess tp = new TransliterationProcess(path, "Currier");
		webOutputText.setText(tp.transliterate(webInputText.getText()));
	}
	
	public void setFontToVoynich(){
		if(inputText.getFont().toString().contains("System")){
			inputText.setFont(Font.font ("Voynich"));
			toVoynich.setText("Latin");
			tableColumnFrom.setStyle("-fx-font-family: \"Voynich\"");
			tableColumnFrom.setCellFactory(getCustomCellFactory("voynich"));
		}else{
			inputText.setFont(Font.font("System"));
			toVoynich.setText("Voynich");
			tableColumnFrom.setStyle("-fx-font-family: \"System\"");
			tableColumnFrom.setCellFactory(getCustomCellFactory("System"));
		}
	}
	
	private Callback<TableColumn<Map.Entry<String, String>, String>, TableCell<Map.Entry<String, String>, String>>getCustomCellFactory(final String font){
		return new Callback<TableColumn<Map.Entry<String, String>, String>, TableCell<Map.Entry<String, String>, String>>(){
			@Override
			public TableCell<Map.Entry<String, String>, String> call(TableColumn<Map.Entry<String, String>, String> param){
				TableCell<Map.Entry<String, String>, String> cell = new TableCell<Map.Entry<String, String>, String>(){
					@Override
					public void updateItem(final String item, boolean empty){
						if(item != null){
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
