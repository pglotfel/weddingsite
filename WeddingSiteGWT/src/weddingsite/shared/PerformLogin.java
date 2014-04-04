package weddingsite.shared;

import weddingsite.persist.DatabaseProvider;

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
		
		String resultValue = "Failed to retrieve user";
		
		switch (model.getType()) {
		
		case LOGIN:
			System.out.println(DatabaseProvider.getInstance().getUser(model.getWeddingName(), model.getUsername()).getPassword());
			if(model.getPassword() == DatabaseProvider.getInstance().getUser(model.getWeddingName(), model.getUsername()).getPassword()) {
				resultValue = "Login succeeded!";
			}
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setMessage(resultValue);
	}
}
