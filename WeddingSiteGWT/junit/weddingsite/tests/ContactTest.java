package weddingsite.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weddingsite.shared.Contact;

public class ContactTest {
	
	Contact contact;

	@Before
	public void setUp() throws Exception {
		
		contact = new Contact();
		
		contact.setAlternatePhone("717-717-7171");
		contact.setEmail("me@whatever.com");
		contact.setID(1);
		contact.setMainPhone("111-111-1111");
		contact.setName("Bobbie McBob");
		contact.setWebsite("www.w.org");
		
	}


	@Test
	public final void testGetName() {
		assertEquals("Bobbie McBob", contact.getName());
		assertFalse(contact.getName().equals("BobbieMcBob"));
	}

	@Test
	public final void testSetName() {
		contact.setName("Bob Bob");
		
		assertEquals("Bob Bob", contact.getName());
		assertFalse(contact.getName().equals("BobBobBobBOb"));
	}

	@Test
	public final void testGetEmail() {
		assertEquals("me@whatever.com", contact.getEmail());
		assertFalse(contact.getEmail().equals("me@nowhere.com"));
	}

	@Test
	public final void testSetEmail() {
		contact.setEmail("you@there.edu");
		
		assertEquals("you@there.edu", contact.getEmail());
		assertFalse(contact.getEmail().equals("you@here.edu"));
	}

	@Test
	public final void testGetMainPhone() {
		assertEquals("111-111-1111", contact.getMainPhone());
		assertFalse(contact.getMainPhone().equals("222-222-2222"));
	}

	@Test
	public final void testSetMainPhone() {
		contact.setMainPhone("424-424-4242");
		
		assertEquals("424-424-4242", contact.getMainPhone());
		assertFalse(contact.getMainPhone().equals("111-111-1111"));
	}

	@Test
	public final void testGetAlternatePhone() {
		assertEquals("717-717-7171", contact.getAlternatePhone());
		assertFalse(contact.getAlternatePhone().equals("171-171-1717"));
	}

	@Test
	public final void testSetAlternatePhone() {
		contact.setAlternatePhone("333-333-3333");
		
		assertEquals("333-333-3333", contact.getAlternatePhone());
		assertFalse(contact.getAlternatePhone().equals("717-717-7171"));
		
	}

	@Test
	public final void testGetWebsite() {
		assertEquals("www.w.org", contact.getWebsite());
		assertFalse(contact.getWebsite().equals("ww.w.org"));
	}

	@Test
	public final void testSetWebsite() {
		contact.setWebsite("www.website.net");
		
		assertEquals("www.website.net", contact.getWebsite());
		assertFalse(contact.getWebsite().equals("www.w.org"));
	}

	@Test
	public final void testGetID() {
		assertEquals(1, contact.getID());
		assertFalse(contact.getID() == 2);
	}

	@Test
	public final void testSetID() {
		contact.setID(3);
		
		assertEquals(3, contact.getID());
		assertFalse(contact.getID() == 1);
		
	}


}
