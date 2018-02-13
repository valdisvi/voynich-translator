package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class addController {

	// add Controller
	@FXML
	Button addSaveButton;
	@FXML
	Button addCancelButton;
	@FXML
	TextArea addTableTextArea;
	@FXML
	TextField addTableNameTextField;
	@FXML
	ComboBox selectExistingTable;
	
	ObservableList transliterationRulesObsList = FXCollections.observableArrayList();
	
	public void initialize() throws IOException {
        File[] allProperties;
        File[] arrfile = allProperties = this.finder("VoynichData");
        int n = arrfile.length;
        int n2 = 0;
        while (n2 < n) {
            File properties = arrfile[n2];
            String path = properties.getPath();
            String name = properties.getName();
            PropertyManager pManager = new PropertyManager(name, path);
            Transliteration rules = pManager.getRules();
            this.transliterationRulesObsList.add((Object)rules);
            ++n2;
        }
        this.selectExistingTable.setItems(this.transliterationRulesObsList);
    }

	public File[] finder(String dirName) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".properties");
			}
		});
	}
	
	public void selectTable() throws FileNotFoundException, IOException{
		/*try(BufferedReader br = new BufferedReader(new FileReader("../VoynichData/"+this.selectExistingTable.getValue()))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    this.addTableTextArea.setText(sb.toString());
		}*/
		
		
		  Throwable throwable = null;
	        Object var2_3 = null;
	        try {
	            BufferedReader br = new BufferedReader(new FileReader("VoynichData/" + this.selectExistingTable.getValue()));
	            try {
	                StringBuilder sb = new StringBuilder();
	                String line = br.readLine();
	                while (line != null) {
	                    sb.append(line);
	                    sb.append(System.lineSeparator());
	                    line = br.readLine();
	                }
	                this.addTableTextArea.setText(sb.toString());
	            }
	            finally {
	                if (br != null) {
	                    br.close();
	                }
	            }
	        }
	        catch (Throwable throwable2) {
	            if (throwable == null) {
	                throwable = throwable2;
	            } else if (throwable != throwable2) {
	                throwable.addSuppressed(throwable2);
	            }
	           
	        }
	}
	
	public void selectAction() throws IOException{
		transliterationRulesObsList.clear();
		initialize();
	}
	
	public void addSaveButtonAction() {
			//creates new table
			if(addTableNameTextField.getText().equals("")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Table name is empty");
				String s = "Please type your table name in text field";
				alert.setContentText(s);
				alert.show();
			}else if(addTableNameTextField.getText().equals("Currier") 
					|| addTableNameTextField.getText().equals("BasicEVA_to_ASCIIsounds") 
					|| addTableNameTextField.getText().equals("Bennett_to_FSG")
					|| addTableNameTextField.getText().equals("Bennett")
					|| addTableNameTextField.getText().equals("FSG")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Cannot overwrite default table");
				String s = "Please choose a different table name";
				alert.setContentText(s);
				alert.show();
			}else if(addTableTextArea.getText().contentEquals("")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Table is empty");
				String s = "Please type transliteration rules in your table";
				alert.setContentText(s);
				alert.show();
			}else if(transliterationRulesObsList.toString().contains(addTableNameTextField.getText())){
					Alert alert = new Alert(AlertType.CONFIRMATION);
	    			alert.setTitle("Confirm table edit");
	    			alert.setHeaderText("Table " + addTableNameTextField.getText() + " already exists");
	    			alert.setContentText("Are you sure you want to overwrite table " + addTableNameTextField.getText()+"?");
	    			Optional<ButtonType> result = alert.showAndWait();
	    			if (result.get() == ButtonType.OK){
	    				writeToFile();
	    			}
	    	}else{
	    		writeToFile();
	    	}
	}
	
	public void writeToFile(){
		
	    File file = new File("VoynichData/"+this.addTableNameTextField.getText()+".properties");
	    FileWriter writer;
	    try {
	        writer = new FileWriter(file, false);
	        Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Table was successfully created");
			alert.show();
	        PrintWriter printer = new PrintWriter(writer);
	        printer.append(addTableTextArea.getText());
	        printer.close();
	        transliterationRulesObsList.clear();
	        addTableNameTextField.setText("");
			addTableTextArea.setText(null);
	    } catch (IOException e) {
	    	Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not add table");
			String s = "There was a problem with table creation";
			alert.setContentText(s);
			alert.show();
	    	}
		}
	
	public void addCancelButtonAction() {
		//closes add window
		Stage addControllerStage = (Stage) addCancelButton.getScene().getWindow();
		addControllerStage.close();
	}
}