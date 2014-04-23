package weddingsite.shared;

import java.io.Serializable;

public class EditAttendanceListModel extends Publisher implements Serializable  {
	
	public enum Events {
		VALUE_OR_ACTION_TYPE_CHANGED,
	}
	
	private String accountName;
	private String attendanceListName;
	private String newName;
	private ActionType type;
	
	public EditAttendanceListModel() {
		
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
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	

}
