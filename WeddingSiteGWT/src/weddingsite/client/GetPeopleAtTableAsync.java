package weddingsite.client;

import weddingsite.shared.GetPeopleAtTableModel;
import weddingsite.shared.GetPeopleAtTableResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetPeopleAtTableAsync {

	void getPeopleAtTable(GetPeopleAtTableModel model,
			AsyncCallback<GetPeopleAtTableResult> callback);

}
