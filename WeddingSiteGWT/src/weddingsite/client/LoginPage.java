package weddingsite.client;

import weddingsite.persist.DatabaseProvider;
import weddingsite.persist.FakeDatabase;
import weddingsite.shared.Login;
import weddingsite.shared.PerformLogin;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LoginPage implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		DatabaseProvider.setInstance(new FakeDatabase());
		
		Login model = new Login();
		PerformLogin controller = new PerformLogin();
		controller.setModel(model);
		
		LoginAndLoginResultView view = new LoginAndLoginResultView();
		view.setModel(model);
		view.setController(controller);
		
		RootLayoutPanel.get().add(view);
		RootLayoutPanel.get().setWidgetLeftWidth(view, 10.0, Unit.PX, 500.0, Unit.PX);
		RootLayoutPanel.get().setWidgetTopHeight(view, 10.0, Unit.PX, 400.0, Unit.PX);
	}
}
