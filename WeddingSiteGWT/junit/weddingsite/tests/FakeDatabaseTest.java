package weddingsite.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weddingsite.server.FakeDatabase;


public class FakeDatabaseTest {
	
	private FakeDatabase f;
	
	@Before
	public void setUp() {
		f = new FakeDatabase();
	}

	@Test
	public void testGetUser() {
		assertTrue(f.getUser("Smith", "Paul").getPassword() == "abdeFG");
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAdmin() {
		fail("Not yet implemented");
	}

}
