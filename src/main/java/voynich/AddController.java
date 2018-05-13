package voynich;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * @author valdis
 *
 */
public class AddController {

	MainController controller = new MainController();

	// loads rules into Text
	void selectTable(String name, JTextArea ef) {
		ef.setText(readFromFile(name));
		ef.setCaretPosition(0);
	}

	void addTable(String name, JTextArea rules) {
		boolean checkFile = new File(MainFrame.dataFolder, name + MainController.ext).exists();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Name cannot be empty." + "\nPlease fill out the name form.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (MainController.defaultTables.contains(name + MainController.ext)) {
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

	/**
	 * FIXME this method normalizes file names coming from different methods
	 * (with or without data folder and .properties suffix
	 * 
	 * @param fileName
	 *            passed file name
	 * @return fixed file name
	 */
	private static String normalizeFileName(String fileName) {

		if (fileName.indexOf(MainFrame.dataFolder) < 0)
			fileName = MainFrame.dataFolder + "/" + fileName;
		if (fileName.indexOf(MainController.ext) < 0)
			fileName = fileName + MainController.ext;
		return fileName;
	}

	static void writeToFile(String name, JTextArea rules) {
		name = normalizeFileName(name);
		try (FileWriter out = new FileWriter(name);) {
			out.write(rules.getText());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	static String readFromFile(String fileName) {
		fileName = normalizeFileName(fileName);
		StringBuilder storeAllString = new StringBuilder("");
		try (FileReader read = new FileReader(fileName); Scanner scan = new Scanner(read);) {
			while (scan.hasNextLine())
				storeAllString.append(scan.nextLine() + "\n");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return storeAllString.toString();
	}

}
