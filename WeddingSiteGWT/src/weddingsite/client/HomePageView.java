package weddingsite.client;

import weddingsite.shared.ActionType;
import weddingsite.shared.Activity;
import weddingsite.shared.EventsModel;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;

public class HomePageView  extends Composite implements ISubscriber {
	private MenuItem eventsItem;
	private MenuItem AttendanceLists;
	private MenuBar menuBar;
	private LayoutPanel layoutPanel;
	private MenuItem calendarItem;
	private MenuItem mntmAccount;
	private MenuItem mntmSeatingCharts;
	private FlowPanel mainFlowPanel;
	private ScrollPanel mainScrollPanel;
	private Label TitleLbl;
	private Label FirstEventName;
	private Label FirstEventDate;
	private Label SecondEventName;
	private Label SecondEventDate;
	
	public HomePageView() {
		
		mainFlowPanel = new FlowPanel();
		initWidget(mainFlowPanel);
		mainFlowPanel.setSize("2000px", "2000px");
		
		mainScrollPanel = new ScrollPanel();
		mainFlowPanel.add(mainScrollPanel);
		mainScrollPanel.setSize("100%", "100%");
		
		
		
		layoutPanel = new LayoutPanel();
		layoutPanel.setStyleName("InnerBackgroundCenter");
		mainScrollPanel.add(layoutPanel);
		layoutPanel.setSize("900px", "1000px");
		
		menuBar = new MenuBar(true);
		layoutPanel.add(menuBar);
		layoutPanel.setWidgetLeftWidth(menuBar, 5, Unit.PCT, 23.1, Unit.PCT);
		layoutPanel.setWidgetTopHeight(menuBar, 5, Unit.PCT, 30.0, Unit.PCT);
		menuBar.setStyleName("menuBar .gwt-MenuBar");
		
		EventsModel model = new EventsModel();
		model.setAccountName(Site.currentUser.getAccountName());
		if(Site.currentUser.getIsAdmin()) {
			model.setType(ActionType.GETEVENTS);
		} else {
			model.setUsername(Site.currentUser.getUsername());
			model.setType(ActionType.GETEVENTSFORUSER);
		}
		
		RPC.getEventsService.GetEvents(model, new AsyncCallback<GetItemsResult<Activity>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(GetItemsResult<Activity> result) {
				Activity first = new Activity();
				Activity second = new Activity();
				Activity temp = null;
				
				int firstYear = 3000;
				int firstMonth = 12;
				int firstDay = 31;
				int secondYear = 3000;
				int secondMonth = 12;
				int secondDay = 31;
				
				if(result.getResult().size() > 0) {
				
					for(Activity a : result.getResult()) {
						String[] tokens = a.getDate().split("[/]");
						int month = Integer.parseInt(tokens[0]);
						int day = Integer.parseInt(tokens[1]);
						int year = Integer.parseInt(tokens[2]);
						
						if(year < firstYear) {
							temp = first;
							first = a;
							second = temp;
						} else if(year <= firstYear && month < firstMonth) {
							temp = first;
							first = a;
							second = temp;
						} else if(year <= firstYear && month <= firstMonth && day < firstDay) {
							temp = first;
							first = a;
							second = temp;
						} else if(year <= firstYear && month <= firstMonth && day <= firstDay) {
							second = a;
						} else if(year < secondYear) {
							second = a;
						} else if(year <= secondYear && month < secondMonth) {
							second = a;
						} else if(year <= secondYear && month <= secondMonth && day < secondDay) {
							second = a;
						}
						
					}
					
					FirstEventName.setText(first.getTitle());
					FirstEventDate.setText(first.getDate());
					SecondEventName.setText(second.getTitle());
					SecondEventDate.setText(second.getDate());;
				} else {
					FirstEventName.setText("There are no upcoming events");
				}
			}
			
		});
		
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
		
		FlowPanel flowPanel = new FlowPanel();
		layoutPanel.add(flowPanel);
		layoutPanel.setWidgetLeftWidth(flowPanel, 391.0, Unit.PX, 404.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(flowPanel, 68.0, Unit.PX, 697.0, Unit.PX);
		
		TitleLbl = new Label("Upcoming Events");
		flowPanel.add(TitleLbl);
		TitleLbl.setHeight("32px");
		TitleLbl.setStyleName("CenterTitles");
		
		FirstEventName = new Label("");
		FirstEventName.setStyleName("CenterText");
		flowPanel.add(FirstEventName);
		FirstEventName.setSize("100%", "40px");
		
		FirstEventDate = new Label("");
		FirstEventDate.setStyleName("CenterText");
		flowPanel.add(FirstEventDate);
		FirstEventDate.setSize("100%", "40px");
		
		SecondEventName = new Label("");
		SecondEventName.setStyleName("CenterText");
		flowPanel.add(SecondEventName);
		SecondEventName.setSize("100%", "40px");
		
		SecondEventDate = new Label("");
		SecondEventDate.setStyleName("CenterText");
		flowPanel.add(SecondEventDate);
		SecondEventDate.setSize("100%", "40px");
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
