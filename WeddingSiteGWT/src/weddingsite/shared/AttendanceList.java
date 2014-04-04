package weddingsite.shared;

public class AttendanceList {
	
	private int id;
	private int accountID;
	
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
}
