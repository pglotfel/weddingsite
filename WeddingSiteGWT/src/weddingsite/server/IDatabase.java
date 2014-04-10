package weddingsite.server;

import java.util.ArrayList;

import weddingsite.shared.User;

public interface IDatabase {
	public User getUser(String accountName, String userName);
	
	public void addUser(String accountName, String userName, String userPassword);
	
	public void addAdmin(String accountName, String userName, String userPassword);
	
	public ArrayList getAttendanceListAttendees(String name, String accountName);
}
