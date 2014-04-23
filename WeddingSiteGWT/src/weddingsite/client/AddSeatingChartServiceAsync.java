package weddingsite.client;

import weddingsite.shared.AddSeatingChartModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddSeatingChartServiceAsync {

	void addSeatingChart(AddSeatingChartModel model,
			AsyncCallback<EditDataResult> callback);

}
