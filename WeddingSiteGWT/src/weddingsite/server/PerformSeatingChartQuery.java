package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.SeatingChart;
import weddingsite.shared.SeatingChartQueryModel;
import weddingsite.shared.SeatingChartQueryResult;

public class PerformSeatingChartQuery {
	
	private SeatingChartQueryModel model;
	
	public void setModel(SeatingChartQueryModel model) {
		
		this.model = model;
	}
	
	public void perform(SeatingChartQueryResult result) {
		
		ArrayList<SeatingChart> resultVal = new ArrayList<SeatingChart>();
		
		switch (model.getType()) {
		
		case GETSEATINGCHARTS:
			
			resultVal = DatabaseProvider.getInstance().getSeatingCharts(model.getAccountName());
			break;
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		
		
		}
		
		result.setSeatingCharts(resultVal);
		
	}

}
