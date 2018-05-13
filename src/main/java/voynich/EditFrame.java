package voynich;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class EditFrame extends JInternalFrame {
	private static final long serialVersionUID = -5687409246419987970L;

	private String fileName;
	JTextArea textArea;
	JScrollPane scr;

	/**
	 * This method is only for testing purposes
	 */
	public static void main(String[] args) {
		JFrame f;
		f = new JFrame("Application");
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new EditFrame(f, "VoynichData/FSG.properties");
		f.setLocationByPlatform(true);
		f.setVisible(true);
	}

	public EditFrame(JFrame parentFrame, String fileName) {
		getContentPane().setPreferredSize(new Dimension(88, 488));
		getContentPane().setMinimumSize(new Dimension(78, 57));
		this.fileName = fileName;
		BasicInternalFrameUI bi = (BasicInternalFrameUI) getUI();
		bi.setNorthPane(null);
		setBorder(null);
		JPanel panel = new JPanel();
		textArea = new JTextArea();
		scr = new JScrollPane(textArea);
		scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		textArea.setText(fileRead(fileName));
		// set scroll to start
		scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getMinimum());
		// set cursor to start
		textArea.setCaretPosition(0);
		getContentPane().add(panel, BorderLayout.SOUTH);
		getContentPane().add(scr, BorderLayout.CENTER);
		pack();
		Dimension d = scr.getPreferredSize();
		setSize(d.width, d.height);
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveBtn();
			}
		});
		panel.add(saveButton, BorderLayout.WEST);
		setVisible(true);
		parentFrame.getContentPane().add(this);
	}

	String fileRead(String file) {
		StringBuilder storeAllString = new StringBuilder("");
		try (FileReader read = new FileReader(file); Scanner scan = new Scanner(read);) {
			while (scan.hasNextLine())
				storeAllString.append(scan.nextLine() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storeAllString.toString();
	}

	void updateFrame(String fileName) {
		this.fileName = fileName;
		textArea.setText(fileRead(this.fileName));
		// set scroll to start
		scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getMinimum());
		// set cursor to start
		textArea.setCaretPosition(0);
	}

	private void saveBtn() {
		File file = new File(fileName);
		try (FileWriter out = new FileWriter(file);) {
			out.write(textArea.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}