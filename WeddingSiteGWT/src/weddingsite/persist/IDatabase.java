package weddingsite.persist;

import weddingsite.shared.User;

public interface IDatabase {
	public User getUser(String accountName, String userName);
	
	public void addUser(String accountName, String userName, String userPassword);
	
	public void addAdmin(String accountName, String userName, String userPassword);
}
