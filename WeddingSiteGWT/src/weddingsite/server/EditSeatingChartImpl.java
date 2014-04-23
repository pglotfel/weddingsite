package weddingsite.server;

import weddingsite.client.EditSeatingChartService;
import weddingsite.shared.EditSeatingChartModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EditSeatingChartImpl extends RemoteServiceServlet implements EditSeatingChartService{

	@Override
	public EditDataResult addSeatingChart(EditSeatingChartModel model) {
		
		EditSeatingChart controller = new EditSeatingChart();
		
		controller.setModel(model);
		EditDataResult result = new EditDataResult();
		controller.perform(result);
		
		return result;
		
	}
	
		
}
