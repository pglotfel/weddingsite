package weddingsite.shared;

import java.io.Serializable;
import java.util.ArrayList;

import weddingsite.shared.LoginResult.Events;

public class AttendanceListQueryResult extends Publisher implements Serializable{
	
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private ArrayList<AttendanceList> attendanceLists;
	
	public AttendanceListQueryResult() {
		
		this.attendanceLists = null;
	}
	
	public ArrayList<AttendanceList> getAttendanceLists() {
		return attendanceLists;
	}

	public void setAttendanceLists(ArrayList<AttendanceList> attendanceLists) {
		this.attendanceLists = attendanceLists;
		notifySubscribers(Events.VALUE_CHANGED, attendanceLists);
	}
	
	
	
	

}
