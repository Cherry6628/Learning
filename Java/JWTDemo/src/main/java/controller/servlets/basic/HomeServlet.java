package controller.servlets.basic;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
@WebServlet("/api/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String user = (String) req.getAttribute("user");

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("msg", "This is secret data from jwt_auth_app!");
        data.put("currentUser", user);

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson(data));
    }
}