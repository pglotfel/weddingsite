package weddingsite.server;

import weddingsite.client.EditTablesService;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditTablesModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EditTablesImpl extends RemoteServiceServlet implements EditTablesService{

	@Override
	public EditDataResult editTable(EditTablesModel model) {

		EditTables controller = new EditTables();
		
		controller.setModel(model);
		EditDataResult result = new EditDataResult();
		controller.perform(result);
		
		return result;
	}
	

}
