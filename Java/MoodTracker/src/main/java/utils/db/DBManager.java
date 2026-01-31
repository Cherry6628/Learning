package utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private static Connection con;

	private static String USER = "tom";
	private static String PASS = "tommy";
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
			con = DriverManager.getConnection(URL,USER,PASS);
			Statement s = con.createStatement();
			s.executeQuery(
				"create table if not exists users("
				+ "id int auto_increment primary key, "
				+ "username varchar(50) unique not null, "
				+ "password varchar(255) not null"
				+ ");"
			);
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
