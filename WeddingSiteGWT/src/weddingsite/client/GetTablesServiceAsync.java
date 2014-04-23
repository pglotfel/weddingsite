package weddingsite.client;

import weddingsite.shared.getTablesModel;
import weddingsite.shared.getTablesResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetTablesServiceAsync {

	void getTables(getTablesModel model, AsyncCallback<getTablesResult> callback);

}
