package weddingsite.shared;

public class AttendanceList {
	
	private int id;
	private int accountID;
	private String name;
	
	public AttendanceList() {
		
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getAccountID() {
		return this.accountID;
	}
	
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
