package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.Account;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.Attendee;
import weddingsite.shared.User;

public class FakeDatabase implements IDatabase  {
	
	private ArrayList<Account> accounts;
	private ArrayList<User> users;
	private ArrayList<AttendanceList> attendanceLists;
	private ArrayList<Attendee>  attendees;
	
	
	public FakeDatabase() {
		
		//Init
		
		accounts = new ArrayList<Account>();
		users = new ArrayList<User>();
		attendees = new ArrayList<Attendee>();
		attendanceLists = new ArrayList<AttendanceList>();
		
		
		String [] accountNames = {"Smith", "Doe", "Harrison"};
		String [] userNames = {"John", "Paul", "Jones", "Misty", "Harry", "Sally", "Molly", "Steven", "Christopher"};
		String [] attendeeNames = {"Bob", "Sally", "Me", "You", "Harry", "John", "Paul", "Jones", "Billy", "hey", "hello", "test", "Fourth",
				"fifth", "sixth", "ugh", "too many", "so many names", "just want", "to see", "if the", "scroll works", "bobby bob"};
		String [] attendanceListNames = {"Wedding", "Rehearsal Dinner", "Bridal Shower"};
		
		for(int i = 0; i < attendeeNames.length; i++){
			Attendee att = new Attendee();
			att.setName(attendeeNames[i]);
			att.setAttending(true);
			att.setNumAttending(2);
			att.setAttendanceListID(0);
			attendees.add(att);	
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
	
	@Override
	public void addUser(String accountName, String userName, String userPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAdmin(String accountName, String userName,
			String userPassword) {
		// TODO Auto-generated method stub
		
	}	
	
}
