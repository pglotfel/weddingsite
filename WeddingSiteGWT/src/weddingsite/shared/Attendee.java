package weddingsite.shared;

public class Attendee {
	
	private int id;
	private int attendanceListID;
	private boolean attending;
	private int numAttending;
	
	private String name;
	
	
	public Attendee() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getAttendanceListID() {
		return attendanceListID;
	}
	
	public void setAttendanceListID(int attendanceListID) {
		this.attendanceListID = attendanceListID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAttending() {
		return attending;
	}

	public void setAttending(boolean attending) {
		this.attending = attending;
	}

	public int getNumAttending() {
		return numAttending;
	}

	public void setNumAttending(int numAttending) {
		this.numAttending = numAttending;
	}
}
