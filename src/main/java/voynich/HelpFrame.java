package voynich;

import java.awt.BorderLayout;
import java.io.File;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class HelpFrame extends JFrame {

	private static final long serialVersionUID = 2910370656003700433L;
	File file = new File("Welcome.html");

	public HelpFrame() throws Exception {

		DefaultMutableTreeNode topTop = new DefaultMutableTreeNode("User Guide Topics");

		createNodes(topTop);

		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		JEditorPane editorPane = new JEditorPane();

		editorPane.setEditable(false);
		JTree tree = new JTree(topTop);
		tree.setRootVisible(false);
		scrollPane.setViewportView(tree);
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		editorPane.setContentType("text/html");
		URL url = getClass().getResource(file.getName());
		editorPane.setPage(url);

		scrollPane_1.setViewportView(editorPane);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object userObj = node.getUserObject();
				File userFile = ((File) userObj);
				if (node.isLeaf()) {
					try {
						URL url = getClass().getResource(userFile.getName());
						editorPane.setPage(url);
					} catch (Exception e1) {

					}
				}
			}
		});

		setBounds(100, 100, 1024, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("User Guide");
		setVisible(true);
	}

	private void createNodes(DefaultMutableTreeNode topTop) {
		File file2 = new File("Transliteration_tab.html");
		File file3 = new File("Transliteration_Table.html");
		File file4 = new File("Web_Tab.html");
		DefaultMutableTreeNode page = null;
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(file);
		DefaultMutableTreeNode page2 = null;
		DefaultMutableTreeNode page3 = null;
		topTop.add(top);
		page = new DefaultMutableTreeNode(file2);
		topTop.add(page);
		page2 = new DefaultMutableTreeNode(file3);
		topTop.add(page2);
		page3 = new DefaultMutableTreeNode(file4);
		topTop.add(page3);

	}
}