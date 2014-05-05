package weddingsite.client;

import weddingsite.shared.Activity;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.EventsModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetEventsServiceAsync {

	void GetEvents(EventsModel model,
			AsyncCallback<GetItemsResult<Activity>> callback);

}
