package weddingsite.shared;

import java.io.Serializable;

public class Login extends Publisher implements Serializable {
	
	public enum Events {
		VALUE_OR_ACTION_TYPE_CHANGED,
	}
	
	private String weddingName;
	private String username;
	private String password;
	private ActionType type;

	public void setWeddingName(String weddingName) {
		this.weddingName = weddingName;
		notifySubscribers(Events.VALUE_OR_ACTION_TYPE_CHANGED, null);
	}
	
	public String getWeddingName() {
		return weddingName;
	}
	
	public void setUsername(String username) {
		this.username = username;
		notifySubscribers(Events.VALUE_OR_ACTION_TYPE_CHANGED, null);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setType(ActionType type) {
		this.type = type;
		notifySubscribers(Events.VALUE_OR_ACTION_TYPE_CHANGED, null);
	}
	
	public ActionType getType() {
		return type;
	}
	
	public void setPassword(String password) {
		this.password = password;
		notifySubscribers(Events.VALUE_OR_ACTION_TYPE_CHANGED, null);
	}
	
	public String getPassword() {
		return password;
	}
	
}
