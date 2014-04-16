package weddingsite.client;

import weddingsite.shared.SeatingChartQueryModel;
import weddingsite.shared.SeatingChartQueryResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("performSeatingChartQuery")
public interface PerformSeatingChartQueryService extends RemoteService {
	
	public SeatingChartQueryResult performSeatingChartQuery(SeatingChartQueryModel model);
	

}
