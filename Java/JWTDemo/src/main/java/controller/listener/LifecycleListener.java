package controller.listener;

import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import utils.DBManager;

@WebListener
public class LifecycleListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {

		Statement s;
		try {
			s = DBManager.getConnection().createStatement();
			s.executeUpdate("create table if not exists users_jwt(" + "id int auto_increment primary key, "
					+ "username varchar(50) unique not null, " + "password varchar(255) not null" + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void contextDestroyed(ServletContextEvent sce) {
		try {
			DBManager.getConnection().createStatement().executeUpdate("drop table if exists users_jwt");
			DBManager.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
