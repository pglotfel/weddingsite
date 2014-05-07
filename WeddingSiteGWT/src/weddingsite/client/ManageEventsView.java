package weddingsite.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import weddingsite.shared.ActionType;
import weddingsite.shared.Activity;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.EventsModel;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.User;
import weddingsite.shared.UserQueryModel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class ManageEventsView extends Composite {
	
	//Widgets/Panels
	private LayoutPanel mainLayoutPanel;
	private MenuBar activityMenu;
	private MenuBar usersOffEventMenu;
	private MenuBar usersOnEventMenu;
	private FlowPanel removeFlowPanel;
	private FlowPanel addFlowPanel;
	private UserQueryModel userQueryModel;
	private Label eventLabel;
	private FlowPanel activtyDateFlowPanel;
	private ListBox startHourListBox;
	private ListBox startMeridiemListBox;
	private Label startTimeLabel;
	private Label endTimeLabel;
	private ListBox endHourListBox;
	private ListBox endMinuteListBox;
	private ListBox endMeridiemListBox;
	private ListBox startMinuteListBox;
	private Label eventTitleLabel;
	private Label eventDescriptionLabel;
	private Button deleteEventButton;
	private Button cancelActivityButton;
	private Label userOffEventNameLabel;
	private Label userOnEventNameLabel;
	
	//models
	private EventsModel eventsModel;
	private Button submitActivityButton;
	private TextArea eventDescriptionTextArea;
	private TextBox eventTitleTextBox;
	private DatePicker eventDatePicker;
	
	//other
	private String currentDay;
	private final String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	public ManageEventsView() {
		
		eventsModel = new EventsModel();
		eventsModel.setAccountName(Site.currentUser.getAccountName());
		
		userQueryModel = new UserQueryModel();
		userQueryModel.setAccountName(Site.currentUser.getAccountName());
		userQueryModel.setUsername(Site.currentUser.getUsername());
		userQueryModel.setType(ActionType.GETUSERS);
		
		FlowPanel mainFlowPanel = new FlowPanel();
		initWidget(mainFlowPanel);
		mainFlowPanel.setSize("2000px", "2000px");
		
		ScrollPanel mainScrollPanel = new ScrollPanel();
		mainFlowPanel.add(mainScrollPanel);
		mainScrollPanel.setSize("100%", "100%");
		
		mainLayoutPanel = new LayoutPanel();
		mainLayoutPanel.setStyleName("InnerBackground");
		mainScrollPanel.setWidget(mainLayoutPanel);
		mainLayoutPanel.setSize("1100px", "1000px");
		
		PageView pageView = new PageView();
		mainLayoutPanel.add(pageView);
		mainLayoutPanel.setWidgetTopBottom(pageView, 95.0,  Unit.PCT, 0.0, Unit.PCT);
		mainLayoutPanel.setWidgetLeftRight(pageView, 2.3, Unit.PCT, 30, Unit.PCT);
		
		activityMenu = new MenuBar(true);
		mainLayoutPanel.add(activityMenu);
		mainLayoutPanel.setWidgetLeftWidth(activityMenu, 8, Unit.PCT, 30, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(activityMenu, 10, Unit.PCT, 30, Unit.PCT);	
		
		Label titleLabel = new Label("Activity Manager");
		titleLabel.setStyleName("CenterTitles");
		mainLayoutPanel.add(titleLabel);
		mainLayoutPanel.setWidgetTopHeight(titleLabel, 4, Unit.PCT, 25, Unit.PX);
		
		usersOnEventMenu = new MenuBar(true);
		mainLayoutPanel.add(usersOnEventMenu);
		mainLayoutPanel.setWidgetLeftWidth(usersOnEventMenu, 76, Unit.PCT, 20.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(usersOnEventMenu, 10, Unit.PCT, 67.6, Unit.PCT);
		
		usersOffEventMenu = new MenuBar(true);
		mainLayoutPanel.add(usersOffEventMenu);
		mainLayoutPanel.setWidgetLeftWidth(usersOffEventMenu, 52, Unit.PCT, 20.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(usersOffEventMenu, 10.0, Unit.PCT, 67.7, Unit.PCT);
		
		addFlowPanel = new FlowPanel();
		mainLayoutPanel.add(addFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(addFlowPanel, 56.5, Unit.PCT, 11, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(addFlowPanel, 82, Unit.PCT, 4, Unit.PCT);
		
		Button addToEventButton = new Button("New button");
		addToEventButton.setText("Add User");
		addToEventButton.setStyleName("ButtonColorScheme");
		addFlowPanel.add(addToEventButton);
		addToEventButton.setSize("100%", "100%");
		
		addToEventButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleAddToEventClick();			
			}
		});
		
		removeFlowPanel = new FlowPanel();
		mainLayoutPanel.add(removeFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(removeFlowPanel, 80.5, Unit.PCT, 11, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(removeFlowPanel, 82, Unit.PCT, 4, Unit.PCT);
		
		Button removeFromEventButton = new Button("New button");
		removeFromEventButton.setText("Remove User");
		removeFromEventButton.setStyleName("ButtonColorScheme");
		removeFlowPanel.add(removeFromEventButton);
		removeFromEventButton.setSize("100%", "100%");
		
		removeFromEventButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleRemoveFromEventClick();			
			}		
		});
		
		eventLabel = new Label("Click on an activity!");
		eventLabel.setStyleName("CenterText");
		mainLayoutPanel.add(eventLabel);
		mainLayoutPanel.setWidgetLeftWidth(eventLabel, 15.5, Unit.PCT, 15, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventLabel, 41, Unit.PCT, 3, Unit.PCT);
		
		FlowPanel addButtonFlowPanel = new FlowPanel();
		mainLayoutPanel.add(addButtonFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(addButtonFlowPanel, 13, Unit.PCT, 4, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(addButtonFlowPanel, 45, Unit.PCT, 3, Unit.PCT);
		
		Button addButton = new Button("New button");
		addButton.setStyleName("ButtonColorScheme");
		addButtonFlowPanel.add(addButton);
		addButton.setSize("100%", "100%");
		addButton.setText("Add");
		
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				removeWidgetsFromView();
				addWidgetsToViewAdd();
			}		
		});
		
		FlowPanel editButtonFlowPanel = new FlowPanel();
		mainLayoutPanel.add(editButtonFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(editButtonFlowPanel, 29, Unit.PCT, 4, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(editButtonFlowPanel, 45, Unit.PCT, 3, Unit.PCT);
		
		Button editButton = new Button("New button");
		editButton.setStyleName("ButtonColorScheme");
		editButtonFlowPanel.add(editButton);
		editButton.setSize("100%", "100%");
		editButton.setText("Edit");
		
		editButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				removeWidgetsFromView();
				addWidgetsToViewEdit();			
			}		
		});
		
		startTimeLabel = new Label("Start Time");
		startTimeLabel.setStyleName("CenterText");
			
		startHourListBox = new ListBox();
		startHourListBox.setVisibleItemCount(1);
			
		startMinuteListBox = new ListBox();
		startMinuteListBox.setVisibleItemCount(1);

		
		startMeridiemListBox = new ListBox();
		startMeridiemListBox.setVisibleItemCount(1);
		
		endTimeLabel = new Label("End Time");
		endTimeLabel.setStyleName("CenterText");

		
		eventTitleLabel = new Label("Title");
		eventTitleLabel.setStyleName("CenterText");
		
		eventTitleTextBox = new TextBox();
		
		eventDescriptionTextArea = new TextArea();
		
		eventDescriptionLabel = new Label("Description");
		eventDescriptionLabel.setStyleName("CenterText");

		
		activtyDateFlowPanel = new FlowPanel();
		
		eventDatePicker = new DatePicker();
		eventDatePicker.setStyleName("Background");
		activtyDateFlowPanel.add(eventDatePicker);
		
		eventDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
	        public void onValueChange(ValueChangeEvent<Date> event) {
	          Date date = event.getValue();
	          currentDay = DateTimeFormat.getMediumDateFormat().format(date);
	          System.out.println(currentDay);
	        }
	      });
		
		endHourListBox = new ListBox();
		endHourListBox.setVisibleItemCount(1);
		
		endMinuteListBox = new ListBox();
		endMinuteListBox.setVisibleItemCount(1);
		
		endMeridiemListBox = new ListBox();
		endMeridiemListBox.setVisibleItemCount(1);
		
		for (int i = 1; i <= 12; i++) {
			startHourListBox.addItem("" + i);
			endHourListBox.addItem("" + i);
		}		
		
		for(int i = 0; i < 60; i++) {
			startMinuteListBox.addItem("" + i);
			endMinuteListBox.addItem("" + i);
		}
		
		startMeridiemListBox.addItem("AM");
		startMeridiemListBox.addItem("PM");
		endMeridiemListBox.addItem("AM");
		endMeridiemListBox.addItem("PM");
		
		submitActivityButton = new Button("New button");
		submitActivityButton.setStyleName("ButtonColorScheme");
		submitActivityButton.setText("Submit");
		
		cancelActivityButton = new Button("New button");
		cancelActivityButton.setStyleName("ButtonColorScheme");
		cancelActivityButton.setText("Cancel");
		
		deleteEventButton = new Button("New button");
		deleteEventButton.setText("Delete");
		deleteEventButton.setStyleName("ButtonColorScheme");
		
		deleteEventButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleDeleteEventClick();			
			}	
		});
		
		userOffEventNameLabel = new Label("Click on a user!");
		userOffEventNameLabel.setStyleName("CenterText");
		mainLayoutPanel.add(userOffEventNameLabel);
		mainLayoutPanel.setWidgetLeftWidth(userOffEventNameLabel, 622.0, Unit.PX, 120.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(userOffEventNameLabel, 782.0, Unit.PX, 18.0, Unit.PX);
		
		userOnEventNameLabel = new Label("Click on a user!");
		userOnEventNameLabel.setStyleName("CenterText");
		mainLayoutPanel.add(userOnEventNameLabel);
		mainLayoutPanel.setWidgetLeftWidth(userOnEventNameLabel, 885.0, Unit.PX, 120.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(userOnEventNameLabel, 781.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblActivities = new Label("Activities");
		lblActivities.setStyleName("TextColorScheme");
		mainLayoutPanel.add(lblActivities);
		mainLayoutPanel.setWidgetLeftWidth(lblActivities, 226.0, Unit.PX, 72.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(lblActivities, 76.0, Unit.PX, 18.0, Unit.PX);
		
		submitActivityButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(mainLayoutPanel.getWidgetIndex(deleteEventButton) == -1) {
					handleSubmitAddActivityClick();
				} else {
					handleSubmitEditActivityClick();
				}
						
			}		
		});
			
		loadEvents();
	}
	
	private void addWidgetsToViewAdd() {
		
		mainLayoutPanel.add(activtyDateFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(activtyDateFlowPanel, 8, Unit.PCT, 30, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(activtyDateFlowPanel, 50, Unit.PCT, 191.0, Unit.PX);
		mainLayoutPanel.add(startTimeLabel);
		mainLayoutPanel.setWidgetLeftWidth(startTimeLabel, 8, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startTimeLabel, 70, Unit.PCT, 15, Unit.PX);
		mainLayoutPanel.add(endTimeLabel);
		mainLayoutPanel.setWidgetLeftWidth(endTimeLabel, 30, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endTimeLabel, 70, Unit.PCT, 15.0, Unit.PX);
		mainLayoutPanel.add(startHourListBox);
		mainLayoutPanel.setWidgetLeftWidth(startHourListBox, 4.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startHourListBox, 72.1, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(startMinuteListBox);
		mainLayoutPanel.setWidgetLeftWidth(startMinuteListBox, 9.5, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startMinuteListBox, 72, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(startMeridiemListBox);
		mainLayoutPanel.setWidgetLeftWidth(startMeridiemListBox, 15.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startMeridiemListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(endHourListBox);
		mainLayoutPanel.setWidgetLeftWidth(endHourListBox, 26.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endHourListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(endMinuteListBox);
		mainLayoutPanel.setWidgetLeftWidth(endMinuteListBox, 31.5, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endMinuteListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(endMeridiemListBox);
		mainLayoutPanel.setWidgetLeftWidth(endMeridiemListBox, 37, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endMeridiemListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(eventTitleLabel);
		mainLayoutPanel.setWidgetLeftWidth(eventTitleLabel, 4, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventTitleLabel, 77, Unit.PCT, 15.0, Unit.PX);
		mainLayoutPanel.add(eventTitleTextBox);
		mainLayoutPanel.setWidgetLeftWidth(eventTitleTextBox, 4, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventTitleTextBox, 79, Unit.PCT, 30, Unit.PX);
		mainLayoutPanel.add(eventDescriptionLabel);
		mainLayoutPanel.setWidgetLeftWidth(eventDescriptionLabel, 4, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventDescriptionLabel, 82.0, Unit.PCT, 18.0, Unit.PX);
		mainLayoutPanel.add(eventDescriptionTextArea);
		mainLayoutPanel.setWidgetLeftWidth(eventDescriptionTextArea, 4.0, Unit.PCT, 34.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventDescriptionTextArea, 84.0, Unit.PCT, 80, Unit.PX);
		mainLayoutPanel.add(submitActivityButton);
		mainLayoutPanel.setWidgetLeftWidth(submitActivityButton, 302.0, Unit.PX, 68.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(submitActivityButton, 790.0, Unit.PX, 32.0, Unit.PX);
		mainLayoutPanel.add(cancelActivityButton);
		mainLayoutPanel.setWidgetLeftWidth(cancelActivityButton, 376.0, Unit.PX, 68.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(cancelActivityButton, 790.0, Unit.PX, 32.0, Unit.PX);
	}
	
	private void addWidgetsToViewEdit() {
		
		mainLayoutPanel.add(activtyDateFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(activtyDateFlowPanel, 8, Unit.PCT, 30, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(activtyDateFlowPanel, 50, Unit.PCT, 191.0, Unit.PX);
		mainLayoutPanel.add(startTimeLabel);
		mainLayoutPanel.setWidgetLeftWidth(startTimeLabel, 8, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startTimeLabel, 70, Unit.PCT, 15, Unit.PX);
		mainLayoutPanel.add(endTimeLabel);
		mainLayoutPanel.setWidgetLeftWidth(endTimeLabel, 30, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endTimeLabel, 70, Unit.PCT, 15.0, Unit.PX);
		mainLayoutPanel.add(startHourListBox);
		mainLayoutPanel.setWidgetLeftWidth(startHourListBox, 4.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startHourListBox, 72.1, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(startMinuteListBox);
		mainLayoutPanel.setWidgetLeftWidth(startMinuteListBox, 9.5, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startMinuteListBox, 72, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(startMeridiemListBox);
		mainLayoutPanel.setWidgetLeftWidth(startMeridiemListBox, 15.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startMeridiemListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(endHourListBox);
		mainLayoutPanel.setWidgetLeftWidth(endHourListBox, 26.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endHourListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(endMinuteListBox);
		mainLayoutPanel.setWidgetLeftWidth(endMinuteListBox, 31.5, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endMinuteListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(endMeridiemListBox);
		mainLayoutPanel.setWidgetLeftWidth(endMeridiemListBox, 37, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endMeridiemListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		mainLayoutPanel.add(eventTitleLabel);
		mainLayoutPanel.setWidgetLeftWidth(eventTitleLabel, 4, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventTitleLabel, 77, Unit.PCT, 15.0, Unit.PX);
		mainLayoutPanel.add(eventTitleTextBox);
		mainLayoutPanel.setWidgetLeftWidth(eventTitleTextBox, 4, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventTitleTextBox, 79, Unit.PCT, 30, Unit.PX);
		mainLayoutPanel.add(eventDescriptionLabel);
		mainLayoutPanel.setWidgetLeftWidth(eventDescriptionLabel, 4, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventDescriptionLabel, 82.0, Unit.PCT, 18.0, Unit.PX);
		mainLayoutPanel.add(eventDescriptionTextArea);
		mainLayoutPanel.setWidgetLeftWidth(eventDescriptionTextArea, 4.0, Unit.PCT, 34.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(eventDescriptionTextArea, 84.0, Unit.PCT, 80, Unit.PX);
		mainLayoutPanel.add(submitActivityButton);
		mainLayoutPanel.setWidgetLeftWidth(submitActivityButton, 302.0, Unit.PX, 68.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(submitActivityButton, 790.0, Unit.PX, 32.0, Unit.PX);
		mainLayoutPanel.add(cancelActivityButton);
		mainLayoutPanel.setWidgetLeftWidth(cancelActivityButton, 376.0, Unit.PX, 68.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(cancelActivityButton, 790.0, Unit.PX, 32.0, Unit.PX);
		mainLayoutPanel.add(deleteEventButton);
		mainLayoutPanel.setWidgetLeftWidth(deleteEventButton, 450.0, Unit.PX, 68.0, Unit.PX);
		mainLayoutPanel.setWidgetTopHeight(deleteEventButton, 790.0, Unit.PX, 32.0, Unit.PX);
	}
	
	private void removeWidgetsFromView() {
		
		mainLayoutPanel.remove(activtyDateFlowPanel);
		mainLayoutPanel.remove(startTimeLabel);
		mainLayoutPanel.remove(endTimeLabel);
		mainLayoutPanel.remove(startHourListBox);
		mainLayoutPanel.remove(startMinuteListBox);
		mainLayoutPanel.remove(startMeridiemListBox);
		mainLayoutPanel.remove(endHourListBox);
		mainLayoutPanel.remove(endMinuteListBox);
		mainLayoutPanel.remove(endMeridiemListBox);
		mainLayoutPanel.remove(eventTitleLabel);
		mainLayoutPanel.remove(eventTitleTextBox);
		mainLayoutPanel.remove(eventDescriptionLabel);
		mainLayoutPanel.remove(eventDescriptionTextArea);
		mainLayoutPanel.remove(submitActivityButton);
		mainLayoutPanel.remove(cancelActivityButton);
		mainLayoutPanel.remove(deleteEventButton);
		eventTitleTextBox.setText("");
		eventDescriptionTextArea.setText("");
	}
	
	public void loadEvents() {
		
		eventsModel.setType(ActionType.GETEVENTS);
		System.out.println("CLIENT: LOADING EVENTS");
		RPC.getEventsService.GetEvents(eventsModel, new AsyncCallback<GetItemsResult<Activity>>() {

			@Override
			public void onFailure(Throwable caught) {
		
				
			}

			@Override
			public void onSuccess(GetItemsResult<Activity> result) {
				activityMenu.clearItems();
				
				List<Activity> events = result.getResult();
				
				if(events.isEmpty()) {
					activityMenu.addItem("No activities!", (Command) null);
				} else {
				
					for(final Activity a : result.getResult()) {
						activityMenu.addItem(a.getTitle(), new Command() {
							@Override
							public void execute() {
								System.out.println("CLIENT: LOADING ACTIVITY " + a.getTitle());
								eventLabel.setText(a.getTitle());
								handleEventClick(a.getTitle());						
							}						
						});
					}
				}
			}
		});
	}
	
	private void handleSubmitEditActivityClick() {
		
		eventsModel.setAccountName(Site.currentUser.getAccountName());
		eventsModel.setType(ActionType.EDITEVENT);
		Activity a = new Activity();
		a.setBody(eventDescriptionTextArea.getText());
		a.setTitle(eventTitleTextBox.getText());
		eventsModel.setActivityName(eventLabel.getText());
		
		String [] tokens = currentDay.split("[ ]+");
		String dateToSet = "";
		
		for(int i = 0; i < 12; i++) {
			if(months[i].equals(tokens[1])) {
				dateToSet += (i + 1);
				dateToSet += "/";
			}
		}
		
		dateToSet += tokens[2] + "/";
		dateToSet += tokens[0];
		
		a.setDate(dateToSet);
		
		System.out.println(dateToSet);
		
		a.setStartTime(startHourListBox.getItemText(startHourListBox.getSelectedIndex()) + ":" + startMinuteListBox.getItemText(startMinuteListBox.getSelectedIndex())
				+ " " + startMeridiemListBox.getItemText(startMeridiemListBox.getSelectedIndex()));
		
		a.setEndTime(endHourListBox.getItemText(endHourListBox.getSelectedIndex()) + ":" + endMinuteListBox.getItemText(endMinuteListBox.getSelectedIndex())
				+ " " + endMeridiemListBox.getItemText(endMeridiemListBox.getSelectedIndex()));
		
		eventsModel.setActivity(a);
		
		RPC.getEventsService.EditEvents(eventsModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: Encountered an error adding an event");				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				System.out.println("ADDING ACTIVITY SUCCESSS!");	
				System.out.println("Am I calling?");
				loadEvents();
				removeWidgetsFromView();
			}
			
		});
	}
	
	private void handleSubmitAddActivityClick() {
		
		eventsModel.setAccountName(Site.currentUser.getAccountName());
		eventsModel.setType(ActionType.ADDEVENT);
		Activity a = new Activity();
		a.setBody(eventDescriptionTextArea.getText());
		a.setTitle(eventTitleTextBox.getText());
		
		String [] tokens = currentDay.split("[ ]+");
		String dateToSet = "";
		
		for(int i = 0; i < 12; i++) {
			if(months[i].equals(tokens[1])) {
				dateToSet += (i + 1);
				dateToSet += "/";
			}
		}
		
		dateToSet += tokens[2] + "/";
		dateToSet += tokens[0];
		
		a.setDate(dateToSet);
		
		System.out.println(dateToSet);
		
		a.setStartTime(startHourListBox.getItemText(startHourListBox.getSelectedIndex()) + ":" + startMinuteListBox.getItemText(startMinuteListBox.getSelectedIndex())
				+ " " + startMeridiemListBox.getItemText(startMeridiemListBox.getSelectedIndex()));
		
		a.setEndTime(endHourListBox.getItemText(endHourListBox.getSelectedIndex()) + ":" + endMinuteListBox.getItemText(endMinuteListBox.getSelectedIndex())
				+ " " + endMeridiemListBox.getItemText(endMeridiemListBox.getSelectedIndex()));
		
		eventsModel.setActivity(a);
		
		RPC.getEventsService.EditEvents(eventsModel, new AsyncCallback<EditDataResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: Encountered an error adding an event");				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				System.out.println("ADDING ACTIVITY SUCCESSS!");				
				System.out.println("Am I calling?");
				loadEvents();
				removeWidgetsFromView();
			}		
		});
	}
	
	public void handleDeleteEventClick() {
		 
		eventsModel.setActivityName(eventLabel.getText());
		eventsModel.setType(ActionType.DELETEEVENT);
		
		RPC.getEventsService.EditEvents(eventsModel, new AsyncCallback<EditDataResult>() {
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: There was an error deleting an event");
				
			}

			@Override
			public void onSuccess(EditDataResult result) {		
				System.out.println("DELETED AN EVENT");			
				loadEvents();
				removeWidgetsFromView();
			}	
		});
	}
	
	public void handleEventClick(String title) {
		
		final ArrayList<String> usersOffEvent = new ArrayList<String>();
		
		RPC.performUserQueryService.performGetUsersQuery(userQueryModel, new AsyncCallback<GetItemsResult<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				
				System.err.println("CLIENT: There was an error getting users");
			}

			@Override
			public void onSuccess(GetItemsResult<String> result) {
				for(String s : result.getResult()) {
					usersOffEvent.add(s);
				}
				
			}				
		});	
		

		eventsModel.setActivityName(title);
		eventsModel.setType(ActionType.GETUSERSONEVENT);
			
		RPC.getEventsService.getUsersOnEvent(eventsModel, new AsyncCallback<GetItemsResult<String>>() {	
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: There was an error getting users on an event");
				
			}

			@Override
			public void onSuccess(GetItemsResult<String> result) {
				
				usersOnEventMenu.clearItems();
				
				for(final String s : result.getResult()) {
					usersOnEventMenu.addItem(new MenuItem(s, new Command() {
						@Override
						public void execute() {
							handleUsersOnEventClick(s);
						}				
					}));	
					usersOffEvent.remove(s);
				}
				
				usersOffEventMenu.clearItems();
				for(final String s : usersOffEvent) {
					usersOffEventMenu.addItem(s, new Command() {
						@Override
						public void execute() {
							handleUsersOffEventClick(s);						
						}					
					});
				}	
			}		
		});
	}
	
	private void handleUsersOnEventClick(String name) {
		userOnEventNameLabel.setText(name);	
	}
	
	private void handleUsersOffEventClick(String name) {
		userOffEventNameLabel.setText(name);	
	}
	
	private void handleAddToEventClick() {
		
		ArrayList<String> u = new ArrayList<String>();
		u.add(userOffEventNameLabel.getText());
		
		eventsModel.setActivityName(eventLabel.getText());
		eventsModel.setUsers(u);
		eventsModel.setType(ActionType.ADDUSERSTOEVENT);
		
		System.out.println("REMOVING USER OFF: " + userOffEventNameLabel.getText());
		
		RPC.getEventsService.addUsersToEvent(eventsModel, new AsyncCallback<EditDataResult>() {
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: There was an error adding a user to an event");
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				System.out.println("ADDED USER TO EVENT");
				handleEventClick(eventLabel.getText());	
			}		
		});
	}
	
	private void handleRemoveFromEventClick() {
		
		ArrayList<String> u = new ArrayList<String>();
		u.add(userOnEventNameLabel.getText());
		
		System.out.println("REMOVING USER ON: " + userOnEventNameLabel.getText());
		
		eventsModel.setActivityName(eventLabel.getText());
		eventsModel.setUsers(u);
		eventsModel.setType(ActionType.REMOVEUSERSFROMEVENT);
		
		RPC.getEventsService.addUsersToEvent(eventsModel, new AsyncCallback<EditDataResult>() {
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("CLIENT: There was an error adding a user to an event");
				
			}

			@Override
			public void onSuccess(EditDataResult result) {
				System.out.println("REMOVED USER FROM EVENT");
				handleEventClick(eventLabel.getText());	
			}		
		});
	}
}

