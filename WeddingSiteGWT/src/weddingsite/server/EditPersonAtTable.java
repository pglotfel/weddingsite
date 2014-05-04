package weddingsite.server;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditPersonAtTableModel;

public class EditPersonAtTable {
	EditPersonAtTableModel model;
	
	public void setModel(EditPersonAtTableModel model){
		this.model = model;
	}
	
	public void perform(EditDataResult result) {
		boolean resultVal = false;
		
		switch (model.getType()) {
		
		case ADDPERSON:
			resultVal = DatabaseProvider.getInstance().addPersonToTable(model.getAccountName(),
					model.getSeatingChartName(), model.getTableName(), model.getPersonName());
			break;
		
		case DELETEPERSON:
			resultVal = DatabaseProvider.getInstance().removePersonFromTable(model.getAccountName(),
					model.getSeatingChartName(), model.getTableName(), model.getPersonName());
			break;
		case EDITPERSON:
			resultVal = DatabaseProvider.getInstance().editPersonInTable(model.getAccountName(),
					model.getSeatingChartName(), model.getTableName(), model.getPersonName(), model.getNewName());
			break;
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		
		}
		
		result.setResult(resultVal);
	}
}
