package weddingsite.client;

import weddingsite.shared.AddAttendanceListModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddAttendanceListServiceAsync {

	void performAddAttendanceList(AddAttendanceListModel model,
			AsyncCallback<EditDataResult> callback);

}
