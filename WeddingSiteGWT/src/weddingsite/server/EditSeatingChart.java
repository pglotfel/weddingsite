package weddingsite.server;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.EditSeatingChartModel;
import weddingsite.shared.EditDataResult;

public class EditSeatingChart {
	
	EditSeatingChartModel model;
	
	public void setModel(EditSeatingChartModel model) {
		this.model = model;
	}
	
	public void perform(EditDataResult result) {
		
		boolean resultVal = false;
		
		switch (model.getType()) {
		
		case ADDSEATINGCHART:
			
			resultVal = DatabaseProvider.getInstance().addSeatingChart(model.getAccountName(), model.getSeatingChartName());
			break;
			
		case DELETESEATINGCHART:
			resultVal = DatabaseProvider.getInstance().deleteSeatingChart(model.getAccountName(), model.getSeatingChartName());
			break;
		case EDITSEATINGCHART:
			resultVal = DatabaseProvider.getInstance().editSeatingChart(model.getAccountName(), model.getSeatingChartName(), model.getNewName());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setResult(resultVal);
	}
}
