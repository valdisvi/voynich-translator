package tests;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.MainFrame;

public class SwingAppTest {
	static MainFrame app;

	@Before
	public void setUp() throws Exception {
		app = new MainFrame();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testTestApp() {
		assertNotNull(app);
	}

}
