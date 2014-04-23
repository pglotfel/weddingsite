package weddingsite.server;

import weddingsite.client.EditAttendanceListService;
import weddingsite.client.PerformAttendanceListQueryService;
import weddingsite.shared.EditAttendanceListModel;
import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EditAttendanceListImpl extends RemoteServiceServlet implements EditAttendanceListService  {

	@Override
	public EditDataResult performAddAttendanceList(EditAttendanceListModel model) {


		EditAttendanceList controller = new EditAttendanceList();
		
		controller.setModel(model);
		controller.setActionType(model.getType());
		controller.setAccountName(model.getAccountName());
		controller.setAttendanceListName(model.getAttendanceListName());
		
		EditDataResult result = new EditDataResult();
		
		controller.perform(result);
		
		return result;
	}

}
