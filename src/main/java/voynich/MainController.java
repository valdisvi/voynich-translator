package voynich;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class MainController {

	static String authorURL = null;
	static final ArrayList<String> defaultTables = new ArrayList<>(Arrays.asList( //
			"BasicEVA_to_ASCIIsounds.properties", //
			"Bennett.properties", //
			"Bennett_to_FSG.properties", //
			"Currier.properties", //
			"FSG.properties"//
	));

	public void dataCreate() {
		// VoynichData - folder containing .properties files with
		// transliteration tables
		for (String item : defaultTables)
			makePropertyFile(item);
	}

	private void makePropertyFile(String propFileName) {
		try {
			InputStream stream = this.getClass().getResourceAsStream("/" + propFileName);
			if (stream != null) {
				new File(MainFrame.dataFolder).mkdirs();
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
				StringBuilder sb = new StringBuilder();
				String line = reader.readLine();
				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = reader.readLine();
				}
				File file = new File(MainFrame.dataFolder + "/" + propFileName);
				FileWriter writer = new FileWriter(file, false);
				PrintWriter printer = new PrintWriter(writer);
				printer.append(sb.toString());
				printer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File[] finder(String dirName) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".properties");
			}
		});
	}

	public void deleteTable(String name) {
		try {

			File file = new File(MainFrame.dataFolder + "/" + name);
			Path p = file.toPath();
			if (defaultTables.contains(name)) {
				JOptionPane.showMessageDialog(null, "Cannot delete default table.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int check = (Integer) JOptionPane.showOptionDialog(null, "Do you want to delete table " + name + "?",
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (check == JOptionPane.YES_OPTION) {
					try {
						// this is a java bug.
						// manual call to a garbage collector necessary to
						// delete file
						System.gc();
						Files.delete(p);
						// check for successful/ unsuccessful file removal
						JOptionPane.showMessageDialog(null, "Table succesfully removed", "Success!",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Could not delete table " + name, "Error",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Could not delete table" + name, "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void setBoxContents(JComboBox box) {
		try {
			box.removeAllItems();
			File[] allProperties = this.finder(MainFrame.dataFolder);
			File[] arrfile = allProperties;
			int n = arrfile.length;
			int n2 = 0;
			while (n2 < n) {
				File properties = arrfile[n2];
				String path = properties.getPath();
				String name = properties.getName();
				PropertyManager pManager;
				pManager = new PropertyManager(name, path);
				Transliteration rules = pManager.getRules();
				box.addItem(name.replace(".properties", ""));
				++n2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		MainFrame.transTableObj = box.getItemAt(0);
	}

	// gets selected value from a button group
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	// loads from the voynich.nu site
	// currently supports only pages in format f(number)(symbol).
	protected void getResult(JTextArea t, JTextPane text, String author, ButtonGroup group) {
		String version = getSelectedButtonText(group);
		StringBuilder str = new StringBuilder();
		String quire = "q01";
		int page = 0;
		double i = 0;
		String f = "f0";
		String webpage = "";
		// String webpage = "http://www.voynich.nu/q01/f00" + t.getText() +
		// version + "_tr.txt"; // source
		// authorURL = webpage;
		try {
			page = Integer.valueOf(t.getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Page number cannot be a symbol or letter", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// setting the right quire
		if (page < 9) {
			quire = "q01";
			f = "f00";
		}
		// for quires 2-7 there are 8 pages per quire
		if ((page >= 9) && (page <= 56)) {

			i = Math.ceil(page / 8.0);
			quire = "q0" + String.format("%.0f", i);
			if (page == 9) {
				f = "f00";
			}
		}

		// all of the other quires have varying amount of pages, hence the
		// coding
		// so blame the source website, not the poor coder that had to write
		// this
		if (page >= 57 && page <= 66) {
			quire = "q08";
			// f = "f00";
		}
		if ((page == 69)) {
			quire = "q10";
		}
		if (page == 71 /* || page == 72 */) {
			quire = "q11";
		}
		if (page == 73) {
			quire = "q12";
		}
		if (page >= 75 && page <= 84) {
			quire = "q13";
		}
		if (page >= 87 && page <= 88) {
			quire = "q15";
		}
		if (page == 93 || page == 94 || page == 96) {
			quire = "q17";
		}
		if (page >= 99 && page <= 100) {
			quire = "q19";
			if (page > 99) {
				f = "f";
			}
		}
		if (page >= 103 && page <= 111) {
			quire = "q20";
			f = "f";
		}

		webpage = "http://www.voynich.nu/" + quire + "/" + f + t.getText() + version + "_tr.txt";
		authorURL = webpage;
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
				// removes source info part that start with <f1v....;H>
				if (inputLine.matches("<f\\d+[v|r]\\.\\D\\.?(((\\d)|(\\d\\.\\d)));" + author + ">\\s.*")) {
					inputLine = inputLine.replaceAll("<f\\d+[v|r]\\.\\D\\.?(((\\d)|(\\d\\.\\d)));" + author + ">\\s*",
							"");
					inputLine = inputLine.replace("{plant}", "");
					str.append(inputLine + "\n");
				}
			}
			text.setText(str.toString());
			str.setLength(0);

			if (author == null) {
				JOptionPane.showMessageDialog(null, "No author selected.\nPlease choose an author from a list", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (text.getText().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Could not transliterate the page. Check if this author \n has transcribed this page.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// closes connection to web page
			in.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					"Page " + t.getText() + version
							+ " does not exist (is missing) \n or there is a problem with the connection.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void transliterate(String fileName, JTextPane in, JTextPane out) throws IOException, InterruptedException {
		String path = MainFrame.dataFolder + "/" + fileName;
		TransliterationProcess tp = new TransliterationProcess(path, "Currier");
		out.setText(tp.transliterate(in.getText()));
	}
}
