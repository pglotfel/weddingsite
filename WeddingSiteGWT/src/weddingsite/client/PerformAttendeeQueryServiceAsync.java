package weddingsite.client;

import weddingsite.shared.AttendeeQueryModel;
import weddingsite.shared.AttendeeQueryResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PerformAttendeeQueryServiceAsync {

	void PerformAttendeeQuery(AttendeeQueryModel model,
			AsyncCallback<AttendeeQueryResult> callback);

}
