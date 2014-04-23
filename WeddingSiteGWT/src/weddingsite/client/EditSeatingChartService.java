package weddingsite.client;

import weddingsite.shared.EditSeatingChartModel;
import weddingsite.shared.EditDataResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("EditSeatingChartService")
public interface EditSeatingChartService extends RemoteService{
	
	public EditDataResult addSeatingChart(EditSeatingChartModel model);

}
