package weddingsite.client;

import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditPersonAtTableModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("EditPersonAtTableService")
public interface EditPersonAtTableService extends RemoteService {
	
	public EditDataResult editPersonAtTable(EditPersonAtTableModel model);
}
