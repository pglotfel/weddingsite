package weddingsite.shared;

import java.io.Serializable;

import weddingsite.shared.Login.Events;

public class AttendanceListQueryModel extends Publisher implements Serializable  {
	
	public enum Events {
		VALUE_OR_ACTION_TYPE_CHANGED,
	}
	
	
	private String weddingName;
	private String username;
	private String name;
	private ActionType type;
	
	
	public String getWeddingName() {
		return weddingName;
	}
	
	public void setWeddingName(String weddingName) {
		this.weddingName = weddingName;
		notifySubscribers(Events.VALUE_OR_ACTION_TYPE_CHANGED, null);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
		notifySubscribers(Events.VALUE_OR_ACTION_TYPE_CHANGED, null);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		notifySubscribers(Events.VALUE_OR_ACTION_TYPE_CHANGED, null);
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}
	
	
	
	

}
