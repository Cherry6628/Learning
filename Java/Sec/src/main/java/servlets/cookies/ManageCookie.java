package servlets.cookies;

import java.io.BufferedReader;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/managecookie")
public class ManageCookie extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		JSONArray array = new JSONArray();
		if (cookies != null) {
			for (Cookie c : cookies) {
				JSONObject o = new JSONObject();
				o.put("name", c.getName());
				o.put("value", c.getValue());
				o.put("path", c.getPath());
				o.put("domain", c.getDomain());
				o.put("maxAge", c.getMaxAge());
				o.put("secure", c.getSecure());
				o.put("httpOnly", c.isHttpOnly());
				array.put(o);
			}
		}
		response.setContentType("application/json");
		response.getWriter().write(new JSONObject().put("cookies", array).toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		try (BufferedReader reader = request.getReader()) {
			while ((line = reader.readLine()) != null)
				sb.append(line);
		}

		JSONArray cookiesToSet = new JSONArray(sb.toString());

		for (int i = 0; i < cookiesToSet.length(); i++) {
			JSONObject jc = cookiesToSet.getJSONObject(i);
			StringBuilder header = new StringBuilder();
			header.append(jc.getString("name")).append("=").append(jc.getString("value"));
			String path = jc.optString("path", "/");
			header.append("; Path=").append(path);

			int maxAge = jc.optInt("maxAge", 3600);
			header.append("; Max-Age=").append(maxAge);

			if (!jc.optString("domain", "").isEmpty()) {
				header.append("; Domain=").append(jc.getString("domain"));
			}

			if (jc.optBoolean("secure"))
				header.append("; Secure");
			if (jc.optBoolean("httpOnly"))
				header.append("; HttpOnly");

			String sameSite = jc.optString("sameSite", "Lax");
			header.append("; SameSite=").append(sameSite);

			response.addHeader("Set-Cookie", header.toString());
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}
}