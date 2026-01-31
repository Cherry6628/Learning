package controller.servlets.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import model.User;
import utils.DBManager;
import utils.JWTManager;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		User user = new Gson().fromJson(req.getReader(), User.class);
		Connection con = DBManager.getConnection();
		PreparedStatement ps;
		res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
		try {
			ps = con.prepareStatement("SELECT * FROM users_jwt WHERE username=? AND password=?;");
			ps.setString(1, user.username);
			ps.setString(2, user.password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String token = JWTManager.generateToken(user.username);
				String cookie="auth_token="+token+";Path=/;HttpOnly;Max-Age=3600;SameSite=Lax";
				res.setHeader("Set-Cookie", cookie);
				res.getWriter().println("{\"msg\": \"Login Successful\"}");
			} else {
				res.getWriter().println("{\"msg\": \"Invalid Credentials\"}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res.setStatus(500);
			res.getWriter().println("{\"msg\": \"Internal Server Error\"}");
		}
	}

}
