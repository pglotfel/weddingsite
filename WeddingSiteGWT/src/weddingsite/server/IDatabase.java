package weddingsite.server;

import java.util.ArrayList;

import weddingsite.model.Attendee;
import weddingsite.model.Person;
import weddingsite.model.SeatingChart;
import weddingsite.model.Table;
import weddingsite.model.User;
import weddingsite.shared.AttendanceList;

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
	
	public ArrayList<SeatingChart> getSeatingCharts(String accountName);
	
	public ArrayList<Table> getTables(String accountName, String SeatingChartName);
	
	public int getTotalAttending(String accountName, String attendanceListName);
	
	public ArrayList<Person> getPeopleAtTable(String accountName, String attendanceListName, String tableName);
	
	public int getNumAtTable(String accountName, String attendanceListName, String tableName);
	
	public boolean addSeatingChart(String accountName, String seatingChartName);
	
	public boolean addTableToSeatingChart(String accountName, String seatingChartName, String tableName, int numSeats);
	
	
}
