package weddingsite.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class GetTablesResult extends Publisher implements Serializable {
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private ArrayList<Table> tables;
	
	
	public GetTablesResult() {
		tables = new ArrayList<Table>();
	}


	public ArrayList<Table> getTables() {
		return tables;
	}


	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}
	
	

}
