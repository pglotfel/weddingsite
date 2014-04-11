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
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;

public class AttendanceListView  extends Composite implements ISubscriber {
	
	private ScrollPanel attendanceListPanel;
	private MenuBar attendanceListMenu;
	private AttendanceListQueryModel attendanceListModel;
	private AttendeeQueryModel attendeeModel;
	private LayoutPanel layoutPanel;
	private Label attendanceListLabel;
	private MenuBar attendeeMenu;
	
	public AttendanceListView() {
			
		layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("558px", "490px");
		//layoutPanel.setSize("641px", "659px");
		
		attendanceListLabel = new Label("Attendance Lists");
		attendanceListLabel.setStyleName("attendanceList");
		layoutPanel.add(attendanceListLabel);
		layoutPanel.setWidgetLeftWidth(attendanceListLabel, 41.0, Unit.PX, 206.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(attendanceListLabel, 26.0, Unit.PX, 27.0, Unit.PX);
		
		attendanceListPanel = new ScrollPanel();
		attendanceListPanel.setStyleName("attendanceList");

		layoutPanel.add(attendanceListPanel);
		layoutPanel.setWidgetLeftWidth(attendanceListPanel, 28.0, Unit.PX, 231.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(attendanceListPanel, 59.0, Unit.PX, 504.0, Unit.PX);
		
		attendanceListPanel.setSize("100", "300");
		
		attendanceListMenu = new MenuBar(true);
		attendanceListPanel.setWidget(attendanceListMenu);
		attendanceListMenu.setSize("100%", "100%");
		attendanceListPanel.setSize("100", "300");
		
		ScrollPanel scrollPanel = new ScrollPanel();
		layoutPanel.add(scrollPanel);
		layoutPanel.setWidgetLeftWidth(scrollPanel, 330.0, Unit.PX, 198.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(scrollPanel, 60.0, Unit.PX, 199.0, Unit.PX);
		
		attendeeMenu = new MenuBar(true);
		scrollPanel.setWidget(attendeeMenu);
		attendeeMenu.setSize("90%", "90%");
		
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
					attendanceListMenu.addItem(new MenuItem(a.getName(), false, new Command() {
						public void execute() {			
							handleAttendanceListClick(a.getName());
						}
						
					}));
				}
			}
		
		});
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
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		//Nothing for now	
	}
}
