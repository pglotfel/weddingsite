package weddingsite.client;

import weddingsite.shared.EditAttendanceListModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EditAttendanceListServiceAsync {

	void performAddAttendanceList(EditAttendanceListModel model,
			AsyncCallback<EditDataResult> callback);

}
