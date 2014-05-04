package weddingsite.server;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.ActionType;
import weddingsite.shared.EditAttendeeModel;
import weddingsite.shared.EditDataResult;

public class PerformEditAttendee {

	private EditAttendeeModel model;
	
	public PerformEditAttendee() {
		
	}
	
	public void setModel(EditAttendeeModel model) {
		this.model = model;
	}
	
	public void setName(String name) {
		model.setName(name);
	}
	
	public void setType(ActionType type) {
		model.setType(type);
	}
	
	public void setNumAttending(int numAttending) {
		model.setNumAttending(numAttending);
	}
	
	public void setAccountName(String accountName) {
		model.setAccountName(accountName);
	}
	
	public void setAttendanceListName(String attendanceListName) {
		model.setAttendanceListName(attendanceListName);
	}
	
	public void perform(EditDataResult result) {
			
		switch(model.getType()) {
		case ADDATTENDEE:
			result.setResult(DatabaseProvider.getInstance().addAttendee(model.getAccountName(), model.getAttendanceListName(), 
					model.getName(), model.getNumAttending()));
		break;
			
		case DELETEATTENDEE:
			result.setResult(DatabaseProvider.getInstance().deleteAttendee(model.getAccountName(), model.getAttendanceListName(), model.getName()));
		break;
		
		case EDITATTENDEE:
			result.setResult(DatabaseProvider.getInstance().modifyAttendee(model.getAccountName(),  model.getAttendanceListName(), model.getName(), model.getNumAttending()));
		break;
		
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
			
		}
	}
}
