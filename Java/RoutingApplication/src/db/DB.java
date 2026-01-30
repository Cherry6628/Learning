package db;

import java.sql.*;

import log.LogService;

public class DB {

	private static final String URL = "jdbc:mysql://localhost:3306/travel";
	private static final String USER = "codeAcc";
	private static final String PASS = "p@ssw0rd@123";
	private static Connection conn = null;

	public static Connection conn() throws SQLException {
		if (conn == null) {
			LogService.debug("Database Connection established");
			conn = DriverManager.getConnection(URL, USER, PASS);
		}
		return conn;
	}
	





}
