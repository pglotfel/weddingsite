package weddingsite.client;

import weddingsite.shared.CreateAccountModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CreateAccountService")
public interface CreateAccountService extends RemoteService{
	public EditDataResult createAccount(CreateAccountModel model);
	public EditDataResult addUser(CreateAccountModel model);
}
