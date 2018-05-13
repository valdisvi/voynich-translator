package voynich;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainControllerTest {
	MainController te;
	AddController t;

	@Before
	public void setUp() throws Exception {
		te = new MainController();
		t = new AddController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDataCreate() {
		try {
			te.dataCreate();
			assertNotNull("Directory VoynichData was not created", te.finder(MainFrame.dataFolder));
			assertTrue("File Currier.properties was not created",
					new File(MainFrame.dataFolder + "/Currier.properties").isFile());
			assertTrue("File FSG.properties was not created",
					new File(MainFrame.dataFolder + "/FSG.properties").isFile());
			assertTrue("File Bennett_to_FSG.properties was not created",
					new File(MainFrame.dataFolder + "/Bennett_to_FSG.properties").isFile());
			assertTrue("File Bennett.properties was not created",
					new File(MainFrame.dataFolder + "/Bennett.properties").isFile());
			assertTrue("File BasicEVA_to_ASCIIsounds.properties was not created",
					new File(MainFrame.dataFolder + "/BasicEVA_to_ASCIIsounds.properties").isFile());
			assertFalse("Voynich Data can not be file", new File(MainFrame.dataFolder).isFile());
		} catch (Exception e) {

		}
		;
	}

	@Test
	public void testFinder() {
		String dummy = "ReAsONAble";
		String dummy2 = "voynichdata";
		assertNull("None existing directory was found", te.finder(dummy));
		assertNull("voynichdata was found instead of VoynichData", te.finder(dummy2));
		assertNotNull(MainFrame.dataFolder + " was not found", te.finder(MainFrame.dataFolder));
	}

	@Test
	public void testDeleteTable() {
		String testName = "testinABC";
		// JTextArea ruleTest= new JTextArea();
		File testFile = new File(MainFrame.dataFolder + "/" + testName + MainController.ext);
		JTextArea ruleTest = new JTextArea("rules");
		AddController.writeToFile(testName, ruleTest);

		te.deleteTable(testName + MainController.ext);
		assertFalse("Table was not deleted", testFile.exists());
		String wontName = "FSG.properties";
		File wontFile = new File(MainFrame.dataFolder + "/" + wontName);
		te.deleteTable(wontName);
		assertTrue("Table was no deleted", wontFile.exists());

	}

	@Test
	public void testSetBoxContents() throws IOException {
		JComboBox testBox = new JComboBox();
		te.setBoxContents(testBox);
		int i = testBox.getItemCount();
		assertNotNull("ComboBox was not set", testBox);
		assertNotEquals("ComboBox was set empty", i, 0);
		assertNotEquals("Too many items were set in ComboBox", i > 5);
		assertNotEquals("Not all items were set in ComboBox", i < 5);
		assertEquals("Expected 5 elements in ComboBox", 5, i);

	}

	@Test
	public void testTransliterate() {
		String path = MainFrame.dataFolder + "/FSG.properties";
		String textIn = "Reasonable appearance companions oh by remarkably me invitation understood. Pursuit elderly ask perhaps all. 246346 &&#222!!)";
		String textOut = "ReAsONAble AKKeArANCe COMKANIONs OD b2 reMArHAbl2 Me INvItAtION FNderstOOd. IKFrsFIt elderl2 AsH KerDAKs All. S4E34E (7)(7)#SSS!!)";
		try {

			TransliterationProcess tp = new TransliterationProcess(path, "Currier");
			assertNotNull("Transliteration process returned null", tp.transliterate(textIn));
			assertEquals("Transliteration process returned wrong value", textOut, tp.transliterate(textIn));
			assertNotEquals("Transliteration process retuned the same value", textIn, tp.transliterate(textIn));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testSetFont() {
		// TODO no idea how to test this
	}

}
