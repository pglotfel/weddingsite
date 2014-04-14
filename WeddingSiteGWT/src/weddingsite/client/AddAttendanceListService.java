package weddingsite.client;

import weddingsite.shared.AddAttendanceListModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("AddAttendanceListService")
public interface AddAttendanceListService extends RemoteService{
	
	public EditDataResult performAddAttendanceList(AddAttendanceListModel model);
}
