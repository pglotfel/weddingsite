package weddingsite.client;

import weddingsite.shared.AttendeeQueryModel;
import weddingsite.shared.AttendeeQueryResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("performAttendeeQuery")
public interface PerformAttendeeQueryService extends RemoteService {
	
	public AttendeeQueryResult PerformAttendeeQuery(AttendeeQueryModel model);
}
