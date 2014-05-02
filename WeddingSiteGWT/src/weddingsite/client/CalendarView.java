package weddingsite.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;

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
	
	public CalendarView() {
		

		currentYear = 2014;
		currentMonth = 5;
		
		buttons = new Button[42];
		
		for(int i = 0; i < 42; i++) {
			Button b = new Button();
			b.setStyleName("ButtonColorScheme");
			b.setSize("98%", "98%");
			buttons[i] = b;
		}
		
		FlowPanel mainFlowPanel = new FlowPanel();
		mainFlowPanel.setStyleName("Background");
		initWidget(mainFlowPanel);
		mainFlowPanel.setSize("2000px", "2000px");
		
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
		mainLayoutPanel.setWidgetLeftRight(pageView, 0.0, Unit.PCT, 30, Unit.PCT);
		
		calendarGrid = new Grid(6, 7);
		calendarGrid.setStyleName("InnerBackgroundCenter");
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
		mainLayoutPanel.setWidgetLeftWidth(yearSelector, 395.0, Unit.PX, 88.0, Unit.PX);
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
		goToDateButton.setStyleName("ButtonColorScheme");
		goToDateButton.setText("Go!");
		mainLayoutPanel.add(goToDateButton);
		mainLayoutPanel.setWidgetLeftWidth(goToDateButton, 360.0, Unit.PX, 65.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(goToDateButton, 783.0, Unit.PX, 26.0, Unit.PX);
		goToDateButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleGoToDateClick();		
			}
		});
		
		Label monthSelectorLabel = new Label("Month");
		monthSelectorLabel.setStyleName("CenterText");
		mainLayoutPanel.add(monthSelectorLabel);
		mainLayoutPanel.setWidgetLeftWidth(monthSelectorLabel, 304.0, Unit.PX, 85.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(monthSelectorLabel, 713.0, Unit.PX, 18.0, Unit.PX);
		
		Label yearSelectorLabel = new Label("Year");
		yearSelectorLabel.setStyleName("CenterText");
		mainLayoutPanel.add(yearSelectorLabel);
		mainLayoutPanel.setWidgetLeftWidth(yearSelectorLabel, 396.0, Unit.PX, 87.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(yearSelectorLabel, 713.0, Unit.PX, 18.0, Unit.PX);
		
		calendarMonthYearLabel = new Label("");
		calendarMonthYearLabel.setStyleName("textBig");
		mainLayoutPanel.add(calendarMonthYearLabel);
		mainLayoutPanel.setWidgetLeftWidth(calendarMonthYearLabel, 270.0, Unit.PX, 235.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(calendarMonthYearLabel, 30.0, Unit.PX, 32.0, Unit.PX);
		
		Label sundayLabel = new Label("Sunday");
		sundayLabel.setStyleName("CenterText");
		mainLayoutPanel.add(sundayLabel);
		mainLayoutPanel.setWidgetLeftWidth(sundayLabel, 63.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(sundayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label mondayLabel = new Label("Monday");
		mondayLabel.setStyleName("CenterText");
		mainLayoutPanel.add(mondayLabel);
		mainLayoutPanel.setWidgetLeftWidth(mondayLabel, 169.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(mondayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label tuesdayLabel = new Label("Tuesday");
		tuesdayLabel.setStyleName("CenterText");
		mainLayoutPanel.add(tuesdayLabel);
		mainLayoutPanel.setWidgetLeftWidth(tuesdayLabel, 267.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(tuesdayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label wednesdayLabel = new Label("Wednesday");
		wednesdayLabel.setStyleName("CenterText");
		mainLayoutPanel.add(wednesdayLabel);
		mainLayoutPanel.setWidgetLeftWidth(wednesdayLabel, 356.0, Unit.PX, 74.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(wednesdayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label thursdayLabel = new Label("Thursday");
		thursdayLabel.setStyleName("CenterText");
		mainLayoutPanel.add(thursdayLabel);
		mainLayoutPanel.setWidgetLeftWidth(thursdayLabel, 458.0, Unit.PX, 65.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(thursdayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label fridayLabel = new Label("Friday");
		fridayLabel.setStyleName("CenterText");
		mainLayoutPanel.add(fridayLabel);
		mainLayoutPanel.setWidgetLeftWidth(fridayLabel, 561.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(fridayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
		Label saturdayLabel = new Label("Saturday");
		saturdayLabel.setStyleName("CenterText");
		mainLayoutPanel.add(saturdayLabel);
		mainLayoutPanel.setWidgetLeftWidth(saturdayLabel, 659.0, Unit.PX, 56.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(saturdayLabel, 68.0, Unit.PX, 18.0, Unit.PX);
		
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
}
