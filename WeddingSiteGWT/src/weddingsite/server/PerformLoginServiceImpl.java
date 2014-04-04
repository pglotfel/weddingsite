package weddingsite.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import weddingsite.client.PerformLoginService;
import weddingsite.shared.Login;
import weddingsite.shared.LoginResult;

public class PerformLoginServiceImpl extends RemoteServiceServlet implements PerformLoginService {

	public LoginResult performLogin(Login login) {
		
		PerformLogin controller = new PerformLogin();
		
		controller.setWeddingName(login.getWeddingName());
		controller.setUsername(login.getUsername());
		controller.setPassword(login.getPassword());
		
		LoginResult result = new LoginResult();
		
		controller.perform(result);
		
		return result;
	}

}
