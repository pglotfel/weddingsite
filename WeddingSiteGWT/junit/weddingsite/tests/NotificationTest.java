package weddingsite.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weddingsite.shared.Notification;

public class NotificationTest {

	private Notification notification;
	
	@Before
	public void setUp() throws Exception {
		notification = new Notification();
		notification.setActivityID(42);
		notification.setDate("1:21:2014");
		notification.setID(42);
		notification.setMessage("Hello!");
		notification.setMethod("e-mail");
		notification.setTime("1:21:PM");
	}

	@Test
	public final void testGetMessage() {
		assertTrue(notification.getMessage() == "Hello!");
	}

	@Test
	public final void testSetMessage() {
		notification.setMessage("Hello?");
		assertTrue(notification.getMessage() == "Hello?");
	}

	@Test
	public final void testGetDate() {
		assertTrue(notification.getDate() == "1:21:2014");
	}

	@Test
	public final void testSetDate() {
		notification.setDate("1:22:2014");
		assertTrue(notification.getDate() == "1:22:2014");
	}

	@Test
	public final void testGetTime() {
		assertTrue(notification.getTime() == "1:21:PM");
	}

	@Test
	public final void testSetTime() {
		notification.setTime("1:22:PM");
		assertTrue(notification.getTime() == "1:22:PM");
	}

	@Test
	public final void testGetMethod() {
		assertTrue(notification.getMethod() == "e-mail");
	}

	@Test
	public final void testSetMethod() {
		notification.setMethod("phone");
		assertTrue(notification.getMethod() == "phone");
	}

	@Test
	public final void testGetID() {
		assertTrue(notification.getID() == 42);
	}

	@Test
	public final void testSetID() {
		notification.setID(7);
		assertTrue(notification.getID() == 7);
	}

	@Test
	public final void testGetActivityID() {
		assertTrue(notification.getActivityID() == 42);
	}

	@Test
	public final void testSetActivityID() {
		notification.setActivityID(7);
		assertTrue(notification.getActivityID() == 7);
	}

}
