package weddingsite.server;

import java.util.ArrayList;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.User;
import weddingsite.shared.UserQueryModel;

public class PerformUserQuery {
	
	private UserQueryModel model;
	
	public PerformUserQuery() {
		
	}
	
	public void setModel(UserQueryModel model) {
		this.model = model;
	}
	
	public UserQueryModel getModel() {
		return model;
	}
	
	public void perform(GetItemsResult<User> result) {
		
		ArrayList<User> resultValue = null;
		
		switch (model.getType()) {
		
		case GETUSERS:
			
			resultValue = DatabaseProvider.getInstance().getUsers(model.getAccountName());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		
		result.setResult(resultValue);		
	}
	
}
