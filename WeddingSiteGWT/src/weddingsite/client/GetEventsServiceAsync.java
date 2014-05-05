package weddingsite.client;

import weddingsite.shared.Activity;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.getEventsModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetEventsServiceAsync {

	void GetEvents(getEventsModel model,
			AsyncCallback<GetItemsResult<Activity>> callback);

}
