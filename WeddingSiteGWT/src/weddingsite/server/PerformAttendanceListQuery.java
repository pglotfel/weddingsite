package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.ActionType;
import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;
import weddingsite.shared.Attendee;

public class PerformAttendanceListQuery {
	
	private AttendanceListQueryModel model;
	
	public void setModel(AttendanceListQueryModel model) {
		this.model = model;
	}
	
	public void setActionType(ActionType type) {
		model.setType(type);
	}
	
	public void setWeddingName(String weddingName) {
		model.setWeddingName(weddingName);
	}
	
	public void setUsername(String username) {
		model.setUsername(username);
	}
	
	public void setName(String name) {
		model.setName(name);
	}
	
	
	public void perform(AttendanceListQueryResult result) {
		
		ArrayList<Attendee> resultValue = null;
		
		
		
		
	}

}
