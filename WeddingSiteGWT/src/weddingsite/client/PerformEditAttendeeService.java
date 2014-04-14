package weddingsite.client;

import weddingsite.shared.EditAttendeeModel;
import weddingsite.shared.EditDataResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("performEditAttendee")
public interface PerformEditAttendeeService extends RemoteService {
	public EditDataResult PerformEditAttendee (EditAttendeeModel model);
}
