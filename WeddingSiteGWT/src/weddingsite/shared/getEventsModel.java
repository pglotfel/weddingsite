package weddingsite.shared;

import java.io.Serializable;

public class getEventsModel extends Publisher implements Serializable{
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private String accountName;
	private ActionType type;
	
	public getEventsModel() {
		
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
}
