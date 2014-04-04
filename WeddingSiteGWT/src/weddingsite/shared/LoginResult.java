package weddingsite.shared;

import java.io.Serializable;

public class LoginResult extends Publisher implements Serializable {
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private String message;
	
	public LoginResult() {
		message = "";
	}
	
	public void setMessage(String message) {
		this.message = message;
		notifySubscribers(Events.VALUE_CHANGED, message);
	}
	
	public String getMessage() {
		return message;
	}
}
