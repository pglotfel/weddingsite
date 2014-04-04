package weddingsite.shared;

public class User {
	
	private int id;
	private int accountID;
	private String username;
	private String password;
	private boolean isAdmin;
	
	public User() {
		
	}
	
	//TODO: Make sure it's a good password!
	public User(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
	public int getID() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
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
	
}
