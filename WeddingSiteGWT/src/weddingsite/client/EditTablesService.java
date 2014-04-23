package weddingsite.client;

import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditTablesModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("EditTable")
public interface EditTablesService extends RemoteService {
	
	public EditDataResult editTable(EditTablesModel model);
	

}
