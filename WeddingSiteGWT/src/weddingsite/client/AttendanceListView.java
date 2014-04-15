package weddingsite.client;


import weddingsite.shared.ActionType;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;
import weddingsite.shared.Attendee;
import weddingsite.shared.AttendeeQueryModel;
import weddingsite.shared.AttendeeQueryResult;
import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;
import weddingsite.shared.Login;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class AttendanceListView  extends Composite implements ISubscriber {
	
	private enum ViewModes {
		INIT,
		NORMAL,
		EDITING,
	}
	
	//TODO: MAKE SURE ATTENDEE LIST IS VALID + ADD BUTTONS TO GET BACK AND BLAH
	
	private MenuBar attendanceListMenu;
	private AttendanceListQueryModel attendanceListModel;
	private AttendeeQueryModel attendeeModel;
	private LayoutPanel layoutPanel;
	private Label attendanceListLabel;
	private MenuBar attendeeMenu;
	private FlowPanel attendeeFlowPanel;
	private FlowPanel attendanceListFlowPanel;
	private Button manageAttendeeButton;
	
	public AttendanceListView() {
		
		layoutPanel = new LayoutPanel();
		layoutPanel.setStyleName("layout");
		initWidget(layoutPanel);
		layoutPanel.setSize("100%", "500px");
			
		attendanceListFlowPanel = new FlowPanel();
		attendanceListMenu = new MenuBar(true);
		attendanceListFlowPanel.add(attendanceListMenu);
		attendanceListMenu.setSize("100%", "100%");	

		PageView pageView = new PageView();
		layoutPanel.add(pageView);
		layoutPanel.setWidgetTopBottom(pageView, 93.7,  Unit.PCT, 0.0, Unit.PCT);
		layoutPanel.setWidgetLeftRight(pageView, 0.0, Unit.PCT, 30, Unit.PCT);
		
		attendanceListLabel = new Label("Attendance Lists");
		layoutPanel.add(attendanceListLabel);
		layoutPanel.setWidgetLeftWidth(attendanceListLabel, 25, Unit.PCT, 50, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendanceListLabel, 5, Unit.PCT, 10, Unit.PCT);
		attendanceListLabel.setStyleName("attendanceList");
		
		attendeeFlowPanel = new FlowPanel();	
		attendeeMenu = new MenuBar(true);
		attendeeFlowPanel.add(attendeeMenu);
		attendeeMenu.setSize("100%", "100%");
		
		manageAttendeeButton = new Button("New button");
		manageAttendeeButton.setText("Manage Parties");
		manageAttendeeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				switchViewMode(ViewModes.EDITING);
			}
		});
		
		//TEST
		//addManageAttendeeButtonToView();
		//addAttendanceListToView();
		//addAttendeeListToViewNormal();
		//TEST
		
		switchViewMode(ViewModes.INIT);
		
		this.setAttendanceListQueryModel(new AttendanceListQueryModel());
		attendanceListModel.setWeddingName(Site.currentUser.getAccountName());
		attendanceListModel.setType(ActionType.GETATTENDANCELISTS);
		
		this.setAttendeeQueryModel(new AttendeeQueryModel());
		
		RPC.performAttendanceListQueryService.performAttendanceListQuery(attendanceListModel, new AsyncCallback<AttendanceListQueryResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with the attendance list query...");				
			}

			@Override
			public void onSuccess(AttendanceListQueryResult result) {
				for (final AttendanceList a : result.getAttendanceLists()) {
					
					MenuItem m = new MenuItem(a.getName(), false, new Command() {
						public void execute() {			
							handleAttendanceListClick(a.getName());
						}
						
					});				
					
					attendanceListMenu.addItem(m);
				}
			}
		
		});
	}
	
	public void addAttendanceListToView() {		
		layoutPanel.add(attendanceListFlowPanel);
		layoutPanel.setWidgetLeftWidth(attendanceListFlowPanel, 15, Unit.PCT, 25, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendanceListFlowPanel, 15,  Unit.PCT, 60,  Unit.PCT);		
	}
	
	public void removeAttendanceListFromView() {
		layoutPanel.remove(attendanceListFlowPanel);
	}
	
	public void addAttendeeListToViewNormal() {
		layoutPanel.add(attendeeFlowPanel);
		layoutPanel.setWidgetLeftWidth(attendeeFlowPanel, 60, Unit.PCT, 25, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendeeFlowPanel, 15,  Unit.PCT, 60,  Unit.PCT);
	}
	
	public void removeAttendeeListFromView() {
		layoutPanel.remove(attendeeFlowPanel);
	}
	
	public void addManageAttendeeButtonToView() {
		layoutPanel.add(manageAttendeeButton);
		layoutPanel.setWidgetLeftWidth(manageAttendeeButton, 65.5, Unit.PCT, 15, Unit.PCT);
		layoutPanel.setWidgetTopHeight(manageAttendeeButton, 80, Unit.PCT, 8, Unit.PCT);
	}
	
	public void addAttendeeListToViewEditing() {
		layoutPanel.add(attendeeFlowPanel);
		layoutPanel.setWidgetLeftWidth(attendeeFlowPanel, 0, Unit.PCT, 25, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendeeFlowPanel, 15,  Unit.PCT, 60,  Unit.PCT);
	}
	
	public void removeManageAttendeeButtonFromView() {
		layoutPanel.remove(manageAttendeeButton);
	}
	
	public void setAttendanceListQueryModel(AttendanceListQueryModel model) {
		this.attendanceListModel = model;
		this.attendanceListModel.subscribe(Login.Events.VALUE_OR_ACTION_TYPE_CHANGED, this);
	}
	
	public void setAttendeeQueryModel(AttendeeQueryModel model) {
		this.attendeeModel = model;
		this.attendeeModel.subscribe(Login.Events.VALUE_OR_ACTION_TYPE_CHANGED, this);
	}
	
	
	public void handleAttendanceListClick(String attendanceListName) {
		attendeeModel.setAccountName(Site.currentUser.getAccountName());
		attendeeModel.setAttendanceListName(attendanceListName);
		attendeeModel.setType(ActionType.GETATTENDEES);
		
		RPC.performAttendeeQueryService.PerformAttendeeQuery(attendeeModel, new AsyncCallback<AttendeeQueryResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with the attendance list query...");				
			}

			@Override
			public void onSuccess(AttendeeQueryResult result) {
				attendeeMenu.clearItems();
				for (final Attendee a : result.getAttendees()) {
					attendeeMenu.addItem(new MenuItem("Party: " + a.getName() + " Attending: " + a.getNumAttending(), false, (Command) null));
				}
			}
		
		});		
	}
	
	public void switchViewMode(ViewModes v) {
		switch(v) {
		
			case INIT:
				addAttendanceListToView();
				addAttendeeListToViewNormal();
				
				if(Site.currentUser.getIsAdmin()) {
					addManageAttendeeButtonToView();
				}
			break;
		
			case NORMAL:
				removeAttendeeListFromView();
				addAttendanceListToView();
				addAttendeeListToViewNormal();	
				if(Site.currentUser.getIsAdmin()) {
					addManageAttendeeButtonToView();
				}
			break;
			
			case EDITING:
				if(Site.currentUser.getIsAdmin()) {
					removeManageAttendeeButtonFromView();
				}
				removeAttendanceListFromView();
				removeAttendeeListFromView();
				addAttendeeListToViewEditing();
			break;
			
			default:
				throw new UnsupportedOperationException("Unknown operation type: " + v);
		
		}
	}
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		//Nothing for now	
	}
}
