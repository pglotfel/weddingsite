package weddingsite.shared;

//TODO: FINISH

public class SeatingChart {

	private int id;
	private int accountID;
	private String name;
	
	public SeatingChart() {

	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getAccountID() {
		return accountID;
	}
	
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
