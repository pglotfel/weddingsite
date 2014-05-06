package weddingsite.server;

import java.util.ArrayList;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.Activity;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.EventsModel;

public class GetEvents {
	
	private EventsModel model;
	
	public void setModel(EventsModel model) {
		this.model = model;
	}
	
public void perform(GetItemsResult<Activity> result) {
		
		ArrayList<Activity> resultVal = new ArrayList<Activity>();
		
		switch (model.getType()) {
		
		case GETEVENTS:
			
			resultVal = DatabaseProvider.getInstance().getActivities(model.getAccountName());
		break;
		
		case GETEVENTSFORUSER:
			resultVal = DatabaseProvider.getInstance().getUserActivities(model.getAccountName(), model.getUsername());
		break;
		
			
		
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setResult(resultVal);
		
	}
}
