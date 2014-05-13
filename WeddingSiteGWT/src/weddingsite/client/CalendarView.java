package weddingsite.client;

import java.util.ArrayList;

import weddingsite.client.Site.Pages;
import weddingsite.shared.ActionType;
import weddingsite.shared.Activity;
import weddingsite.shared.EventsModel;
import weddingsite.shared.GetItemsResult;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

public class CalendarView extends Composite {
	
	private Button[] buttons;
	private LayoutPanel mainLayoutPanel;
	private Grid calendarGrid;
	private ListBox monthSelector;
	private ListBox yearSelector;
	private String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private int currentYear;
	private int currentMonth;
	private Label calendarMonthYearLabel;
	private EventsModel eventsModel;
	private ArrayList<Activity> events;
	private TextArea eventTextArea;
	
	public CalendarView() {
			
		eventsModel = new EventsModel();
		
		if(Site.currentUser.getIsAdmin()) {		
			eventsModel.setAccountName(Site.currentUser.getAccountName());
			eventsModel.setType(ActionType.GETEVENTS);		
			RPC.getEventsService.GetEvents(eventsModel, new AsyncCallback<GetItemsResult<Activity>>() {
				@Override
				public void onFailure(Throwable caught) {
					System.err.println("CLIENT: Something went wrong getting activites for an admin");
					
				}

				@Override
				public void onSuccess(GetItemsResult<Activity> result) {
					events = (ArrayList<Activity>) result.getResult();			
				}			
			});
		} else {
			
			eventsModel.setAccountName(Site.currentUser.getAccountName());
			eventsModel.setUsername(Site.currentUser.getUsername());
			eventsModel.setType(ActionType.GETEVENTSFORUSER);
			
			RPC.getEventsService.GetEvents(eventsModel, new AsyncCallback<GetItemsResult<Activity>>() {

				@Override
				public void onFailure(Throwable caught) {
					
					System.err.println("CLIENT: Something went wrong getting activites for a user");
				}

				@Override
				public void onSuccess(GetItemsResult<Activity> result) {
					events = (ArrayList<Activity>) result.getResult();					
				}				
			});		
		}
		
		eventsModel = new EventsModel();
		currentYear = 2014;
		currentMonth = 5;
		
		buttons = new Button[42];
		
		for(int i = 0; i < 42; i++) {
			final Button b = new Button();
			b.setStyleName("calendarButtons");
			b.setSize("98%", "98%");
			b.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					handleDateClick(currentMonth, Integer.parseInt(b.getText()), currentYear);
				}				
			});
			buttons[i] = b;
		}
		
		FlowPanel mainFlowPanel = new FlowPanel();
		mainFlowPanel.setStyleName("Background");
		initWidget(mainFlowPanel);
		mainFlowPanel.setSize("100%", "100%");
		
		ScrollPanel mainScrollPanel = new ScrollPanel();
		mainFlowPanel.add(mainScrollPanel);
		mainScrollPanel.setSize("100%", "100%");
		
		mainLayoutPanel = new LayoutPanel();
		mainLayoutPanel.setStyleName("InnerBackground");
		mainScrollPanel.setWidget(mainLayoutPanel);
		mainLayoutPanel.setSize("1200px", "900px");
		
		PageView pageView = new PageView();
		mainLayoutPanel.add(pageView);
		mainLayoutPanel.setWidgetTopBottom(pageView, 93.7,  Unit.PCT, 0.0, Unit.PCT);
		mainLayoutPanel.setWidgetLeftRight(pageView, 2.3, Unit.PCT, 30, Unit.PCT);
		
		calendarGrid = new Grid(6, 7);
		calendarGrid.setStyleName("calendarBackground");
		mainLayoutPanel.add(calendarGrid);
		calendarGrid.setSize("700px", "600px");
		mainLayoutPanel.setWidgetLeftWidth(calendarGrid, 39.0, Unit.PX, 700, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(calendarGrid, 92.0, Unit.PX, 600, Unit.PX);
		
		monthSelector = new ListBox();
		mainLayoutPanel.add(monthSelector);
		mainLayoutPanel.setWidgetLeftWidth(monthSelector, 301.0, Unit.PX, 88.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(monthSelector, 737.0, Unit.PX, 26.0, Unit.PX);
		
		monthSelector.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				handleMonthChange(monthSelector.getItemText(monthSelector.getSelectedIndex()));
			}		
		});
		
		for(int i = 0; i < 12; i++) {
			monthSelector.addItem(months[i]);
		}
		
		yearSelector = new ListBox();
		mainLayoutPanel.add(yearSelector);
		mainLayoutPanel.setWidgetLeftWidth(yearSelector, 398.0, Unit.PX, 88.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(yearSelector, 737.0, Unit.PX, 26.0, Unit.PX);
		
		yearSelector.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				handleYearChange(yearSelector.getItemText(yearSelector.getSelectedIndex()));
			}		
		});
		
		for(int i = 1950; i < 2100; i++) {
			yearSelector.addItem("" + i);
		}
		
		Button goToDateButton = new Button("New button");
		goToDateButton.setStyleName("calendarButtons");
		goToDateButton.setText("Go!");
		mainLayoutPanel.add(goToDateButton);
		mainLayoutPanel.setWidgetLeftWidth(goToDateButton, 357.0, Unit.PX, 65.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(goToDateButton, 780.0, Unit.PX, 26.0, Unit.PX);
		goToDateButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleGoToDateClick();		
			}
		});
		
		Label monthSelectorLabel = new Label("Month");
		monthSelectorLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(monthSelectorLabel);
		mainLayoutPanel.setWidgetLeftWidth(monthSelectorLabel, 301.0, Unit.PX, 85.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(monthSelectorLabel, 713.0, Unit.PX, 18.0, Unit.PX);
		
		Label yearSelectorLabel = new Label("Year");
		yearSelectorLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(yearSelectorLabel);
		mainLayoutPanel.setWidgetLeftWidth(yearSelectorLabel, 398.0, Unit.PX, 87.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(yearSelectorLabel, 713.0, Unit.PX, 18.0, Unit.PX);
		
		calendarMonthYearLabel = new Label("");
		calendarMonthYearLabel.setStyleName("calendarTitle");
		mainLayoutPanel.add(calendarMonthYearLabel);
		mainLayoutPanel.setWidgetLeftWidth(calendarMonthYearLabel, 270.0, Unit.PX, 235.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(calendarMonthYearLabel, 30.0, Unit.PX, 32.0, Unit.PX);
		
		Label sundayLabel = new Label("Sunday");
		sundayLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(sundayLabel);
		mainLayoutPanel.setWidgetLeftWidth(sundayLabel, 63.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(sundayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label mondayLabel = new Label("Monday");
		mondayLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(mondayLabel);
		mainLayoutPanel.setWidgetLeftWidth(mondayLabel, 169.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(mondayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label tuesdayLabel = new Label("Tuesday");
		tuesdayLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(tuesdayLabel);
		mainLayoutPanel.setWidgetLeftWidth(tuesdayLabel, 267.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(tuesdayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label wednesdayLabel = new Label("Wednesday");
		wednesdayLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(wednesdayLabel);
		mainLayoutPanel.setWidgetLeftWidth(wednesdayLabel, 356.0, Unit.PX, 74.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(wednesdayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label thursdayLabel = new Label("Thursday");
		thursdayLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(thursdayLabel);
		mainLayoutPanel.setWidgetLeftWidth(thursdayLabel, 458.0, Unit.PX, 65.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(thursdayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label fridayLabel = new Label("Friday");
		fridayLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(fridayLabel);
		mainLayoutPanel.setWidgetLeftWidth(fridayLabel, 561.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(fridayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label saturdayLabel = new Label("Saturday");
		saturdayLabel.setStyleName("calendarLabel");
		mainLayoutPanel.add(saturdayLabel);
		mainLayoutPanel.setWidgetLeftWidth(saturdayLabel, 659.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(saturdayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label eventTitle = new Label("Event");
		eventTitle.setStyleName("calendarTitle");
		mainLayoutPanel.add(eventTitle);
		mainLayoutPanel.setWidgetLeftWidth(eventTitle, 917.0, Unit.PX, 116.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(eventTitle, 68.0, Unit.PX, 26.0, Unit.PX);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		mainLayoutPanel.add(scrollPanel);
		mainLayoutPanel.setWidgetLeftWidth(scrollPanel, 864.0, Unit.PX, 223.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(scrollPanel, 110.0, Unit.PX, 582.0, Unit.PX);
		
		eventTextArea = new TextArea();
		eventTextArea.setStyleName("textBigWithBackground");
		eventTextArea.setEnabled(false);
		scrollPanel.setWidget(eventTextArea);
		eventTextArea.setSize("95%", "95%");
		
		if(Site.currentUser.getIsAdmin()) {
			
			Button manageActivitiesButton = new Button("New button");
			manageActivitiesButton.setStyleName("ButtonColorScheme");
			manageActivitiesButton.setText("Manage Activities");
			mainLayoutPanel.add(manageActivitiesButton);
			mainLayoutPanel.setWidgetLeftWidth(manageActivitiesButton, 917.0, Unit.PX, 125.0, Unit.PX);
			mainLayoutPanel.setWidgetTopHeight(manageActivitiesButton, 713.0, Unit.PX, 32.0, Unit.PX);
			
			manageActivitiesButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Site.search(Pages.MANAGEACTIVITIESPAGE);			
				}		
			});
		}
		
		for(int i = 0; i < 42; i++) {
			calendarGrid.setWidget((i / 7), (i % 7), buttons[i]);
		}
		
		handleGoToDateClick();
	}
	
	public void handleMonthChange(String month) {
		for(int i = 0; i < months.length; i++) {
			if(months[i].equals(month)) {
				currentMonth = i + 1;
				break;
			}
		}
	}
	
	public void handleYearChange(String year) {
		currentYear = new Integer(Integer.parseInt(year)).intValue();
	}
	
	public void handleGoToDateClick() {
		calendarMonthYearLabel.setText(months[currentMonth - 1] + " " + currentYear);
		refreshCalendar(currentYear, currentMonth);
	}
	
	public boolean isLeapYear(int year) {
		
		if(((year / 4) == 0 && (year / 100) != 0) || (year / 400 == 0)) {
			return true;
		} else {
			return false;
		}		
	}
	
	public int getNumDayInMonth(int year, int month) {
		
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if(month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else {
			if(isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
	}
	
	public int getFirstDayOfMonth(int year, int month) {
		
		return ((1461 * (year + 4800 + (month - 14)/12))/4 + (367 * (month - 2 - 12 * ((month - 14)/12)))/12 - (3 * ((year + 4900 + (month - 14)/12)/100))/4 + 1 - 32075) % 7;
	}
	
	public void refreshCalendar(int year, int month) {
		
		int start = getFirstDayOfMonth(year, month);
		int end = getNumDayInMonth(year, month);
		
		for(int i = 0; i < 42; i++) {
			if(i >= start && i < (end + start)) {
				buttons[i].setEnabled(true);
				if((i - start) > 8) {
					buttons[i].setText("" + (i - start + 1));
				} else {
					buttons[i].setText("" + 0 + (i - start + 1));
				}
			} else {
				buttons[i].setEnabled(false);
				buttons[i].setText("");
			}
		}
	}
	
	public void handleDateClick(int month, int day, int year) {
		
		String textToSet = "No activities scheduled!";
		
		String m = Integer.toString(month);
		String d = Integer.toString(day);
		String y = Integer.toString(year);	
	
		for(Activity a : events) {
			
			String delims = "[/]+";
			String[] tokens = a.getDate().split(delims);
			
			if(tokens[0].equals(m) && tokens[1].equals(d) && tokens[2].equals(y)) {
				
				if(textToSet.equals("No activities scheduled!")) {
					textToSet = "";
				}
				
				textToSet += a.getTitle() + "\n\n";
				textToSet += "Start Time: \n";
				textToSet += a.getStartTime() + "\n\n";
				textToSet += "End Time: \n\n";
				textToSet += a.getEndTime() + "\n\n";
				textToSet += a.getBody() + "\n\n\n";			
			}
		}
		
		eventTextArea.setText(textToSet);
	}
}
