package weddingsite.server;

import weddingsite.shared.ActionType;
import weddingsite.shared.AddAttendanceListModel;
import weddingsite.shared.EditDataResult;

public class AddAttendanceList {

	AddAttendanceListModel model;
	
	public void setModel(AddAttendanceListModel model) {
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
	
	public void perform(EditDataResult result) {
		
		boolean resultVal = false;
		
		switch (model.getType()) {
		
		
		case ADDATTENDANCELIST:
			
			resultVal = DatabaseProvider.getInstance().addAttendanceList(model.getAccountName(),model.getAttendanceListName());
			break;
		
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
			
		}
		
		result.setResult(resultVal);
		
	}
	
}
