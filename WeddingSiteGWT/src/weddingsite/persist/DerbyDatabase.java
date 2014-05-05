package weddingsite.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;











import weddingsite.shared.Account;
import weddingsite.shared.Activity;
import weddingsite.shared.AttendanceList;
import weddingsite.shared.Attendee;
import weddingsite.shared.Person;
import weddingsite.shared.SeatingChart;
import weddingsite.shared.Table;
import weddingsite.shared.User;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby JDBC driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	private static final String DB_NAME = "H:/weddingsite.db";
	
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:" + DB_NAME + ";create=true");
		
		// Set autocommit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	
	
	
	
	
	
	//OUR CODE###############################
	
	//Create Tables!!!!
		public void createTables() {
			executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					PreparedStatement accounts = null;
					PreparedStatement users = null;
					PreparedStatement attendanceLists = null;
					PreparedStatement seatingCharts = null;
					PreparedStatement tables = null;
					PreparedStatement people = null;
					PreparedStatement attendees = null;
					PreparedStatement events = null;
					PreparedStatement eventsUsersLink = null;
					//TODO:
					try {
						accounts = conn.prepareStatement(
								"create table accounts (" +
								"    id integer primary key not null generated always as identity," +
								"    accountName varchar(50) unique" +
								")");
						accounts.executeUpdate();
						
						users = conn.prepareStatement(
								"create table users (" +
								"    id integer primary key not null generated always as identity," +
								"    accountID integer," +
								"    username varchar(50)," +
								"    password varchar(20)," +
								"    isAdmin boolean" +
								")");
						users.executeUpdate();
						
						attendanceLists = conn.prepareStatement(
								"create table attendanceLists (" +
										"    id integer primary key not null generated always as identity," +
										"    accountID integer," +
										"    name varchar(50)" +
								")");
						attendanceLists.executeUpdate();	
						
						seatingCharts = conn.prepareStatement(
								"create table seatingCharts (" +
								"    id integer primary key not null generated always as identity," +
								"    accountID integer," +
								"    name varchar(50)" +
								")");
						seatingCharts.executeUpdate();
						
						tables = conn.prepareStatement( 
								"create table tables (" +
								" id integer primary key not null generated always as identity," +
								" seatingChartID integer," +
								" numSeats integer," +
								" name varchar(50)" +
								")");
						tables.executeUpdate();
						
						people = conn.prepareStatement( 
								"create table people (" +
								"id integer primary key not null generated always as identity," +
								"tableID integer," +
								"name varchar(50)" +
								")");
						people.executeUpdate();
						
						attendees = conn.prepareStatement( 
								"create table attendees (" +
								" id integer primary key not null generated always as identity," +
								"attendanceListID integer," +
								"attending boolean," +
								"numAttending integer," +
								"name varchar(50)" +
								")"
								);
						attendees.executeUpdate();
						
						events = conn.prepareStatement(
								"create table events (" +
								" id integer primary key not null generated always as identity," +
								"accountID integer," +
								"date varchar(15)," +
								"body varchar(500)," +
								"startTime varchar(15)," +
								"endTime varchar(15)" +
								")");
						events.executeUpdate();
						
						eventsUsersLink = conn.prepareStatement(
								"create table eventsUsersLink (" +
								"eventID integer," +
								"userID integer" +
								")");
						
						eventsUsersLink.executeUpdate();
						
						return true;
					} finally {
						DBUtil.closeQuietly(accounts);
						DBUtil.closeQuietly(users);
						DBUtil.closeQuietly(attendanceLists);
						DBUtil.closeQuietly(seatingCharts);
						DBUtil.closeQuietly(tables);
						DBUtil.closeQuietly(attendees);
						DBUtil.closeQuietly(events);
						DBUtil.closeQuietly(eventsUsersLink);
					}
				}
			});
		}
	
		
	@Override
	public Account getAccountByAccountName(final String accountName) {
		
		return executeTransaction(new Transaction<Account>() {
			@Override
			public Account execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"select accounts.* " +
							"  from accounts " +
							" where accounts.accountName = ?" 
					);
					
					stmt.setString(1, accountName);
					
					Account result = null;
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						result = new Account();
						result.setID(resultSet.getInt(1));
						result.setAccountName(resultSet.getString(2));
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean createAccount(final String accountName, final String adminName,
			final String password) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet generatedKeys = null;
				ResultSet moreKeys = null;
				
				Account a = new Account();
				
				
				try {
					stmt = conn.prepareStatement(
						"insert into accounts (accountName) values (?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					stmt.setString(1, accountName);
					
					
					 //Attempt to insert new account
					stmt.executeUpdate();

					 //Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted Account");
					}
					
					a.setID(generatedKeys.getInt(1));
					System.out.println("New account has id " + a.getID());
					
					stmt2 = conn.prepareStatement( 
							"insert into users (accountId, username, password, isAdmin) values (?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
						);
					
					stmt2.setInt(1, a.getID());
					stmt2.setString(2, adminName);
					stmt2.setString(3, password);
					stmt2.setBoolean(4, true);
					
					//Try to insert new User
					stmt2.executeUpdate();
					
					
					moreKeys = stmt2.getGeneratedKeys();
					if (!moreKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted User");
					}
					
					int key = generatedKeys.getInt(1);
					System.out.println("New user has id " + key);
					
					
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(moreKeys);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	

	@Override
	public User getUser(final String accountName, final String userName) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				User u = null;
				try {
					
					stmt = conn.prepareStatement(
							"select users.* " +
							"  from users, accounts " +
							" where users.username = ? and accounts.accountName = ? and users.accountId = accounts.Id" 
					);
					
					stmt.setString(1, userName);
					stmt.setString(2, accountName);
					
					
					stmt.setString(1, accountName);
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						u = new User();
						u.setID(resultSet.getInt("id"));
						u.setAccountID(resultSet.getInt("accountID"));
						u.setUsername(resultSet.getString("username"));
						u.setPassword(resultSet.getString("password"));
						u.setIsAdmin(resultSet.getBoolean("isAdmin"));
					}
					
					return u;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean addUser(final String accountName, final String userName,
			final String userPassword, final boolean isAdmin) {
		
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				Account a = new Account();
				a = getAccountByAccountName(accountName);
				
				try {
					stmt = conn.prepareStatement(
							"insert into users (accountId, username, password, isAdmin) values (?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					stmt.setInt(1, a.getID());
					stmt.setString(2, userName);
					stmt.setString(3, userPassword);
					stmt.setBoolean(4, isAdmin);

					// Attempt to insert new account
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted Account");
					}
					
					
					System.out.println("New user has id " + generatedKeys.getInt(1));
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<AttendanceList> getAttendanceLists(final String accountName) {
		return executeTransaction(new Transaction<ArrayList<AttendanceList>>() {
			@Override
			public ArrayList<AttendanceList> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select attendanceLists.*" +
							"  from attendanceLists, accounts " +
							" where attendanceLists.accountId = accounts.id and accounts.accountName = ?"
							
					);
					
					stmt.setString(1, accountName);
					
					ArrayList<AttendanceList> result = new ArrayList<AttendanceList>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						AttendanceList list = new AttendanceList();
						list.setAccountID(resultSet.getInt("accountID"));
						list.setID(resultSet.getInt("id"));
						list.setName(resultSet.getString("name"));
						result.add(list);
						
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean addAttendanceList(final String accountName,
			final String attendanceListName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				Account a = new Account();
				a = getAccountByAccountName(accountName);
				
				try {
					stmt = conn.prepareStatement(
							"insert into attendanceLists (accountID, name) values (?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					stmt.setInt(1, a.getID());
					stmt.setString(2, attendanceListName);
					

					// Attempt to insert new account
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted Attendance List");
					}
					
					
					System.out.println("New attendance list has id " + generatedKeys.getInt(1));
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean deleteAttendanceList(final String accountName,
			final String attendanceListName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				Account a = new Account();
				a = getAccountByAccountName(accountName);
				
				try {
					stmt = conn.prepareStatement(
							"delete from attendanceLists" +
							" where attendanceLists.accountID = ? and attendanceLists.name = ?"
					);
					
					stmt.setInt(1, a.getID());
					stmt.setString(2, attendanceListName);
					

					// Attempt to insert new account
					stmt.executeUpdate();

					// Determine the auto-generated id
					
					
					
					System.out.println("successfuly deleted " + attendanceListName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	
	}

	@Override
	public boolean editAttendanceList(final String accountName,
			final String attendanceListName, final String newName) {
		
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				Account a = new Account();
				a = getAccountByAccountName(accountName);
				
				try {
					stmt = conn.prepareStatement(
							"update attendanceLists" +
							" set name = ? " +
							" where attendanceLists.accountID = ? and attendanceLists.name = ?"
					);
					
					stmt.setString(1, newName);
					stmt.setInt(2, a.getID());
					stmt.setString(3, attendanceListName);
					

					// Attempt to modify the attendance list
					stmt.executeUpdate();

					
					System.out.println("successfuly updated " + attendanceListName + " to " + newName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public int getTotalAttending(String accountName, String attendanceListName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Attendee> getAttendanceListAttendees(final String name,
			final String accountName) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<ArrayList<Attendee>>() {
			@Override
			public ArrayList<Attendee> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				Account b = getAccountByAccountName(accountName);
				
				
				try {
					stmt = conn.prepareStatement(
							"select attendees.*" +
							"  from attendanceLists, attendees " +
							" where attendanceLists.accountId = ? " +
							"and attendees.attendanceListID = attendanceLists.id " +
							"and attendanceLists.name = ?"		
					);
					
					stmt.setInt(1, b.getID());
					stmt.setString(2, name);
					
					ArrayList<Attendee> result = new ArrayList<Attendee>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Attendee a = new Attendee();
						a.setAttendanceListID(resultSet.getInt("attendanceListID"));
						a.setID(resultSet.getInt("id"));
						a.setAttending(resultSet.getBoolean("attending"));
						a.setName(resultSet.getString("name"));
						a.setNumAttending(resultSet.getInt("numAttending"));
						result.add(a);						
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});

	}

	@Override
	public boolean addAttendee(final String accountName, final String attendanceListName, final String attendeeName, final int numAttending) {
		
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				if(attendanceListName == null) {
					return false;
				}
				
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				ArrayList<AttendanceList> aList = getAttendanceLists(accountName);
				
				AttendanceList a = null;
				
				for(int i = 0; i < aList.size(); i++) {
					if(aList.get(i).getName().equals(attendanceListName)) {
						a = aList.get(i); 
						break;
					}
				}
				
				
				//Get right list, store ID
				
				try {
					stmt = conn.prepareStatement(
							"insert into attendees (attendanceListID, attending, numAttending, name) values (?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					stmt.setInt(1, a.getID());
					stmt.setInt(3, numAttending);
					stmt.setBoolean(2, true);
					stmt.setString(4, attendeeName);
					

					// Attempt to insert new attendee
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted Attendance List");
					}
					
					
					System.out.println("New attendee has id " + generatedKeys.getInt(1));
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean modifyAttendee(final String accountName,
			final String attendanceListName, final String attendeeName, final int numAttending) {
		
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				if(attendanceListName == null || attendeeName == null) {
					return false;
				}
				
				ArrayList<AttendanceList> aList = new ArrayList<AttendanceList>();
				aList = getAttendanceLists(accountName);
				
				AttendanceList a = null;
				
				for(AttendanceList i : aList) {
					if(i.getName().equals(attendanceListName)) {
						a = i; 
						break;
					}
				}
				
				try {
					stmt = conn.prepareStatement(
							"update attendees" +
							" set numAttending = ? " +
							" where attendees.attendanceListID = ? and attendees.name = ?"
					);
					
					stmt.setInt(1, numAttending);
					stmt.setInt(2, a.getID());
					stmt.setString(3, attendeeName);
					

					// Attempt to modify the attendance list
					stmt.executeUpdate();

					
					System.out.println("successfuly updated number attending to " + numAttending);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean deleteAttendee(final String accountName, final String attendanceListName, final String attendeeName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				if(attendanceListName == null || attendeeName == null) {
					return false;
				}
				
				ArrayList<AttendanceList> aList = new ArrayList<AttendanceList>();
				aList = getAttendanceLists(accountName);
				
				AttendanceList a = null;
				
				for(AttendanceList i : aList) {
					if(i.getName().equals(attendanceListName)) {
						a = i; 
						break;
					}
				}
				
				try {
					stmt = conn.prepareStatement(
							"delete from attendees" +
							" where attendees.attendanceListID = ? and attendees.name = ?"
					);
					
					stmt.setInt(1, a.getID());
					stmt.setString(2, attendeeName);
					

					// Attempt to insert new account
					stmt.executeUpdate();
				
					System.out.println("successfuly deleted " + attendeeName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<SeatingChart> getSeatingCharts(final String accountName) {
		return executeTransaction(new Transaction<ArrayList<SeatingChart>>() {
			@Override
			public ArrayList<SeatingChart> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select seatingCharts.*" +
							"  from accounts, seatingCharts " +
							" where accounts.accountName = ?" +
							"and seatingCharts.accountID = accounts.id"
							
					);
					
					stmt.setString(1, accountName);
					
					ArrayList<SeatingChart> result = new ArrayList<SeatingChart>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						SeatingChart a = new SeatingChart();
						a.setAccountID(resultSet.getInt("accountID"));
						a.setID(resultSet.getInt("id"));
						a.setName(resultSet.getString("name"));
						result.add(a);
						
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean addSeatingChart(final String accountName, final String seatingChartName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				Account a = getAccountByAccountName(accountName);
				
				
				
				//Get right list, store ID
				
				try {
					stmt = conn.prepareStatement(
							"insert into seatingCharts (accountID, name) values (?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					stmt.setInt(1, a.getID());
					stmt.setString(2, seatingChartName);
					

					// Attempt to insert new attendee
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted Seating Chart");
					}
					
					
					System.out.println("New seating Chart has id " + generatedKeys.getInt(1));
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean deleteSeatingChart(final String accountName,
			final String seatingChartName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				Account a = getAccountByAccountName(accountName);
				
				try {
					stmt = conn.prepareStatement(
							"delete from seatingCharts" +
							" where seatingCharts.accountID = ? and seatingCharts.name = ?"
					);
					
					stmt.setInt(1, a.getID());
					stmt.setString(2, seatingChartName);
					
					stmt.executeUpdate();
				
					System.out.println("successfuly deleted " + seatingChartName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean editSeatingChart(final String accountName,
			final String seatingChartName, final String newName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				Account a = getAccountByAccountName(accountName);
				
				
				try {
					stmt = conn.prepareStatement(
							"update seatingCharts" +
							" set seatingCharts.name = ? " +
							" where seatingCharts.accountID = ? and seatingCharts.name = ?"
					);
					
					stmt.setString(1, newName);
					stmt.setInt(2, a.getID());
					stmt.setString(3, seatingChartName);
					

					// Attempt to modify the attendance list
					stmt.executeUpdate();

					
					System.out.println("successfuly updated seating chart name to " + newName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<Table> getTables(final String accountName,
			final String SeatingChartName) {
		return executeTransaction(new Transaction<ArrayList<Table>>() {
			@Override
			public ArrayList<Table> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select tables.*" +
							"  from accounts, seatingCharts, tables " +
							" where accounts.accountName = ? " +
							"and seatingCharts.accountID = accounts.id " +
							"and seatingCharts.name = ? " +
							"and tables.seatingChartID = seatingCharts.id "
							
					);
					
					stmt.setString(1, accountName);
					stmt.setString(2, SeatingChartName);
					
					ArrayList<Table> result = new ArrayList<Table>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Table a = new Table();
						a.setID(resultSet.getInt("id"));
						a.setName(resultSet.getString("name"));
						a.setNumSeats(resultSet.getInt("numSeats"));
						a.setSeatingChartID(resultSet.getInt("seatingChartID"));
						result.add(a);
						
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public int getNumAtTable(String accountName, String attendanceListName,
			String tableName) {
		return 0;
	}

	@Override
	public boolean addTableToSeatingChart(final String accountName,
			final String seatingChartName, final String tableName, final int numSeats) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				ArrayList<SeatingChart> charts = getSeatingCharts(accountName);
				int seatingChartID = -1;
				for(int i = 0; i < charts.size(); i++) {
					if(charts.get(i).getName().equals(seatingChartName)) {
						seatingChartID = charts.get(i).getID();
					}
				}
				try {
					stmt = conn.prepareStatement(
							"insert into tables (seatingChartID, numSeats, name) values (?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					stmt.setInt(1, seatingChartID);
					stmt.setInt(2, numSeats);
					stmt.setString(3, tableName);
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted table");
					}
					System.out.println("New table has id " + generatedKeys.getInt(1));
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean editTableInSeatingChart(final String accountName,
			final String seatingChartName, final String tableName, final String newName,
			final int numSeats) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				ArrayList<SeatingChart> charts = getSeatingCharts(accountName);
				int seatingChartID = -1;
				for(int i = 0; i < charts.size(); i++) {
					if(charts.get(i).getName().equals(seatingChartName)) {
						seatingChartID = charts.get(i).getID();
					}
				}
				try {
					stmt = conn.prepareStatement(
							"update tables" +
							" set tables.name = ?, tables.numSeats = ?" +
							" where tables.seatingChartID = ? and tables.name = ?"
					);
					
					stmt.setString(1, newName);
					stmt.setInt(2, numSeats);
					stmt.setInt(3, seatingChartID);
					stmt.setString(4, tableName);
					stmt.executeUpdate();
					
					System.out.println("successfuly updated table name to " + newName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean deleteTableInSeatingChart(final String accountName,
			final String seatingChartName, final String tableName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				ArrayList<SeatingChart> charts = getSeatingCharts(accountName);
				int seatingChartID = -1;
				for(int i = 0; i < charts.size(); i++) {
					if(charts.get(i).getName().equals(seatingChartName)) {
						seatingChartID = charts.get(i).getID();
					}
				}
				
				try {
					stmt = conn.prepareStatement(
							"delete from tables" +
							" where tables.seatingChartID = ? and tables.name = ?"
					);
					
					stmt.setInt(1, seatingChartID);
					stmt.setString(2, tableName);
					
					stmt.executeUpdate();
				
					System.out.println("successfuly deleted " + tableName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<Person> getPeopleAtTable(final String accountName,
			final String seatingChartName, final String tableName) {
		return executeTransaction(new Transaction<ArrayList<Person>>() {
			@Override
			public ArrayList<Person> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select people.*" +
							"from accounts, seatingCharts, tables, people " +
							" where accounts.accountName = ? " +
							" and seatingCharts.accountID = accounts.id" +
							" and seatingCharts.name = ?" +
							" and tables.seatingChartID = seatingCharts.id" +
							" and tables.name = ?" +
							" and people.tableID = tables.id" 
							
							
					);
					
					stmt.setString(1, accountName);
					stmt.setString(2, seatingChartName);
					stmt.setString(3, tableName);
					
					ArrayList<Person> result = new ArrayList<Person>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Person a = new Person();
						a.setId(resultSet.getInt("id"));
						a.setName(resultSet.getString("name"));
						a.setTableID(resultSet.getInt("tableID"));
						result.add(a);
						
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean removePersonFromTable(final String accountName,
			final String seatingChartName, final String tableName, final String personName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				ArrayList<Table> tables = getTables(accountName, seatingChartName);
				int tableID = -1;
				for(int i = 0; i < tables.size(); i++) {
					if(tables.get(i).getName().equals(tableName)) {
						tableID = tables.get(i).getID();
					}
				}
				
				
				
				try {
					stmt = conn.prepareStatement(
							"delete from people" +
							" where people.tableID = ? and people.name = ?"
					);
					
					stmt.setInt(1, tableID);
					stmt.setString(2, personName);
					
					stmt.executeUpdate();
				
					System.out.println("successfuly deleted " + personName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean addPersonToTable(final String accountName,
			final String seatingChartName, final String tableName, final String personName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				ArrayList<Table> tables = getTables(accountName, seatingChartName);
				int tableID = -1;
				for(int i = 0; i < tables.size(); i++) {
					if(tables.get(i).getName().equals(tableName)) {
						tableID = tables.get(i).getID();
					}
				}
				
				try {
					stmt = conn.prepareStatement(
							"insert into people (tableID, name) values (?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					stmt.setInt(1, tableID);
					stmt.setString(2, personName);
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted person");
					}
					System.out.println("New person has id " + generatedKeys.getInt(1));
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean editPersonInTable(final String accountName,
			final String seatingChartName, final String tableName, final String personName,
			final String newName) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				ArrayList<Table> tables = getTables(accountName, seatingChartName);
				int tableID = -1;
				for(int i = 0; i < tables.size(); i++) {
					if(tables.get(i).getName().equals(tableName)) {
						tableID = tables.get(i).getID();
					}
				}
				
				
				try {
					stmt = conn.prepareStatement(
							"update people" +
							" set people.name = ?" +
							" where people.tableID = ? and people.name = ?"
					);
					
					stmt.setString(1, newName);
					stmt.setInt(2, tableID);
					stmt.setString(3, personName);
					stmt.executeUpdate();
					
					System.out.println("successfuly updated person name to " + newName);
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<Activity> getUserActivities(final String accountName, final String username) {
		
		return executeTransaction(new Transaction<ArrayList<Activity>>() {
			@Override
			public ArrayList<Activity> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				User u = getUser(accountName, username);			
				ArrayList<Activity> result = new ArrayList<Activity>();
				
				if(u != null) {
					
					try {
						stmt = conn.prepareStatement(
								"select eventsUsersLink.*" +
								" from eventsUsersLink " +
								" where eventsUsersLink.userID = ?"
						);
						
						stmt.setInt(1, u.getID());
						
						ResultSet resultSet = stmt.executeQuery();
						while (resultSet.next()) {											
												
							try {
								stmt = conn.prepareStatement(
										"select events.*" +
										" from events " +
										" where events.id = ?"
								);
								
								stmt.setInt(1, resultSet.getInt("eventID"));
								
								stmt.setString(1, accountName);
								
								ResultSet resultSetEvents = stmt.executeQuery();
								while (resultSetEvents.next()) {
									Activity a = new Activity();
									a.setAccountID(resultSetEvents.getInt("accountID"));
									a.setBody(resultSetEvents.getString("body"));
									a.setDate(resultSetEvents.getString("date"));
									a.setEndTime(resultSetEvents.getString("date"));
									a.setID(resultSetEvents.getInt("id"));
									a.setStartTime(resultSetEvents.getString("startTime"));
									a.setTitle(resultSetEvents.getString("title"));
									result.add(a);
									System.out.println("Found event " + a.getTitle());
								}
								
							} finally {
								DBUtil.closeQuietly(stmt);
							}		
						}
					} finally {
						DBUtil.closeQuietly(stmt);
					}
				}
				return result;
			}
		});
	}

	public static void main(String[] args) {
		DerbyDatabase db = new DerbyDatabase();
		System.out.println("Creating tables...");
		db.createTables();
		System.out.println("Done!");
	}

	@Override
	public ArrayList<User> getUsers(final String accountName) {
		return executeTransaction(new Transaction<ArrayList<User>>() {
			@Override
			public ArrayList<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select users.*" +
							"from accounts, users " +
							" where accounts.accountName = ? " +
							" and users.accountID = accounts.id"		
					);
					
					stmt.setString(1, accountName);
					
					ArrayList<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						User a = new User();
						a.setAccountID(resultSet.getInt("accountID"));
						a.setID(resultSet.getInt("id"));
						a.setIsAdmin(resultSet.getBoolean("isAdmin"));
						a.setPassword(resultSet.getString("password"));
						a.setUsername(resultSet.getString("username"));
						result.add(a);
						
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public boolean addActivity(final String accountName, final Activity activity) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				int accountID = getAccountByAccountName(accountName).getID();
				
				
				
				try {
					stmt = conn.prepareStatement(
							"insert into events (accountID, date, title, body, startTime, endTime) values (?, ?, ?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					stmt.setInt(1, accountID);
					stmt.setString(2, activity.getDate());
					stmt.setString(3, activity.getTitle());
					stmt.setString(4, activity.getBody());
					stmt.setString(5, activity.getStartTime());
					stmt.setString(6, activity.getEndTime());

				
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted event");
					}
					System.out.println("New event has id " + generatedKeys.getInt(1));
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
		
		@Override
		public boolean editActivity(final String accountName, final Activity activity, final String activityName) {
			return executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					
					int accountID = getAccountByAccountName(accountName).getID();
					ArrayList<Activity> all = getActivities(accountName);
					int activityID = -1;
					for(int i = 0; i < all.size(); i++) {
						if(all.get(i).getTitle().equals(activityName)) {
							activityID = all.get(i).getID();
						}
					}
					
					try {
						stmt = conn.prepareStatement(
								"update events" +
								" set events.title = ?, events.body = ?, events.date = ?, events.startTime = ?, events.endTime = ? " +
								" where events.id = ?"
						);
						stmt.setString(1, activity.getTitle());
						stmt.setString(2, activity.getBody());
						stmt.setString(3, activity.getDate());
						stmt.setString(4, activity.getStartTime());
						stmt.setString(5, activity.getEndTime());
						stmt.setInt(6, activityID);
						
						stmt.executeUpdate();
						System.out.println("successfuly updated event to " + activity.getTitle());
						return true;
						
					} finally {
						DBUtil.closeQuietly(stmt);
					}
				}
			});
	}
	
	@Override
	public ArrayList<Activity> getActivities(final String accountName) {
		return executeTransaction(new Transaction<ArrayList<Activity>>() {
			@Override
			public ArrayList<Activity> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
							
				ArrayList<Activity> result = new ArrayList<Activity>();
				
				Account b = getAccountByAccountName(accountName);
				
				
				if(b != null) {
					
					try {
						stmt = conn.prepareStatement(
								"select events.*" +
								" from events " +
								" where events.accountID = ?"
						);
						
						stmt.setInt(1, b.getID());
						
						ResultSet resultSet = stmt.executeQuery();
						while (resultSet.next()) {
							Activity a = new Activity();
							a.setAccountID(resultSet.getInt("accountID"));
							a.setBody(resultSet.getString("body"));
							a.setDate(resultSet.getString("date"));
							a.setStartTime(resultSet.getString("startTime"));
							a.setEndTime(resultSet.getString("endTime"));
							a.setTitle(resultSet.getString("title"));
							result.add(a);
							
						}
						
					} finally {
						DBUtil.closeQuietly(stmt);
					}
				}
				return result;
			}
		});
	}
	
	public boolean addUsersToActivity(final String accountName, final String activityName, final ArrayList<String> usernames) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				
				PreparedStatement stmt = null;
				
				
				ArrayList<Activity> all = getActivities(accountName);
				int activityID = -1;
				for(int i = 0; i < all.size(); i++) {
					if(all.get(i).getTitle().equals(activityName)) {
						activityID = all.get(i).getID();
					}
				}
				
			for(int i = 0; i < usernames.size(); i++) {	
				
				User u = getUser(accountName, usernames.get(i));
				
				if(u != null) {
						try {
							stmt = conn.prepareStatement(
									"insert into eventsUsersLink (eventID, userID) values (?, ?)"
							);
							
							stmt.setInt(1, activityID);
							stmt.setInt(2, u.getID());
						
							stmt.executeUpdate();

							
						} finally {
							
							DBUtil.closeQuietly(stmt);
						}
					}
				}
				return true;
			}
		});
	}
	
	public boolean removeUsersFromActivity(final String accountName, final String activityName, final ArrayList<String> usernames) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				ArrayList<Activity> all = getActivities(accountName);
				int activityID = -1;
				for(int i = 0; i < all.size(); i++) {
					if(all.get(i).getTitle().equals(activityName)) {
						activityID = all.get(i).getID();
					}
				}
				
				for(int i = 0; i < usernames.size(); i++) {	
					
					User u = getUser(accountName, usernames.get(i));
					
					if(u != null) {
							try {
								stmt = conn.prepareStatement(
										"delete from eventsUsersLink" +
												" where eventsUsersLink.userID = ? and eventsUsersLink.eventID = ?"
								);
								
								stmt.setInt(1, u.getID());
								stmt.setInt(2, activityID);
								
							
								stmt.executeUpdate();

								
							} finally {
								
								DBUtil.closeQuietly(stmt);
							}
						}
					}
				return true;
			}
		});
	}
	
	
	
}
