package weddingsite.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import weddingsite.shared.Activity;
import weddingsite.shared.Notification;

public class ActivityTest {
	
	private Activity event;


	@Before
	public void setUp() throws Exception {
		event = new Activity();

		//set body
		event.setBody("Nothing here");

		
		
		//set Title
		event.setTitle("Dress fitting");
		
		
		//add date
		event.setDate("07/04/2014");

		
		//set notification
		
		Notification notif = new Notification();
		notif.setMessage("Dont forget!");
		
		
		//ONLY FOR EVENT TYPE
		
		event.setStartTime("09:30AM");
		event.setEndTime("10:30AM");
		
	}

	@Test
	public final void testGetDate() {
		assertEquals("07/04/2014", event.getDate());	
		
	}

	@Test
	public final void testSetDate() {
		event.setDate("11/14/2015");
		
		assertEquals("11/14/2015", event.getDate());
	}

	@Test
	public final void testGetTitle() {
		assertEquals("Dress fitting", event.getTitle());
		
	}

	@Test
	public final void testSetTitle() {
		event.setTitle("Wedding day!");
		
		assertEquals("Wedding day!", event.getTitle());
		
	}

	@Test
	public final void testGetBody() {
		assertEquals("Nothing here", event.getBody());
		
	}

	@Test
	public final void testSetBody() {
		event.setBody("Actually there is something now");
		
		assertEquals("Actually there is something now", event.getBody());
		
		
	}
	
	
	
	//ONLY FOR EVENT CLASS
	
	@Test
	public final void testGetStartTime(){
		
		assertEquals("09:30AM", event.getStartTime());
		
	}
	
	@Test
	public final void testSetStartTime(){
		
		
		event.setStartTime("08:30AM");
		
		assertEquals("08:30AM", event.getStartTime());
	
	}
	
	@Test
	public final void testGetEndTime(){
		assertEquals("10:30PM", event.getEndTime());
	}
	
	@Test
	public final void testSetEndTime(){
		
		event.setEndTime("09:15AM");
		
		assertEquals("09:15AM", event.getEndTime());
	}

}
