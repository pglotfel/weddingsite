package weddingsite.client;

import weddingsite.shared.GetPeopleAtTableModel;
import weddingsite.shared.GetPeopleAtTableResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GetPeopleAtTable")
public interface GetPeopleAtTable extends RemoteService{
	
	public GetPeopleAtTableResult getPeopleAtTable(GetPeopleAtTableModel model);

}
