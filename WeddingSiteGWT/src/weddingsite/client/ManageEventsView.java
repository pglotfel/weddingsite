package weddingsite.client;

import weddingsite.shared.ActionType;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.User;
import weddingsite.shared.UserQueryModel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.datepicker.client.DatePicker;

public class ManageEventsView extends Composite {
	
	private LayoutPanel mainLayoutPanel;
	private MenuBar activityMenu;
	private MenuBar userOffActivityMenu;
	private MenuBar userOnActivityMenu;
	private FlowPanel removeFlowPanel;
	private FlowPanel addFlowPanel;
	
	//Models
	private UserQueryModel userQueryModel;
	private Label activityLabel;
	private FlowPanel activtyDateFlowPanel;
	private ListBox startHourListBox;
	private ListBox startMeridiemListBox;
	private Label startTimeLabel;
	private Label endTimeLabel;
	private ListBox endHourListBox;
	private ListBox endMinuteListBox;
	private ListBox endMeridiemListBox;
	
	public ManageEventsView() {
		
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
		
		Label titleLabel = new Label("Activitiy Manager");
		titleLabel.setStyleName("CenterTitles");
		mainLayoutPanel.add(titleLabel);
		mainLayoutPanel.setWidgetTopHeight(titleLabel, 4, Unit.PCT, 25, Unit.PX);
		
		userOnActivityMenu = new MenuBar(true);
		mainLayoutPanel.add(userOnActivityMenu);
		mainLayoutPanel.setWidgetLeftWidth(userOnActivityMenu, 76, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(userOnActivityMenu, 10, Unit.PCT, 70, Unit.PCT);
		
		userOffActivityMenu = new MenuBar(true);
		mainLayoutPanel.add(userOffActivityMenu);
		mainLayoutPanel.setWidgetLeftWidth(userOffActivityMenu, 52, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(userOffActivityMenu, 10.0, Unit.PCT, 70, Unit.PCT);
		
		addFlowPanel = new FlowPanel();
		mainLayoutPanel.add(addFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(addFlowPanel, 56.5, Unit.PCT, 11, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(addFlowPanel, 82, Unit.PCT, 4, Unit.PCT);
		
		Button addToActivityButton = new Button("New button");
		addToActivityButton.setText("Add User");
		addToActivityButton.setStyleName("ButtonColorScheme");
		addFlowPanel.add(addToActivityButton);
		addToActivityButton.setSize("100%", "100%");
		
		removeFlowPanel = new FlowPanel();
		mainLayoutPanel.add(removeFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(removeFlowPanel, 80.5, Unit.PCT, 11, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(removeFlowPanel, 82, Unit.PCT, 4, Unit.PCT);
		
		Button removeFromActivityButton = new Button("New button");
		removeFromActivityButton.setText("Remove User");
		removeFromActivityButton.setStyleName("ButtonColorScheme");
		removeFlowPanel.add(removeFromActivityButton);
		removeFromActivityButton.setSize("100%", "100%");
		
		activityLabel = new Label("Click on an activity!");
		activityLabel.setStyleName("CenterText");
		mainLayoutPanel.add(activityLabel);
		mainLayoutPanel.setWidgetLeftWidth(activityLabel, 15.5, Unit.PCT, 15, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(activityLabel, 41, Unit.PCT, 3, Unit.PCT);
		
		FlowPanel addButtonFlowPanel = new FlowPanel();
		mainLayoutPanel.add(addButtonFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(addButtonFlowPanel, 13, Unit.PCT, 4, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(addButtonFlowPanel, 45, Unit.PCT, 3, Unit.PCT);
		
		Button addButton = new Button("New button");
		addButtonFlowPanel.add(addButton);
		addButton.setSize("100%", "100%");
		addButton.setText("Add");
		
		FlowPanel editButtonFlowPanel = new FlowPanel();
		mainLayoutPanel.add(editButtonFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(editButtonFlowPanel, 29, Unit.PCT, 4, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(editButtonFlowPanel, 45, Unit.PCT, 3, Unit.PCT);
		
		Button editButton = new Button("New button");
		editButtonFlowPanel.add(editButton);
		editButton.setSize("100%", "100%");
		editButton.setText("Edit");
		
		startTimeLabel = new Label("Start Time");
		startTimeLabel.setStyleName("CenterText");
		mainLayoutPanel.add(startTimeLabel);
		mainLayoutPanel.setWidgetLeftWidth(startTimeLabel, 8, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startTimeLabel, 70, Unit.PCT, 15, Unit.PX);
			
		startHourListBox = new ListBox();
		startHourListBox.setVisibleItemCount(1);
		mainLayoutPanel.add(startHourListBox);
		mainLayoutPanel.setWidgetLeftWidth(startHourListBox, 4.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startHourListBox, 72.1, Unit.PCT, 32.0, Unit.PX);
		
		
		
		ListBox startMinuteListBox = new ListBox();
		startMinuteListBox.setVisibleItemCount(1);
		mainLayoutPanel.add(startMinuteListBox);
		mainLayoutPanel.setWidgetLeftWidth(startMinuteListBox, 9.5, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startMinuteListBox, 72, Unit.PCT, 32.0, Unit.PX);

		
		startMeridiemListBox = new ListBox();
		startMeridiemListBox.setVisibleItemCount(1);
		mainLayoutPanel.add(startMeridiemListBox);
		mainLayoutPanel.setWidgetLeftWidth(startMeridiemListBox, 15.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(startMeridiemListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		
		endTimeLabel = new Label("End Time");
		endTimeLabel.setStyleName("CenterText");
		mainLayoutPanel.add(endTimeLabel);
		mainLayoutPanel.setWidgetLeftWidth(endTimeLabel, 30, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endTimeLabel, 70, Unit.PCT, 15.0, Unit.PX);

		
		Label lblTitle = new Label("Title");
		lblTitle.setStyleName("CenterText");
		mainLayoutPanel.add(lblTitle);
		mainLayoutPanel.setWidgetLeftWidth(lblTitle, 4, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(lblTitle, 77, Unit.PCT, 15.0, Unit.PX);
		
		TextBox textBox = new TextBox();
		mainLayoutPanel.add(textBox);
		mainLayoutPanel.setWidgetLeftWidth(textBox, 4, Unit.PCT, 20, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(textBox, 79, Unit.PCT, 30, Unit.PX);
		
		TextArea textArea = new TextArea();
		mainLayoutPanel.add(textArea);
		mainLayoutPanel.setWidgetLeftWidth(textArea, 4.0, Unit.PCT, 34.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(textArea, 84.0, Unit.PCT, 80, Unit.PX);
		
		Label lblBody = new Label("Description");
		lblBody.setStyleName("CenterText");
		mainLayoutPanel.add(lblBody);
		mainLayoutPanel.setWidgetLeftWidth(lblBody, 4, Unit.PCT, 8, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(lblBody, 82.0, Unit.PCT, 18.0, Unit.PX);

		
		activtyDateFlowPanel = new FlowPanel();
		mainLayoutPanel.add(activtyDateFlowPanel);
		mainLayoutPanel.setWidgetLeftWidth(activtyDateFlowPanel, 8, Unit.PCT, 30, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(activtyDateFlowPanel, 50, Unit.PCT, 191.0, Unit.PX);
		
		DatePicker activityDatePicker = new DatePicker();
		activityDatePicker.setStyleName("Background");
		activtyDateFlowPanel.add(activityDatePicker);
		
		endHourListBox = new ListBox();
		mainLayoutPanel.add(endHourListBox);
		mainLayoutPanel.setWidgetLeftWidth(endHourListBox, 26.0, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endHourListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		endHourListBox.setVisibleItemCount(1);
		
		endMinuteListBox = new ListBox();
		mainLayoutPanel.add(endMinuteListBox);
		mainLayoutPanel.setWidgetLeftWidth(endMinuteListBox, 31.5, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endMinuteListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
		endMinuteListBox.setVisibleItemCount(1);
		
		endMeridiemListBox = new ListBox();
		mainLayoutPanel.add(endMeridiemListBox);
		mainLayoutPanel.setWidgetLeftWidth(endMeridiemListBox, 37, Unit.PCT, 5.0, Unit.PCT);
		mainLayoutPanel.setWidgetTopHeight(endMeridiemListBox, 72.0, Unit.PCT, 32.0, Unit.PX);
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
		
		RPC.performUserQueryService.performGetUsersQuery(userQueryModel, new AsyncCallback<GetItemsResult<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				
				System.err.println("CLIENT: There was an error getting users");
			}

			@Override
			public void onSuccess(GetItemsResult<User> result) {
				
				for(User u : result.getResult()) {
					userOffActivityMenu.addItem(new MenuItem(u.getUsername(), (Command) null));
				}
				
			}	
			
		});
		
		
	}
}
