package weddingsite.shared;

public class GetPeopleAtTableModel {
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private String accountName;
	private String seatingChartName;
	private String tableName;
	private ActionType type;
	
	public GetPeopleAtTableModel() {
		
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

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}
	
	

}
