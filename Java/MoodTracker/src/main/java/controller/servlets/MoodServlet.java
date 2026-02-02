package controller.servlets;

import com.google.gson.Gson;
import service.MoodService;
import model.Mood;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/mood")
public class MoodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private final MoodService service = new MoodService();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Mood mood = gson.fromJson(req.getReader(), Mood.class);
//        System.out.println(mood);
        boolean success = service.logMood(
                mood.getName(),
                mood.getDescription(),
                mood.getMood()
        );

        resp.setContentType("application/json");
        resp.getWriter().write(
                "{\"success\": " + success + "}"
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Mood> moods = service.fetchAllMoods();
        req.setAttribute("moods", moods);

        RequestDispatcher rd = req.getRequestDispatcher("/view/moods.jsp");
        rd.forward(req, resp);
    }
}