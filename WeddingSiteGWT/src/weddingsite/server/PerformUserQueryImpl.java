package weddingsite.server;

import weddingsite.client.PerformUserQueryService;
import weddingsite.shared.AttendanceListQueryResult;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.GetItemsResult;
import weddingsite.shared.User;
import weddingsite.shared.UserQueryModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PerformUserQueryImpl extends RemoteServiceServlet implements PerformUserQueryService {

	@Override
	public GetItemsResult<User> performGetUsersQuery(UserQueryModel model) {

		PerformUserQuery controller = new PerformUserQuery();
		
		controller.setModel(model);		
		
		GetItemsResult<User> result = new GetItemsResult<User>();
		
		controller.perform(result);
		
		return result;
	}

	@Override
	public EditDataResult performEditUser(UserQueryModel model) {
		// TODO: Finish!
		return null;
	}

}
