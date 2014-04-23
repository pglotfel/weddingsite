package weddingsite.server;

import java.util.ArrayList;

import weddingsite.model.Table;
import weddingsite.shared.getTablesModel;
import weddingsite.shared.getTablesResult;

public class getTablesQuery {
	
	private getTablesModel model;
	
	public void setModel(getTablesModel model) {
		this.model = model;
	}
	
	public void perform(getTablesResult result) {
		ArrayList<Table> resultList = new ArrayList<Table>();
		
		switch (model.getType()) {
		
		case GETTABLES:
			
			resultList = DatabaseProvider.getInstance().getTables(model.getAccountName(), model.getSeatingChartName());
			break;
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		}
		
		result.setTables(resultList);
	}
}
