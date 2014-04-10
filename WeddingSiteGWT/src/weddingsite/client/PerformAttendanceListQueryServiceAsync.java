package weddingsite.client;

import weddingsite.shared.AttendanceListQueryModel;
import weddingsite.shared.AttendanceListQueryResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PerformAttendanceListQueryServiceAsync {


	void performAttendanceListQuery(
			AttendanceListQueryModel attendanceListQueryModel,
			AsyncCallback<AttendanceListQueryResult> callback);

}
