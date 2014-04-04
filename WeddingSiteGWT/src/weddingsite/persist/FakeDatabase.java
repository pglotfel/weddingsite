package weddingsite.persist;

import java.util.ArrayList;
import weddingsite.shared.Account;
import weddingsite.shared.User;

public class FakeDatabase implements IDatabase  {
	
	private ArrayList<Account> accounts;
	private ArrayList<User> users;
	
	public FakeDatabase() {
		
		//Init
		
		accounts = new ArrayList<Account>();
		users = new ArrayList<User>();
		
		String [] accountNames = {"Smith", "Doe", "Harrison"};
		String [] userNames = {"John", "Paul", "Jones", "Misty", "Harry", "Sally", "Molly", "Steven", "Christopher"};
		
		for (int i = 0; i < 3; i++) {
			Account a = new Account();
			a.setAccountName(accountNames[i]);
			a.setID(i);
			accounts.add(a);
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
