package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.GetPeopleAtTableModel;
import weddingsite.shared.GetPeopleAtTableResult;
import weddingsite.shared.Person;

public class GetPeopleAtTableQuery {
	
	private GetPeopleAtTableModel model;
	
	public void setModel(GetPeopleAtTableModel model) {
		this.model = model;
	}
	
	public void perform(GetPeopleAtTableResult result) {
		
		ArrayList<Person> people = new ArrayList<Person>();
		int numAtTable = -1;
		
		
		switch (model.getType()) {
		
		case GETPEOPLEATTABLE:
			
			numAtTable = DatabaseProvider.getInstance().getNumAtTable(model.getAccountName(), model.getSeatingChartName(), model.getTableName());
			people = DatabaseProvider.getInstance().getPeopleAtTable(model.getAccountName(), model.getSeatingChartName(), model.getTableName());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown operation type: " + model.getType());
		
		}
		
		result.setNumAtTable(numAtTable);
		result.setPeople(people);
		
	}
}
