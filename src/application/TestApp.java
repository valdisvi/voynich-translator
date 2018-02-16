package application;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextPane;
import javax.swing.JTextArea;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestApp {

	private JFrame mainFrame;
	private JTable tableTrans;
	public TestController t;
	JComboBox comboBox;
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
	 * @throws IOException 
	 */
	public TestApp() throws IOException {
		t = new TestController();
		t.dataCreate();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
		GroupLayout groupLayout = new GroupLayout(mainFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(translatedPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE))
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(translatedPanel, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
						.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JTextPane txtpnText = new JTextPane();
		GroupLayout gl_translatedPanel = new GroupLayout(translatedPanel);
		gl_translatedPanel.setHorizontalGroup(
			gl_translatedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_translatedPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(txtpnText, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		gl_translatedPanel.setVerticalGroup(
			gl_translatedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_translatedPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnText, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
					.addContainerGap())
		);
		translatedPanel.setLayout(gl_translatedPanel);
		
		JLabel lblTable = new JLabel("Transliteration tables");
		lblTable.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFrame addFrame = new TestAddFrame();
			}
		});
		
		JComboBox comboBox = new JComboBox();
		//loads comboBox values
		t.setBoxContents(comboBox);
	        
	    JButton btnDelete = new JButton("-");   
	    btnDelete.addMouseListener(new MouseAdapter() {
	    		@Override
				public void mouseClicked(MouseEvent e) {
					Object a = comboBox.getSelectedItem();
					String b = a.toString();
					t.deleteTable(b);
					try {
						t.setBoxContents(comboBox);
						//check why this throw is necessary and the response we need for this.
					} catch (IOException e1) {e1.printStackTrace();}
					
	    	}
	    });
	        
		//transliteration table testing 
	    String[] columnNames = {"From", "To"};
	    Object nnn = comboBox.getSelectedItem();
	    File[] allProperties =  t.finder("VoynichData");
	    for (File f : allProperties) {
	    	if (nnn.toString().equals(f.getName()))
	    	{
	    		PropertyManager p = new PropertyManager(f.getName(),f.getPath());
	    		
	    	}
	    }
	   // System.out.println(nnn);
		tableTrans = new JTable();
		
		
		
		//panel for table
		GroupLayout gl_tablePanel = new GroupLayout(tablePanel);
		gl_tablePanel.setHorizontalGroup(
			gl_tablePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tablePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, 0, 205, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addContainerGap())
				.addGroup(gl_tablePanel.createSequentialGroup()
					.addGap(12)
					.addComponent(tableTrans, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_tablePanel.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addComponent(lblTable)
					.addGap(81))
		);
		gl_tablePanel.setVerticalGroup(
			gl_tablePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tablePanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblTable)
					.addGap(11)
					.addGroup(gl_tablePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tablePanel.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_tablePanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDelete)
								.addComponent(btnAdd)))
						.addGroup(gl_tablePanel.createSequentialGroup()
							.addGap(6)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableTrans, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_tablePanel.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnDelete, btnAdd});
		tablePanel.setLayout(gl_tablePanel);
		
		JPanel inputPanel = new JPanel();
		tabbedPane.addTab("Transliterate", null, inputPanel, null);
		
		JButton btnVoynich = new JButton("Voynich");
		
		JTextPane textPane = new JTextPane();
		
		JButton btnTrans = new JButton("Transliterate");
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addComponent(btnVoynich)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnTrans)))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		gl_inputPanel.setVerticalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoynich)
						.addComponent(btnTrans))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addContainerGap())
		);
		inputPanel.setLayout(gl_inputPanel);
		
		JPanel webPanel = new JPanel();
		tabbedPane.addTab("Web", null, webPanel, null);
		
		JTextPane textPaneW = new JTextPane();
		
		JButton buttonTransW = new JButton("Transliterate");
		
		JComboBox comboBoxW = new JComboBox();
		
		JLabel lblW = new JLabel("Source: " + "none");
		
		JTextArea textW = new JTextArea();
		
		JButton btnGetTextW = new JButton("Get text");
		btnGetTextW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		
		JButton btnPPage = new JButton("Previous page");
		
		JButton btnDeleteW = new JButton("Delete");
		btnDeleteW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object a = comboBox.getSelectedItem();
				String b = a.toString();
				//JFrame delete = new TestDeleteFrame(b);
				
			}
		});
		
		JButton btnNPage = new JButton("Next page");
		GroupLayout gl_webPanel = new GroupLayout(webPanel);
		gl_webPanel.setHorizontalGroup(
			gl_webPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_webPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_webPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_webPanel.createSequentialGroup()
							.addComponent(comboBoxW, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblW)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textW, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnGetTextW)
							.addGap(18)
							.addComponent(buttonTransW, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(66, Short.MAX_VALUE))
						.addGroup(gl_webPanel.createSequentialGroup()
							.addGroup(gl_webPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_webPanel.createSequentialGroup()
									.addComponent(btnPPage)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNPage)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDeleteW))
								.addComponent(textPaneW, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_webPanel.setVerticalGroup(
			gl_webPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_webPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_webPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonTransW)
						.addComponent(comboBoxW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblW)
						.addComponent(textW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGetTextW))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(textPaneW, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_webPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPPage)
						.addComponent(btnNPage)
						.addComponent(btnDeleteW))
					.addGap(10))
		);
		webPanel.setLayout(gl_webPanel);
		
		
		
		
		
		mainFrame.getContentPane().setLayout(groupLayout);
		
		
	}

	
}
