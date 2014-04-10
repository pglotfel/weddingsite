package weddingsite.shared;

import java.io.Serializable;
import java.util.ArrayList;

import weddingsite.shared.LoginResult.Events;

public class AttendanceListQueryResult extends Publisher implements Serializable{
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private ArrayList<Attendee> attendees;
	
	public AttendanceListQueryResult() {
		
		this.attendees = null;
	}
	
	

	public ArrayList<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<Attendee> attendees) {
		this.attendees = attendees;
		notifySubscribers(Events.VALUE_CHANGED, attendees);
	}
	
	
	
	

}
