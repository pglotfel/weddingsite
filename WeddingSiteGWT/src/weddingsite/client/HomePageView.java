package weddingsite.client;

import weddingsite.client.Site.Pages;
import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;
import weddingsite.shared.LoginResult;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.MenuItemSeparator;

public class HomePageView  extends Composite implements ISubscriber {
	private MenuItem eventsItem;
	private MenuItem AttendanceLists;
	private MenuBar menuBar;
	private LayoutPanel layoutPanel;
	private MenuItem calendarItem;
	private MenuItem mntmAccount;
	private MenuItem mntmSeatingCharts;
	
	public HomePageView() {
		
		layoutPanel = new LayoutPanel();
		layoutPanel.setStyleName("layout");
		initWidget(layoutPanel);
		layoutPanel.setSize("100%", "100%");
		
		menuBar = new MenuBar(true);
		layoutPanel.add(menuBar);
		layoutPanel.setWidgetLeftWidth(menuBar, 5, Unit.PCT, 40, Unit.PCT);
		layoutPanel.setWidgetTopHeight(menuBar, 5, Unit.PCT, 80, Unit.PCT);
		menuBar.setStyleName("menuBar .gwt-MenuBar");
		
		AttendanceLists = new MenuItem("Attendance Lists", false, new Command() {
			public void execute() {
				
				handleAttendanceListClick();
			}
			
		});
		menuBar.addItem(AttendanceLists);
		
		eventsItem = new MenuItem("Events", false, new Command() {
			public void execute() {
				
				handleEventsClick();
				
			}

			
		});
		menuBar.addItem(eventsItem);
		
		calendarItem = new MenuItem("Calendar", false, new Command() {
			public void execute() {
				
				handleCalendarClick();
			}
		});
		menuBar.addItem(calendarItem);
		
		mntmAccount = new MenuItem("Account", false, new Command() {
			public void execute() {
				HandleAccountClick();
			}
		});
		menuBar.addItem(mntmAccount);
		
		mntmSeatingCharts = new MenuItem("Seating Charts", false, new Command() {
			public void execute() {
				handleSeatingChartsClick();
			}

		});
		menuBar.addItem(mntmSeatingCharts);

	}
	
	

	
	protected void HandleAccountClick() {
		// TODO Auto-generated method stub
		
	}




	protected void handleCalendarClick() {
		Site.search(Site.Pages.CALENDARPAGE);
		
	}


	//TODO: STUFF
	private void handleSeatingChartsClick() {
		Site.search(Site.Pages.SEATINGCHARTPAGE);
		
		
	}
	
	private void handleAttendanceListClick() {
		Site.search(Site.Pages.ATTENDANCELISTPAGE);	
	}
	
	private void handleEventsClick() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		//Do nothing for now
		
	}
}
