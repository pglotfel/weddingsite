package weddingsite.server;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.EventsModel;

public class EditEvents {
	EventsModel model;
	
	public void setMode(EventsModel model){
		this.model = model;
	}
	
	public void perform(EditDataResult result) {
		
		boolean resultVal = false;
		
		switch (model.getType()) {
		
		case ADDEVENT:
			
			resultVal = DatabaseProvider.getInstance().addActivity(model.getAccountName(), model.getActivity());
			break;
		
		case EDITEVENT:
			
			resultVal = DatabaseProvider.getInstance().editActivity(model.getAccountName(), model.getActivity(), model.getActivityName());
			break;
			
		case DELETEEVENT:
			
			resultVal = DatabaseProvider.getInstance().deleteActivity(model.getAccountName(), model.getActivityName());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		
		}
		
		result.setResult(resultVal);
		
	}
	
}
