package weddingsite.shared;

import java.io.Serializable;

public class Account implements Serializable{
	
	private int id;
	private String accountName;
	
	public Account() {
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
