package weddingsite.model;

public class Notification {
	
	private int id;
	private int activityID;
	private String message;
	private String date;
	private String time;
	private String method;
	
	public Notification(){
		
	}
	
	public Notification(String message, String date, String time, String method) {
		this.message = message;
		this.date = date;
		this.time = time;
		this.method = method;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getActivityID() {
		return activityID;
	}
	
	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}
	
}
