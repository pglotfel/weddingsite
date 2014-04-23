package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.ActionType;
import weddingsite.shared.Attendee;
import weddingsite.shared.AttendeeQueryModel;
import weddingsite.shared.AttendeeQueryResult;

public class PerformAttendeeQuery {
	
	private AttendeeQueryModel model;
	
	public void setModel(AttendeeQueryModel model) {
		this.model = model;
	}
	
	public void setActionType(ActionType type) {
		model.setType(type);
	}
	
	public void setAccountName(String accountName) {
		model.setAccountName(accountName);
	}
	
	public void setAttendanceListName(String attendanceListName) {
		model.setAttendanceListName(attendanceListName);
	}
	
	public void perform(AttendeeQueryResult result) {
		
		ArrayList<Attendee> resultVal = new ArrayList<Attendee>();
		int totalAttending = -1;
		switch (model.getType()) {
		
		case GETATTENDEES:
			
			totalAttending = DatabaseProvider.getInstance().getTotalAttending(model.getAccountName(), model.getAttendanceListName());
			resultVal = DatabaseProvider.getInstance().getAttendanceListAttendees(model.getAttendanceListName(), model.getAccountName());
			break;
			
		
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setAttendees(resultVal);
		result.setTotalAttending(totalAttending);
		
	}

}
