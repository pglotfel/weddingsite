package weddingsite.server;

import weddingsite.client.EventsService;
import weddingsite.shared.Activity;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.EventsModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventsImpl extends RemoteServiceServlet implements EventsService {

	@Override
	public GetItemsResult<Activity> GetEvents(EventsModel model) {
		 GetEvents controller = new GetEvents();
		 
		 controller.setModel(model);
		 GetItemsResult<Activity> result = new GetItemsResult<Activity>();
		 
		 controller.perform(result);

		 return result;
	}

	@Override
	public EditDataResult EditEvents(EventsModel model) {
		EditEvents controller = new EditEvents();
		
		controller.setMode(model);
		EditDataResult result = new EditDataResult();
		
		controller.perform(result);
		
		return result;
	}

	

}
