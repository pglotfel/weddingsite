package weddingsite.shared;

import java.io.Serializable;

public class EditAttendeeModel implements Serializable {
	
	public String name;
	public String accountName;
	public String attendanceListName;
	public int numAttending;
	public ActionType type;
	
	public EditAttendeeModel() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumAttending() {
		return numAttending;
	}
	
	public void setNumAttending(int numAttending) {
		this.numAttending = numAttending;
	}
	
	public ActionType getType() {
		return type;
	}
	
	public void setType(ActionType type) {
		this.type = type;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAttendanceListName() {
		return attendanceListName;
	}

	public void setAttendanceListName(String attendanceListName) {
		this.attendanceListName = attendanceListName;
	}
	
}
