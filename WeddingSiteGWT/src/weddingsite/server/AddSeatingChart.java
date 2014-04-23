package weddingsite.server;

import weddingsite.shared.AddSeatingChartModel;
import weddingsite.shared.EditDataResult;

public class AddSeatingChart {
	
	AddSeatingChartModel model;
	
	public void setModel(AddSeatingChartModel model) {
		this.model = model;
	}
	
	public void perform(EditDataResult result) {
		
		boolean resultVal = false;
		
		switch (model.getType()) {
		
		case ADDSEATINGCHART:
			
			resultVal = DatabaseProvider.getInstance().addSeatingChart(model.getAccountName(), model.getSeatingChartName());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setResult(resultVal);
	}
}
