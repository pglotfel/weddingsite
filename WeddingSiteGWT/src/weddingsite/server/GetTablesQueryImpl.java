package weddingsite.server;

import weddingsite.client.GetTablesService;
import weddingsite.shared.GetTablesModel;
import weddingsite.shared.GetTablesResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GetTablesQueryImpl extends RemoteServiceServlet implements GetTablesService{

	@Override
	public GetTablesResult getTables(GetTablesModel model) {
		
		GetTablesQuery controller = new GetTablesQuery();
		
		controller.setModel(model);
		
		GetTablesResult result = new GetTablesResult();
		controller.perform(result);
		
		return result;
	}
	

}
