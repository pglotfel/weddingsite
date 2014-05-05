package weddingsite.server;

import weddingsite.client.GetEventsService;
import weddingsite.shared.Activity;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.getEventsModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GetEventsImpl extends RemoteServiceServlet implements GetEventsService {

	@Override
	public GetItemsResult<Activity> GetEvents(getEventsModel model) {
		 GetEvents controller = new GetEvents();
		 
		 controller.setModel(model);
		 GetItemsResult<Activity> result = new GetItemsResult<Activity>();
		 
		 controller.perform(result);

		 return result;
	}

	

}