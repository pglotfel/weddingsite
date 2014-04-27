package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.Account;
import weddingsite.shared.Activity;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.Attendee;
import weddingsite.shared.Pair;
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
	private ArrayList<Activity> activities;
	private ArrayList<Pair<Integer, Integer>> userActivityLinking;
	
	
	public FakeDatabase() {
		
		userActivityLinking = new ArrayList<Pair<Integer, Integer>>(); 
		accounts = new ArrayList<Account>();
		users = new ArrayList<User>();
		attendees = new ArrayList<Attendee>();
		attendanceLists = new ArrayList<AttendanceList>();
		seatingCharts = new ArrayList<SeatingChart>();
		tables = new ArrayList<Table>();
		people = new ArrayList<Person>();
		activities = new ArrayList<Activity>();
		
		for(int i = 0; i < 10; i++) {
			Activity a = new Activity();
			a.setBody("This is a test activity.");
			a.setDate("06/01/1994");
			a.setEndTime("1:00 P.M.");
			a.setStartTime("10:00 A.M.");
			a.setTitle("Test Activity!");
			a.setID(i);
			activities.add(a);
			Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, i);
			userActivityLinking.add(p);
		}
		
		
		
		
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
	public ArrayList<Activity> getUserActivities(String accountName, String username) {
		
		Account a = findAccountByAccountName(accountName);
		ArrayList<Activity> result = new ArrayList<Activity>();
		
		if(a != null) {
			
			User u = findUserByUserNameAndAccountID(a.getID(), username);
			int uID = u.getID();
			
			if(u != null) {
				
				Pair<Integer, Integer> p = null;
				
				for(int i = 0; i < userActivityLinking.size(); i++) {
					
					p = userActivityLinking.get(i);
					
					if(p.getLeft().intValue() == uID) {
						for(int j = 0; j < activities.size(); j++) {
							
							Activity act = activities.get(i);
							
							if(p.getRight().intValue() == act.getID()) {
								result.add(act);
							}
						}
					}
				}
			} 			
		} 
		
		return result;		
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
	
	@Override
	public boolean deleteSeatingChart(String accountName, String seatingChartName) {
		Account a = findAccountByAccountName(accountName);
		if(a != null) {
			int acctId = a.getID();
			for(int i = 0; i < seatingCharts.size(); i++) {
				if(seatingCharts.get(i).getAccountID() == acctId && seatingCharts.get(i).getName().equals(seatingChartName)) {
					seatingCharts.remove(i);
					return true;
				}
			}
		
		}
		
		return false;
	}
	
	public boolean editSeatingChart(String accountName, String seatingChartName, String newName) {
		Account a = findAccountByAccountName(accountName);
		if(a != null) {
			int acctId = a.getID();
			for(int i = 0; i < seatingCharts.size(); i++) {
				if(seatingCharts.get(i).getAccountID() == acctId && seatingCharts.get(i).getName().equals(seatingChartName)) {
					seatingCharts.get(i).setName(newName);
					return true;
				}
			}
		
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
	
	public boolean editTableInSeatingChart(String accountName, String seatingChartName, String tableName, String newName, int numSeats) {
		
		Account a = findAccountByAccountName(accountName);
		
		if(a != null) {
			int acctId = a.getID();
			int seatingChartId = -1;
			
			for(int i = 0; i < seatingCharts.size(); i++) {
				if(seatingCharts.get(i).getAccountID() == acctId && seatingCharts.get(i).getName().equals(seatingChartName)) {
					seatingChartId = seatingCharts.get(i).getID();
				}
			}
			
			for(int i = 0; i < tables.size(); i++){
				if(tables.get(i).getSeatingChartID() == seatingChartId && tables.get(i).getName().equals(tableName)) {
					tables.get(i).setName(newName);
					tables.get(i).setNumSeats(numSeats);
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public boolean deleteTableInSeatingChart(String accountName, String seatingChartName, String tableName) {
		Account a = findAccountByAccountName(accountName);
		
		if(a != null) {
			int acctId = a.getID();
			int seatingChartId = -1;
			
			for(int i = 0; i < seatingCharts.size(); i++) {
				if(seatingCharts.get(i).getAccountID() == acctId && seatingCharts.get(i).getName().equals(seatingChartName)) {
					seatingChartId = seatingCharts.get(i).getID();
				}
			}
			
			for(int i = 0; i < tables.size(); i++){
				if(tables.get(i).getSeatingChartID() == seatingChartId && tables.get(i).getName().equals(tableName)) {
					tables.remove(i);
					return true;
				}
			}
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
	public ArrayList<Person> getPeopleAtTable(String accountName, String seatingChartName, String tableName) {
		int acctId = findAccountByAccountName(accountName).getID();
		int seatingChartId = getSeatingChartId(acctId, seatingChartName);
		int tableId = getTableId(seatingChartId, tableName);
		
		ArrayList<Person> result = new ArrayList<Person>();
		
		for(int i = 0; i < people.size(); i++) {
			if(people.get(i).getTableID() == tableId) {
				result.add(people.get(i));
			}
		}
		
		return result;
	}
	
	public boolean removePersonFromTable(String accountName, String seatingChartName, String tableName, String personName) {
		int acctId = findAccountByAccountName(accountName).getID();
		int seatingChartId = getSeatingChartId(acctId, seatingChartName);
		int tableId = getTableId(seatingChartId, tableName);
		
		for(int i = 0; i < people.size(); i++) {
			if(people.get(i).getTableID() == tableId && people.get(i).getName().equals(personName)) {
				people.remove(i);
				return true;
			}
		}
		
		return false;
		
	}
	
	public boolean addPersonToTable(String accountName, String seatingChartName, String tableName, String personName) {
		int acctId = findAccountByAccountName(accountName).getID();
		int seatingChartId = getSeatingChartId(acctId, seatingChartName);
		int tableId = getTableId(seatingChartId, tableName);
		
		if(tableId != -1) {
			Person p = new Person();
			p.setName(personName);
			p.setTableID(tableId);
			p.setId(people.size());
			
			people.add(p);
			
			return true;
		}
		return false;
	}
	
	public boolean editPersonInTable(String accountName, String seatingChartName, String tableName, String personName, String newName) {
		int acctId = findAccountByAccountName(accountName).getID();
		int seatingChartId = getSeatingChartId(acctId, seatingChartName);
		int tableId = getTableId(seatingChartId, tableName);
		
		for(int i = 0; i < people.size(); i++) {
			if(people.get(i).getTableID() == tableId && people.get(i).getName().equals(personName)) {
				people.get(i).setName(newName);
				return true;
			}
		}
		
		return false;
	}
	
	public int getNumAtTable(String accountName, String seatingChartName, String tableName) {
		ArrayList<Person> people = getPeopleAtTable(accountName, seatingChartName, tableName);
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
		
		return result;
		
		
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
	
	@Override
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
	public boolean deleteAttendanceList(String accountName, String attendanceListName) {
		Account account = findAccountByAccountName(accountName);
		int acctId = account.getID();
		
		for(AttendanceList a : attendanceLists) {
			
			if(a.getAccountID() == acctId && a.getName().equals(attendanceListName)) {
				attendanceLists.remove(a);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean editAttendanceList(String accountName, String attendanceListName, String newName) {
		Account account = findAccountByAccountName(accountName);
		int acctId = account.getID();
		
		for(AttendanceList a : attendanceLists) {
			
			if(a.getAccountID() == acctId && a.getName().equals(attendanceListName)) {
				a.setName(newName);;
				return true;
			}
		}
		
		return false;
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
	public boolean addUser(String accountName, String userName, String userPassword, boolean isAdmin) {
		Account a = findAccountByAccountName(accountName);
		
		if(a != null) {
			User u = new User();
			u.setUsername(userName);
			u.setPassword(userPassword);
			u.setAccountID(a.getID());
			u.setID(users.size());
			u.setIsAdmin(isAdmin);
			users.add(u);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean createAccount(String accountName, String adminName, String password) {
		Account a = new Account();
		a.setAccountName(accountName);
		int accountId = accounts.size();
		a.setID(accountId);
		accounts.add(a);
		
		User u = new User();
		u.setAccountID(accountId);
		u.setID(users.size());
		u.setIsAdmin(true);
		u.setPassword(password);
		u.setUsername(adminName);
		users.add(u);
		
		return true;
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
