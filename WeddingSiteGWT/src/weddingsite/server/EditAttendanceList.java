package weddingsite.server;

import weddingsite.shared.ActionType;
import weddingsite.shared.EditAttendanceListModel;
import weddingsite.shared.EditDataResult;

public class EditAttendanceList {

	EditAttendanceListModel model;
	
	public void setModel(EditAttendanceListModel model) {
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
			
		case DELETEATTENDANCELIST:
			//delete
			break;
		case EDITATTENDANCELIST:
			//edit
			break;
		
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
			
		}
		
		result.setResult(resultVal);
		
	}
	
}
