package application;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

public class SwingAddFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	// constructor for the frame that gets created on add.
	public SwingAddFrame() {
		SwingController t = new SwingController();
		SwingAddC tc = new SwingAddC();
		setBounds(450, 130, 400, 574);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Add table..");

		JEditorPane nameEditor = new JEditorPane();

		JLabel lblAdd = new JLabel("Choose from list:");

		JComboBox comboBox = new JComboBox();
		JTextArea ruleEditor = new JTextArea();
		StringBuilder toolTipAdd = new StringBuilder();
		toolTipAdd.append(
				"<html>Create a new table in format<br>symbol=symbol;<br>if you want edit existing table, then enter existing<br>Table Name to rewrite it</html>");
		ruleEditor.setToolTipText(toolTipAdd.toString());
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object a = comboBox.getSelectedItem();
				String b = a.toString();
				try {
					tc.selectTable(b, ruleEditor);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		try {
			t.setBoxContents(comboBox);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String b = nameEditor.getText();
				tc.addTable(b, ruleEditor);
				nameEditor.setText("");
				// problem of refreshing contents of combobox
				// kinda solved by adding a "refresh" button.
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewTableName = new JLabel("New table name:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addGroup(groupLayout
						.createSequentialGroup().addGap(52).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addComponent(btnCancel)
										.addPreferredGap(ComponentPlacement.RELATED, 134, Short.MAX_VALUE).addComponent(
												btnSave, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup().addGap(32).addGroup(groupLayout
								.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewTableName)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(nameEditor, GroupLayout.PREFERRED_SIZE, 182,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblAdd)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBox,
												GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(44, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(21)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(nameEditor, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewTableName))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblAdd)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(31).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
								.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnCancel).addComponent(btnSave))
								.addGap(28)));

		scrollPane.setViewportView(ruleEditor);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}
