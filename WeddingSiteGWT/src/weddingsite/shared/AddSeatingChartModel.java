package weddingsite.shared;

import java.io.Serializable;

public class AddSeatingChartModel extends Publisher implements Serializable {
	public enum Events {
		VALUE_CHANGED
	}
	
	private String accountName;
	private String seatingChartName;
	private ActionType type;
	
	public AddSeatingChartModel() {
		
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
	
	
}
