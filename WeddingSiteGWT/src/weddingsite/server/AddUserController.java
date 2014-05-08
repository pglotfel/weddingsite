package weddingsite.server;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.CreateAccountModel;
import weddingsite.shared.EditDataResult;

public class AddUserController {
private CreateAccountModel model;
	
	public void setModel(CreateAccountModel model) {
		this.model = model;
	}
	
	public void perform(EditDataResult result) {
		boolean resultVal = false;
		
		switch(model.getType()) {
		
		case CREATEUSER:
			
			resultVal = DatabaseProvider.getInstance().addUser(model.getAccountName(), model.getAdminName(), model.getPassword(), false);
			break;
		
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setResult(resultVal);
	}
}
