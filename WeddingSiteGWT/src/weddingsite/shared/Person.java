package weddingsite.shared;

import java.io.Serializable;

public class Person implements Serializable {
	
	private int id;
	private int tableID;
	private String name;
	
	public Person() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
