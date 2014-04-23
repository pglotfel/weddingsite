package weddingsite.server;

import weddingsite.shared.EditDataResult;
import weddingsite.shared.EditTablesModel;

public class EditTables {
	private EditTablesModel model;
	
	public void setModel(EditTablesModel model) {
		this.model = model;
	}
	
	public void perform(EditDataResult result) {
		
		boolean resultVal = false;
		
		switch(model.getType()) {
		
		case ADDTABLE:
			
			resultVal = DatabaseProvider.getInstance().addTableToSeatingChart(model.getAccountName(), 
					model.getSeatingChartName(), model.getTableName(), model.getNumSeats());
			break;
		case EDITTABLE:
			
			resultVal = DatabaseProvider.getInstance().editTableInSeatingChart(model.getAccountName(), 
					model.getSeatingChartName(), model.getTableName(), model.getNewName(), model.getNumSeats());
			break;
		case DELETETABLE:
			
			resultVal = DatabaseProvider.getInstance().deleteTableInSeatingChart(model.getAccountName(), 
					model.getSeatingChartName(), model.getTableName());
			break;
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		
		}
		
		result.setResult(resultVal);
	}
}
