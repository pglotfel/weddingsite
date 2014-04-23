package weddingsite.server;

import weddingsite.client.AddSeatingChartService;
import weddingsite.shared.AddSeatingChartModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AddSeatingChartImpl extends RemoteServiceServlet implements AddSeatingChartService{

	@Override
	public EditDataResult addSeatingChart(AddSeatingChartModel model) {
		
		AddSeatingChart controller = new AddSeatingChart();
		
		controller.setModel(model);
		EditDataResult result = new EditDataResult();
		controller.perform(result);
		
		return result;
		
	}
	
		
}
