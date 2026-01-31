package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import secrets.Secrets;

public class DBManager {
	private static Connection con;

	private static String DATABASE = "tomcat_main";
	private static String IP = "127.0.0.1";
	private static int PORT = 3306;

	private DBManager() {
	}

	private static void initializeConnection() {

		String DOMAIN = IP + ":" + PORT;
		String URL = "jdbc:mysql://" + DOMAIN + "/" + DATABASE;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,Secrets.USER,Secrets.PASS);

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
