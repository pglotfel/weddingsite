package weddingsite.client;

import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditTablesModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EditTablesServiceAsync {

	void editTable(EditTablesModel model, AsyncCallback<EditDataResult> callback);

}
