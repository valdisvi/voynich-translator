package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class TestController {
	
	static String authorURL = null;
	

	public void dataCreate() throws IOException {
		//VoynichData - folder containing .properties files with transliteration tables
		File[] allProperties = this.finder("VoynichData");
		InputStream Currier = this.getClass().getResourceAsStream("/data/Currier.properties");
		InputStream FSG = this.getClass().getResourceAsStream("/data/FSG.properties");
		InputStream Bennett_to_FSG = this.getClass().getResourceAsStream("/data/Bennett_to_FSG.properties");
		InputStream Bennett = this.getClass().getResourceAsStream("/data/Bennett.properties");
		InputStream BasicEVA_to_ASCIIsounds = this.getClass()
				.getResourceAsStream("/data/BasicEVA_to_ASCIIsounds.properties");

		// if there are no files, create new ones.
		if (allProperties == null) {
			new File("VoynichData").mkdirs();
			BufferedReader Currierbr = new BufferedReader(new InputStreamReader(Currier));
			StringBuilder sb = new StringBuilder();
			String line = Currierbr.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = Currierbr.readLine();
			}
			File file = new File("VoynichData/Currier.properties");
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
			File file2 = new File("VoynichData/FSG.properties");
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
			File file3 = new File("VoynichData/Bennett_to_FSG.properties");
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
			File file4 = new File("VoynichData/Bennett.properties");
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
			File file5 = new File("VoynichData/BasicEVA_to_ASCIIsounds.properties");
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
			File file = new File("VoynichData/" + name);
			if (name.equals("Currier.properties") || name.equals("BasicEVA_to_ASCIIsounds.properties")
					|| name.equals("Bennett_to_FSG.properties") || name.equals("Bennett.properties")
					|| name.equals("FSG.properties")) {
				JOptionPane.showMessageDialog(null, "Cannot delete default table.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int check = (Integer) JOptionPane.showOptionDialog(null, "Do you want to delete table " + name + "?",
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (check == JOptionPane.YES_OPTION) {
					try {
						file.delete();
						//  check for successful/ unsuccessful file removal
						JOptionPane.showMessageDialog(null, "Table succesfully removed", "Success!",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Could not delete table" + name, "Error",
								JOptionPane.ERROR_MESSAGE);
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
		File[] allProperties = this.finder("VoynichData");
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
		TestApp.a = box.getItemAt(0);
	}

	protected void getResult(JTextArea t, JTextPane text, String author) {
		StringBuilder str = new StringBuilder();
		String webpage = "http://www.voynich.nu/q01/f00" + t.getText() + "v_tr.txt"; // source
		authorURL = webpage;
		// labelPage.setText("Page number: " + t.getText());
		try {
			int page = Integer.valueOf(t.getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Page number cannot be a symbol or letter", "Error",
					JOptionPane.ERROR_MESSAGE);
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
				if (inputLine.matches("<f\\d+[v|r]\\.P\\.\\d+;" + author + ">\\s.*")) {
					inputLine = inputLine.replaceAll("<f\\d+[v|r]\\.P\\.\\d+;" + author + ">\\s*", "");
					inputLine = inputLine.replace("{plant}", "");
					str.append(inputLine + "\n");
				}
			}
			text.setText(str.toString());
			str.setLength(0);

			if (author == null) {
				JOptionPane.showMessageDialog(null, "No resource selected.\nPlease choose a resouce from a list",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else if (text.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "This page does not exist\n in selected resource", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// closes connection to web page
			in.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					"Selected web page does not exist\n or there is a problem with the connection.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void transliterate(JComboBox box, JTextPane in, JTextPane out) throws IOException, InterruptedException {
		String path = "VoynichData/" + box.getSelectedItem().toString();
		TransliterationProcess tp = new TransliterationProcess(path, "Currier");
		out.setText(tp.transliterate(in.getText()));
	}
	

	public void setFont(JButton b, JTextPane text, JTable table) throws FontFormatException, IOException {
		Font v = Font.createFont(Font.TRUETYPE_FONT, new File("src/application/voynich.ttf")).deriveFont(12f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/application/voynich.ttf")));
		if (text.getFont().toString().contains("Dialog")) { //to Voynich
			text.setFont(v);
			@SuppressWarnings("serial")
			DefaultTableCellRenderer toVoynich = new DefaultTableCellRenderer() {
			    Font font = v;
			    @Override
			    public Component getTableCellRendererComponent(JTable table,
			            Object value, boolean isSelected, boolean hasFocus,
			            int row, int column) {
			        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
			                row, column);
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
