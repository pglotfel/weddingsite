package weddingsite.model;



public class Table {
	
	
	private int id;
	private int seatingChartID;
	private int numSeats;
	private String name;
	
	public Table() {
		
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getSeatingChartID() {
		return seatingChartID;
	}
	
	public void setSeatingChartID(int seatingChartID) {
		this.seatingChartID = seatingChartID;
	}
	
	public void setNumSeats(int num) {
		this.numSeats = num;
	}
	
	
	public int getNumSeats() {
		return numSeats;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
