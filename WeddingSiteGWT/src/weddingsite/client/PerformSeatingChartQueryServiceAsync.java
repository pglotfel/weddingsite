package weddingsite.client;

import weddingsite.shared.SeatingChartQueryModel;
import weddingsite.shared.SeatingChartQueryResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PerformSeatingChartQueryServiceAsync {

	void performSeatingChartQuery(SeatingChartQueryModel model,
			AsyncCallback<SeatingChartQueryResult> callback);

}
