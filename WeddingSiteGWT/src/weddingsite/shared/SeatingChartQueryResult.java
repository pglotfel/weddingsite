package weddingsite.shared;

import java.io.Serializable;
import java.util.ArrayList;

import weddingsite.model.SeatingChart;
import weddingsite.shared.AttendanceListQueryResult.Events;

public class SeatingChartQueryResult extends Publisher implements Serializable{

	public enum Events {
		VALUE_CHANGED
	}
	
	private ArrayList<SeatingChart> charts;
	
	public SeatingChartQueryResult() {
		this.charts = null;
	}
	
	public ArrayList<SeatingChart> getSeatingCharts() {
		return charts;
	}
	
	public void setSeatingCharts(ArrayList<SeatingChart> charts) {
		this.charts = charts;
		notifySubscribers(Events.VALUE_CHANGED, charts);
	}
}
