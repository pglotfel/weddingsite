package weddingsite.client;

import weddingsite.client.Site.Pages;
import weddingsite.shared.ActionType;
import weddingsite.shared.Activity;
import weddingsite.shared.EventsModel;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Button;

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
	private Button LogOutButton;
	private Label FirstEventBody;
	private Label SecondEventBody;
	
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
		layoutPanel.setSize("900px", "700px");
		
		menuBar = new MenuBar(true);
		layoutPanel.add(menuBar);
		menuBar.setSize("200px", "300px");
		layoutPanel.setWidgetLeftWidth(menuBar, 7.8, Unit.PCT, 22.2, Unit.PCT);
		layoutPanel.setWidgetTopHeight(menuBar, 9.6, Unit.PCT, 39.3, Unit.PCT);
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
							secondYear = firstYear;
							secondMonth = firstMonth;
							secondDay = firstDay;
							firstYear = year;
							firstMonth = month;
							firstDay = day;
							second = temp;
						} else if(year <= firstYear && month < firstMonth) {
							temp = first;
							first = a;
							secondYear = firstYear;
							secondMonth = firstMonth;
							secondDay = firstDay;
							firstYear = year;
							firstMonth = month;
							firstDay = day;
							second = temp;
						} else if(year <= firstYear && month <= firstMonth && day < firstDay) {
							temp = first;
							first = a;
							secondYear = firstYear;
							secondMonth = firstMonth;
							secondDay = firstDay;
							firstYear = year;
							firstMonth = month;
							firstDay = day;
							second = temp;
						} else if(year <= firstYear && month <= firstMonth && day <= firstDay) {
							second = a;
							secondYear = year;
							secondMonth = month;
							secondDay = day;
						} else if(year < secondYear) {
							second = a;
							secondYear = year;
							secondMonth = month;
							secondDay = day;
						} else if(year <= secondYear && month < secondMonth) {
							second = a;
							secondYear = year;
							secondMonth = month;
							secondDay = day;
						} else if(year <= secondYear && month <= secondMonth && day < secondDay) {
							second = a;
							secondYear = year;
							secondMonth = month;
							secondDay = day;
						}
						
					}
					
					FirstEventName.setText(first.getTitle());
					FirstEventDate.setText(first.getDate());
					FirstEventBody.setText(first.getBody());
					SecondEventName.setText(second.getTitle());
					SecondEventDate.setText(second.getDate());;
					SecondEventBody.setText(second.getBody());
				} else {
					FirstEventName.setText("There are no upcoming events");
				}
			}
			
		});
		
		mntmAccount = new MenuItem("Account", false, new Command() {
			public void execute() {
				HandleAccountClick();
			}
		});
		menuBar.addItem(mntmAccount);
		calendarItem = new MenuItem("Calendar", false, new Command() {
			public void execute() {
				
				handleCalendarClick();
			}
		});
		menuBar.addItem(calendarItem);
		
		AttendanceLists = new MenuItem("Attendance Lists", false, new Command() {
			public void execute() {
				
				handleAttendanceListClick();
			}
			
		});
		menuBar.addItem(AttendanceLists);
		
		
		
		mntmSeatingCharts = new MenuItem("Seating Charts", false, new Command() {
			public void execute() {
				handleSeatingChartsClick();
			}

		});
		
		menuBar.addItem(mntmSeatingCharts);
		
		FlowPanel EventsPanel = new FlowPanel();
		layoutPanel.add(EventsPanel);
		EventsPanel.setSize("400px", "600px");
		layoutPanel.setWidgetLeftWidth(EventsPanel, 391.0, Unit.PX, 404.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(EventsPanel, 68.0, Unit.PX, 697.0, Unit.PX);
		
		TitleLbl = new Label("Upcoming Events");
		EventsPanel.add(TitleLbl);
		TitleLbl.setHeight("32px");
		TitleLbl.setStyleName("CenterTitles");
		
		FirstEventName = new Label("");
		FirstEventName.setStyleName("homePageTextUnderline");
		EventsPanel.add(FirstEventName);
		FirstEventName.setSize("100%", "40px");
		
		FirstEventDate = new Label("");
		FirstEventDate.setStyleName("homePageText");
		EventsPanel.add(FirstEventDate);
		FirstEventDate.setSize("100%", "40px");
		
		FirstEventBody = new Label("");
		FirstEventBody.setStyleName("homePageText");
		EventsPanel.add(FirstEventBody);
		
		SecondEventName = new Label("");
		SecondEventName.setStyleName("homePageTextUnderline");
		EventsPanel.add(SecondEventName);
		SecondEventName.setSize("100%", "40px");
		
		SecondEventDate = new Label("");
		SecondEventDate.setStyleName("homePageText");
		EventsPanel.add(SecondEventDate);
		SecondEventDate.setSize("100%", "40px");
		
		SecondEventBody = new Label("");
		SecondEventBody.setStyleName("homePageText");
		EventsPanel.add(SecondEventBody);
		
		FlowPanel logOutButtonHolder = new FlowPanel();
		layoutPanel.add(logOutButtonHolder);
		layoutPanel.setWidgetLeftWidth(logOutButtonHolder, 70.0, Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(logOutButtonHolder, 620.0, Unit.PX, 40.0, Unit.PX);
		
		LogOutButton = new Button("New button");
		LogOutButton.setStyleName("ButtonBold");
		LogOutButton.setText("Log Out");
		logOutButtonHolder.add(LogOutButton);
		LogOutButton.setSize("100%", "100%");
		
		LogOutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Site.search(Pages.LOGINPAGE);
				
			}
			
		});
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
