package weddingsite.shared;

import java.io.Serializable;

import weddingsite.shared.Login.Events;

public class AttendanceListQueryModel extends Publisher implements Serializable  {
	
	public enum Events {
		VALUE_OR_ACTION_TYPE_CHANGED,
	}
	
	
	private String weddingName;
	private ActionType type;
	
	public AttendanceListQueryModel() {
		
	}
	public String getWeddingName() {
		return weddingName;
	}
	
	public void setWeddingName(String weddingName) {
		this.weddingName = weddingName;
		notifySubscribers(Events.VALUE_OR_ACTION_TYPE_CHANGED, null);
	}
	
	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}
	
}
