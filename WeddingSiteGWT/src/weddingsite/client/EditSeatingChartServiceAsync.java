package weddingsite.client;

import weddingsite.shared.EditSeatingChartModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EditSeatingChartServiceAsync {

	void addSeatingChart(EditSeatingChartModel model,
			AsyncCallback<EditDataResult> callback);

}
