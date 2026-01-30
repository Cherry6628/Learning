package auth;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import db.Queries;
import log.LogService;

public class AuthService {

	private static final Argon2ID argon;
	static {
		argon=Argon2ID.object;
		LogService.debug("Argon2ID Hash Engine Loaded");
	}
	public boolean signup(String username, String password, String email) throws Exception {
		String hash = argon.hash(password);

		try {
			Queries.INSERT_NEW_USER.setString(1, username);
			Queries.INSERT_NEW_USER.setString(2, hash);
			Queries.INSERT_NEW_USER.setString(3, email);
			Queries.INSERT_NEW_USER.executeUpdate();
			return true;

		} catch (SQLIntegrityConstraintViolationException e) {
			return false;

		}
	}

	public User login(String username, String password) throws Exception {
		Queries.SELECT_USER.setString(1, username);
		ResultSet rs = Queries.SELECT_USER.executeQuery();

		if (!rs.next())
			return null;

		int userId = rs.getInt("id");
		String uname = rs.getString("uname");
		String email = rs.getString("email");
		String hashFromDb = rs.getString("pwd");

		return argon.verify(hashFromDb, password) ? new User(userId,uname,email):null;

	}
}
