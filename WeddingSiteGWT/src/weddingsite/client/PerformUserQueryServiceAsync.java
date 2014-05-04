package weddingsite.client;

import weddingsite.shared.EditDataResult;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.User;
import weddingsite.shared.UserQueryModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PerformUserQueryServiceAsync {

	void performEditUser(UserQueryModel model,
			AsyncCallback<EditDataResult> callback);

	void performGetUsersQuery(UserQueryModel model,
			AsyncCallback<GetItemsResult<User>> callback);

}
