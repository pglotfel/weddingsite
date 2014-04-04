package weddingsite.client;

import weddingsite.shared.Login;
import weddingsite.shared.LoginResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PerformLoginServiceAsync {
	void performLogin(Login login, AsyncCallback<LoginResult> callBack);
}
