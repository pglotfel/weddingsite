package weddingsite.server;

import weddingsite.client.AddAttendanceListService;
import weddingsite.client.PerformAttendanceListQueryService;
import weddingsite.shared.AddAttendanceListModel;
import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AddAttendanceListImpl extends RemoteServiceServlet implements AddAttendanceListService  {

	@Override
	public EditDataResult performAddAttendanceList(AddAttendanceListModel model) {


		AddAttendanceList controller = new AddAttendanceList();
		
		controller.setModel(model);
		controller.setActionType(model.getType());
		controller.setAccountName(model.getAccountName());
		controller.setAttendanceListName(model.getAttendanceListName());
		
		EditDataResult result = new EditDataResult();
		
		controller.perform(result);
		
		return result;
	}

}
