package application;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
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
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;

public class SwingController {

	static String authorURL = null;

	public void dataCreate() throws IOException {
		// VoynichData - folder containing .properties files with
		// transliteration tables
		File[] allProperties = this.finder(SwingApp.dataFolder);
		File test = new File (SwingApp.dataFolder + "/Currier.properties");
		System.out.println(test.getAbsolutePath() + " " + test.canWrite());
		InputStream Currier = this.getClass().getResourceAsStream(SwingApp.dataFolder + "/Currier.properties");
		InputStream FSG = this.getClass().getResourceAsStream(SwingApp.dataFolder + "/FSG.properties");
		InputStream Bennett_to_FSG = this.getClass()
				.getResourceAsStream(SwingApp.dataFolder + "/Bennett_to_FSG.properties");
		InputStream Bennett = this.getClass().getResourceAsStream(SwingApp.dataFolder + "/Bennett.properties");
		InputStream BasicEVA_to_ASCIIsounds = this.getClass()
				.getResourceAsStream(SwingApp.dataFolder + "/BasicEVA_to_ASCIIsounds.properties");

		// if there are no files, create some.
		if (allProperties == null) {
			new File(SwingApp.dataFolder).mkdirs();
			BufferedReader Currierbr = new BufferedReader(new InputStreamReader(Currier));
			StringBuilder sb = new StringBuilder();
			String line = Currierbr.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = Currierbr.readLine();
			}
			File file = new File(SwingApp.dataFolder + "/Currier.properties");
			FileWriter writer = new FileWriter(file, false);
			PrintWriter printer = new PrintWriter(writer);
			printer.append(sb.toString());
			printer.close();
			BufferedReader FSGbr = new BufferedReader(new InputStreamReader(FSG));
			StringBuilder sb2 = new StringBuilder();
			String line2 = FSGbr.readLine();
			while (line2 != null) {
				sb2.append(line2);
				sb2.append(System.lineSeparator());
				line2 = FSGbr.readLine();
			}
			File file2 = new File(SwingApp.dataFolder + "/FSG.properties");
			FileWriter writer2 = new FileWriter(file2, false);
			PrintWriter printer2 = new PrintWriter(writer2);
			printer2.append(sb2.toString());
			printer2.close();
			BufferedReader Bennett_to_FSGbr = new BufferedReader(new InputStreamReader(Bennett_to_FSG));
			StringBuilder sb3 = new StringBuilder();
			String line3 = Bennett_to_FSGbr.readLine();
			while (line3 != null) {
				sb3.append(line3);
				sb3.append(System.lineSeparator());
				line3 = Bennett_to_FSGbr.readLine();
			}
			File file3 = new File(SwingApp.dataFolder + "/Bennett_to_FSG.properties");
			FileWriter writer3 = new FileWriter(file3, false);
			PrintWriter printer3 = new PrintWriter(writer3);
			printer3.append(sb3.toString());
			printer3.close();
			BufferedReader Bennettbr = new BufferedReader(new InputStreamReader(Bennett));
			StringBuilder sb4 = new StringBuilder();
			String line4 = Bennettbr.readLine();
			while (line4 != null) {
				sb4.append(line4);
				sb4.append(System.lineSeparator());
				line4 = Bennettbr.readLine();
			}
			File file4 = new File(SwingApp.dataFolder + "/Bennett.properties");
			FileWriter writer4 = new FileWriter(file4, false);
			PrintWriter printer4 = new PrintWriter(writer4);
			printer4.append(sb4.toString());
			printer4.close();
			BufferedReader BasicEVA_to_ASCIIsoundsbr = new BufferedReader(
					new InputStreamReader(BasicEVA_to_ASCIIsounds));
			StringBuilder sb5 = new StringBuilder();
			String line5 = BasicEVA_to_ASCIIsoundsbr.readLine();
			while (line5 != null) {
				sb5.append(line5);
				sb5.append(System.lineSeparator());
				line5 = BasicEVA_to_ASCIIsoundsbr.readLine();
			}
			File file5 = new File(SwingApp.dataFolder + "/BasicEVA_to_ASCIIsounds.properties");
			FileWriter writer5 = new FileWriter(file5, false);
			PrintWriter printer5 = new PrintWriter(writer5);
			printer5.append(sb5.toString());
			printer5.close();
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

			File file = new File(SwingApp.dataFolder + "/" + name);
			Path p = file.toPath();
			if (name.equals("Currier.properties") || name.equals("BasicEVA_to_ASCIIsounds.properties")
					|| name.equals("Bennett_to_FSG.properties") || name.equals("Bennett.properties")
					|| name.equals("FSG.properties")) {
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
						JOptionPane.showMessageDialog(null, "Could not delete table" + name, "Error",
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

	public void setBoxContents(JComboBox box) throws IOException {
		box.removeAllItems();
		File[] allProperties = this.finder(SwingApp.dataFolder);
		File[] arrfile = allProperties;
		int n = arrfile.length;
		int n2 = 0;
		while (n2 < n) {
			File properties = arrfile[n2];
			String path = properties.getPath();
			String name = properties.getName();
			PropertyManager pManager = new PropertyManager(name, path);
			Transliteration rules = pManager.getRules();
			box.addItem(rules);
			++n2;
		}
		SwingApp.transTableObj = box.getItemAt(0);
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

	public void transliterate(JComboBox box, JTextPane in, JTextPane out) throws IOException, InterruptedException {
		String path = SwingApp.dataFolder + "/" + box.getSelectedItem().toString();
		TransliterationProcess tp = new TransliterationProcess(path, "Currier");
		out.setText(tp.transliterate(in.getText()));
	}

	public void setFont(JButton b, JTextPane text, JTable table) throws FontFormatException, IOException {
		Font v = Font.createFont(Font.TRUETYPE_FONT,
				getClass().getClassLoader().getResourceAsStream("application/voynich.ttf")).deriveFont(12f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
				getClass().getClassLoader().getResourceAsStream("application/voynich.ttf")));
		if (text.getFont().toString().contains("Dialog")) { // to Voynich
			text.setFont(v);
			@SuppressWarnings("serial")
			DefaultTableCellRenderer toVoynich = new DefaultTableCellRenderer() {
				Font font = v;

				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					setFont(font);
					return this;
				}
			};
			table.getColumnModel().getColumn(0).setCellRenderer(toVoynich);
			table.repaint();
			b.setText("Latin");
		} else { // to Latin
			text.setFont(new Font("Dialog", Font.PLAIN, 12));
			table.getColumnModel().getColumn(0).setCellRenderer(null);
			table.setFont(new Font("Dialog", Font.PLAIN, 12));
			table.repaint();
			b.setText("Voynich");
		}
	}

}
