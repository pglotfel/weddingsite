package weddingsite.shared;

import java.util.ArrayList;

public class GetPeopleAtTableResult {
	public enum Events {
		VALUE_CHANGED
	}
	
	private ArrayList<Person> people;
	private int numAtTable;
	
	
	public GetPeopleAtTableResult() {
		people = new ArrayList<Person>();
		numAtTable = 0;
	}


	public ArrayList<Person> getPeople() {
		return people;
	}


	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}
	
	public int getNumAtTable() {
		return numAtTable;
	}
	
	public void setNumAtTable(int numAtTable) {
		this.numAtTable = numAtTable;
	}
	
}
