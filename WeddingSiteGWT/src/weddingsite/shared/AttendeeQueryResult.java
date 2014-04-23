package weddingsite.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class AttendeeQueryResult extends Publisher implements Serializable  {
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private ArrayList<Attendee> attendees;
	private int totalAttending;
	
	public AttendeeQueryResult() {
		attendees = new ArrayList<Attendee>();
		
	}

	public ArrayList<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<Attendee> attendees) {
		this.attendees = attendees;
	}
	
	public int getTotalAttending() {
		return totalAttending;
	}

	public void setTotalAttending(int totalAttending) {
		this.totalAttending = totalAttending;
	}

}
