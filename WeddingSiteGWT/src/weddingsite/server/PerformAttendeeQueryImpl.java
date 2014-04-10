package weddingsite.server;

import weddingsite.client.PerformAttendeeQueryService;
import weddingsite.shared.AttendeeQueryModel;
import weddingsite.shared.AttendeeQueryResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PerformAttendeeQueryImpl extends RemoteServiceServlet implements PerformAttendeeQueryService {

	@Override
	public AttendeeQueryResult PerformAttendeeQuery(AttendeeQueryModel model) {
		
		
		PerformAttendeeQuery controller = new PerformAttendeeQuery();
		
		controller.setModel(model);
		controller.setAccountName(model.getAccountName());
		controller.setActionType(model.getType());
		controller.setAttendanceListName(model.getAttendanceListName());
		
		AttendeeQueryResult result = new AttendeeQueryResult();
		
		controller.perform(result);
		
		return result;
	}
		
	
}
