package weddingsite.shared;

import java.io.Serializable;

public class CreateAccountModel extends Publisher implements Serializable{
	public enum Events {
		VALUE_OR_ACTION_TYPE_CHANGED,
	}
	
	private String accountName;
	private String adminName;
	private String password;
	private ActionType type;
	
	
	public CreateAccountModel() {
		
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getAdminName() {
		return adminName;
	}


	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ActionType getType() {
		return type;
	}


	public void setType(ActionType type) {
		this.type = type;
	}
	
	
	
}
