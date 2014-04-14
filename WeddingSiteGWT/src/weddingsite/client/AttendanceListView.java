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

public class AttendanceListView  extends Composite implements ISubscriber {
	private MenuBar attendanceListMenu;
	private AttendanceListQueryModel attendanceListModel;
	private AttendeeQueryModel attendeeModel;
	private LayoutPanel layoutPanel;
	private Label attendanceListLabel;
	private MenuBar attendeeMenu;
	
	public AttendanceListView() {
			
		layoutPanel = new LayoutPanel();
		layoutPanel.setStyleName("layout");
		initWidget(layoutPanel);
		layoutPanel.setSize("100%", "100%");
		
		PageView pageView = new PageView();
		layoutPanel.add(pageView);
		layoutPanel.setWidgetTopBottom(pageView, 94.3,  Unit.PCT, 0.0, Unit.PCT);
		layoutPanel.setWidgetLeftRight(pageView, 0.0, Unit.PCT, 79.4, Unit.PCT);
		
		attendanceListLabel = new Label("Attendance Lists");
		layoutPanel.add(attendanceListLabel);
		layoutPanel.setWidgetLeftWidth(attendanceListLabel, 25, Unit.PCT, 50, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendanceListLabel, 5, Unit.PCT, 10, Unit.PCT);
		attendanceListLabel.setStyleName("attendanceList");
		
		FlowPanel attendanceListFlowPanel = new FlowPanel();
		layoutPanel.add(attendanceListFlowPanel);
		layoutPanel.setWidgetLeftWidth(attendanceListFlowPanel, 15, Unit.PCT, 25, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendanceListFlowPanel, 15,  Unit.PCT, 60,  Unit.PCT);
		
		attendanceListMenu = new MenuBar(true);
		attendanceListFlowPanel.add(attendanceListMenu);
		attendanceListMenu.setSize("100%", "100%");
		
		FlowPanel attendeeFlowPanel = new FlowPanel();
		layoutPanel.add(attendeeFlowPanel);
		layoutPanel.setWidgetLeftWidth(attendeeFlowPanel, 60, Unit.PCT, 25, Unit.PCT);
		layoutPanel.setWidgetTopHeight(attendeeFlowPanel, 15,  Unit.PCT, 60,  Unit.PCT);
		
		attendeeMenu = new MenuBar(true);
		attendeeFlowPanel.add(attendeeMenu);
		attendeeMenu.setSize("100%", "100%");
		
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
					//m.setStyleName("gwt-MenuBar-vertical .gwt-MenuItem");				
					attendanceListMenu.addItem(m);
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
