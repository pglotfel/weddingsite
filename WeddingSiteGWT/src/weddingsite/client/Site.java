package weddingsite.client;

import weddingsite.shared.CurrentUser;
import weddingsite.shared.Login;
import weddingsite.shared.SlidingStack;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Site implements EntryPoint {	
	
	private static Composite currentView;
	private static SlidingStack<Pages> viewHistory = new SlidingStack<Pages>(10);
	public static CurrentUser currentUser = new CurrentUser();
	private static Pages currentPage;
	
	public enum Pages {
		LOGINPAGE,
		HOMEPAGE,
		USERPAGE,
		ATTENDANCELISTPAGE,
		BACK,
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
			
		search(Pages.LOGINPAGE);
		currentPage = Pages.HOMEPAGE;
	}
	
	
	public static void search(Pages page) {
		
		switch(page) {		
		case LOGINPAGE:
			Login model = new Login();
			LoginAndLoginResultView v1 = new LoginAndLoginResultView();
			currentView = v1;
			v1.setModel(model);
			RootLayoutPanel.get().add(v1);
			RootLayoutPanel.get().setWidgetLeftWidth(v1, 0, Unit.PCT, 100.0, Unit.PCT);
			RootLayoutPanel.get().setWidgetTopHeight(v1, 0, Unit.PCT, 100.0, Unit.PCT);
		break;
		
		case HOMEPAGE:
			viewHistory.push(currentPage);
			RootLayoutPanel.get().remove(currentView);
			HomePageView v2 = new HomePageView();
			RootLayoutPanel.get().add(v2);
			currentView = v2;
			RootLayoutPanel.get().setWidgetLeftWidth(v2, 0, Unit.PCT, 100.0, Unit.PCT);
			RootLayoutPanel.get().setWidgetTopHeight(v2, 0, Unit.PCT, 100.0, Unit.PCT);		
			currentPage = Pages.HOMEPAGE;
		break;
		
		case ATTENDANCELISTPAGE:
			viewHistory.push(currentPage);
			RootLayoutPanel.get().remove(currentView);
			AttendanceListView v3 = new AttendanceListView();
			RootLayoutPanel.get().add(v3);
			currentView = v3;
			RootLayoutPanel.get().setWidgetLeftWidth(v3, 0, Unit.PCT, 100.0, Unit.PCT);
			RootLayoutPanel.get().setWidgetTopHeight(v3, 0, Unit.PCT, 100.0, Unit.PCT);		
			currentPage = Pages.ATTENDANCELISTPAGE;
		break;
		
		case BACK:
			Pages p = null;
			try {
				p = viewHistory.pop();
			} catch (Exception e) {}
			
			if(p != null) {
				search(p);
			}
		break;
		
		default:
			System.err.println("Something went wrong in page loading...");
			break;
			
		}
	}
}
