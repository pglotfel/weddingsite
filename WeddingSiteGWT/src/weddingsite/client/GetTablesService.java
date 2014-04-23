package weddingsite.client;

import weddingsite.shared.GetTablesModel;
import weddingsite.shared.GetTablesResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GetTablesService")
public interface GetTablesService extends RemoteService{
	
	public GetTablesResult getTables(GetTablesModel model);

}
