package weddingsite.client;


import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.MenuBar;

public class AttendanceListView  extends Composite implements ISubscriber {
	private ScrollPanel attandanceListPanel;
	private ScrollPanel attendeePanel;
	private MenuBar attendanceListMenu;
	public AttendanceListView() {
		
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
		
		//TODO: put this in a query for attendance List
		attendanceListMenu.addItem(new MenuItem("Attendance List", false, new Command() {
			public void execute() {
				
				//handleAttendanceListClick();
			}
			
		}));
		
		attendeePanel = new ScrollPanel();
		layoutPanel.add(attendeePanel);
		layoutPanel.setWidgetLeftWidth(attendeePanel, 326.0, Unit.PX, 315.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(attendeePanel, 52.0, Unit.PX, 506.0, Unit.PX);
		
		
		
	}

	
	
	
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		// TODO Auto-generated method stub
		//Nothing for now
		
	}
}
