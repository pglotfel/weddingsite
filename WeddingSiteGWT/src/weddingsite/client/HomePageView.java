package weddingsite.client;

import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;
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
		layoutPanel.setStyleName("menuBar .gwt-MenuBar");
		initWidget(layoutPanel);
		layoutPanel.setSize("447px", "625px");
		
		menuBar = new MenuBar(true);
		layoutPanel.add(menuBar);
		layoutPanel.setWidgetLeftWidth(menuBar, 50.0, Unit.PX, 202.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(menuBar, 50.0, Unit.PX, 353.0, Unit.PX);
		menuBar.setStyleName("menuBar .gwt-MenuBar");
		
		AttendanceLists = new MenuItem("Attendance Lists", false, new Command() {
			public void execute() {
				
				handleAttendanceListClick();
			}
			
		});
		menuBar.addItem(AttendanceLists);
		AttendanceLists.setSize("200", "60");
		
		eventsItem = new MenuItem("Events", false, new Command() {
			public void execute() {
				
				handleEventsClick();
				
			}

			
		});
		menuBar.addItem(eventsItem);
		eventsItem.setSize("200", "60");
		
		calendarItem = new MenuItem("Calendar", false, new Command() {
			public void execute() {
				
				handleCalendarClick();
			}
		});
		menuBar.addItem(calendarItem);
		calendarItem.setSize("200", "60");
		
		mntmAccount = new MenuItem("Account", false, new Command() {
			public void execute() {
				HandleAccountClick();
			}
		});
		menuBar.addItem(mntmAccount);
		mntmAccount.setSize("200", "60");
		
		mntmSeatingCharts = new MenuItem("Seating Charts", false, new Command() {
			public void execute() {
				handleSeatingChartsClick();
			}

		});
		menuBar.addItem(mntmSeatingCharts);
		mntmSeatingCharts.setSize("200", "60");

	}
	
	

	
	protected void HandleAccountClick() {
		// TODO Auto-generated method stub
		
	}




	protected void handleCalendarClick() {
		// TODO Auto-generated method stub
		
	}


	//TODO: STUFF
	private void handleSeatingChartsClick() {
		//query server and load new web page
		
		
	}
	
	private void handleAttendanceListClick() {
		// TODO Auto-generated method stub
		
	}
	
	private void handleEventsClick() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		//Do nothing for now
		
	}
}
