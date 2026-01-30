package controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONObject;
//import 

import dao.MoodDAO;

@WebServlet("/mood")
public class LogMood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String readResponse(BufferedReader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null)
			sb.append(line);
		reader.close();
		return sb.toString();

	}

	public LogMood() {
		super();
	}
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		res.getWriter().println("Remote Address: "+req.getRemoteAddr());
//		res.getWriter().println("Remote Host: "+req.getRemoteHost());
//		res.getWriter().println("Remote Port: "+req.getRemotePort());
//		res.getWriter().println("Remote User: "+req.getRemoteUser());
//	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			if (!MoodDAO.logMood(readResponse(request.getReader())))
				response.setStatus(200);
			response.getWriter().println("Uploaded");
		} catch (Exception e) {
			response.setStatus(400);
			response.getWriter().println(e.getStackTrace().toString());
		}

	}

}
