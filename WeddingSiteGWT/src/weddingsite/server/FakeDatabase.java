package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.Account;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.Attendee;
import weddingsite.shared.Person;
import weddingsite.shared.SeatingChart;
import weddingsite.shared.Table;
import weddingsite.shared.User;

public class FakeDatabase implements IDatabase  {
	
	private ArrayList<Account> accounts;
	private ArrayList<User> users;
	private ArrayList<AttendanceList> attendanceLists;
	private ArrayList<Attendee>  attendees;
	private ArrayList<SeatingChart> seatingCharts;
	private ArrayList<Table> tables;
	private ArrayList<Person> people;
	
	
	public FakeDatabase() {
		
		accounts = new ArrayList<Account>();
		users = new ArrayList<User>();
		attendees = new ArrayList<Attendee>();
		attendanceLists = new ArrayList<AttendanceList>();
		seatingCharts = new ArrayList<SeatingChart>();
		tables = new ArrayList<Table>();
		people = new ArrayList<Person>();
		
		
		String [] accountNames = {"Smith", "Doe", "Harrison"};
		String [] userNames = {"John", "Paul", "Jones", "Misty", "Harry", "Sally", "Molly", "Steven", "Christopher"};
		String [] attendeeNames = {"Bob", "Sally", "Me", "You", "Harry", "John", "Paul", "Jones", "Billy", "hey", "hello", "test", "Fourth",
				"fifth", "sixth", "ugh", "too many", "so many names", "just want", "to see", "if the", "scroll works", "bobby bob"};
		String [] attendanceListNames = {"Wedding", "Rehearsal Dinner", "Bridal Shower"};
		String [] tableNames = {"1","2","3","4","4","6","7","8","9"};
		
		for(int i = 0; i < attendeeNames.length; i++){
			Attendee att = new Attendee();
			att.setName(attendeeNames[i]);
			att.setAttending(true);
			att.setNumAttending(2);
			att.setAttendanceListID(0);
			attendees.add(att);	
			
			Person p = new Person();
			p.setName(attendeeNames[i]);
			p.setTableID(0);
			p.setId(i);
			people.add(p);
		}
		
		for(int i = 0; i < tableNames.length; i++) {
			Table t = new Table();
			t.setName(tableNames[i]);
			t.setSeatingChartID(0);
			t.setID(i);
			t.setNumSeats(8);
			tables.add(t);
		}
		
		for (int i = 0; i < 3; i++) {
			Account a = new Account();
			a.setAccountName(accountNames[i]);
			a.setID(i);
			
			AttendanceList attend = new AttendanceList();
			attend.setName(attendanceListNames[i]);
			attend.setAccountID(0);
			attend.setID(i);

			accounts.add(a);
			attendanceLists.add(attend);
			
			SeatingChart seat = new SeatingChart();
			seat.setAccountID(0);
			seat.setID(i);
			seat.setName(attendanceListNames[i]);
			
			seatingCharts.add(seat);
			
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				User u = new User();
				u.setID((3 * i) + j);
				u.setAccountID(i);
				u.setPassword("abdeFG");
				u.setUsername(userNames[(i * 3) + j]);
				users.add(u);
			}
		}
		
