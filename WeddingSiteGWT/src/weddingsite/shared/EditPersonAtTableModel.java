package weddingsite.shared;

import java.io.Serializable;

public class EditPersonAtTableModel implements Serializable{
	
	private String accountName;
	private String seatingChartName;
	private String tableName;
	private String personName;
	private String newName;
	private ActionType type;
	
	public EditPersonAtTableModel() {
		
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

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
