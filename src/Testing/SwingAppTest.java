package Testing;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import application.TestApp;

public class TestAppTest {
static TestApp app;
	@Before
	public void setUp() throws Exception {
		app= new TestApp();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testTestApp() {
		assertNotNull(app);
	}
	
}
