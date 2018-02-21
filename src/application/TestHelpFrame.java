package application;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class TestHelpFrame extends JFrame{
	File file= new File("Welcome.html");

	public TestHelpFrame() throws Exception, IOException{
		
		DefaultMutableTreeNode topTop = new DefaultMutableTreeNode("User Guide Topics");
		
		createNodes(topTop);
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		JEditorPane editorPane = new JEditorPane();
	
		editorPane.setEditable(false);
		JTree tree = new JTree(topTop);
		scrollPane.setViewportView(tree);
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		editorPane.setContentType("text/html");
		editorPane.setPage(file.toURI().toURL());
		
		scrollPane_1.setViewportView(editorPane);	
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (node==null ) return;
					Object userObj= node.getUserObject();
				if (node.isLeaf()){
					try {
						editorPane.setPage(((File) userObj).toURI().toURL());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			};
		}});
		
		
		
		setBounds(100, 100, 1024, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("User Guide");	
		setVisible(true);
	}
	private void createNodes(DefaultMutableTreeNode topTop) {
		File file2= new File("Transliteration Tab.html");
	    DefaultMutableTreeNode page = null;
	    DefaultMutableTreeNode	top=new DefaultMutableTreeNode(file);
	    topTop.add(top);
	    page = new DefaultMutableTreeNode(file2);
	    topTop.add(page);

	}
	}