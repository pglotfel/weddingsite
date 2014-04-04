package weddingsite.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import weddingsite.shared.AttendanceList;

public class AttendanceListTest {
	
	private AttendanceList attendanceList;
	
	@Before
	public void setUp() {
		attendanceList = new AttendanceList();
		attendanceList.setID(1);
		attendanceList.setAccountID(42);
	}
	
	@Test
	public void testGetID () {
		assertTrue(attendanceList.getID() == 1);
	}
	
	@Test
	public void testSetID() {
		attendanceList.setID(0);
		assertTrue(attendanceList.getID() == 0);
	}
	
	@Test
	public void testGetAccountID() {
		assertTrue(attendanceList.getAccountID() == 42);
	}
	
	@Test
	public void testSetAccountID() {
		attendanceList.setAccountID(7);
		assertTrue(attendanceList.getAccountID() == 7);
		
	}

}
