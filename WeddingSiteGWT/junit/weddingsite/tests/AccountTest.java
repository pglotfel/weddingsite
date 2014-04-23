package weddingsite.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weddingsite.model.Account;

public class AccountTest {

	private Account account;
	
	@Before
	public void setUp() throws Exception {
		account = new Account();
		account.setAccountName("Misty");
		account.setID(42);
	}

	@Test
	public final void testGetAccountName() {
		assertTrue(account.getAccountName() == "Misty");
	}

	@Test
	public final void testSetAccountName() {
		account.setAccountName("Paul");
		assertTrue(account.getAccountName() == "Paul");
	}

	@Test
	public final void testGetID() {
		assertTrue(account.getID() == 42);
	}

	@Test
	public final void testSetID() {
		account.setID(7);
		assertTrue(account.getID() == 7);		
	}

}
