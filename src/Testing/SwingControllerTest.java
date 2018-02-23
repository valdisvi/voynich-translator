package Testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.TestAddC;
import application.TestController;
import application.TransliterationProcess;

public class TestControllerTest {
	TestController te;
	TestAddC t;
	@Before
	public void setUp() throws Exception {
		te=new TestController();
		t=new TestAddC();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDataCreate() {
		try{
			te.dataCreate();
			assertNotNull("Directory VoynichData was not created",te.finder("VoynichData"));
			assertTrue("File Currier.properties was not created",new File("VoynichData/Currier.properties").isFile());
			assertTrue("File FSG.properties was not created",new File("VoynichData/FSG.properties").isFile());
			assertTrue("File Bennett_to_FSG.properties was not created",new File("VoynichData/Bennett_to_FSG.properties").isFile());
			assertTrue("File Bennett.properties was not created",new File("VoynichData/Bennett.properties").isFile());
			assertTrue("File BasicEVA_to_ASCIIsounds.properties was not created",new File("VoynichData/BasicEVA_to_ASCIIsounds.properties").isFile());
			assertFalse("Voynich Data can not be file",new File("VoynichData").isFile());		
		}
		catch(Exception e){
			
		};		
	}

	@Test
	public void testFinder() {
		String dummy ="ReAsONAble";
		String dummy2= "voynichdata";
		assertNull("None existing directory was found",te.finder(dummy));
		assertNull("voynichdata was found instead of VoynichData",te.finder(dummy2));
		assertNotNull("VoynichData was not found",te.finder("VoynichData"));
	}

	@Test
	public void testDeleteTable() {
		String testName= "testinABC";
	//	JTextArea ruleTest= new JTextArea();
		File testFile = new File("VoynichData/" +testName+".properties");
		JTextArea ruleTest= new JTextArea("rules");
		t.writeToFile(testName, ruleTest);
	
		te.deleteTable(testName+".properties");
		assertFalse("Table was not deleted",testFile.exists());
		String wontName= "FSG.properties";
		File wontFile = new File("VoynichData/" + wontName);
		te.deleteTable(wontName);
		assertTrue("Table was no deleted",wontFile.exists());
		
	}

	@Test
	public void testSetBoxContents() throws IOException {	
			JComboBox testBox = new JComboBox();
			te.setBoxContents(testBox);
			int i= testBox.getItemCount();
			assertNotNull("ComboBox was not set", testBox);
			assertNotEquals("ComboBox was set empty", i,0);
			assertNotEquals("Too many items were set in ComboBox", i>5);
			assertNotEquals("Not all items were set in ComboBox", i<5);
			assertEquals("Expected 5 elements in ComboBox", i, 5);
		
		}			

	
	@Test
	public void testTransliterate() {
		String path= "VoynichData/FSG.properties";
		String textIn= "Reasonable appearance companions oh by remarkably me invitation understood. Pursuit elderly ask perhaps all. 246346 &&#222!!)";
		String textOut= "ReAsONAble AKKeArANCe COMKANIONs OD b2 reMArHAbl2 Me INvItAtION FNderstOOd. IKFrsFIt elderl2 AsH KerDAKs All. S4E34E (7)(7)#SSS!!)";
		try {
	
			TransliterationProcess tp = new TransliterationProcess(path, "Currier");
			assertNotNull("Transliteration process returned null",tp.transliterate(textIn));
			assertEquals("Transliteration process returned wrong value",textOut, tp.transliterate(textIn));
			assertNotEquals("Transliteration process retuned the same value",textIn, tp.transliterate(textIn));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());}
		
	
	}

	@Test
	public void testSetFont() {
		 // TODO no idea how to test this
	}

}
