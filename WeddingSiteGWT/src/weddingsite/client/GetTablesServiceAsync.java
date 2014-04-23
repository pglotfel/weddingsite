package weddingsite.client;

import weddingsite.shared.GetTablesModel;
import weddingsite.shared.GetTablesResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetTablesServiceAsync {

	void getTables(GetTablesModel model, AsyncCallback<GetTablesResult> callback);

}
