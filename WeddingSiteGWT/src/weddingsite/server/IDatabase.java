package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.AttendanceList;
import weddingsite.shared.Attendee;
import weddingsite.shared.Person;
import weddingsite.shared.SeatingChart;
import weddingsite.shared.Table;
import weddingsite.shared.User;

public interface IDatabase {
	public User getUser(String accountName, String userName);
	
	public void addUser(String accountName, String userName, String userPassword);
	
	public void addAdmin(String accountName, String userName, String userPassword);
	
	public ArrayList<Attendee> getAttendanceListAttendees(String name, String accountName);
	
	public ArrayList<AttendanceList> getAttendanceLists(String accountName);
	
	public boolean addAttendee(String accountName, String attendanceListName, String attendeeName, int numAttending);
	
	public boolean modifyAttendee(String accountName, String attendanceListName, String attendeeName, int numAttending);
	
	public boolean deleteAttendee(String accountName, String attendanceListName, String attendeeName);
	
	public boolean addAttendanceList(String accountName, String attendanceListName);
	//public boolean deleteAttendanceList(String accountName, String attendanceListName);
	//public boolean editAttendanceList(String accountName, String attendanceListName, String newName);
	
	public ArrayList<SeatingChart> getSeatingCharts(String accountName);
	
	public ArrayList<Table> getTables(String accountName, String SeatingChartName);
	
	public int getTotalAttending(String accountName, String attendanceListName);
	
	public ArrayList<Person> getPeopleAtTable(String accountName, String attendanceListName, String tableName);
	
	public int getNumAtTable(String accountName, String attendanceListName, String tableName);
	
	public boolean addSeatingChart(String accountName, String seatingChartName);
	
	public boolean addTableToSeatingChart(String accountName, String seatingChartName, String tableName, int numSeats);
	
	public boolean editTableInSeatingChart(String accountName, String seatingChartName, String tableName, String newName, int numSeats);
	
	public boolean deleteTableInSeatingChart(String accountName, String seatingChartName, String tableName);
	
}
