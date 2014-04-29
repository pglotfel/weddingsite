package weddingsite.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;



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
		Connection conn = DriverManager.getConnection("jdbc:derby:books.db;create=true");
		
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
					
					try {
						accounts = conn.prepareStatement(
								"create table accounts (" +
								"    id integer primary key," +
								"    accountName varchar(50)" +
								")");
						accounts.executeUpdate();
						
						users = conn.prepareStatement(
								"create table users (" +
								"    id integer primary key," +
								"    accountID integer," +
								"    username varchar(50)," +
								"    password varchar(20)" +
								"    isAdmin boolean" +
								")");
						users.executeUpdate();
						
						attendanceLists = conn.prepareStatement(
								"create table attendanceLists (" +
										"    id integer primary key," +
										"    accountID integer," +
										"    name varchar(50)" +
								")");
						attendanceLists.executeUpdate();	
						
						seatingCharts = conn.prepareStatement(
								"create table seatingCharts (" +
								"    id integer primary key," +
								"    accountID integer," +
								"    name varchar(50)" +
								")");
						seatingCharts.executeUpdate();
						
						tables = conn.prepareStatement( 
								"create table tables (" +
								" id integer primary key," +
								" seatingChartID integer," +
								" numSeats integer," +
								" name varchar(50)" +
								")");
						tables.executeUpdate();
						
						people = conn.prepareStatement( 
								"create table people (" +
								"id integer primary key," +
								"tableID integer," +
								"name varChar(50)" +
								")");
						
						
						return true;
					} finally {
						DBUtil.closeQuietly(accounts);
						DBUtil.closeQuietly(users);
						DBUtil.closeQuietly(attendanceLists);
						DBUtil.closeQuietly(seatingCharts);
						DBUtil.closeQuietly(tables);
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
							" where account.name = ?" 
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
	public boolean createAccount(String accountName, String adminName,
			String password) {
//		return executeTransaction(new Transaction<boolean>() {
//			@Override
//			public boolean execute(Connection conn) throws SQLException {
//				PreparedStatement stmt = null;
//				ResultSet resultSet = null;
//				
//				try {
//					
//					stmt = conn.prepareStatement("insert into accounts values (?, ?)");
//					
//					
//					stmt.setString(1, accountName);
//					
//					Account result = null;
//					
//					resultSet = stmt.executeQuery();
//					
//					while (resultSet.next()) {
//						result = new Account();
//						result.setID(resultSet.getInt(1));
//						result.setAccountName(resultSet.getString(2));
//					}
//					
//					return result;
//					
//				} finally {
//					DBUtil.closeQuietly(resultSet);
//					DBUtil.closeQuietly(stmt);
//				}
//			}
//		});
		return false;
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
							" where user.name = ? and accounts.name = ? and users.accountId = accounts.Id" 
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
	public boolean addUser(String accountName, String userName,
			String userPassword, boolean isAdmin) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean addAttendanceList(String accountName,
			String attendanceListName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAttendanceList(String accountName,
			String attendanceListName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editAttendanceList(String accountName,
			String attendanceListName, String newName) {
		// TODO Auto-generated method stub
		return false;
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
				
				try {
					stmt = conn.prepareStatement(
							"select attendees.*" +
							"  from attendanceLists, accounts, attendees " +
							" where attendanceLists.accountId = accounts.id" +
							"and attendees.attendanceListID = attendanceLists.id" +
							"and attendanceLists.name = ?" +
							"and accounts.name = ?"
							
					);
					
					stmt.setString(1, name);
					stmt.setString(2, accountName);
					
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

	
	
	
	
}
