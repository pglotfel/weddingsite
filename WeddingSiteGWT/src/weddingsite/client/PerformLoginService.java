package weddingsite.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import weddingsite.shared.Login;
import weddingsite.shared.LoginResult;

@RemoteServiceRelativePath("performLogin")
public interface PerformLoginService extends RemoteService {
	public LoginResult performLogin (Login login);

}
