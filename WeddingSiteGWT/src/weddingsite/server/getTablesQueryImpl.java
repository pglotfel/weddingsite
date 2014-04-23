package weddingsite.server;

import weddingsite.client.GetTablesService;
import weddingsite.shared.getTablesModel;
import weddingsite.shared.getTablesResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class getTablesQueryImpl extends RemoteServiceServlet implements GetTablesService{

	@Override
	public getTablesResult getTables(getTablesModel model) {
		
		getTablesQuery controller = new getTablesQuery();
		
		controller.setModel(model);
		
		getTablesResult result = new getTablesResult();
		controller.perform(result);
		
		return result;
	}
	

}
