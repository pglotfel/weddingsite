package weddingsite.server;

import weddingsite.client.CreateAccountService;
import weddingsite.shared.CreateAccountModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CreateAccountImpl  extends RemoteServiceServlet implements CreateAccountService{

	@Override
	public EditDataResult createAccount(CreateAccountModel model) {
		CreateAccount controller = new CreateAccount();
		
		controller.setModel(model);
		
		EditDataResult result = new EditDataResult();
		controller.perform(result);
		
		return result;
	}
	

}
