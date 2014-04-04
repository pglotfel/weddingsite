package weddingsite.shared;

public class Attendee {
	
	private int id;
	private int attendanceListID;
	
	private String name;
	
	public Attendee(String name) {
		this.name = name;
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
}
