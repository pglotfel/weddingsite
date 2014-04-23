package weddingsite.server;

import weddingsite.client.GetPeopleAtTable;
import weddingsite.shared.GetPeopleAtTableModel;
import weddingsite.shared.GetPeopleAtTableResult;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GetPeopleAtTableQueryImpl extends RemoteServiceServlet implements GetPeopleAtTable {

	@Override
	public GetPeopleAtTableResult getPeopleAtTable(GetPeopleAtTableModel model) {
			GetPeopleAtTableQuery controller = new GetPeopleAtTableQuery();
			
			controller.setModel(model);
			GetPeopleAtTableResult result = new GetPeopleAtTableResult();
			
			controller.perform(result);
			
			return result;
	}

}
