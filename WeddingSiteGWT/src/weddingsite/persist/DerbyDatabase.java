package weddingsite.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import weddingsite.server.IDatabase;
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
								"name varChar(50)" +
								")");
						people.executeUpdate();
						
						attendees = conn.prepareStatement( 
								"create table attendees (" +
								" id integer primary key not null generated always as identity," +
								"attendanceListID integer," +
								"attending boolean," +
								"numAttending int" +
								")"
								);
						attendees.executeUpdate();
						
						return true;
					} finally {
						DBUtil.closeQuietly(accounts);
						DBUtil.closeQuietly(users);
						DBUtil.closeQuietly(attendanceLists);
						DBUtil.closeQuietly(seatingCharts);
						DBUtil.closeQuietly(tables);
						DBUtil.closeQuietly(attendees);
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
						a.setAttendanceListID(resultSet.getInt("accountID"));
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
	public boolean addAttendee(String accountName, String attendanceListName,
			String attendeeName, int numAttending) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyAttendee(String accountName,
			String attendanceListName, String attendeeName, int numAttending) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAttendee(String accountName,
			String attendanceListName, String attendeeName) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean addSeatingChart(String accountName, String seatingChartName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSeatingChart(String accountName,
			String seatingChartName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editSeatingChart(String accountName,
			String seatingChartName, String newName) {
		// TODO Auto-generated method stub
		return false;
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
							" where accounts.accountName = ?" +
							"and seatingCharts.accountID = accounts.id" +
							"and seatingCharts.name = ?" +
							"and tables.seatingChartID = seatingCharts.id"
							
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addTableToSeatingChart(String accountName,
			String seatingChartName, String tableName, int numSeats) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editTableInSeatingChart(String accountName,
			String seatingChartName, String tableName, String newName,
			int numSeats) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTableInSeatingChart(String accountName,
			String seatingChartName, String tableName) {
		// TODO Auto-generated method stub
		return false;
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
							"where accounts.accountName = ?" +
							"and seatingCharts.accountID = accounts.id" +
							"and seatingCharts.name = ?" +
							"and tables.seatingChartID = seatingCharts.id" +
							"and tables.name = ?" +
							"and people.tableID = tables.id" 
							
							
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
	public boolean removePersonFromTable(String accountName,
			String seatingChartName, String tableName, String personName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPersonToTable(String accountName,
			String seatingChartName, String tableName, String personName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editPersonInTable(String accountName,
			String seatingChartName, String tableName, String personName,
			String newName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Activity> getUserActivities(String accountName,
			String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		DerbyDatabase db = new DerbyDatabase();
		System.out.println("Creating tables...");
		db.createTables();
		System.out.println("Done!");
	}
	
	
	
}
