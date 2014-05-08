package weddingsite.client;

import weddingsite.shared.CreateAccountModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CreateAccountServiceAsync {

	void createAccount(CreateAccountModel model,
			AsyncCallback<EditDataResult> callback);

	void addUser(CreateAccountModel model,
			AsyncCallback<EditDataResult> callback);

}
