package weddingsite.shared;

import java.io.Serializable;

public class GetTablesModel extends Publisher implements Serializable {
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private String accountName;
	private String seatingChartName;
	private ActionType type;
	
	public GetTablesModel() {
		
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

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}
	
	

}
