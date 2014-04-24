package weddingsite.server;

import weddingsite.client.EditPersonAtTableService;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditPersonAtTableModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EditPersonAtTableImpl extends RemoteServiceServlet implements EditPersonAtTableService {

	@Override
	public EditDataResult editPersonAtTable(EditPersonAtTableModel model) {
		
		EditPersonAtTable controller = new EditPersonAtTable();
		
		controller.setModel(model);
		EditDataResult result = new EditDataResult();
		controller.perform(result);
		
		return result;
		
		
	}

}
