package weddingsite.client;

import weddingsite.shared.EditAttendeeModel;
import weddingsite.shared.EditDataResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PerformEditAttendeeServiceAsync {

	void PerformEditAttendee(EditAttendeeModel model, AsyncCallback<EditDataResult> callback);

}
