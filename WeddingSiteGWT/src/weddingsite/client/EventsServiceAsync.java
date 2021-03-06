package weddingsite.client;

import weddingsite.shared.Activity;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.EventsModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventsServiceAsync {

	void GetEvents(EventsModel model, AsyncCallback<GetItemsResult<Activity>> callback);
	void EditEvents(EventsModel model, AsyncCallback<EditDataResult> callback);
	void getUsersOnEvent(EventsModel model, AsyncCallback<GetItemsResult<String>> callback);
	void removeUsersFromEvent(EventsModel model, AsyncCallback<EditDataResult> callback);
	void addUsersToEvent(EventsModel model, AsyncCallback<EditDataResult> callback);
}
