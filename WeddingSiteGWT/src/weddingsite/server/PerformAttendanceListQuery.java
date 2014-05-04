package weddingsite.server;

import java.util.ArrayList;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.ActionType;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;
import weddingsite.shared.Attendee;
import weddingsite.shared.User;

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
	
	public void perform(AttendanceListQueryResult result) {
		
		ArrayList<AttendanceList> resultValue = null;
	
		
		switch (model.getType()) {
		
		case GETATTENDANCELISTS:
			
			resultValue = DatabaseProvider.getInstance().getAttendanceLists(model.getWeddingName());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		
		result.setAttendanceLists(resultValue);
		
		
	}

}
