package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class AddController {

	SwingController controller = new SwingController();
	final String dataFolder = MainFrame.dataFolder;

	// loads rules into Text
	// TODO need to show from the top not bottom of rules.
	public void selectTable(String name, JTextArea ef) throws FileNotFoundException, IOException {
		Throwable throwable = null;
		Object var2_3 = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFolder + "/" + name));
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
		boolean checkFile = new File(dataFolder, name + ".properties").exists();
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
		// confirmation for overwriting table
		else if (checkFile) {
			int check = (Integer) JOptionPane.showOptionDialog(null, "Do you want to overwrite table " + name + "?",
					"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (check == JOptionPane.YES_OPTION) {
				writeToFile(name, rules);
				// check for successful/ unsuccessful file removal
				JOptionPane.showMessageDialog(null, "Table " + name + " overwritten", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			writeToFile(name, rules);
			JOptionPane.showMessageDialog(null, "Table succesfully created", "Success!",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void writeToFile(String name, JTextArea rules) {

		try (FileWriter writer = new FileWriter(new File(dataFolder + "/" + name + ".properties"), false)) {
			PrintWriter printer = new PrintWriter(writer);
			printer.append(rules.getText());
			printer.close();
			// need to solve the problem of updating the contents after creating
			rules.setText(null);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong.\n Please try again", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
