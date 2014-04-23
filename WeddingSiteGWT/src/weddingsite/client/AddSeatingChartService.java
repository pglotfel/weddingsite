package weddingsite.client;

import weddingsite.shared.AddSeatingChartModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("AddSeatingChartService")
public interface AddSeatingChartService extends RemoteService{
	
	public EditDataResult addSeatingChart(AddSeatingChartModel model);

}
