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
import com.google.gwt.user.client.ui.ScrollPanel;

public class AttendanceListView  extends Composite implements ISubscriber {
	
	private ScrollPanel attandanceListPanel;
	private ScrollPanel attendeePanel;
	private MenuBar attendanceListMenu;
	private AttendanceListQueryModel attendanceListModel;
	private AttendanceListQueryResult attendanceListResult;
	private AttendeeQueryModel attendeeModel;
	private AttendeeQueryResult atendeeResult;
	private MenuBar attendeeMenu;
	
	public AttendanceListView() {
		
		this.setAttendanceListQueryModel(new AttendanceListQueryModel());
		attendanceListModel.setWeddingName(Site.currentUser.getAccountName());
		attendanceListModel.setType(ActionType.GETATTENDANCELISTS);
		
		this.setAttendeeQueryModel(new AttendeeQueryModel());
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("641px", "578px");
		
		Label lblAttendanceLists = new Label("Attendance Lists");
		lblAttendanceLists.setStyleName("attendaceList");
		layoutPanel.add(lblAttendanceLists);
		layoutPanel.setWidgetLeftWidth(lblAttendanceLists, 30.0, Unit.PX, 206.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblAttendanceLists, 10.0, Unit.PX, 36.0, Unit.PX);
		
		attandanceListPanel = new ScrollPanel();
		layoutPanel.add(attandanceListPanel);
		layoutPanel.setWidgetLeftWidth(attandanceListPanel, 30.0, Unit.PX, 231.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(attandanceListPanel, 52.0, Unit.PX, 506.0, Unit.PX);
		
		attendanceListMenu = new MenuBar(true);
		attandanceListPanel.setWidget(attendanceListMenu);
		attendanceListMenu.setSize("100%", "100%");
		
		attendeeMenu = new MenuBar(true);
		attendeePanel.setWidget(attendeeMenu);
		attendeeMenu.setSize("100%", "100%");
		
		RPC.performAttendanceListQueryService.performAttendanceListQuery(attendanceListModel, new AsyncCallback<AttendanceListQueryResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Something went wrong with the attendance list query...");				
			}

			@Override
			public void onSuccess(AttendanceListQueryResult result) {
				for (final AttendanceList a : result.getAttendanceLists()) {
					attendanceListMenu.addItem(new MenuItem(a.getName(), false, new Command() {
						public void execute() {			
							handleAttendanceListClick(a.getName());
						}
						
					}));
				}
			}
		
		});

		attendeePanel = new ScrollPanel();
		layoutPanel.add(attendeePanel);
		layoutPanel.setWidgetLeftWidth(attendeePanel, 326.0, Unit.PX, 315.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(attendeePanel, 52.0, Unit.PX, 506.0, Unit.PX);	
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
					attendeeMenu.addItem(new MenuItem("Party: " + a.getName() + " Attending: " + a.getNumAttending(), false, new Command() {
						public void execute() {			
						}				
					}));
				}
			}
		
		});	
		
	}
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		//Nothing for now	
	}
}
