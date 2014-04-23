package weddingsite.client;

import weddingsite.shared.EditAttendanceListModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("EditAttendanceListService")
public interface EditAttendanceListService extends RemoteService{
	
	public EditDataResult performAddAttendanceList(EditAttendanceListModel model);
}
