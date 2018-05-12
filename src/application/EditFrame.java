package application;

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
	private static JFrame f;
	private String fileName;
	JTextArea textArea;

	public static void main(String[] args) {
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
		textArea = new JTextArea(30, 10);
		JScrollPane scr = new JScrollPane(textArea);
		scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 12));
		textArea.setText(fileRead(fileName));
		textArea.setCaretPosition(textArea.getDocument().getLength());
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
		//revalidate();
	}

	private String fileRead(String file) {
		FileReader read;
		Scanner scan;
		StringBuilder storeAllString = new StringBuilder("");
		try {
			read = new FileReader(file);
			scan = new Scanner(read);
			while (scan.hasNextLine())
				storeAllString.append(scan.nextLine() + "\n");
			scan.close();
			read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storeAllString.toString();
	}

	private void saveBtn() {
		File file = null;
		FileWriter out = null;
		try {
			file = new File(fileName);
			out = new FileWriter(file);
			out.write(textArea.getText());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}