package weddingsite.shared;

import java.io.Serializable;

public class EditSeatingChartModel extends Publisher implements Serializable {
	public enum Events {
		VALUE_CHANGED
	}
	
	private String accountName;
	private String seatingChartName;
	private String newName;
	private ActionType type;
	
	public EditSeatingChartModel() {
		
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public ActionType getType() {
		return type;
	}
	public void setType(ActionType type) {
		this.type = type;
	}
	public String getSeatingChartName() {
		return seatingChartName;
	}
	public void setSeatingChartName(String seatingChartName) {
		this.seatingChartName = seatingChartName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	
}
