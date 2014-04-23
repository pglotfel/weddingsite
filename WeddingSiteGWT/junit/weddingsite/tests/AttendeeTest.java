package weddingsite.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weddingsite.shared.Attendee;

public class AttendeeTest {
	
	private Attendee attendee;
	
	@Before
	public void setUp() {
		attendee = new Attendee();
		attendee.setName("Billy Bob");
		attendee.setID(42);
		attendee.setAttendanceListID(42);
	}
	
	@Test 
	public void testAttendee() {
		assertTrue(attendee.getName().equals("Billy Bob"));
	}
	
	@Test
	public void testSetName() {
		attendee.setName("Billy Williams");
		assertTrue(attendee.getName().equals("Billy Williams"));
	}
	
	@Test
	public void testGetID() {
		assertTrue(attendee.getID() == 42);
	}
	
	@Test
	public void testSetID() {
		attendee.setID(7);
		assertTrue(attendee.getID() == 7);
	}
	
	@Test
	public void testGetAttendanceListID()  {
		assertTrue(attendee.getAttendanceListID() == 42);
	}
	
	@Test
	public void testSetAttendanceID() {
		attendee.setAttendanceListID(7);
		assertTrue(attendee.getAttendanceListID() == 7);
	}
}
