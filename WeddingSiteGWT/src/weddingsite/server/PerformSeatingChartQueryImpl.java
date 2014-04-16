package weddingsite.server;

import weddingsite.client.PerformSeatingChartQueryService;
import weddingsite.shared.SeatingChartQueryModel;
import weddingsite.shared.SeatingChartQueryResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PerformSeatingChartQueryImpl extends RemoteServiceServlet implements PerformSeatingChartQueryService{

	@Override
	public SeatingChartQueryResult performSeatingChartQuery(SeatingChartQueryModel model) {
		
		PerformSeatingChartQuery controller = new PerformSeatingChartQuery();
		
		controller.setModel(model);
		
		SeatingChartQueryResult result = new SeatingChartQueryResult();
		
		controller.perform(result);
		
		return result;
	}

}
