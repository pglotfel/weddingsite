package weddingsite.model;

public class Contact {
	
	private int id;
	private int taskID;
	private String name;
	private String email;
	private String mainPhone;
	private String alternatePhone;
	private String website;
	
	public Contact() {
		
	}
	
	public Contact(String name, String email,
			String mainPhone, String alternatePhone, String website) {
		
		this.name = name;
		this.email = email;
		this.mainPhone = mainPhone;
		this.alternatePhone = alternatePhone;
		this.website = website;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMainPhone() {
		return mainPhone;
	}

	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	public String getAlternatePhone() {
		return alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getTaskID() {
		return taskID;
	}
	
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
}
	
