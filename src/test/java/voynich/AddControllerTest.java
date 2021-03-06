package voynich;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddControllerTest {
	AddController t;

	// SwingController tc;
	@Before
	public void setUp() throws Exception {
		MainController main = new MainController();
		main.dataCreate();
		t = new AddController();
		
		// tc= new SwingController();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testSelectTable() throws Exception {
		String testName = "testerEmpty.properties";
		File testFile = new File(MainFrame.dataFolder + "/" + testName);
		JTextArea efer = new JTextArea();
		t.selectTable(testName, efer);
		assertEquals("Expected empty text pane if selected file is empty", "", efer.getText());
		File fileContent = new File(MainFrame.dataFolder + "/" + "Bennett.properties");
		t.selectTable("Bennett.properties", efer);
		assertEquals("Selected tables contents does not mach text are",
				this.readFile(MainFrame.dataFolder + "/Bennett.properties"), efer.getText());
	}

	@Test
	public void testAddTable() {
		String testName = "thisIsATest";
		File testFile = new File(MainFrame.dataFolder + "/" + testName + MainController.ext);
		JTextArea ruleTest = new JTextArea();
		ruleTest.setText("test=tset");
		t.addTable(testName, ruleTest);
		assertTrue("New table file is not created", testFile.isFile());
		testFile.delete();
		// TODO need abstraction for JOptionPane or return values to test this
		// thoroughly
	}

	@Test
	public void testWriteToFile() {
		String testName = "thisIsATest";
		File testFile = new File(MainFrame.dataFolder + "/" + testName + MainController.ext);
		JTextArea ruleTest = new JTextArea();
		ruleTest.setText("test=tset");
		AddController.writeToFile(testName, ruleTest);
		assertTrue("New table file is not created", testFile.isFile());
		testFile.delete();
	}

	public String readFile(String path) {
		String content = null;
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path, new String[0]));
			content = new String(encoded, StandardCharsets.UTF_8);
			content.replaceAll("\r|\n", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
