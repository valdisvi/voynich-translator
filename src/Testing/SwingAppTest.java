package Testing;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import application.SwingApp;

public class SwingAppTest {
static SwingApp app;
	@Before
	public void setUp() throws Exception {
		app= new SwingApp();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testTestApp() {
		assertNotNull(app);
	}
	
}
