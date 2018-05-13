package voynich;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		new EditFrame(f, "FSG.properties");
		f.setLocationByPlatform(true);
		f.setVisible(true);
	}

	public EditFrame(JFrame parentFrame, String fileName) {
		getContentPane().setPreferredSize(new Dimension(88, 488));
		getContentPane().setMinimumSize(new Dimension(78, 57));
		this.fileName = MainFrame.dataFolder + "/" + fileName;
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
		textArea.setText(AddController.readFromFile(this.fileName));
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

	void updateFrame(String fileName) {
		this.fileName = fileName;
		textArea.setText(AddController.readFromFile(this.fileName));
		// set scroll to start
		scr.getVerticalScrollBar().setValue(scr.getVerticalScrollBar().getMinimum());
		// set cursor to start
		textArea.setCaretPosition(0);
	}

	private void saveBtn() {
		AddController.writeToFile(fileName, textArea);
	}

}