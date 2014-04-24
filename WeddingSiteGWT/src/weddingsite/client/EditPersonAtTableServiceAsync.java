package weddingsite.client;

import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditPersonAtTableModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EditPersonAtTableServiceAsync {

	void editPersonAtTable(EditPersonAtTableModel model,
			AsyncCallback<EditDataResult> callback);

}
