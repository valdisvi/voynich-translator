package application;

import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TestApp {

	private JFrame mainFrame;
	private TestController t;
	JComboBox comboBox;
	String author;
	private JTable tableTrans;
	public static Object a ;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TestApp window = new TestApp();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public TestApp() throws IOException {
		t = new TestController();
		t.dataCreate();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		t = new TestController();
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 1024, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Voynich Translator");

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);

		JPanel tablePanel = new JPanel();

		JPanel translatedPanel = new JPanel();
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					@SuppressWarnings("unused")
					JFrame helpFrame = new TestHelpFrame();
					} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(mainFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnHelp)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(translatedPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 637, Short.MAX_VALUE)))
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnHelp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(translatedPanel, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_translatedPanel = new GroupLayout(translatedPanel);
		gl_translatedPanel.setHorizontalGroup(
			gl_translatedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_translatedPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_translatedPanel.setVerticalGroup(
			gl_translatedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_translatedPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		JTextPane txtpnText = new JTextPane();
		scrollPane_1.setViewportView(txtpnText);
		translatedPanel.setLayout(gl_translatedPanel);

		JLabel lblTable = new JLabel("Transliteration tables");
		lblTable.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add new table");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// calls for creation of another frame
				@SuppressWarnings("unused")
				JFrame addFrame = new TestAddFrame();
			}
		});

		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Choose table from list");
		// loads comboBox values
		t.setBoxContents(comboBox);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Delete selected table");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object a = comboBox.getSelectedItem();
				String b = a.toString();
				t.deleteTable(b);
				try {
					t.setBoxContents(comboBox);
					// check why this throw is necessary and the response we
					// need for this.
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		JTextPane txtpnEnterTextHere = new JTextPane();
		txtpnEnterTextHere.setText("Enter your text here...");
		txtpnEnterTextHere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (txtpnEnterTextHere.getText().equals("Enter your text here...")) {
				txtpnEnterTextHere.setText("");}
			}
		});
		
		TestTableModel modelView = new TestTableModel();
		tableTrans = new JTable(modelView);
		tableTrans.setFillsViewportHeight(true);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				//checks for change in selected item that is not caused by reloading
				//refreshes table according to selection
				if (comboBox.getItemCount() != 0){
				a = comboBox.getSelectedItem();
				TestTableModel model = new TestTableModel();
				tableTrans.setModel(model);
				}
			}
		});
		
		JButton refresh = new JButton("Refresh");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					t.setBoxContents(comboBox);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		refresh.setToolTipText("Refresh tables after adding");
		
		JPanel textPane1 = new JPanel();
		tabbedPane.addTab("Transliterate", null, textPane1, null);

		JButton btnVoynich = new JButton("Voynich");
		btnVoynich.setToolTipText("Switch between fonts");
		btnVoynich.addMouseListener(new MouseAdapter() {
			@Override
			// on click calls font change method
			public void mouseClicked(MouseEvent arg0) {
				try {
					t.setFont(btnVoynich, txtpnEnterTextHere, tableTrans);
				} catch (FontFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		JButton btnTrans = new JButton("Transliterate");
		btnTrans.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					txtpnText.setText("");
					t.transliterate(comboBox, txtpnEnterTextHere, txtpnText);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_textPane1 = new GroupLayout(textPane1);
		gl_textPane1.setHorizontalGroup(
			gl_textPane1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textPane1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_textPane1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_textPane1.createSequentialGroup()
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_textPane1.createSequentialGroup()
							.addComponent(btnVoynich)
							.addPreferredGap(ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
							.addComponent(btnTrans)
							.addGap(33))))
		);
		gl_textPane1.setVerticalGroup(
			gl_textPane1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textPane1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_textPane1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoynich)
						.addComponent(btnTrans))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		
		scrollPane_2.setViewportView(txtpnEnterTextHere);
		textPane1.setLayout(gl_textPane1);

		JPanel webPanel = new JPanel();
		tabbedPane.addTab("Web", null, webPanel, null);
		JScrollPane scrollPane = new JScrollPane();
		JTextPane textPaneW = new JTextPane();
		scrollPane.setViewportView(textPaneW);
		
		
		JButton buttonTransW = new JButton("Transliterate");
		buttonTransW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					txtpnText.setText("");
					t.transliterate(comboBox, textPaneW, txtpnText);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}

			}
		});

		JComboBox comboBoxW = new JComboBox();
		//loads resources into box
		comboBoxW.addItem("H");
		comboBoxW.addItem("C");
		comboBoxW.addItem("F");
		comboBoxW.addItem("D");
		comboBoxW.addItem("U");

		

		JTextArea textW = new JTextArea();
		textW.setText("1");// initialvalue
		JTextPane sourceLink = new JTextPane();
		sourceLink.setText("Source: ");
		
		JButton btnGetTextW = new JButton("Get text");
		btnGetTextW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				author = comboBoxW.getSelectedItem().toString();
				t.getResult(textW, textPaneW, author);
				sourceLink.setText("Source: " + TestController.authorURL.toString());
			}
		});

		JButton btnPPage = new JButton("Previous page");
		btnPPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//maybe put this one to controller?
				int page = Integer.valueOf(textW.getText());
				if (page > 1) {
					page--;
					textW.setText("" + page);
					author = comboBoxW.getSelectedItem().toString();
					t.getResult(textW, textPaneW, author);
					sourceLink.setText("Source: " + TestController.authorURL.toString());
				} else {
					JOptionPane.showMessageDialog(null, "Currently at first page", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnDeleteW = new JButton("Delete");
		btnDeleteW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPaneW.setText(null);
				txtpnText.setText(null);
				textW.setText("1");
				sourceLink.setText("Source: none");
			}
		});

		JButton btnNPage = new JButton("Next page");
		btnNPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int page = Integer.valueOf(textW.getText());
				page++;
				textW.setText("" + page);
				author = comboBoxW.getSelectedItem().toString();
				t.getResult(textW, textPaneW, author);
				sourceLink.setText("Source: " + TestController.authorURL.toString());
			}
		});

		JLabel lblPageNr = new JLabel("Page nr.:");
		
		GroupLayout gl_webPanel = new GroupLayout(webPanel);
		gl_webPanel.setHorizontalGroup(
			gl_webPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_webPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_webPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_webPanel.createSequentialGroup()
							.addGroup(gl_webPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
								.addGroup(gl_webPanel.createSequentialGroup()
									.addComponent(btnPPage)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNPage)
									.addGap(28)
									.addComponent(sourceLink, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(gl_webPanel.createSequentialGroup()
							.addComponent(comboBoxW, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPageNr)
							.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
							.addComponent(textW, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnGetTextW)
							.addGap(18)
							.addComponent(btnDeleteW)
							.addGap(18)
							.addComponent(buttonTransW, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(30))))
		);
		gl_webPanel.setVerticalGroup(
			gl_webPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_webPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_webPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonTransW)
						.addComponent(btnDeleteW)
						.addComponent(btnGetTextW)
						.addComponent(textW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPageNr))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_webPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_webPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnPPage)
							.addComponent(btnNPage))
						.addComponent(sourceLink, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		
		
		JScrollPane scrollPane_3 = new JScrollPane();
		// panel for table
		GroupLayout gl_tablePanel = new GroupLayout(tablePanel);
		gl_tablePanel.setHorizontalGroup(
			gl_tablePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tablePanel.createSequentialGroup()
					.addGroup(gl_tablePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tablePanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_tablePanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBox, Alignment.LEADING, 0, 295, Short.MAX_VALUE)
								.addGroup(gl_tablePanel.createSequentialGroup()
									.addComponent(btnAdd)
									.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
									.addComponent(btnDelete)
									.addGap(29)
									.addComponent(refresh)))
							.addGap(6))
						.addGroup(gl_tablePanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
						.addGroup(gl_tablePanel.createSequentialGroup()
							.addGap(75)
							.addComponent(lblTable)))
					.addContainerGap())
		);
		gl_tablePanel.setVerticalGroup(
			gl_tablePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tablePanel.createSequentialGroup()
					.addComponent(lblTable)
					.addGap(22)
					.addGroup(gl_tablePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(refresh)
						.addComponent(btnAdd)
						.addComponent(btnDelete))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		scrollPane_3.setViewportView(tableTrans);
		tablePanel.setLayout(gl_tablePanel);
		webPanel.setLayout(gl_webPanel);

		mainFrame.getContentPane().setLayout(groupLayout);
	}
}
