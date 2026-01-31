package controller.servlets.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import utils.DBManager;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User user = new Gson().fromJson(req.getReader(), User.class);
		
		try {if(user==null||user.username==null||user.username.strip().isEmpty()) {
			res.setStatus(400);
			res.getWriter().println("{\"error\": \"Empty Username given\"}");
			return;
		}
			PreparedStatement ps = DBManager.getConnection()
					.prepareStatement("INSERT INTO users_jwt(username,password)values(?,?);");
			ps.setString(1, user.username);
			ps.setString(2, user.password);
			if (ps.executeUpdate() > 0) {
				res.getWriter().println("{\"msg\": \"Signup Successful\"}");
			} else {
				res.getWriter().println("{\"msg\": \"Signup Failed\"}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res.setStatus(500);
			res.getWriter().println("{\"msg\": \"Internal Server Error\", \"error\":\""+e.getMessage()+"\"}");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			res.setStatus(500);
			res.getWriter().println("{\"msg\": \"Internal Server Error\", \"error\":\""+e.getMessage()+"\"}");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(500);
			res.getWriter().println("{\"msg\": \"Internal Server Error\", \"error\":\""+e.getMessage()+"\"}");
			return;
		}
	}

}
