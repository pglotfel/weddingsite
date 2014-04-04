package weddingsite.shared;



public class Table {
	
	
	private int id;
	private int seatingChartID;
	private int numSeats;
	
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
}
