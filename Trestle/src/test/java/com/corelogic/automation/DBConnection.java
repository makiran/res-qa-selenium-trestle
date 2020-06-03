package com.corelogic.automation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.corelogic.utaf.main.internal.Base;

public class DBConnection {

	/**
	 * Connects to the database.
	 * 
	 * @return true if the connection was established, false otherwise.
	 * 
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public static Connection getConnection() throws Exception {
		// Get UserName and Password details
		String db_userName = Base.get("DBUserName");
		String db_password = Base.get("DBPassword");
		String severName = Base.get("DBServerName");

		String db_connect_string = "jdbc:jtds:sqlserver://" + severName + "/"
				+ "MDC_CHILD6";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		Connection conn = DriverManager.getConnection(db_connect_string,
				db_userName, db_password);
		return conn;
	}

	/**
	 * Retrieve the result of a query.
	 * 
	 * @return true if the connection was established, false otherwise.
	 * 
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public static String getResult(String query) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				result = rs.getString(1);
				return result;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return result;
	}

	/**
	 * Does the operations on DB
	 * 
	 * @return true if the connection was established, false otherwise.
	 * 
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public static int execute(String query) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return result;
	}
}
