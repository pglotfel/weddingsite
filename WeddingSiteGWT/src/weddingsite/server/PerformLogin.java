package weddingsite.server;

import weddingsite.shared.ActionType;
import weddingsite.shared.Login;
import weddingsite.shared.LoginResult;
import weddingsite.shared.User;

public class PerformLogin {
	private Login model;
	
	public void setModel(Login model) {
		this.model = model;
	}
	
	public void setActionType(ActionType type) {
		model.setType(type);
	}
	
	public void setUsername(String username) {
		model.setUsername(username);
	}
	
	public void setPassword(String password) {
		model.setPassword(password);
	}
	
	public void setWeddingName(String weddingName) {
		model.setWeddingName(weddingName);
	}
	
	public void perform(LoginResult result) {
		
		String resultValue = "Failed to login!";
		
		switch (model.getType()) {
		
		case LOGIN:
			
			String wn = model.getWeddingName();
			String un = model.getUsername();
			String pw = model.getPassword();
			
			
			User u = DatabaseProvider.getInstance().getUser(wn, un);
			if(u != null) {
				if(pw.equals(u.getPassword())) {
					resultValue = "";
				}
			}
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setMessage(resultValue);
	}

}
