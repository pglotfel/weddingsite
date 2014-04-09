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
	private MenuItem activitiesItem;
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
		
		LayoutPanel layoutPanel_1 = new LayoutPanel();
		layoutPanel.add(layoutPanel_1);
		layoutPanel.setWidgetLeftWidth(layoutPanel_1, 234.0, Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_1, 161.0, Unit.PX, 100.0, Unit.PX);
		
		menuBar = new MenuBar(true);
		layoutPanel_1.add(menuBar);
		menuBar.setStyleName("menuBar .gwt-MenuBar");
		
		activitiesItem = new MenuItem("Tasks", false, new Command() {
			public void execute() {
			}
		});
		menuBar.addItem(activitiesItem);
		activitiesItem.setSize("200", "60");
		
		eventsItem = new MenuItem("Events", false, (Command) null);
		menuBar.addItem(eventsItem);
		eventsItem.setSize("200", "60");
		
		calendarItem = new MenuItem("Calendar", false, (Command) null);
		menuBar.addItem(calendarItem);
		calendarItem.setSize("200", "60");
		
		mntmAccount = new MenuItem("Account", false, (Command) null);
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

	
	//TODO: STUFF
	private void handleSeatingChartsClick() {
		//query server and load new web page		
	}
	
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		//Do nothing for now
		
	}
}
