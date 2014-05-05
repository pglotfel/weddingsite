package weddingsite.server;

import java.util.ArrayList;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.Activity;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.getEventsModel;

public class GetEvents {
	
	private getEventsModel model;
	
	public void setModel(getEventsModel model) {
		this.model = model;
	}
	
public void perform(GetItemsResult<Activity> result) {
		
		ArrayList<Activity> resultVal = new ArrayList<Activity>();
		
		switch (model.getType()) {
		
		case GETEVENTS:
			
			resultVal = DatabaseProvider.getInstance().getActivities(model.getAccountName());
			break;
			
		
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setResult(resultVal);
		
	}
}
