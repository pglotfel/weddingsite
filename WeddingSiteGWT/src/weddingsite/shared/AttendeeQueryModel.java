package weddingsite.shared;

import java.io.Serializable;

public class AttendeeQueryModel  extends Publisher implements Serializable  {
	
	public enum Events {
		VALUE_OR_ACTION_TYPE_CHANGED,
	}
	
	
	private String accountName;
	private String AttendanceListName;
	private ActionType type;
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAttendanceListName() {
		return AttendanceListName;
	}
	
	public void setAttendanceListName(String attendanceListName) {
		AttendanceListName = attendanceListName;
	}
	
	public ActionType getType() {
		return type;
	}
	
	public void setType(ActionType type) {
		this.type = type;
	}
	
	
	

}
