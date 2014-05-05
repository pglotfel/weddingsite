package weddingsite.client;

import weddingsite.shared.Activity;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.getEventsModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GetEventsService")
public interface GetEventsService extends RemoteService{
	public GetItemsResult<Activity> GetEvents(getEventsModel model);
}
