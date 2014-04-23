package weddingsite.shared;

import java.io.Serializable;

public class EditTablesModel extends Publisher implements Serializable {
	public enum Events {
		VALUE_CHANGED
	}
	
	private String accountName;
	private String seatingChartName;
	private String tableName;
	private int numSeats;
	private ActionType type;
	
	public EditTablesModel() {
		
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSeatingChartName() {
		return seatingChartName;
	}

	public void setSeatingChartName(String seatingChartName) {
		this.seatingChartName = seatingChartName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}
	
	
	
}
