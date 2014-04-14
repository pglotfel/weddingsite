package weddingsite.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import weddingsite.client.PerformEditAttendeeService;
import weddingsite.shared.EditAttendeeModel;
import weddingsite.shared.EditDataResult;

public class PerformEditAttendeeImpl extends RemoteServiceServlet implements PerformEditAttendeeService  {
	
	@Override
	public EditDataResult PerformEditAttendee(EditAttendeeModel model) {
		
		PerformEditAttendee controller = new PerformEditAttendee();
		EditDataResult result = new EditDataResult();
		
		controller.setModel(model);
		
		controller.setAccountName(model.getAccountName());	
		controller.setAttendanceListName(model.getAttendanceListName());		
		controller.setName(model.getName());		
		controller.setNumAttending(model.getNumAttending());	
		controller.setType(model.getType());
		
		controller.perform(result);
			
		return result;
	}
}
