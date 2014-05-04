package weddingsite.client;

import weddingsite.shared.EditDataResult;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.User;
import weddingsite.shared.UserQueryModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("performUserQuery")
public interface PerformUserQueryService extends RemoteService {
	public GetItemsResult<User> performGetUsersQuery (UserQueryModel model);
	public EditDataResult performEditUser (UserQueryModel model);
}
