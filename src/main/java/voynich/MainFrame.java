package voynich;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.Insets;
import java.awt.Dimension;

public class MainFrame {

	private JFrame mainFrame;
	static MainController controller;
	static JComboBox transTables;
	static String currentTableName = "BasicEVA_to_ASCIIsounds.properties";
	String author;
	public static final String dataFolder = "VoynichData";
	EditFrame editFrame;
	JPanel tablePanel;
	GroupLayout gl_tablePanel;
	JButton btnAdd;
	JButton btnDelete;
	JLabel lblTable;

	/**
	 * Launch the application a.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() {
		controller = new MainController();
		controller.dataCreate();
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setBounds(100, 100, 1024, 620);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Voynich Translator");

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tablePanel = new JPanel();
		JPanel translatedPanel = new JPanel();

		JButton btnHelp = new JButton("Help");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					@SuppressWarnings("unused")
					JFrame helpFrame = new HelpFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(mainFrame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(translatedPanel, GroupLayout.PREFERRED_SIZE, 657,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 659,
												Short.MAX_VALUE))
								.addGap(26))
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addComponent(btnHelp).addGap(43)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(3).addComponent(btnHelp).addGap(3)
										.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(translatedPanel,
												GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(tablePanel,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addContainerGap()));

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_translatedPanel = new GroupLayout(translatedPanel);
		gl_translatedPanel.setHorizontalGroup(gl_translatedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_translatedPanel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE).addContainerGap()));
		gl_translatedPanel.setVerticalGroup(gl_translatedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_translatedPanel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(20, Short.MAX_VALUE)));

		JTextPane txtpnText = new JTextPane();
		scrollPane_1.setViewportView(txtpnText);
		translatedPanel.setLayout(gl_translatedPanel);

		lblTable = new JLabel("Transliteration tables");
		lblTable.setHorizontalAlignment(SwingConstants.CENTER);

		btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add new table");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// calls for creation of an add frame
				@SuppressWarnings("unused")
				JFrame addFrame = new AddFrame();
			}
		});

		transTables = new JComboBox();
		transTables.setToolTipText("Choose table from list");
		// loads comboBox values
		controller.setBoxContents(transTables);

		btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Delete selected table");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String itemName = transTables.getSelectedItem().toString();
				controller.deleteTable(itemName + MainController.ext);
				controller.setBoxContents(transTables);
				// check why this throw is necessary and the response we
				// need for this.
			}
		});
		JTextPane txtpnEnterTextHere = new JTextPane();
		txtpnEnterTextHere.setText("Enter your text here...");
		txtpnEnterTextHere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (txtpnEnterTextHere.getText().equals("Enter your text here...")) {
					txtpnEnterTextHere.setText("");
				}
			}
		});

		transTables.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				// refresh table according to selection
				if (transTables.getItemCount() != 0) {
					currentTableName = transTables.getSelectedItem().toString();
					editFrame.updateFrame(MainFrame.dataFolder + "/" + currentTableName + MainController.ext);
				}
			}
		});

		JPanel textPane1 = new JPanel();
		tabbedPane.addTab("Transliterate", null, textPane1, null);

		JButton btnTrans = new JButton("Transliterate");
		btnTrans.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					txtpnText.setText("");
					controller.transliterate(transTables.getSelectedItem().toString() + MainController.ext,
							txtpnEnterTextHere, txtpnText);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_textPane1 = new GroupLayout(textPane1);
		gl_textPane1
				.setHorizontalGroup(
						gl_textPane1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_textPane1.createSequentialGroup().addContainerGap()
										.addGroup(gl_textPane1.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 630,
														Short.MAX_VALUE)
												.addComponent(btnTrans, Alignment.TRAILING))
										.addContainerGap()));
		gl_textPane1.setVerticalGroup(gl_textPane1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textPane1.createSequentialGroup().addGap(11).addComponent(btnTrans).addGap(7)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		scrollPane_2.setViewportView(txtpnEnterTextHere);
		textPane1.setLayout(gl_textPane1);

		JPanel webPanel = new JPanel();
		tabbedPane.addTab("Web", null, webPanel, null);
		JScrollPane scrollPane = new JScrollPane();
		JTextPane textPaneW = new JTextPane();
		textPaneW.setMargin(new Insets(0, 3, 3, 3));
		scrollPane.setViewportView(textPaneW);

		JButton buttonTransW = new JButton("Transliterate");
		buttonTransW.setMargin(new Insets(2, 14, 0, 14));
		buttonTransW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					txtpnText.setText("");
					controller.transliterate(transTables.getSelectedItem().toString() + MainController.ext, textPaneW,
							txtpnText);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JComboBox<String> comboBoxW = new JComboBox<>();
		comboBoxW.setMinimumSize(new Dimension(32, 20));
		StringBuilder boxHelp = new StringBuilder();
		boxHelp.append(
				"<html>Transliteration versions are marked by a <br>single capital letter. For more insight on transcription<br> versions please visit the source website.</html>");
		comboBoxW.setToolTipText(boxHelp.toString());
		comboBoxW.setEditable(true);
		// loads resources into box
		String[] resources = { "H", "F", "U", "C" };
		for (String i : resources)
			comboBoxW.addItem(i);

		JTextArea textW = new JTextArea();
		textW.setFont(new Font("Dialog", Font.BOLD, 13));
		textW.setText("1");// initialvalue
		JTextPane sourceLink = new JTextPane();
		sourceLink.setText("Source: ");

		JRadioButton pickR = new JRadioButton("r");
		JRadioButton pickV = new JRadioButton("v");
		ButtonGroup group = new ButtonGroup();
		group.add(pickR);
		group.add(pickV);
		pickV.setSelected(true);

		JButton btnGetTextW = new JButton("Get text");
		btnGetTextW.setMargin(new Insets(2, 14, 0, 14));
		btnGetTextW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				author = comboBoxW.getSelectedItem().toString();
				controller.getResult(textW, textPaneW, author, group);
				sourceLink.setText("Source: " + MainController.authorURL);
			}
		});

		BasicArrowButton btnPPage = new BasicArrowButton(BasicArrowButton.WEST);
		btnPPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// maybe put this one to controller?
				int page = Integer.parseInt(textW.getText());
				if (page > 1) {
					page--;
					textW.setText("" + page);
					author = comboBoxW.getSelectedItem().toString();
					controller.getResult(textW, textPaneW, author, group);
					sourceLink.setText("Source: " + MainController.authorURL);
				} else {
					JOptionPane.showMessageDialog(null, "Currently at first page", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnDeleteW = new JButton("Delete");
		btnDeleteW.setMargin(new Insets(2, 14, 0, 14));
		btnDeleteW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPaneW.setText(null);
				txtpnText.setText(null);
				textW.setText("1");
				sourceLink.setText("Source: ");
			}
		});

		BasicArrowButton btnNPage = new BasicArrowButton(BasicArrowButton.EAST);
		btnNPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int page = Integer.parseInt(textW.getText());
				page++;
				textW.setText("" + page);
				author = comboBoxW.getSelectedItem().toString();
				controller.getResult(textW, textPaneW, author, group);
				sourceLink.setText("Source: " + MainController.authorURL);
			}
		});

		JLabel lblPageNr = new JLabel("Page nr.:");

		JLabel lblNewLabel = new JLabel("Transcription:");
		lblNewLabel.setPreferredSize(new Dimension(99, 10));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));

		GroupLayout gl_webPanel = new GroupLayout(webPanel);
		gl_webPanel
				.setHorizontalGroup(
						gl_webPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_webPanel.createSequentialGroup()
										.addContainerGap().addGroup(gl_webPanel
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_webPanel
														.createSequentialGroup().addGroup(
																gl_webPanel.createParallelGroup(Alignment.LEADING)
																		.addComponent(scrollPane,
																				GroupLayout.DEFAULT_SIZE, 630,
																				Short.MAX_VALUE)
																		.addGroup(gl_webPanel.createSequentialGroup()
																				.addComponent(lblPageNr).addGap(2)
																				.addComponent(btnPPage,
																						GroupLayout.PREFERRED_SIZE, 24,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.RELATED)
																				.addComponent(textW,
																						GroupLayout.PREFERRED_SIZE,
																						35, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.RELATED)
																				.addComponent(btnNPage,
																						GroupLayout.PREFERRED_SIZE, 25,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addComponent(pickR).addGap(12)
																				.addComponent(pickV,
																						GroupLayout.PREFERRED_SIZE, 34,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(18).addComponent(sourceLink,
																						GroupLayout.PREFERRED_SIZE,
																						331,
																						GroupLayout.PREFERRED_SIZE)))
														.addContainerGap())
												.addGroup(gl_webPanel.createSequentialGroup().addGap(28)
														.addComponent(lblNewLabel)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(comboBoxW, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 127,
																Short.MAX_VALUE)
														.addComponent(btnGetTextW).addGap(18).addComponent(btnDeleteW)
														.addGap(18)
														.addComponent(buttonTransW, GroupLayout.PREFERRED_SIZE, 127,
																GroupLayout.PREFERRED_SIZE)
														.addGap(19)))));
		gl_webPanel.setVerticalGroup(gl_webPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_webPanel
				.createSequentialGroup()
				.addGroup(gl_webPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_webPanel.createSequentialGroup().addGap(18).addComponent(lblNewLabel))
						.addGroup(gl_webPanel.createSequentialGroup().addGap(14)
								.addGroup(gl_webPanel.createParallelGroup(Alignment.BASELINE).addComponent(buttonTransW)
										.addComponent(btnDeleteW).addComponent(btnGetTextW)))
						.addGroup(gl_webPanel.createSequentialGroup().addGap(14).addComponent(comboBoxW,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_webPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_webPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_webPanel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_webPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNPage, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
										.addComponent(btnPPage, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
										.addGroup(gl_webPanel.createSequentialGroup().addGap(16)
												.addComponent(lblPageNr))))
						.addGroup(gl_webPanel.createSequentialGroup().addGap(15).addComponent(textW,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_webPanel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_webPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(sourceLink, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_webPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(pickV).addComponent(pickR)))))
				.addContainerGap()));

		editFrame = new EditFrame(mainFrame, currentTableName);
		gl_tablePanel = new GroupLayout(tablePanel);
		gl_tablePanel.setHorizontalGroup(gl_tablePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tablePanel.createSequentialGroup().addGap(75).addComponent(lblTable).addContainerGap(89,
						Short.MAX_VALUE))
				.addGroup(gl_tablePanel.createSequentialGroup().addGap(16)
						.addComponent(transTables, 0, 289, Short.MAX_VALUE).addGap(14))
				.addGroup(gl_tablePanel.createSequentialGroup().addGap(17)
						.addComponent(editFrame, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(17, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_tablePanel.createSequentialGroup().addGap(17)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addGap(15)));
		gl_tablePanel.setVerticalGroup(gl_tablePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tablePanel.createSequentialGroup().addComponent(lblTable)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(transTables, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
						.addGroup(gl_tablePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDelete, Alignment.TRAILING).addComponent(btnAdd, Alignment.TRAILING))
						.addGap(10).addComponent(editFrame, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
						.addGap(8)));
		tablePanel.setLayout(gl_tablePanel);
		webPanel.setLayout(gl_webPanel);

		mainFrame.getContentPane().setLayout(groupLayout);
	}

	static void refresh() {
		controller.setBoxContents(transTables);
	}
}
