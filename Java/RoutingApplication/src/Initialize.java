



import java.sql.Connection;

import java.sql.Statement;



import db.DB;



public class Initialize {

	static private void initialize() throws Exception {

		Connection c = DB.conn();

		Statement s = c.createStatement();

//		s.execute("drop table if exists users;");

		s.execute("""

		create table if not exists users(

			id int unsigned primary key auto_increment,

			uname varchar(100) unique key,

			pwd varbinary(255) 

		);

		""");

		s.execute("");

	}

	

	public static void main(String[]args) throws Exception {

		initialize();

	}

}

