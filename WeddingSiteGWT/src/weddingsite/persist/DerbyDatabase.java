package weddingsite.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	@Override
	
	public Account getAccountByAccountName(final String accountName) {
		
		return executeTransaction(new Transaction<Account>() {
			@Override
			public Account execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"select account.* " +
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
		// TODO Auto-generated method stub
		return false;
	}
	
	

	@Override
	public User getUser(String accountName, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(String accountName, String userName,
			String userPassword, boolean isAdmin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<AttendanceList> getAttendanceLists(String accountName) {
		// TODO Auto-generated method stub
		return null;
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
	public ArrayList<Attendee> getAttendanceListAttendees(String name,
			String accountName) {
		// TODO Auto-generated method stub
		return null;
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
	public ArrayList<SeatingChart> getSeatingCharts(String accountName) {
		// TODO Auto-generated method stub
		return null;
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
	public ArrayList<Table> getTables(String accountName,
			String SeatingChartName) {
		// TODO Auto-generated method stub
		return null;
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
	public ArrayList<Person> getPeopleAtTable(String accountName,
			String seatingChartName, String tableName) {
		// TODO Auto-generated method stub
		return null;
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