		users.get(1).setIsAdmin(true);
		
	}
	
	@Override
	public boolean addSeatingChart(String accountName, String seatingChartName) {
		
		Account a = findAccountByAccountName(accountName);
		
		if(a != null) {
			int acctId = a.getID();
			SeatingChart chart = new SeatingChart();
			chart.setName(seatingChartName);
			chart.setAccountID(acctId);
			chart.setID(seatingCharts.size());
			seatingCharts.add(chart);
			return true;
		
		}
		
		return false;
	}
	
	public boolean addTableToSeatingChart(String accountName, String seatingChartName, String tableName, int numSeats) {
		Account a = findAccountByAccountName(accountName);
		
		if(a != null) {
			int acctId = a.getID();
			int chartId = getSeatingChartId(acctId, seatingChartName);
			Table t = new Table();
			t.setName(tableName);
			t.setSeatingChartID(chartId);
			t.setNumSeats(numSeats);
			t.setID(tables.size());
			tables.add(t);
			return true;
		}
		
		return false;
	}
	
	public ArrayList<SeatingChart> getSeatingCharts(String accountName) {
		
		ArrayList<SeatingChart> charts = new ArrayList<SeatingChart>();
		
		int acctID = findAccountByAccountName(accountName).getID();
		
		for(int i = 0; i < seatingCharts.size(); i++) {
			if(seatingCharts.get(i).getAccountID() == acctID) {
				charts.add(seatingCharts.get(i));
			}
		}
		
		return charts;
		
	}
	
	@Override
	public ArrayList<Person> getPeopleAtTable(String accountName, String attendanceListName, String tableName) {
		int acctId = findAccountByAccountName(accountName).getID();
		int seatingChartId = getSeatingChartId(acctId, attendanceListName);
		int tableId = getTableId(seatingChartId, tableName);
		
		ArrayList<Person> result = new ArrayList<Person>();
		
		for(int i = 0; i < people.size(); i++) {
			if(people.get(i).getTableID() == tableId) {
				result.add(people.get(i));
			}
		}
		
		return result;
	}
	
	public int getNumAtTable(String accountName, String attendanceListName, String tableName) {
		ArrayList<Person> people = getPeopleAtTable(accountName, attendanceListName, tableName);
		int count = people.size();
		
		return count;
	}
	
	@Override
	public ArrayList<Table> getTables(String accountName, String seatingChartName) {
		
		Account a = findAccountByAccountName(accountName);
		int seatingChartId = getSeatingChartId(a.getID(), seatingChartName);
		ArrayList<Table> result = new ArrayList<Table>();
		
		for(int i = 0; i < tables.size(); i++) {
			if(tables.get(i).getSeatingChartID() == seatingChartId) {
				result.add(tables.get(i));
			}
		}
		
		return tables;
		
		
	}
	
	@Override
	public int getTotalAttending(String accountName, String seatingChartName) {
		
		ArrayList<Attendee> attendees = getAttendanceListAttendees(seatingChartName, accountName);
		int count = 0;
		for(int i = 0; i < attendees.size(); i++) {
			count += attendees.get(i).getNumAttending();
		}
		
		return count;
		
	}
	
	
	@Override
	public boolean addAttendee(String accountName, String attendanceListName, String attendeeName, int numAttending) {
		
		
		Attendee a = new Attendee();
		
		
		int accID = findAccountByAccountName(accountName).getID();
		int attID = -1;
		
		for(int i = 0; i < attendanceLists.size(); i++) {
				
			if(attendanceLists.get(i).getAccountID() == accID && attendanceLists.get(i).getName().equals(attendanceListName)) {
				attID = attendanceLists.get(i).getID();
					
			}
		}
			
		if(attID != -1 && !attendeeExists(attID, attendeeName)) {
			
			a.setAttendanceListID(attID);
			a.setName(attendeeName);
			a.setNumAttending(numAttending);
			a.setID(attendees.size());
			attendees.add(a);
			return true;
		}
	
		return false;
		
		
	}
	
	@Override
	public boolean modifyAttendee(String accountName, String attendanceListName, String attendeeName, int numAttending) {
		
		int accID = findAccountByAccountName(accountName).getID();
		int attID = -1;
		
		
		for(int i = 0; i < attendanceLists.size(); i++) {
			
			if(attendanceLists.get(i).getAccountID() == accID && attendanceLists.get(i).getName().equals(attendanceListName)) {
				attID = attendanceLists.get(i).getID();
			}
		}
		
		for(int i = 0; i < attendees.size(); i++) {
			
			if(attendees.get(i).getAttendanceListID() == attID && attendees.get(i).getName().equals(attendeeName)) {
				attendees.get(i).setNumAttending(numAttending);
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public boolean deleteAttendee(String accountName, String attendanceListName, String attendeeName) {
	
		
		int accID = findAccountByAccountName(accountName).getID();
		int attID = -1;
		
		for(int i = 0; i < attendanceLists.size(); i++) {
			
			if(attendanceLists.get(i).getAccountID() == accID && attendanceLists.get(i).getName().equals(attendanceListName)) {
				attID = attendanceLists.get(i).getID();
			}
		}
		
		for(int i = 0; i < attendees.size(); i++) {
			
			if(attendees.get(i).getAttendanceListID() == attID && attendees.get(i).getName().equals(attendeeName)) {
				attendees.remove(i);
				return true;
			}
			
		}
		
		return false;
		
	}

	@Override
	public User getUser(String accountName, String userName) {		
		
		Account a = findAccountByAccountName(accountName);
		
		if(a != null) {
			return findUserByUserNameAndAccountID(a.getID(), userName);
		}
		
		return null;
	}
	
	private User findUserByUserNameAndAccountID(int accountID, String userName) {
				
		for(User u : users) {
			if(u.getUsername().equals(userName) && u.getAccountID() == accountID) {
				return u;
			}
		}		
		
		return null;
	}
	
	private Account findAccountByAccountName(String accountName) {
		
		for (Account a : accounts) {
			if (a.getAccountName().equals(accountName)) {
				return a;
			}
		}
		
		return null;
	}
	
	private boolean attendeeExists(int attendanceListID, String name) {
		for(int i = 0; i < attendees.size(); i++) {
			
			if(attendees.get(i).getAttendanceListID() == attendanceListID && attendees.get(i).getName() == name) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public ArrayList<AttendanceList> getAttendanceLists(String accountName) {
		
		Account account = findAccountByAccountName(accountName);
		int accountID = account.getID();
		
		ArrayList<AttendanceList> list = new ArrayList<AttendanceList>();
		
		
		for(AttendanceList a : attendanceLists) {
			
			if(a.getAccountID() == accountID) {
				list.add(a);
			}
		}
		
		return list;
	}
	
	@Override
	public ArrayList<Attendee> getAttendanceListAttendees(String name, String accountName) {
		
		Account account = findAccountByAccountName(accountName);
		
		int AccountID = account.getID();
		
		ArrayList<Attendee> list = new ArrayList<Attendee>();
		int listID = 0;
		
		for(int i = 0; i < attendanceLists.size(); i++) {
			if( attendanceLists.get(i).getName().equals(name) && attendanceLists.get(i).getAccountID() == AccountID) {
				listID = attendanceLists.get(i).getID();
			}
		}
		
		for(Attendee a : attendees) {
			
			if(a.getAttendanceListID() == listID) {
				list.add(a);
			}
		}
		
		return list;
	}
	
	public boolean addAttendanceList(String accountName, String attendanceListName) {
		int accountID = findAccountByAccountName(accountName).getID();
		
		AttendanceList a = new AttendanceList();
		a.setAccountID(accountID);
		a.setName(attendanceListName);
		a.setID(attendanceLists.size());
		attendanceLists.add(a);
		
		return true;
	}
	
	@Override
	public void addUser(String accountName, String userName, String userPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAdmin(String accountName, String userName,
			String userPassword) {
		// TODO Auto-generated method stub
		
	}	
	
	private int getSeatingChartId(int accountId, String name) {
		for(int i = 0; i < seatingCharts.size(); i++) {
			if(seatingCharts.get(i).getName().equals(name) && seatingCharts.get(i).getAccountID() == accountId) {
				return seatingCharts.get(i).getID();
			}
		}
		
		return -1;
	}
	
	private int getTableId(int seatingChartid, String name) {
		for(int i = 0; i < tables.size(); i++) {
			if(tables.get(i).getName().equals(name) && tables.get(i).getSeatingChartID() == seatingChartid) {
				return tables.get(i).getID();
			}
		}
		
		return -1;
	}
	
}
