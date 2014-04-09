package weddingsite.client;

import weddingsite.shared.IPublisher;
import weddingsite.shared.ISubscriber;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ScrollPanel;

public class AttendanceListView  extends Composite implements ISubscriber {
	public AttendanceListView() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("641px", "578px");
		
		Label lblAttendanceLists = new Label("Attendance Lists");
		lblAttendanceLists.setStyleName("attendaceList");
		layoutPanel.add(lblAttendanceLists);
		layoutPanel.setWidgetLeftWidth(lblAttendanceLists, 30.0, Unit.PX, 206.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblAttendanceLists, 10.0, Unit.PX, 36.0, Unit.PX);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		layoutPanel.add(scrollPanel);
		layoutPanel.setWidgetLeftWidth(scrollPanel, 30.0, Unit.PX, 231.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(scrollPanel, 52.0, Unit.PX, 506.0, Unit.PX);
		
		ScrollPanel scrollPanel_1 = new ScrollPanel();
		layoutPanel.add(scrollPanel_1);
		layoutPanel.setWidgetLeftWidth(scrollPanel_1, 326.0, Unit.PX, 315.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(scrollPanel_1, 52.0, Unit.PX, 506.0, Unit.PX);
	}

	
	
	
	
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		// TODO Auto-generated method stub
		//Nothing for now
		
	}
}
