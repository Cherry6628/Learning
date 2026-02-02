package utils.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import secrets.Secrets;

public class DBManager {
	private static Connection con;

	private static String USER = Secrets.user();
	private static String PASS = Secrets.pwd();
	private static String DATABASE = Secrets.db();
	private static String IP = Secrets.ip();
	private static int PORT = Secrets.port();

	private DBManager(){}

	private static void initializeConnection() {

		String DOMAIN = IP + ":" + PORT;
		String URL = "jdbc:mysql://" + DOMAIN + "/" + DATABASE;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASS);
			Statement s = con.createStatement();
			s.executeUpdate("delete from moods");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		if (con == null)
			initializeConnection();
		return con;
	}
}
