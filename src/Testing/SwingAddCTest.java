package Testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.SwingAddC;
import application.SwingController;

public class SwingAddCTest {
	SwingAddC t;
//SwingController tc;
	@Before
	public void setUp() throws Exception {
	 t= new SwingAddC();
//	 tc= new SwingController();
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void testSelectTable() throws Exception {
		String testName= "testerEmpty";
		File testFile= new File("VoynichData/" + testName + ".properties");
		JTextArea efer= new JTextArea();
		t.selectTable(testName, efer);
		assertEquals("Expected empty text pane if selected file is empty","" ,efer.getText());
		File fileContent = new File("VoynichData/" + "Bennett" + ".properties");
		t.selectTable("Bennett.properties", efer);
		assertEquals("Selected tables contents does not mach text are",this.readFile("VoynichData/Bennett.properties"),efer.getText());
	}

	@Test
	public void testAddTable() {
		String testName= "thisIsATest";
		File testFile=new File("VoynichData/" + testName + ".properties");
		JTextArea ruleTest= new JTextArea();
		ruleTest.setText("test=tset");
		t.addTable(testName,ruleTest);
		assertTrue("New table file is not created",testFile.isFile());
		testFile.delete();
		//TODO need abstraction for JOptionPane or return values to test this thoroughly
	}
	

	@Test
	public void testWriteToFile() {
		String testName= "thisIsATest";
		File testFile=new File("VoynichData/" + testName + ".properties");
		JTextArea ruleTest= new JTextArea();
		ruleTest.setText("test=tset");
		t.writeToFile(testName,ruleTest);
		assertTrue("New table file is not created",testFile.isFile());
		testFile.delete();
	}

  public String readFile(String path)
  {
    String content = null;
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(path, new String[0]));
      content = new String(encoded, StandardCharsets.UTF_8);
      content.replaceAll("\r|\n", "");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return content;
  }
}
