package weddingsite.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weddingsite.model.User;

public class UserTest {
	
	private User user;
	
	@Before
	public void setUp() {
		user = new User("h", "1", false);
		user.setID(42);
		user.setAccountID(42);
	}

	@Test
	public void testUser() {
		assertTrue(user.getUsername().equals("h"));
		assertTrue(user.getPassword().equals("1"));
	}
	
	@Test
	public void testSetPassword() {
		user.setPassword("2");
		assertTrue(user.getPassword().equals("2"));
	}
	
	@Test
	public void testGetPassword() {
		assertTrue(user.getPassword().equals("1"));
	}
	
	@Test
	public void testGetID() {
		assertTrue(user.getID() == 42);
	}
	
	@Test 
	public void testSetId() {
		user.setID(7);
		assertTrue(user.getID() == 7);
	}
	
	@Test 
	public void testGetAccountID() {
		assertTrue(user.getAccountID() == 42);
	}
	
	@Test
	public void testSetAccountID() {
		user.setAccountID(7);
		assertTrue(user.getAccountID() == 7);
	}
}
