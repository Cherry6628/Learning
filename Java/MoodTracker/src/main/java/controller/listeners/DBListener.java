package controller.listeners;

import java.sql.SQLException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import utils.db.DBManager;


@WebListener
public class DBListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce)  { 
    	DBManager.getConnection();
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
         try {
			DBManager.getConnection().close();
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
    }
	
}
