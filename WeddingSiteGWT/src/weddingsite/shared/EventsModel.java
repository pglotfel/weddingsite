package weddingsite.shared;

import java.io.Serializable;

public class EventsModel extends Publisher implements Serializable{
	
	public enum Events {
		VALUE_CHANGED
	}
	
	private String accountName;
	private String activityName;
	private Activity activity;
	
	private ActionType type;
	
	public EventsModel() {
		
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
