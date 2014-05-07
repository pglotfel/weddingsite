package weddingsite.client;

import weddingsite.shared.Activity;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.EventsModel;
import weddingsite.shared.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GetEventsService")
public interface EventsService extends RemoteService {
	
	public GetItemsResult<Activity> GetEvents(EventsModel model);
	public EditDataResult EditEvents(EventsModel model);
	public GetItemsResult<String> getUsersOnEvent(EventsModel model);
	public EditDataResult removeUsersFromEvent(EventsModel model);
	public EditDataResult addUsersToEvent(EventsModel model);
}
