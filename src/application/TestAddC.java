package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Optional;

import javax.swing.*;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class TestAddC {

	TestController t = new TestController();

	// loads rules into Text
	// TODO need to show from the top not bottom of rules.
	public void selectTable(String name, JTextArea ef) throws FileNotFoundException, IOException {
		Throwable throwable = null;
		Object var2_3 = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("VoynichData/" + name));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}

				ef.setText(sb.toString());
			} finally {
				if (br != null) {
					br.close();
				}
			}
		} catch (Throwable throwable2) {
			if (throwable == null) {
				throwable = throwable2;
			} else if (throwable != throwable2) {
				throwable.addSuppressed(throwable2);
			}
		}
	}

	public void addTable(String name, JTextArea rules) {
		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Name cannot be empty." + "\nPlease fill out the name form.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (name.equals("Currier") || name.equals("BasicEVA_to_ASCIIsounds") || name.equals("Bennett_to_FSG")
				|| name.equals("Bennett") || name.equals("FSG")) {
			JOptionPane.showMessageDialog(null, "Cannot overwrite default table." + "\nPlease choose a different name",
					"Default name used", JOptionPane.ERROR_MESSAGE);
		} else if (rules.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Table cannot be empty" + "\nPlease fill out the rules.", "Empty table",
					JOptionPane.ERROR_MESSAGE);
		}
		// required to ask confirmation for rewriting table, but im lazy and
		// confused atm.
		/*
		 * else if (!(rules.getText().isEmpty())){ Alert alert = new
		 * Alert(AlertType.CONFIRMATION); alert.setTitle("Confirm table edit");
		 * alert.setHeaderText("Table " + addTableNameTextField.getText() +
		 * " already exists");
		 * alert.setContentText("Are you sure you want to overwrite table " +
		 * addTableNameTextField.getText()+"?"); Optional<ButtonType> result =
		 * alert.showAndWait(); if (result.get() == ButtonType.OK){
		 * writeToFile(); } }
		 */else {
			writeToFile(name, rules);
		}

	}

	public void writeToFile(String name, JTextArea rules) {

		File file = new File("VoynichData/" + name + ".properties");
		FileWriter writer;
		try {
			writer = new FileWriter(file, false);
			JOptionPane.showMessageDialog(null, "Table succesfully created", "Success!",
					JOptionPane.INFORMATION_MESSAGE);
			PrintWriter printer = new PrintWriter(writer);
			printer.append(rules.getText());
			printer.close();
			// need to solve the problem of updating the contents after creating
			rules.setText(null);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong.\n Please try again" ,"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
