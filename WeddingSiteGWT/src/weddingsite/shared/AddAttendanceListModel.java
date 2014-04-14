package weddingsite.shared;

import java.io.Serializable;

public class AddAttendanceListModel extends Publisher implements Serializable  {
	
	public enum Events {
		VALUE_OR_ACTION_TYPE_CHANGED,
	}
	
	private String accountName;
	private String attendanceListName;
	private ActionType type;
	
	public AddAttendanceListModel() {
		
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
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}
	

}
