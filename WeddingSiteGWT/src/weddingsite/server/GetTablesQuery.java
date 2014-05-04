package weddingsite.server;

import java.util.ArrayList;

import weddingsite.persist.DatabaseProvider;
import weddingsite.shared.Table;
import weddingsite.shared.GetTablesModel;
import weddingsite.shared.GetTablesResult;

public class GetTablesQuery {
	
	private GetTablesModel model;
	
	public void setModel(GetTablesModel model) {
		this.model = model;
	}
	
	public void perform(GetTablesResult result) {
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
