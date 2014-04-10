package weddingsite.server;

import weddingsite.client.PerformAttendanceListQueryService;
import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PerformAttendanceListQueryImpl extends RemoteServiceServlet implements PerformAttendanceListQueryService {

	@Override
	public AttendanceListQueryResult performAttendanceListQuery(
			AttendanceListQueryModel attendanceListQueryModel) {


		PerformAttendanceListQuery controller = new PerformAttendanceListQuery();
		
		controller.setModel(attendanceListQueryModel);
		
		controller.setWeddingName(attendanceListQueryModel.getWeddingName());
		controller.setActionType(attendanceListQueryModel.getType());
		
		AttendanceListQueryResult result = new AttendanceListQueryResult();
		
		controller.perform(result);
		
		return result;
	}
	
	
	
	

}
