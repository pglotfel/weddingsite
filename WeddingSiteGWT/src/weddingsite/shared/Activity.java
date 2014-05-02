package weddingsite.shared;

import java.util.ArrayList;

public class Activity {
	
	private int accountID;
	private int id;
	private String date;
	private String title;
	private String body;
	private String startTime;
	private String endTime;
	
	public Activity() {
		
	}
	
	public Activity(String date, String title, String body, Notification notification, ArrayList<Contact> contacts) {
		this.date = date;
		this.title = title;
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
}
