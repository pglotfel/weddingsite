package weddingsite.client;

import weddingsite.shared.CurrentUser;
import weddingsite.shared.Login;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Site implements EntryPoint {	
	
	private static Composite currentView;
	public static CurrentUser currentUser = new CurrentUser();
	
	public enum Pages {
		LOGINPAGE,
		HOMEPAGE,
		USERPAGE,
		ATTENDANCELISTPAGE,
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
			
		search(Pages.LOGINPAGE);
	}
	
	
	public static void search(Pages page) {
		
		switch(page) {		
		case LOGINPAGE:
			Login model = new Login();
			LoginAndLoginResultView v1 = new LoginAndLoginResultView();
			currentView = v1;
			v1.setModel(model);
			RootLayoutPanel.get().add(v1);
			RootLayoutPanel.get().setWidgetLeftWidth(v1, 10.0, Unit.PX, 500.0, Unit.PX);
			RootLayoutPanel.get().setWidgetTopHeight(v1, 10.0, Unit.PX, 400.0, Unit.PX);
		break;
		
		case HOMEPAGE:
			RootLayoutPanel.get().remove(currentView);
			HomePageView v2 = new HomePageView();
			RootLayoutPanel.get().add(v2);
			currentView = v2;
			RootLayoutPanel.get().setWidgetLeftWidth(v2, 10.0, Unit.PX, 500.0, Unit.PX);
			RootLayoutPanel.get().setWidgetTopHeight(v2, 10.0, Unit.PX, 400.0, Unit.PX);			
		break;
		
		case ATTENDANCELISTPAGE:
			RootLayoutPanel.get().remove(currentView);
			AttendanceListView v3 = new AttendanceListView();
			RootLayoutPanel.get().add(v3);
			currentView = v3;
			RootLayoutPanel.get().setWidgetLeftWidth(v3, 10.0, Unit.PX, 500.0, Unit.PX);
			RootLayoutPanel.get().setWidgetTopHeight(v3, 10.0, Unit.PX, 400.0, Unit.PX);
		break;
		
		default:
			System.err.println("Something went wrong in page loading...");
			break;
			
		}
	}
}
