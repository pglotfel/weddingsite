package weddingsite.client;

import weddingsite.shared.getTablesModel;
import weddingsite.shared.getTablesResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GetTablesService")
public interface GetTablesService extends RemoteService{
	
	public getTablesResult getTables(getTablesModel model);

}
